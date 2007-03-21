package com.justjournal.utility;

/*---------------------------------------------------------------------------*\
  $Id: HTMLUtil.java,v 1.7 2007/03/21 01:44:11 laffer1 Exp $
  ---------------------------------------------------------------------------
  This software is released under a Berkeley-style license:

  Copyright (c) 2004-2005 Brian M. Clapper. All rights reserved.

  Redistribution and use in source and binary forms are permitted provided
  that: (1) source distributions retain this entire copyright notice and
  comment; and (2) modifications made to the software are prominently
  mentioned, and a copy of the original software (or a pointer to its
  location) are included. The name of the author may not be used to endorse
  or promote products derived from this software without specific prior
  written permission.

  THIS SOFTWARE IS PROVIDED ``AS IS'' AND WITHOUT ANY EXPRESS OR IMPLIED
  WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTIES OF
  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.

  Effectively, this means you can do what you want with the software except
  remove this notice or take advantage of the author's name. If you modify
  the software and redistribute your modified version, you must indicate that
  your version is a modification of the original, and you must provide either
  a pointer to or a copy of the original.
\*---------------------------------------------------------------------------*/

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Static class containing miscellaneous HTML-related utility methods.
 *
 * @author Copyright &copy; 2004 Brian M. Clapper
 * @version <tt>$Revision: 1.7 $</tt>
 */
public final class HTMLUtil {
    /*----------------------------------------------------------------------*\
                            Private Constants
    \*----------------------------------------------------------------------*/

    /**
     * Resource bundle containing the character entity code mappings.
     */
    private static final String BUNDLE_NAME = "com.justjournal.utility.HTMLUtil";

    /*----------------------------------------------------------------------*\
                            Private Data Items
    \*----------------------------------------------------------------------*/

    private static ResourceBundle resourceBundle = null;

    /**
     * For regular expression substitution. Instantiated first time it's
     * needed.
     */
    private static Pattern entityPattern = null;

    /*----------------------------------------------------------------------*\
                                Constructor
    \*----------------------------------------------------------------------*/

    private HTMLUtil() {
    }

    /*----------------------------------------------------------------------*\
                              Public Methods
    \*----------------------------------------------------------------------*/

    /**
     * Removes all HTML element tags from a string, leaving just the character
     * data. This method does <b>not</b> touch any inline HTML character
     * entity codes. Use
     * {@link #convertCharacterEntities convertCharacterEntities()}
     * to convert HTML character entity codes.
     *
     * @param s the string to adjust
     * @return the resulting, possibly modified, string
     * @see #convertCharacterEntities
     */
    public static String stripHTMLTags(String s) {
        char[]         ch = s.toCharArray();
        boolean inElement = false;
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < ch.length; i++) {
            switch (ch[i]) {
                case '<':
                    inElement = true;
                    break;

                case '>':
                    if (inElement)
                        inElement = false;
                    else
                        buf.append(ch[i]);
                    break;

                default:
                    if (! inElement)
                        buf.append(ch[i]);
                    break;
            }
        }

        return buf.toString();
    }

    /**
     * Converts all inline HTML character entities (c.f.,
     * <a href="http://www.w3.org/TR/REC-html40/sgml/entities.html">http://www.w3.org/TR/REC-html40/sgml/entities.html</a>)
     * to their Unicode character counterparts, if possible.
     *
     * @param s the string to convert
     * @return the resulting, possibly modified, string
     * @see #stripHTMLTags
     */
    public static String convertCharacterEntities(String s) {
        // The resource bundle contains the mappings for symbolic entity
        // names like "amp". Note: Must protect matching and MatchResult in
        // a critical section, for thread-safety. 

        synchronized (HTMLUtil.class) {
            try {
                if (entityPattern == null)
                    entityPattern = Pattern.compile("&(#?[^; \t]+);");
            }

            catch (PatternSyntaxException ex) {
                // Should not happen unless I've screwed up the pattern.
                // Throw a runtime error.

                assert (false);
            }
        }

        ResourceBundle bundle = getResourceBundle();
        StringBuffer buf = new StringBuffer();
        Matcher matcher;

        synchronized (HTMLUtil.class) {
            matcher = entityPattern.matcher(s);
        }

        for (; ;) {
            String match;
            String preMatch;
            String postMatch;

            if (! matcher.find())
                break;

            match = matcher.group(1);
            preMatch = s.substring(0, matcher.start(1) - 1);
            postMatch = s.substring(matcher.end(1) + 1);

            if (preMatch != null)
                buf.append(preMatch);

            if (match.charAt(0) == '#') {
                if (match.length() == 1)
                    buf.append('#');

                else {
                    // It might be a numeric entity code. Try to parse it
                    // as a number. If the parse fails, just put the whole
                    // string in the result, as is.

                    try {
                        int cc = Integer.parseInt(match.substring(1));

                        // It parsed. Is it a valid Unicode character?

                        if (Character.isDefined((char) cc))
                            buf.append((char) cc);
                        else
                            buf.append("&#").append(match).append(";");
                    }

                    catch (NumberFormatException ex) {
                        buf.append("&#").append(match).append(";");
                    }
                }
            } else {
                // Not a numeric entity. Try to find a matching symbolic
                // entity.

                try {
                    String rep = bundle.getString("html_" + match);
                    buf.append(rep);
                }

                catch (MissingResourceException ex) {
                    buf.append("&").append(match).append(";");
                }
            }

            if (postMatch == null)
                break;

            s = postMatch;
            matcher.reset(s);
        }

        if (s.length() > 0)
            buf.append(s);

        return buf.toString();
    }

    /**
     * Convenience method to convert embedded HTML to text. This method:
     * <p/>
     * <ul>
     * <li> Strips embedded HTML tags via a call to
     * {@link #stripHTMLTags #stripHTMLTags()}
     * <li> Uses {@link #convertCharacterEntities convertCharacterEntities()}
     * to convert HTML entity codes to appropriate Unicode characters.
     * </ul>
     *
     * @param s the string to parse
     * @return the resulting, possibly modified, string
     * @see #convertCharacterEntities
     * @see #stripHTMLTags
     */
    public static String textFromHTML(String s) {
        return convertCharacterEntities(stripHTMLTags(s));
    }

    /*----------------------------------------------------------------------*\
                              Private Methods
    \*----------------------------------------------------------------------*/

    /**
     * Load the resource bundle, if it hasn't already been loaded.
     */
    private static ResourceBundle getResourceBundle() {
        synchronized (HTMLUtil.class) {
            if (resourceBundle == null)
                resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }

        return resourceBundle;
    }

    /**
     * Looks through a string for Uniform Resource Indicators (URI)
     * and converts them to HTML a tags.  (hyperlinks)
     *
     * @param input Text containing URIs
     * @return Text with HTML a tags added.
     */
    public static String uriToLink(String input) {
        String SubDomain = "(?i:[a-z0-9]|[a-z0-9][-a-z0-9]*[a-z0-9])";
        String TopDomains = "(?x-i:com\\b        \n" +
                "     |edu\\b        \n" +
                "     |biz\\b        \n" +
                "     |in(?:t|fo)\\b \n" +
                "     |mil\\b        \n" +
                "     |net\\b        \n" +
                "     |org\\b        \n" +
                "     |[a-z][a-z]\\b \n" + // country codes
                ")                   \n";
        String Hostname = "(?:" + SubDomain + "\\.)+" + TopDomains;

        String NOT_IN = ";\"'<>()\\[\\]\\{\\}\\s\\x7F-\\xFF";
        String NOT_END = "!.,?";
        String ANYWHERE = "[^" + NOT_IN + NOT_END + "]";
        String EMBEDDED = "[" + NOT_END + "]";
        String UrlPath = "/" + ANYWHERE + "*(" + EMBEDDED + "+" + ANYWHERE + "+)*";

        String Url =
                "(?x:                                                \n" +
                        "  \\b                                               \n" +
                        "  ## match the hostname part                        \n" +
                        "  (                                                 \n" +
                        "    (?: ftp | http s? ): // [-\\w]+(\\.\\w[-\\w]*)+ \n" +
                        "   |                                                \n" +
                        "    " + Hostname + "                                \n" +
                        "  )                                                 \n" +
                        "  # allow optional port                             \n" +
                        "  (?: \\d+ )?                                       \n" +
                        "                                                    \n" +
                        "  # rest of url is optional, and begins with /      \n" +
                        " (?: " + UrlPath + ")?                              \n" +
                        ")";

        String url2 = "((ftp|https?://(.*?))\\s)";
        // Now convert string we've built up into a real regex object
        Pattern UrlRegex = Pattern.compile(url2);
        // Now ready to apply to raw text to find urls . . .

        //final Pattern p = Pattern.compile("(\\sI\\n|^)(\\w+://[^\\s\\n]+)");

        final Matcher m = UrlRegex.matcher(input);

        input = m.replaceAll("<a href=\"$2\">$2</a>");
        return input;

    }

    /**
     * Determine the correct mime type to send for content type in an
     * HTTP response for XHTML documents.
     * <p/>
     * Does not handle q values currently.
     * <p/>
     * Based on http://www.workingwith.me.uk/articles/scripting/mimetypes
     *
     * @param httpAccept The HTTP_ACCEPT HTTP Header for a request
     * @param userAgent  The USER_AGENT HTTP Header for a request
     * @return Correct XHTML mime type for the user agent.
     */
    public static String determineMimeType(String httpAccept, String userAgent) {

        String mimeType = "text/html";
        //String testpattern = "/application\\/html\\+xml;q=0(\\.[1-9]+)/i";

        if (httpAccept == null)
            httpAccept = "";

        if (userAgent == null)
            userAgent = "";

        if (httpAccept.indexOf("application/xhtml+xml") > -1) {
            // warning the q value is not checked.  This value
            // determines how much the browser likes the match.
            // 0 bad 1 good (range 0.0-1.0)
            return "application/xhtml+xml";
        } else if (userAgent.indexOf("W3C_Validator") > -1) {
            // The W3C Validator does not advertise its support for
            // the correct mime type.
            return "application/xhtml+xml";
        } else {
            return mimeType;
        }
    }
}

