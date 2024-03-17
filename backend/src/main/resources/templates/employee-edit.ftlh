<!DOCTYPE HTML>

<html>
    <head>
        <meta charset="UTF-8" />
          <title>
            <#if add>Create a Note
            <#else>Edit a Note</#if>
          </title>
        <link rel="stylesheet" type="text/css" href="/css/style.css"/>
    </head>

    <body>
        <h1>
          <#if add>Create a Employee:
          <#else>Edit a Employee:</#if>
        </h1>
        <a href="/api/v2/employees/all">Back to Employees List</a>
        <br/><br/>
        <#if add>
            <#assign urlAction>/api/v2/employees/add</#assign>
            <#assign submitTitle>Create</#assign>
        <#else>
            <#assign urlAction>${'/api/v2/employees/' + employee.id + '/edit'}</#assign>
            <#assign submitTitle>Edit</#assign>
        </#if>
        <form action="${urlAction}" name="employee" method="POST">
            <table border="0">
                <#if employee.id??>
                <tr>
                    <td>ID</td>
                    <td>:</td>
                    <td>${employee.id}</td>
                </tr>
                </#if>
                <tr>
                    <td>First name</td>
                    <td>:</td>
                    <td><input type="text" name="employee.firstName" value="${(employee.firstName)!''}"/></td>
                </tr>
                <tr>
                    <td>Surname</td>
                    <td>:</td>
                    <td><input type="text" name="employee.surname" value="${(employee.surname)!''}" /></td>
                </tr>
                <tr>
                     <td>Age</td>
                     <td>:</td>
                     <td><input type="text" name="employee.age" value="${(employee.age)!''}" /></td>
                </tr>
            </table>
            <input type="submit" value="${submitTitle}" />
        </form>

        <br/>
        <!-- Check if errorMessage is not null and not empty -->
        <#if errorMessage?has_content>
            <div class="error">${errorMessage}</div>
        </#if>
    </body>
</html>