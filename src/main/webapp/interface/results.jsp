<html>
    <head>
        <meta charset="UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" type="text/css" href="/interface/styles/results.css">
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

            <div id="results-container">
                <h3>
                    <%= request.getAttribute("queryString") %>
                </h3>

                <% if(request.getAttribute("r") == null) { %>
                    <code id="results" style="white-space: pre-wrap;"> <%= request.getAttribute("results") %> </code>
                <% } else{ %>
                    <p> Error: <%= request.getAttribute("r") %> </p>
                <% } %>
            </div>
            <a href="/index/<%= request.getAttribute("indexName") %>" class="w3-bar-item w3-button" id="back-button" style="float: left;"> Back </a>
        </div>
    </body>
</html>
