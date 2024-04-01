<!DOCTYPE html>
<html>
<head>
    <title>Repository Report</title>
</head>
<body>
    <h1>Repository Report</h1>

    <h2>Repository Path:</h2>
    <p>${repositoryPath}</p>

    <h2>Repository Content:</h2>
    <ul>
        <#list repositoryContent as folderName>
            <li>${folderName}</li>
        </#list>
    </ul>
</body>
</html>
