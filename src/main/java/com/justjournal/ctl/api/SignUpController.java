/*
 * Copyright (c) 2013 Lucas Holt
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

import com.justjournal.Login;
import com.justjournal.core.Settings;
import com.justjournal.model.*;
import com.justjournal.model.api.NewUser;
import com.justjournal.repository.UserBioDao;
import com.justjournal.repository.UserContactRepository;
import com.justjournal.repository.UserPrefRepository;
import com.justjournal.repository.UserRepository;
import com.justjournal.utility.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Create new accounts in Just Journal. To delete accounts, use AccountController.
 *
 * @author Lucas Holt
 * @see com.justjournal.ctl.api.AccountController
 */
@Transactional
@Controller
@RequestMapping("/api/signup")
public class SignUpController {
    private static final Logger log = Logger.getLogger(SignUpController.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserBioDao userBioDao;
    @Autowired
    private UserContactRepository userContactRepository;
    @Autowired
    private UserPrefRepository userPrefRepository;

    @Autowired
    private Settings settings;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    Map<String, String> post(@RequestBody final NewUser user, final HttpServletResponse response) {

        if (!settings.isUserAllowNew()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return java.util.Collections.singletonMap("error", "Could not add user");
        }

        if (!StringUtil.lengthCheck(user.getEmail(), 6, 100)) {
            throw new IllegalArgumentException("Invalid email address");
        }

        if (!Login.isUserName(user.getUsername())) {

            throw new IllegalArgumentException(
                    "Username must be letters and numbers only");
        }

        if (!Login.isPassword(user.getPassword())) {

            throw new IllegalArgumentException(
                    "Password must be 5-18 characters.");
        }

        return newUser(user, response);
    }

    private Map<String, String> newUser(final NewUser newUser, final HttpServletResponse response) {

        try {
            User user = new User();
            user.setName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setType(0);
            user.setSince(Calendar.getInstance().get(Calendar.YEAR));
            user.setLastLogin(new Date());
            user = userRepository.saveAndFlush(user);
            if (user == null)
                throw new Exception("Unable to save user");

            log.debug("user id is " + user.getId());

            UserPref userPref = new UserPref();
            userPref.setAllowSpider(PrefBool.Y);
            userPref.setOwnerViewOnly(PrefBool.N);
            userPref.setPingServices(PrefBool.Y);
            userPref.setJournalName(user.getName() + "\'s Journal");
            userPref.setUser(user);
            userPref = userPrefRepository.save(userPref);
            if (userPref == null)
                throw new Exception("Unable to save user preferences");

            UserContact userContact = new UserContact();
            userContact.setEmail(newUser.getEmail());
            userContact.setUser(user);
            userContact = userContactRepository.save(userContact);
            if (userContact == null)
                throw new Exception("Unable to save user contact");

            UserBio userBio = new UserBio();
            userBio.setBio("");
            userBio.setUser(user);
            userBio = userBioDao.save(userBio);
            if (userBio == null)
                throw new Exception("Unable to save user bio");

            return java.util.Collections.singletonMap("id", Integer.toString(user.getId()));
        } catch (final Exception e) {
            log.error(e);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return java.util.Collections.singletonMap("error", "Could not add user");
        }
    }
}
