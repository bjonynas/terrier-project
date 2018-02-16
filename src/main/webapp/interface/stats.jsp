<%@ page import="java.util.LinkedList" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" type="text/css" href="/interface/styles/stats.css">
    </head>

    <body>
        <div>
            <div class="header">
                <img id="logo" src="/interface/resources/terrier-logo-web.jpg">
            </div>
            <div class="sidebar">
                <a href="/main" class="w3-bar-item w3-button">Home</a>
                <a href="/index/import" class="w3-bar-item w3-button">Import Index</a>
                <a href="#" class="w3-bar-item w3-button">Status</a>
            </div>
            <div class="container" id="index-stats-container">
                <h2> <%= request.getAttribute("indexName") %> </h2>  </ hr> </ br>

                <%! int s = 0; %>
                <%  LinkedList stats = (LinkedList<String>) request.getAttribute("indexStats");
                    for(s = 0; s < stats.size(); s++){ %>
                        <p> <%= stats.get(s) %> </p>
                    <% } %>
            </div>

            <% if( ((boolean) request.getAttribute("searched")) == false) { %>
                <div class="container" id="query-container" >
                    <form name="queryForm" action="statsServlet" method="post">
                        <div id="searchDiv">
                            Query: <input type="text" name="queryString" />
                            <input type="submit" value="Search" />
                        </div>
                        Matching Model: <input type="text" name="matchingModel" />
                    </form>
                </div>

            <% } else { %>
                <div>
                    <h3> <% request.getAttribute("queryString"); %> </h3>
                    <p> <% request.getAttribute("results"); %> </p>
                </div>
            <% } %>

        </div>
    </body>
</html>