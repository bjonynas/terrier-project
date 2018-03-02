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
                <a href="/status" class="w3-bar-item w3-button">Status</a>
            </div>
            <div class="container" id="index-stats-container">
                <h2> <%= request.getAttribute("indexName") %> </h2>  </ hr> </ br>

                <%! int s = 0; %>
                <%  LinkedList stats = (LinkedList<String>) request.getAttribute("indexStats");
                    for(s = 0; s < stats.size(); s++){ %>
                        <p> <%= stats.get(s) %> </p>
                    <% } %>
            </div>

            <div class="container" >
                <form name="queryForm" id="query-form" action="statsServlet" method="post">
                    <div class="inline-div">
                        Query: <input type="text" name="queryString" />
                        <input type="submit" value="Search" />
                    </div>
                    <div class="inline-div">
                        Results type:
                        <input type="radio" name="displayType" value="JSON" checked="checked">JSON</input>
                        <input type="radio" name="displayType" value="PlainText">Plain Text</input>
                    </div>
                    Matching Model: <input type="text" name="matchingModel" />
                </form>
            </div>
                <a href="/main" class="w3-bar-item w3-button" id="back-button"> Back </a>
        </div>
    </body>
</html>