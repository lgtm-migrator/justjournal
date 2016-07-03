/*
 * Copyright (c) 2014 Lucas Holt
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

package com.justjournal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Lucas Holt
 */
@Entity
@Table(name = "user_pref")
public class UserPref implements Serializable {

    private static final long serialVersionUID = -4301597963273403598L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id = 0;

    @Column(name = "allow_spider")
    @Enumerated(EnumType.STRING)
    private PrefBool allowSpider = PrefBool.Y;

    private int style = 0;

    @Column(name = "owner_view_only", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private PrefBool ownerViewOnly = PrefBool.N;

    @Column(name = "journal_name", length = 150, nullable = true)
    private String journalName = "";

    @Column(name = "ping_services", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private PrefBool pingServices = PrefBool.Y;


    @Column(name = "modified", nullable = false)
    private Timestamp modified;

    @JsonBackReference
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public PrefBool getAllowSpider() {
        return allowSpider;
    }

    public void setAllowSpider(final PrefBool allowSpider) {
        this.allowSpider = allowSpider;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(final int style) {
        this.style = style;
    }

    public PrefBool getOwnerViewOnly() {
        return ownerViewOnly;
    }

    public void setOwnerViewOnly(final PrefBool ownerViewOnly) {
        this.ownerViewOnly = ownerViewOnly;
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(final String journalName) {
        this.journalName = journalName;
    }

    public PrefBool getPingServices() {
        return pingServices;
    }

    public void setPingServices(final PrefBool pingServices) {
        this.pingServices = pingServices;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(final Timestamp modified) {
        this.modified = modified;
    }
}
