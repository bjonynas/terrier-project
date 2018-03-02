<%@ page import="java.util.List, java.util.LinkedList, java.util.HashMap" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" type="text/css" href="/interface/styles/status.css">
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

            <div id="statusContainer">

                <h2> Server Status </h2>

                <p> Available Memory: <%= request.getAttribute("availableMemory") %>MB </p>
                <p> Free Memory: <%= request.getAttribute("usedMemory") %>MB </p>
                <p> Imported Indexes: <%= request.getAttribute("importedIndexes") %> </p>

            </div>

        </div>
    </body>
</html>