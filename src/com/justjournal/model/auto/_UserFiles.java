package com.justjournal.model.auto;

/** Class _UserFiles was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _UserFiles extends org.apache.cayenne.CayenneDataObject {

    public static final String DATA_PROPERTY = "data";
    public static final String DATE_MODIFIED_PROPERTY = "dateModified";
    public static final String MIMETYPE_PROPERTY = "mimetype";
    public static final String OWNERID_PROPERTY = "ownerid";
    public static final String TITLE_PROPERTY = "title";

    public static final String ID_PK_COLUMN = "id";

    public void setData(byte[] data) {
        writeProperty("data", data);
    }
    public byte[] getData() {
        return (byte[])readProperty("data");
    }
    
    
    public void setDateModified(java.util.Date dateModified) {
        writeProperty("dateModified", dateModified);
    }
    public java.util.Date getDateModified() {
        return (java.util.Date)readProperty("dateModified");
    }
    
    
    public void setMimetype(String mimetype) {
        writeProperty("mimetype", mimetype);
    }
    public String getMimetype() {
        return (String)readProperty("mimetype");
    }
    
    
    public void setOwnerid(Long ownerid) {
        writeProperty("ownerid", ownerid);
    }
    public Long getOwnerid() {
        return (Long)readProperty("ownerid");
    }
    
    
    public void setTitle(String title) {
        writeProperty("title", title);
    }
    public String getTitle() {
        return (String)readProperty("title");
    }
    
    
}
