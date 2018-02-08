<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List, java.util.LinkedList, java.util.HashMap" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" type="text/css" href="/interface/styles/main.css">
    </head>

    <body>
        <div>
            <div class="header">
                <img id="logo" src="/interface/resources/terrier-logo-web.jpg">
            </div>
            <div class="sidebar">
                <a href="#" class="w3-bar-item w3-button">Home</a>
                <a href="#" class="w3-bar-item w3-button">Import Index</a>
                <a href="#" class="w3-bar-item w3-button">Status</a>
            </div>

            <div id="tableContainer">

                <h2> Imported Indexes </h2>

                <table id="indexTable">
                    <% HashMap<String, List<String>> indexInfo = (HashMap<String, List<String>>) request.getAttribute("indexInfo"); %>
                    <%! int indexStat; %>
                    <%! List<String> curInfo; %>

                    <% if( indexInfo == null || indexInfo.size() == 0 ) { %>
                        <p> No imported indexes! </p>
                    <% } else { %>

                        <% for(String key : indexInfo.keySet()){ %>
                            <tr>
                                <td> <%= key %> </td>
                                <td> <p>
                                    <% curInfo = indexInfo.get(key); %>
                                    <% for(indexStat=0; indexStat < curInfo.size(); indexStat++) { %>
                                        <%= curInfo.get(indexStat) %>
                                    <% } %>
                                </p> </td>
                                <td>
                                    <a href="#" class="w3-bar-item w3-button"> >> </a>
                                </td>
                            </tr>
                        <% } %>
                    <% } %>
                </table>
            </div>

        </div>
    </body>
</html>