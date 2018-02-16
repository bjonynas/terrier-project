<html>
    <head>
        <meta charset="UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" type="text/css" href="/interface/styles/import.css">
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

            <div id="import-body">
                <form name="importForm" method="post" action="importServlet">
                    Index path: <input type="text" name="indexPath" />
                    Index prefix: <input type="text" name="indexPrefix" />
                    Index name: <input type="text" name="indexName" />
                    <input type="submit" value="Import" />
                </form>
            </div>
        </div>
    </body>
</html>
