<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>JustJournal.com: Technical Information</title>
    <link rel="stylesheet" type="text/css" href="../layout.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="../font-normal.css" media="all"/>
    <link rel="home" title="Home" href="../index.jsp"/>
    <link rel="contents" title="Site Map" href="../sitemap.jsp"/>
    <link rel="help" title="Technical Support" href="../support/index.jsp"/>
</head>

<body>

<jsp:include page="../header.inc" flush="false"/>

<div id="content">
    <h2>Technical Information</h2>

    <p>&nbsp;</p>

    <h3>What server platform are you using?</h3>

    <p>Just Journal is hosted on a <a href="http://www.midnightbsd.org/">MidnightBSD</a> server using <a
            href="http://www.apache.org/">Apache</a> 2.2, Apache Tomcat
        5.5, and Java 1.5 (native). I use <a href="http://www.mysql.com/">MySQL</a> 5.x for the data store.</p>

    <h3>What programming languages are used?</h3>

    <p>The entire site has been written from the ground up using <a href="http://java.sun.com/">Java</a> 1.4. Servlets
        and JSP are used to serve content. JSP will be replaced by a new templating
        system in development.</p>

    <h3>Isn't Java slow?</h3>

    <p>This is a common misconception. Java used at the server level is quite fast.
        Most people are thinking of Java Applications that use AWT or Swing when
        they say Java is slow. The Java GUI libraries are slow, but server side
        technologies power huge Internet sites including BestBuy.com, CircuitCity.com,
        and sun.com.</p>

    <h3>Do you think this site will scale?</h3>

    <p>The current version needs work in this area, but I think that I can handle
        the load for some time. In reality, the weakest link is MySQL. I will work
        on Caching systems, and generating dynamic content into static form. I
        may end up moving away from mysql if performance becomes an issue.</p>

    <h3>Isn't this site like LiveJournal? Why didn't you just use their code?</h3>

    <p><a href="http://www.livejournal.com/">LiveJournal</a> was a great website when I first started using it.
        Unfortunetely,
        it didn't scale well. I suggested some changes to Live Journal including
        changing the styling system and switching to CSS for many of the &quot;template
        tasks.&quot; It appeared that my comments were not going to be considered
        since the creator based it on a &quot;language&quot; he created called BML.</p>

    <p>BML is an HTML template system written in Perl 5. It was designed help reuse
        blocks of html similar to objects. The idea was nice at the time, but it
        was implemented in Perl making it slow and Cascading Style Sheets solve
        most of the same problems. (presentation code reuse)</p>

    <p>I did download
        the LiveJournal code to see if I could alter it and import my own style
        system, but LiveJournal is implemented as mod perl modules tightly integrated
        with the BML code. The use of mod perl for a legacy version of apache (1.3)
        showed me that the project was not salvagable without a lot of work. I
        finally decided to write a site from scratch in a language more suited
        to me.</p>

    <p>LiveJournal has since implemented a caching system using a C server program
        (hash, storing &quot;objects&quot; in ram), and upgraded the style system.
        Recently, LiveJournal started storing practically everything in RAM.</p>

    <p>There are several other limitations LiveJournal has because they must be
        backward compatible. Since I am writing JustJournal from scratch, I may
        avoid some of their mistakes making this site better than LJ.</p>

    <h3>Is this site open source?</h3>

    <p>Yes. I released the code under the BSD license. The windows client
        is under the GPL license due to code that I based it on. (LJ.NET client)
    </p>
</div>

<jsp:include page="../footer.inc" flush="false"/>

</body>
</html>