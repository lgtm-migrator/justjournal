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

package com.justjournal.ctl;

import com.justjournal.db.CommentDao;
import com.justjournal.db.EntryDAO;

/**
 * Created by IntelliJ IDEA.
 * User: laffer1
 * Date: Jan 1, 2004
 * Time: 8:46:34 PM
 * To change this template use Options | File Templates.
 */
public class DeleteEntry extends Protected {
    int entryId;

    public int getEntryid() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    protected String insidePerform() throws Exception {
        EntryDAO edao = new EntryDAO();
        CommentDao cdao = new CommentDao();
        boolean result;
        boolean result2 = false;

        if (entryId < 1)
            addError("entryId", "The entry id was invalid.");

        if (this.currentLoginId() < 1)
            addError("login", "The login timed out or is invalid.");

        if (!this.hasErrors()) {
            // we need to delete the entry and comments associated
            // with the entry
            result = EntryDAO.delete(entryId, this.currentLoginId());

            if (result)
                result2 = CommentDao.deleteByEntry(entryId);

            if (!result)
                addError("Unknown", "Could not delete entry.");
            if (!result2)
                addError("Unknown", "Unable to delete comments associated with entry.");
        }

        if (this.hasErrors())
            return ERROR;
        else
            return SUCCESS;
    }
}
