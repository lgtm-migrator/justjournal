/*
Copyright (c) 2005, Lucas Holt
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are
permitted provided that the following conditions are met:

  Redistributions of source code must retain the above copyright notice, this list of
  conditions and the following disclaimer.

  Redistributions in binary form must reproduce the above copyright notice, this
  list of conditions and the following disclaimer in the documentation and/or other
  materials provided with the distribution.

  Neither the name of the Just Journal nor the names of its contributors
  may be used to endorse or promote products derived from this software without
  specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
*/

package com.justjournal.ctl.api;

import com.justjournal.Login;
import com.justjournal.model.*;
import com.justjournal.repository.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/comment")
public class CommentController {
    private org.slf4j.Logger log = LoggerFactory.getLogger(CommentController.class);

    private CommentRepository commentDao;
    private EntryRepository entryDao;
    private UserRepository userRepository;
    private SettingsRepository settingsRepository;
    private QueueMailRepository queueMailRepository;

    @Autowired
    public CommentController(final CommentRepository commentRepository,
                             final EntryRepository entryRepository,
                             final SettingsRepository settingsRepository,
                             final UserRepository userRepository,
                             final QueueMailRepository queueMailRepository) {
        this.userRepository = userRepository;
        this.commentDao = commentRepository;
        this.entryDao = entryRepository;
        this.settingsRepository = settingsRepository;
        this.queueMailRepository = queueMailRepository;
    }


    @RequestMapping("/api/comment/{id}")
    @ResponseBody
    public Comment getById(@PathVariable("id") final Integer id) {
        return commentDao.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<Comment> getComments(@RequestParam("entryId") final Integer entryId, final HttpServletResponse response) throws Exception {
        final Entry entry = entryDao.findOne(entryId);

        try {
            if (entry.getUser().getJournals().get(0).isOwnerViewOnly()  ||
                    entry.getAllowComments() == PrefBool.N ||
                    entry.getSecurity().getId() == 0) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (final Exception e) {
            log.error(e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return commentDao.findByEntryId(entryId);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    Map<String, String> delete(@PathVariable("id") final int id, final HttpSession session, final HttpServletResponse response) throws Exception {

        if (!Login.isAuthenticated(session)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return java.util.Collections.singletonMap("error", "The login timed out or is invalid.");
        }

        try {
            final Comment comment = commentDao.findOne(id);
            if (comment.getUser().getId() == Login.currentLoginId(session))
                commentDao.delete(id);

            return java.util.Collections.singletonMap("id", Integer.toString(comment.getId()));
        } catch (final Exception e) {
            log.error(e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return java.util.Collections.singletonMap("error", "Could not delete the comment.");
        }
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    Map<String, String> post(@RequestBody final Comment comment, final HttpSession session, final HttpServletResponse response) {
        return put(comment, session, response);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public
    @ResponseBody
    Map<String, String> put(@RequestBody final Comment comment, final HttpSession session, final HttpServletResponse response) {
        if (!Login.isAuthenticated(session)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return java.util.Collections.singletonMap("error", "The login timed out or is invalid.");
        }

        try {
            final User user = userRepository.findOne(Login.currentLoginId(session));
            final Entry et = entryDao.findOne(comment.getEid());

            if (et.getAllowComments().equals(PrefBool.N)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return java.util.Collections.singletonMap("error", "Comments blocked by owner of this blog entry.");
            }

            boolean update = false;
            final Comment saved;
            // new case
            if (comment.getId() == 0) {
                comment.setUser(user);
                comment.setDate(new Date());
                comment.setEntry(et);
                saved = commentDao.save(comment);
            } else {
                update = true;
                final Comment c = commentDao.findOne(comment.getId());
                if (c.getEntry().getId() != et.getId()) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    String error = "Error saving comment. Entry id does not match original on comment.";
                    return java.util.Collections.singletonMap("error", error);
                }
                c.setUser(user);
                c.setBody(comment.getBody());
                c.setSubject(comment.getSubject());
                saved = commentDao.save(c);
            }

            if (saved == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                String error = "Error adding comment";
                if (update)
                    error = "Error editing comment";

                return java.util.Collections.singletonMap("error", error);
            }

            try {
                final User pf = et.getUser();
                final Settings mailfrom = settingsRepository.findByName("mailFrom");
                final Settings baseuri = settingsRepository.findByName("baseuri");

                final String entryUrl = baseuri + "users" + et.getUser().getUsername() + "/entry/" + et.getId();

                // TODO: should we allow the user making the comment to disable email notifications?
                if (et.getEmailComments().equals(PrefBool.Y)) {
                    final QueueMail mail = new QueueMail();
                    if (mailfrom != null)
                        mail.setFrom(mailfrom.getValue());
                    else
                        mail.setFrom("root@localhost");
                    mail.setTo(pf.getUserContact().getEmail());
                    // TODO: site name should be stored in settings not hard coded
                    mail.setBody(user.getUsername() + " said: \n"
                            + "Subject: " + comment.getSubject() + "\n"
                            + comment.getBody() + "\n\nIn response to:\n"
                            + entryUrl + "\n\n"
                            + "From here, you can:\n\n"
                            + "View all comments to this entry: "
                            + entryUrl + "\n\n"
                            + "Reply at the webpage: http://www.justjournal.com/#!/comment/"
                            + comment.getEid()
                            + "\n\n-- JustJournal.com\n\n"
                            + "(If you would prefer not to get these updates," +
                            " edit the entry to disable comment notifications.)\n");

                    mail.setSubject("JustJournal: Comment Notification");
                    mail.setPurpose("comment_notify");
                    queueMailRepository.save(mail);
                }
            } catch (final Exception e) {
                log.error("Could not send mail: " + e.getMessage());
            }

            return java.util.Collections.singletonMap("id", Integer.toString(comment.getId()));
        } catch (final Exception e) {
            log.error(e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return java.util.Collections.singletonMap("error", "Error adding comment");
        }
    }
}
