package com.justjournal.model.auto;

/** Class _Favorites was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _Favorites extends org.apache.cayenne.CayenneDataObject {

    public static final String ENTRYID_PROPERTY = "entryid";
    public static final String OWNER_PROPERTY = "owner";

    public static final String ID_PK_COLUMN = "id";

    public void setEntryid(Long entryid) {
        writeProperty("entryid", entryid);
    }
    public Long getEntryid() {
        return (Long)readProperty("entryid");
    }
    
    
    public void setOwner(Long owner) {
        writeProperty("owner", owner);
    }
    public Long getOwner() {
        return (Long)readProperty("owner");
    }
    
    
}
