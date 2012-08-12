/*
 * Copyright (c) 2003-2011 Lucas Holt
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

package com.justjournal;

import com.justjournal.db.SQLHelper;
import com.justjournal.utility.StringUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import sun.security.provider.SHA;

/**
 * Provides authentication and password management services to web applications using the just journal data tier.
 * <p/>
 * Created on March 23, 2003, 2:34 PM
 *
 * @author Lucas Holt
 * @version $Id: WebLogin.java,v 1.13 2012/07/04 18:49:20 laffer1 Exp $
 * @since 1.0
 */
public final class WebLogin {
    private static final Logger log = Logger.getLogger(WebLogin.class);
    private static final int BAD_USER_ID = 0;

    public static boolean isUserName(String input) {
        final Pattern p = Pattern.compile("[A-Za-z0-9_]+");
        final Matcher m = p.matcher(input);

        return m.matches(); // valid on true
    }

    public static boolean isPassword(String input) {
        final Pattern p = Pattern.compile("[A-Za-z0-9_@.#$ ]+");
        final Matcher m = p.matcher(input);

        return m.matches(); // valid on true
    }

    /**
     * Authenticate the user using clear text username and password.
     *
     * @param UserName Three to Fifteen characters...
     * @param Password Clear text password
     * @return userid of the user who logged in or 0 if the login failed.
     */
    public static int validate(final String UserName, final String Password) {
        // the password is SHA1 encrypted in the sql server
        int userID = BAD_USER_ID;

        if (!StringUtil.lengthCheck(UserName, 3, 15))
            return BAD_USER_ID; // bad username

        if (!StringUtil.lengthCheck(Password, 5, 18))
            return BAD_USER_ID;

        if (!isUserName(UserName))
            return BAD_USER_ID; // bad username

        if (!isPassword(Password))
            return BAD_USER_ID; // bad password


        String SqlStatement = "SELECT id FROM user WHERE username='" + UserName
                + "' AND password=SHA1('" + Password + "') LIMIT 1;";



        try {
            ResultSet RS = SQLHelper.executeResultSet(SqlStatement);

            if (RS.next())
                userID = RS.getInt(1);  // id field

            RS.close();
        } catch (Exception e) {
            log.error("validate(): " + e.getMessage());
        }

        return userID;
    }


    public static int validateSHA1(final String UserName, final String Password) {
        // the password is SHA1 encrypted in the sql server
        int userID = BAD_USER_ID;

        if (!StringUtil.lengthCheck(UserName, 3, 15))
            return BAD_USER_ID; // bad username

        if (!isUserName(UserName))
            return BAD_USER_ID; // bad username

        if (!StringUtil.lengthCheck(Password, 40, 40))
            return BAD_USER_ID;

        String SqlStatement = "SELECT id FROM user WHERE username='" + UserName
                + "' AND password='" + Password + "' LIMIT 1;";

        try {
            ResultSet RS = SQLHelper.executeResultSet(SqlStatement);

            if (RS.next())
                userID = RS.getInt(1);  // id field

            RS.close();
        } catch (Exception e) {
            log.error("validate(): " + e.getMessage());
        }

        return userID;
    }

    public static void setLastLogin(final int id) {
        /* verify its a real login */
        if (id < 1)
            return;

        String sqlstmt = "UPDATE user SET lastlogin=NOW() WHERE id=" + id + " LIMIT 1;";
        try {
            SQLHelper.executeNonQuery(sqlstmt);
        } catch (Exception e) {
            log.error("validate(): " + e.getMessage());
        }
    }


    /**
     * Change the password for an account given the username, old and new passwords.
     *
     * @param userName username
     * @param password existing password
     * @param newPass  new password
     * @return true if the password change worked.
     */
    public static boolean changePass(final String userName,
                                     final String password,
                                     final String newPass) {
        boolean result = false;
        int uid;
        int x;
        String sSQL;

        try {
            uid = validate(userName, password);

            if (uid > BAD_USER_ID && isPassword(newPass)) {
                sSQL = "UPDATE user SET password=SHA1('" + newPass + "') WHERE id='"
                        + uid + "' LIMIT 1;";
                x = SQLHelper.executeNonQuery(sSQL);

                if (x == 1)
                    result = true;
            }
        } catch (Exception e) {
            log.error("validate(): " + e.getMessage());
        }

        return result;
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (final byte aData : data) {
            int halfbyte = (aData >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = aData & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String SHA1(String text)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-1");
        byte[] sha1hash = new byte[40];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        sha1hash = md.digest();
        return convertToHex(sha1hash);
    }
}