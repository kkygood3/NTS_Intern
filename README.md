2019년 1st NTS 인턴
======================

https://gitlab.com/n_tech_service/2019_1st_intern/wikis/home

Project 1
    This project is created based on basic HTML and CSS with servlet.
    This project consist of 4 pages,
    
    1. index (index.html)
    2. aboutme (aboutme.html)
    3. photo (photo.html)
    4. currentTime (today , TodayServlet.java)
    
    Each HTML file uses its own,dependant CSS file with same file name(.css)
    -> exception - TodayServlet(servlet, inline css styling)
    All HTML file uses base.css, since it is shared with all other HTML files,
    base.css includes all basic css styling.
    Servlet shoud not use constructor with two reasons.
    
    1. first you cannot declare constructors on interface in Java

    2. Servlets are not directly instantiated by Java code, instead container create there instance and keep them in pool