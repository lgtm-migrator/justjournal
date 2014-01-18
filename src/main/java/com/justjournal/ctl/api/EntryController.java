/*
 * Copyright (c) 2013, 2014 Lucas Holt
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */

package com.justjournal.ctl.api;

import com.justjournal.WebLogin;
import com.justjournal.db.*;
import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * @author Lucas Holt
 */
@Controller
@RequestMapping("/api/entry")
final public class EntryController {
    private static final Logger log = Logger.getLogger(EntryController.class);

    /**
     * Get an individual entry
     *
     * @param id entry id
     * @return entry
     */
    @RequestMapping("/api/entry/{id}")
    @ResponseBody
    public EntryTo getById(@PathVariable Integer id) {
        return EntryDAO.viewSinglePublic(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Collection<EntryTo> getEntries(@RequestParam String username) {
        return EntryDAO.viewAll(username, false);
    }

    @JsonIgnoreProperties({"tags", "date"})
    public class EntryJsonMixin extends EntryTo {

    }

    @JsonAutoDetect
    public class Entry {
        private String subject;
        private String body;

        public Entry() {
            subject = "";
            body = "";
        }

        public Entry(String subject, String body) {
            this.subject = subject;
            this.body = body;
        }

        public String getBody() {
            return this.body;
        }

        public String getSubject() {
            return this.subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public void setBody(String body) {
            this.body = body;
        }

        @Override
        public String toString() {
            return this.getSubject() + " | " + this.getBody();
        }
    }

    private EntryTo convertEntryToEntryTo(Entry entry) {
        EntryTo entryTo = new EntryTo();
        entryTo.setSubject(entry.getSubject());
        entryTo.setBody(entry.getBody());
        return entryTo;
    }

    /**
     * Creates a new entry resource
     *
     * @param entry
     * @param session  HttpSession
     * @param response HttpServletResponse
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public
    @ResponseBody
    Map<String, String> post(@RequestBody Entry entry, HttpSession session, HttpServletResponse response) {
        ObjectMapper mapper = new ObjectMapper();
        SerializationConfig serializationConfig = mapper.getSerializationConfig();
        serializationConfig.addMixInAnnotations(EntryTo.class, EntryJsonMixin.class);

        if (!WebLogin.isAuthenticated(session)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return java.util.Collections.singletonMap("error", "The login timed out or is invalid.");
        }
        // TODO: validate
        boolean result = EntryDAO.add(convertEntryToEntryTo(entry));

        if (!result) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return java.util.Collections.singletonMap("error", "Could not add the entry.");
        }
        // return java.util.Collections.singletonMap("id", Integer.toString(entry.getId()));
        return java.util.Collections.singletonMap("status", "OK");
    }

    /**
     * PUT generally allows for add or edit in REST.
     *
     * @param entry
     * @param session  HttpSession
     * @param response HttpServletResponse
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public
    @ResponseBody
    Map<String, String> put(@RequestBody EntryJsonMixin entry, HttpSession session, HttpServletResponse response) {
        ObjectMapper mapper = new ObjectMapper();
        SerializationConfig serializationConfig = mapper.getSerializationConfig();
        serializationConfig.addMixInAnnotations(EntryTo.class, EntryJsonMixin.class);


        if (!WebLogin.isAuthenticated(session)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return java.util.Collections.singletonMap("error", "The login timed out or is invalid.");
        }
        // TODO: validate
        boolean result;
        EntryTo entryTo = EntryDAO.viewSingle(entry.getId(), WebLogin.currentLoginId(session));
        if (entryTo != null && entryTo.getId() > 0)
            result = EntryDAO.update(entry);
        else
            result = EntryDAO.add(entry);

        if (!result) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return java.util.Collections.singletonMap("error", "Could not add/edit entry.");
        }
        return java.util.Collections.singletonMap("id", Integer.toString(entry.getId()));
    }

    /**
     * @param entryId  entry id
     * @param session  HttpSession
     * @param response HttpServletResponse
     * @return errors or entry id if success
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public
    @ResponseBody
    Map<String, String> delete(@RequestBody int entryId, HttpSession session, HttpServletResponse response) throws Exception {

        if (!WebLogin.isAuthenticated(session)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return java.util.Collections.singletonMap("error", "The login timed out or is invalid.");
        }

        if (entryId < 1)
            return Collections.singletonMap("error", "The entry id was invalid.");

        try {
            boolean result2;
            boolean result = EntryDAO.delete(entryId, WebLogin.currentLoginId(session));

            if (!result) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return java.util.Collections.singletonMap("error", "Could not delete entry.");
            }

            result2 = CommentDao.deleteByEntry(entryId);
            if (!result2) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return java.util.Collections.singletonMap("error", "Could not delete comments associated with entry.");
            }

            return java.util.Collections.singletonMap("id", Integer.toString(entryId));
        } catch (Exception e) {
            log.error(e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return java.util.Collections.singletonMap("error", "Could not delete the comment.");
        }
    }
}
