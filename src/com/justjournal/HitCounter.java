package com.justjournal;

import com.justjournal.db.SQLHelper;
import org.apache.log4j.Logger;

import javax.sql.rowset.CachedRowSet;

/**
 * Count access attempts to a resource
 *
 * TODO: Lock the insert/update code at the database level.  stored proc?
 *
 * @author Lucas Holt
 * @version $Id: HitCounter.java,v 1.4 2009/05/16 00:40:02 laffer1 Exp $
 */
public class HitCounter {
    private static final Logger log = Logger.getLogger(HitCounter.class);

    /**
     * Create a resource record
     * @param resource url
     * @throws Exception Database access or creation error
     */
    public void insert(String resource) throws Exception {
        try {
            SQLHelper.executeNonQuery("Insert into hitcount (resource) VALUES('" + resource + "');");
        } catch (Exception e) {
            log.error("insert(): resource: " + resource + " exception: " + e.getMessage());
            throw new Exception("Could not create resource: " + resource);
        }
    }

    /**
     * Update a resource record
     * @param resource url
     * @param count counter value to save
     * @throws Exception database access error
     */
    public void update(String resource, int count) throws Exception {
        if (count > 0)
        SQLHelper.executeNonQuery("UPDATE hitcount set count='" + count + "' WHERE resource='" + resource + "';");
    }

    /**
     * Log a hit
     * @param resource url
     * @return New value of the counter (assumes it worked)
     */
    public int increment(String resource) {
        int count = count(resource);

        try {
            // probably not in the database yet.
            if (count < 0)
                insert(resource);
            else
                update(resource, count + 1);
        } catch (Exception e) {
            log.error("increment(): " + e.getMessage());
        }

        return count + 1;
    }

    /**
     * Retrieve the counter value
     * @param resource url
     * @return current counter value
     */
    public int count(String resource) {
        int count = -1;
        CachedRowSet rs;

        try {
            rs = SQLHelper.executeResultSet("SELECT count FROM hitcount WHERE resource=\"" + resource + "\" LIMIT 1;");

            if (rs.next())
                count = rs.getInt(1);

        } catch (Exception e) {
            log.error("count(): " + e.getMessage());
        }

        return count;
    }

}
