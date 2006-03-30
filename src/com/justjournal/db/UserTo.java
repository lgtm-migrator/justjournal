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

package com.justjournal.db;

/**
 * Represents a user most basic properties.
 *
 * @author Lucas Holt
 *         Created by IntelliJ IDEA.
 *         User: laffer1
 *         Date: Jan 21, 2004
 *         Time: 12:20:53 PM
 *         <p/>
 *         TODO: add the rest of the properties.
 */
public final class UserTo {
    private int id;          // the user id imposed by mysql
    private String userName;
    private String name;     // first name
    private String password;
    private String passwordSha1;


    /**
     * Retrieve the user id.
     *
     * @return
     */
    public final int getId() {
        return this.id;
    }

    /**
     * Set the user id.
     *
     * @param id
     */
    public final void setId(final int id) {
        this.id = id;
    }

    /**
     * Retrieve the user name.
     *
     * @return
     */
    public final String getUserName() {
        return this.userName;
    }

    /**
     * Set the user name.
     *
     * @param userName
     */
    public final void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * get the first name of the user.
     *
     * @return
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Set the first name of the user.
     *
     * @param name
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Retrieve clear text password.
     * Used to set passwords in the database.
     * Passwords are not permenently stored
     * clear text.
     *
     * @return
     */
    public final String getPassword() {
        return this.password;
    }

    /**
     * Set clear text password.
     * Used to set passwords, but they are
     * not stored perminently in clear
     * text password.
     *
     * @param password
     */
    public final void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Retrieve SHA1 password.
     *
     * @return
     */
    public final String getPasswordSha1() {
        return this.passwordSha1;
    }

    /**
     * Set SHA1 password.
     *
     * @param passwordSha1
     */
    public final void setPasswordSha1(final String passwordSha1) {
        this.passwordSha1 = passwordSha1;
    }

    /**
     * A string representation of the user
     * in the form
     * field: value, nextfield: value ...
     * <p/>
     * Password fields are not returned
     * by this method.
     *
     * @return
     */
    public final String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("id: ");
        sb.append(id);
        sb.append(", userName: ");
        sb.append(name);
        sb.append(", name: ");
        sb.append(name);

        return sb.toString();
    }

    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final UserTo userTo = (UserTo) o;

        if (id != userTo.id) return false;
        if (name != null ? !name.equals(userTo.name) : userTo.name != null) return false;
        if (password != null ? !password.equals(userTo.password) : userTo.password != null) return false;
        if (passwordSha1 != null ? !passwordSha1.equals(userTo.passwordSha1) : userTo.passwordSha1 != null)
            return false;
        if (!userName.equals(userTo.userName)) return false;

        return true;
    }

    public final int hashCode() {
        int result;
        result = id;
        result = 29 * result + userName.hashCode();
        result = 29 * result + (name != null ? name.hashCode() : 0);
        result = 29 * result + (password != null ? password.hashCode() : 0);
        result = 29 * result + (passwordSha1 != null ? passwordSha1.hashCode() : 0);
        return result;
    }
}
