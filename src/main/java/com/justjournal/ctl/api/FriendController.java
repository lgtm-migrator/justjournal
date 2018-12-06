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
import com.justjournal.model.Friend;
import com.justjournal.model.User;
import com.justjournal.repository.FriendsRepository;
import com.justjournal.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Manage Friends
 *
 * @author Lucas Holt
 */
@Slf4j
@RestController
@RequestMapping("/api/friend")
public class FriendController {

    private final UserRepository userRepository;

    private final FriendsRepository friendsDao;

    @Autowired
    public FriendController(final UserRepository userRepository, final FriendsRepository friendsDao) {
        this.userRepository = userRepository;
        this.friendsDao = friendsDao;
    }

    // TODO: refactor to return user objects?


    @RequestMapping(value = "{username}/friendswith/{other}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Boolean> areWeFriends(@PathVariable("username") String username, @PathVariable("other") String otherUsername) {
        try {
            User user = userRepository.findByUsername(username);
            if (user == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            
            for (Friend friend : user.getFriends()) {

                if (otherUsername.equalsIgnoreCase(friend.getFriend().getUsername()))
                    return ResponseEntity.ok(true);
            }
            return ResponseEntity.ok(false);
        } catch (final Exception e) {
            return ResponseEntity.badRequest().body(false);
        }
    }

    /**
     * @param username       username
     * @param response http response
     * @return List of usernames as strings
     */
    @Cacheable(value = "friends", key = "username")
    @RequestMapping(value = "{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<User> getByUsername(@PathVariable("username") String username, HttpServletResponse response) {
        try {
            ArrayList<User> friends = new ArrayList<User>();

            User user = userRepository.findByUsername(username);
            for (Friend friend : user.getFriends()) {
                friends.add(friend.getFriend());
            }
            return friends;
        } catch (final Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @CacheEvict(value = "friends", key = "friend")
    @RequestMapping(value="{friend}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, String> put(@PathVariable("friend") String friend, HttpSession session, HttpServletResponse response) {
        if (!Login.isAuthenticated(session)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return java.util.Collections.singletonMap("error", "The login timed out or is invalid.");
        }

        try {
            final User friendUser = userRepository.findByUsername(friend);
            final User owner = userRepository.findById(Login.currentLoginId(session)).orElse(null);

            if (friendUser == null)
                return java.util.Collections.singletonMap("error", "Could not find friend's username");

            if (owner == null)
                return java.util.Collections.singletonMap("error", "Could not find logged in user account.");

            final Friend f = new Friend();
            f.setFriend(friendUser);
            f.setUser(owner);
            f.setPk(owner.getId());
            if (friendsDao.save(f) != null)
                return java.util.Collections.singletonMap("status", "success");

            return java.util.Collections.singletonMap("error", "Unable to add friend");
        } catch (final Exception e) {
            log.error(e.getMessage());
            return java.util.Collections.singletonMap("error", "Could not find friend's username");
        }

    }
    
    @CacheEvict(value = "friends", allEntries = true)
    @RequestMapping(method = RequestMethod.DELETE, value="{friend}")
    @ResponseBody
    public
    Map<String, String> delete(@PathVariable("friend") String friend, HttpSession session, HttpServletResponse response) throws Exception {


        if (!Login.isAuthenticated(session)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return java.util.Collections.singletonMap("error", "The login timed out or is invalid.");
        }

        try {
            final User friendUser = userRepository.findByUsername(friend);
            final User user = userRepository.findById(Login.currentLoginId(session)).orElse(null);

            final Friend f = friendsDao.findOneByUserAndFriend(user, friendUser);
            if (f != null) {
                friendsDao.delete(f);
                return java.util.Collections.singletonMap("status", "success");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                   return java.util.Collections.singletonMap("error", "Error deleting friend");
            }
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
        }

        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return java.util.Collections.singletonMap("error", "Error deleting friend");
    }
}
