
package com.justjournal;

import com.justjournal.db.EntryTo;
import com.justjournal.db.EntryDAO;
import com.justjournal.utility.Spelling;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Adds journal entries to database.
 *
 * Optionally spell checks entries and returns the user
 * to the update view to make changes.
 * @author Lucas Holt
 * @version 1.3
 * @since 1.0
 * Created on March 23, 2003, 12:42 PM
 */
public final class updateJournal extends HttpServlet
{

    static final char endl = '\n';

    /** Initializes the servlet.
     */
    public void init( final ServletConfig config ) throws ServletException
    {
        super.init( config );
    }

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest( final HttpServletRequest request, final HttpServletResponse response )
            throws ServletException, java.io.IOException
    {

        boolean blnError = false;
        final StringBuffer sb = new StringBuffer();

        // start session if one does not exist.
        final HttpSession session = request.getSession( true );
        int userID = 0;
        String userName = (String) session.getAttribute( "auth.user" );
        final Integer userIDasi = (Integer) session.getAttribute( "auth.uid" );

        if ( userIDasi != null )
        {
            userID = userIDasi.intValue();
        }

        String userAgent = request.getHeader("User-Agent");
        boolean webClient = true;  // browser

        if ( userAgent != null && userAgent.indexOf("JustJournal") > -1 )
            webClient = false; // desktop client.. win/mac


        if ( webClient )
        {
            // Send HTML type in http stream
            response.setContentType( "text/html" );
            response.setDateHeader( "Expires", System.currentTimeMillis() );
            response.setDateHeader( "Last-Modified", System.currentTimeMillis() );
            response.setHeader( "Cache-Control", "no-store, no-cache, must-revalidate" );
            response.setHeader( "Pragma", "no-cache" );
        }
        else
        {
            response.setContentType( "text/xml" );
            response.setDateHeader( "Expires", System.currentTimeMillis() );
            response.setDateHeader( "Last-Modified", System.currentTimeMillis() );
        }

        // Validate the login
        if ( userID < 1 )
        {
            try
            {
                userName = request.getParameter( "user" ).toLowerCase();
                String password = request.getParameter( "pass" );
                userID = webLogin.validate( userName, password );

                if ( request.getParameter( "keeplogin" ).compareTo( "checked" ) == 0 )
                {
                    session.setAttribute( "auth.uid", new Integer( userID ) );
                    session.setAttribute( "auth.user", userName );
                }
            }
            catch ( Exception e3 )
            {
                if ( webClient )
                    webError.Display(
                        "Authentication Error",
                        "Unable to login.  Please check your username and password.",
                        sb );

                // TODO: Add error logic for desktop client.
            }
        }


        if ( userID > 0 )
        {
            // We authenticated OK.  Continue...

            final EntryTo et = new EntryTo();

            // Get the user input
            int security = Integer.valueOf( request.getParameter( "security" ) ).intValue();
            int location = Integer.valueOf( request.getParameter( "location" ) ).intValue();
            int mood = Integer.valueOf( request.getParameter( "mood" ) ).intValue();
            String music = request.getParameter( "music" );
            String aformat = request.getParameter( "aformat" );
            String discomments = request.getParameter( "discomments" );
            String noemail = request.getParameter( "noemail" );

            if ( music == null )
                music = "";

            try
            {
                et.setUserId( userID );
                et.setDate( request.getParameter( "date" ) );
                et.setSubject( StringUtil.replace( request.getParameter( "subject" ), '\'', "\\\'" ) );
                et.setBody( StringUtil.replace( request.getParameter( "body" ), '\'', "\\\'" ) );
                et.setMusic( StringUtil.replace( music, '\'', "\\\'" ) );
                et.setSecurityLevel( security );
                et.setLocationId( location );
                et.setMoodId( mood );

                // the check box says disable auto format
                if ( aformat != null && aformat.equals( "checked" ) )
                    et.setAutoFormat( false );
                else
                    et.setAutoFormat( true );

                // disable comments
                if ( discomments != null && discomments.equals( "checked" ) )
                    et.setAllowComments( false );
                else
                    et.setAllowComments( true );

                // disable email notifications
                if ( noemail != null && noemail.equals( "checked" ) )
                    et.setEmailComments( false );
                else
                    et.setEmailComments( true );


            }
            catch ( IllegalArgumentException e1 )
            {
                if ( webClient )
                    webError.Display( "Input Error", e1.getMessage(), sb );

                // TODO: Add error logic for desktop client
                blnError = true;
            }

            final String isSpellCheck = request.getParameter( "spellcheck" );
            if ( isSpellCheck != null &&
                 isSpellCheck.compareTo( "checked" ) == 0 )
            {
                Spelling sp = new Spelling();

                // store everything
                session.setAttribute( "spell.check", "true" );
                session.setAttribute( "spell.body", et.getBody() );
                session.setAttribute( "spell.music", et.getMusic() );
                session.setAttribute( "spell.location", new Integer( et.getLocationId() ) );
                session.setAttribute( "spell.subject", et.getSubject() );
                session.setAttribute( "spell.date", et.getDate() );
                session.setAttribute( "spell.security", new Integer( et.getSecurityLevel() ) );
                session.setAttribute( "spell.mood", new Integer( et.getMoodId() ) );

                //check the spelling now
                session.setAttribute( "spell.suggest", sp.checkSpelling( et.getBody() ) );

                // redirect the user agent to the promised land.
                response.sendRedirect( "/update.jsp" );

            }
            else
            {
                // clear out spell check variables to be safe
                // note this might be wrong still
                session.setAttribute( "spell.check", "" ); //false
                session.setAttribute( "spell.body", "" );
                session.setAttribute( "spell.music", "" );
                session.setAttribute( "spell.location", new Integer( 0 ) );
                session.setAttribute( "spell.subject", "" );
                session.setAttribute( "spell.date", "" );
                session.setAttribute( "spell.security", new Integer( 0 ) );
                session.setAttribute( "spell.mood", new Integer( 0 ) );

                // insert header fields
                if ( blnError == false )
                {
                    EntryDAO edao = new EntryDAO();
                    boolean result = edao.add( et );

                    if ( result == false && webClient )
                        webError.Display( "Error", "Error adding the journal entry", sb );
                }


                // display message to user.
                if ( blnError == false )
                {

                    /* Initialize Preferences Object */
                    Preferences pf;
                    try
                    {
                        pf = new Preferences( userName );
                    }
                    catch ( Exception ex )
                    {
                        throw new ServletException( ex );
                    }


                    // Begin HTML document.
                    sb.append( "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"  \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">" );
                    sb.append( endl );

                    sb.append( "<html xmlns=\"http://www.w3.org/1999/xhtml\">" );
                    sb.append( endl );

                    sb.append( "<head>" );
                    sb.append( endl );
                    if ( pf.isSpiderAllowed() == false )
                    {
                        sb.append( "\t<meta name=\"robots\" content=\"noindex, nofollow, noarchive\" />" );
                        sb.append( endl );
                        sb.append( "\t<meta name=\"googlebot\" content=\"nosnippet\" />" );
                        sb.append( endl );
                    }
                    sb.append( "\t<title>" + pf.getName() + "'s Journal</title>" );
                    sb.append( endl );

                    /* User's custom style URL.. i.e. uri to css doc outside domain */
                    if ( pf.getStyleUrl() != "" && pf.getStyleUrl() != null )
                    {
                        sb.append( "\t<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"" + pf.getStyleUrl() + "\" />" );
                        sb.append( endl );
                    }
                    else
                    {
                        /* use our template system instead */
                        sb.append( "\t<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"/styles/" + pf.getStyleId() + ".css\" />" );
                        sb.append( endl );
                    }

                    /* Optional style sheet overrides! */
                    if ( pf.getStyleDoc() != "" && pf.getStyleDoc() != null )
                    {
                        sb.append( "<style type=\"text/css\" media=\"screen\">" );
                        sb.append( endl );
                        sb.append( "<!--" );
                        sb.append( endl );
                        sb.append( pf.getStyleDoc() );
                        sb.append( "-->" );
                        sb.append( endl );
                        sb.append( "</style>" );
                        sb.append( endl );
                    }
                    /* End overrides */
                    sb.append( "</head>\n" );

                    sb.append( "<body>\n" );

                    // BEGIN MENU
                    sb.append( "\t<!-- Header: Begin -->" );
                    sb.append( endl );
                    sb.append( "\t\t<div id=\"header\">" );
                    sb.append( endl );
                    sb.append( "\t\t<h1>" + pf.getName() + "'s Journal</h1>" );
                    sb.append( endl );
                    sb.append( "\t</div>" );
                    sb.append( endl );
                    sb.append( "\t<!-- Header: End -->\n" );
                    sb.append( endl );

                    sb.append( "\t<!-- Menu: Begin -->" );
                    sb.append( endl );
                    sb.append( "\t<div id=\"menu\">" );
                    sb.append( endl );

                    sb.append( "\t<p id=\"muser\">" );
                    sb.append( endl );
                    sb.append( "\t\t<a href=\"/users/" + userName + "\">recent entries</a><br />" );
                    sb.append( endl );
                    sb.append( "\t\t<a href=\"/users/" + userName + "/calendar\">Calendar</a><br />" );
                    sb.append( endl );
                    sb.append( "\t\t<a href=\"/users/" + userName + "/friends\">Friends</a><br />" );
                    sb.append( endl );
                    sb.append( "\t\t<a href=\"/users/" + userName + "/ljfriends\">LJ Friends</a><br />" );
                    sb.append( endl );
                    sb.append( "\t\t<a href=\"/profile.jsp?user=" + userName + "\">Profile</a><br />" );
                    sb.append( endl );
                    sb.append( "\t</p>" );
                    sb.append( endl );

                    // General stuff...
                    sb.append( "\t<p id=\"mgen\">" );
                    sb.append( endl );
                    sb.append( "\t\t<a href=\"/update.jsp\">Update Journal</a><br />" );
                    sb.append( endl );

                    // Authentication menu choice
                    if ( userID > 0 )
                    {
                        // User is logged in.. give them the option to log out.
                        sb.append( "\t\t<a href=\"/prefs/index.jsp\">Preferences</a><br />" );
                        sb.append( endl );
                        sb.append( "\t\t<a href=\"/logout.jsp\">Log Out</a>" );
                        sb.append( endl );
                    }
                    else
                    {
                        // User is logged out.. give then the option to login.
                        sb.append( "\t\t<a href=\"/login.jsp\">Login</a>" );
                        sb.append( endl );
                    }
                    sb.append( "\t</p>" );
                    sb.append( endl );

                     sb.append( "\t<p>RSS Syndication<br /><br />" );
                sb.append( "<a href=\"/users/" );
                sb.append( userName );
                sb.append( "/rss\"><img src=\"/img/v4_xml.gif\" alt=\"RSS content feed\" /> Recent</a><br />" );
                 sb.append( "<a href=\"/users/" );
                sb.append( userName );
                sb.append( "/subscriptions\">Subscriptions</a>" );
                sb.append( "\t</p>" );
                sb.append( endl );

                    sb.append( "\t</div>" );
                    sb.append( endl );
                    sb.append( "\t<!-- Menu: End -->\n" );
                    sb.append( endl );
                    // END MENU

                    sb.append( "\t<!-- Content: Begin -->" );
                    sb.append( endl );
                    sb.append( "\t<div id=\"content\">" );
                    sb.append( endl );

                    if ( userID > 0 )
                    {
                        sb.append( "\t<p>You are logged in as <a href=\"/users/" + userName + "\"><img src=\"/images/user.gif\" alt=\"user\" />" + userName + "</a>.</p>" );
                        sb.append( endl );
                    }

                    sb.append( "\t\t<h2>Update Journal</h2>" );
                    sb.append( endl );

                    sb.append( "\t\t<p><strong>entry added</strong></p>" );
                    sb.append( endl );
                    sb.append( "\t\t<p><a href=\"/update.jsp\">Add another entry</a></p>" );
                    sb.append( endl );
                    sb.append( "\t\t<p><a href=\"/users/" + userName + "\">View journal</a></p>" );
                    sb.append( endl );

                    sb.append( "\t</div>" );
                    sb.append( endl );
                    sb.append( "\t<!-- Content: End -->" );
                    sb.append( endl );
                    sb.append( endl );

                    sb.append( "\t<!-- Footer: Begin -->" );
                    sb.append( endl );
                    sb.append( "\t<div id=\"footer\">" );
                    sb.append( endl );
                    sb.append( "\t\t<a href=\"/index.jsp\" title=\"JustJournal.com: Online Journals\">JustJournal.com</a> " );
                    sb.append( "\t</div>" );
                    sb.append( endl );

                    sb.append( "\t<!-- Footer: End -->\n" );
                    sb.append( endl );

                    sb.append( "</body>" );
                    sb.append( endl );
                    sb.append( "</html>" );
                    sb.append( endl );
                }

                // output the result of our processing
                final ServletOutputStream outstream = response.getOutputStream();
                outstream.println( sb.toString() );
                outstream.flush();
            }

        }
        else
        {
            // We couldn't authenticate.  Tell the user.
            webError.Display(
                    "Authentication Error",
                    "Unable to login.  Please check your username and password.",
                    sb );

            // output the result of our processing
            final ServletOutputStream outstream = response.getOutputStream();
            outstream.println( sb.toString() );
            outstream.flush();
        }

    }

    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, java.io.IOException
    {
        processRequest( request, response );
    }

    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, java.io.IOException
    {
        processRequest( request, response );
    }

    /** Returns a short description of the servlet.
     */
    public String getServletInfo()
    {
        return "adds a journal entry";
    }

}