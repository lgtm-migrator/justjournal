<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%@ include file="../Connections/jjdb.jsp" %>
<%
    Integer userID = (Integer) session.getAttribute("auth.uid");
    int ival = 0;
    if (userID != null) {
        ival = userID.intValue();
    }

    Driver DriverRecordset1 = (Driver) Class.forName(MM_jjdb_DRIVER).newInstance();
    Connection ConnRecordset1 = DriverManager.getConnection(MM_jjdb_STRING, MM_jjdb_USERNAME, MM_jjdb_PASSWORD);
    PreparedStatement StatementRecordset1 = ConnRecordset1.prepareStatement("SELECT owner_view_only FROM user_pref where id='" + ival + "';");
    ResultSet Recordset1 = StatementRecordset1.executeQuery();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>JustJournal.com: Preferences</title>
    <link rel="stylesheet" type="text/css" href="../layout.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="../font-normal.css" media="all"/>
    <link rel="home" title="Home" href="../index.jsp"/>
    <link rel="contents" title="Site Map" href="../sitemap.jsp"/>
    <link rel="help" title="Technical Support" href="../support/index.jsp"/>
</head>

<body>

<jsp:include page="../header.inc" flush="false"/>

<div id="content">
    <h2>Preferences</h2>

    <%
        if (ival > 0) {
    %>

    <jsp:include page="inc_login.jsp" flush="false"/>

    <div style="width: 700px; padding: 5px; margin: 0">
        <form name="frmSecurity" id="frmSecurity" method="post" action="securitysubmit.h">

            <fieldset>
                <legend><strong>Security</strong><br/></legend>

                <div class="row">
                    <%
                        if (Recordset1.next())
                            if (Recordset1.getString("owner_view_only").equals("Y")) {
                    %>
                    <input type="checkbox" id="ownerOnly" name="ownerOnly" value="checked" checked="checked"/>
                    <% } else { %>
                    <input type="checkbox" id="ownerOnly" name="ownerOnly" value="checked"/>
                    <% } %>
                    Journal viewable only by owner (must login to see it)
                </div>

                <!-- Hack to fix spacing problem.. especially with text boxes -->
                <div class="spacer">&nbsp;</div>

            </fieldset>

            <div class="row"><input type="submit" name="submit" id="submit" value="submit"/></div>

        </form>
    </div>

    <% } else { %>
    <p>You must <a href="../login.jsp">login</a> before you can edit your preferences.</p>
    <% } %>

</div>

<jsp:include page="../footer.inc" flush="false"/>

</body>
</html>
<%
    Recordset1.close();
    StatementRecordset1.close();
    ConnRecordset1.close();
%>