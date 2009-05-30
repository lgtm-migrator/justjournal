package com.justjournal.model.auto;

/** Class _UserContact was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _UserContact extends org.apache.cayenne.CayenneDataObject {

    public static final String AIM_PROPERTY = "aim";
    public static final String EMAIL_PROPERTY = "email";
    public static final String HP_TITLE_PROPERTY = "hpTitle";
    public static final String HP_URI_PROPERTY = "hpUri";
    public static final String ICQ_PROPERTY = "icq";
    public static final String MSN_PROPERTY = "msn";
    public static final String PHONE_PROPERTY = "phone";
    public static final String YAHOO_PROPERTY = "yahoo";

    public static final String ID_PK_COLUMN = "id";

    public void setAim(String aim) {
        writeProperty("aim", aim);
    }
    public String getAim() {
        return (String)readProperty("aim");
    }
    
    
    public void setEmail(String email) {
        writeProperty("email", email);
    }
    public String getEmail() {
        return (String)readProperty("email");
    }
    
    
    public void setHpTitle(String hpTitle) {
        writeProperty("hpTitle", hpTitle);
    }
    public String getHpTitle() {
        return (String)readProperty("hpTitle");
    }
    
    
    public void setHpUri(String hpUri) {
        writeProperty("hpUri", hpUri);
    }
    public String getHpUri() {
        return (String)readProperty("hpUri");
    }
    
    
    public void setIcq(String icq) {
        writeProperty("icq", icq);
    }
    public String getIcq() {
        return (String)readProperty("icq");
    }
    
    
    public void setMsn(String msn) {
        writeProperty("msn", msn);
    }
    public String getMsn() {
        return (String)readProperty("msn");
    }
    
    
    public void setPhone(String phone) {
        writeProperty("phone", phone);
    }
    public String getPhone() {
        return (String)readProperty("phone");
    }
    
    
    public void setYahoo(String yahoo) {
        writeProperty("yahoo", yahoo);
    }
    public String getYahoo() {
        return (String)readProperty("yahoo");
    }
    
    
}
