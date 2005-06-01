
package com.justjournal.db;

import com.justjournal.SQLHelper;
import sun.jdbc.rowset.CachedRowSet;

/**
 * Created by IntelliJ IDEA.
 * User: laffer1
 * Date: Jan 21, 2004
 * Time: 12:32:03 PM
 */
public class UserDao
{
    public boolean add( UserTo user )
    {
         boolean noError = true;
        int records = 0;

        final String sqlStmt =
                "Insert INTO user (username,password,name) VALUES('"
                + user.getUserName() + "',sha1('" + user.getPassword()
                + "'),'" + user.getName() + "');";

        try {
           records = SQLHelper.executeNonQuery( sqlStmt );
        } catch ( Exception e ) {
            noError = false;
        }

        if (records != 1)
            noError = false;

        return noError;
    }

    /**
     * update the user record with a new first name.
     * usernames can not be changed.
     * passwords can be changed via the
     * <code>com.justjournal.ctl.ChangePasswordSubmit</code> class.
     * @see com.justjournal.ctl.ChangePasswordSubmit
     * @param user
     * @return
     */
    public boolean update( UserTo user )
    {
     boolean noError = true;

        final String sqlStmt =
                "Update user SET name='" + user.getName() + "' WHERE id='" + user.getId() + "' LIMIT 1;";

        try {
           SQLHelper.executeNonQuery( sqlStmt );
        } catch ( Exception e ) {
            noError = false;
        }

        return noError;
    }

    /**
     * Deletes a user from the database.  This
     * should not be called by cancel account.  Accounts
     * should be deactivated.
     *
     * @param userId
     * @return
     */
    public boolean delete( int userId )
    {
        boolean noError = true;

        final String sqlStmt = "DELETE FROM user WHERE id='" + userId + "' LIMIT 1;";

        if ( userId > 0)
        {
            try {
                SQLHelper.executeNonQuery(sqlStmt);
            } catch (Exception e) {
                noError = false;
            }
        } else {
            noError = false;
        }

        return noError;
    }

    /**
     * Retrieve a user given the user id.
     * Does NOT retrieve password or sha1 password.
     *
     * @param userId
     * @return
     */
    public UserTo view( int userId )
    {
        UserTo user = new UserTo();
                CachedRowSet rs = null;
                String sqlStmt = "SELECT username, name from user WHERE id='" + userId + "' Limit 1;";

                try
                {

                    rs = SQLHelper.executeResultSet( sqlStmt );

                    if ( rs.next() )
                    {
                        user.setId( userId );
                        user.setUserName( rs.getString( 1 )); // username
                        user.setName( rs.getString( 2 )); // first name
                    }

                    rs.close();

                }
                catch ( Exception e1 )
                {
                    if ( rs != null )
                    {
                        try
                        {
                            rs.close();
                        }
                        catch ( Exception e )
                        {
                            // NOTHING TO DO
                        }
                    }
                }
        return user;

    }

    /**
     * Retrieve a user give the user name.
     * Does not retrieve the password or
     * sha1 password.
     *
     * @param userName
     * @return
     */
    public UserTo view ( String userName )
    {
         UserTo user = new UserTo();
                CachedRowSet rs = null;
                String sqlStmt = "SELECT id, name from user WHERE username='" + userName + "' Limit 1;";

                try
                {

                    rs = SQLHelper.executeResultSet( sqlStmt );

                    if ( rs.next() )
                    {
                        user.setId( rs.getInt(1));
                        user.setUserName( userName );
                        user.setName( rs.getString( 2 )); // first name
                    }

                    rs.close();

                }
                catch ( Exception e1 )
                {
                    if ( rs != null )
                    {
                        try
                        {
                            rs.close();
                        }
                        catch ( Exception e )
                        {
                            // NOTHING TO DO
                        }
                    }
                }
        return user;
    }


}
