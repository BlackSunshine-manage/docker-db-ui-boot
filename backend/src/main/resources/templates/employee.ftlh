<!DOCTYPE HTML>

<html>
    <head>
        <meta charset="UTF-8" />
        <title>View Employee</title>
        <link rel="stylesheet" type="text/css" href="/css/style.css"/>
    </head>

    <body>
        <h1>View Employee</h1>
        <a href="/api/v2/employees/all">Back to Employees</a>
        <br/><br/>
        <#if employee??>
            <table border="0">
                <tr>
                    <td>ID</td>
                    <td>:</td>
                    <td>${employee.id}</td>
                </tr>
                <tr>
                    <td>First name</td>
                    <td>:</td>
                    <td>${employee.firstName}</td>
                </tr>
                <tr>
                    <td>Surname</td>
                    <td>:</td>
                    <td>${employee.surname}</td>
                </tr>
                <tr>
                    <td>Age</td>
                    <td>:</td>
                    <td>${employee.age}</td>
                </tr>
            </table>
            <br/><br/>
            <#if allowDelete>
                <form action="${'/api/v2/employees/' + employee.id + '/delete'}" method="POST">
                    Delete this note? <input type="submit" value="Yes" />
                </form>
            <#else>
                <div>
                    <a href="${'/api/v2/employees/' + employee.id + '/edit'}">Edit</a> |
                    <a href="${'/api/v2/employees/' + employee.id + '/delete'}">Delete</a>
                </div>
            </#if>
        </#if>
        <#if errorMessage?has_content>
            <div class="error">${errorMessage}</div>
        </#if>
    </body>
</html>