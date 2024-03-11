<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8"/>
<link rel="stylesheet" type="text/css" href="/css/styles.css"/>
<div id="header">
  <h2>Procedure adding new employee ~</h2>
</div>
</head>
<body>
<div id="content">
  <div>
       <nobr>
              <a href="/api/v2/employees/add">Add new Employee</a>
       </nobr>
  </div>
  <br/>
  <table class="datatable" id="datatable">
    <tr>
      <th class=datatable-column>Id</th>
      <th class=datatable-column>Name</th>
      <th class=datatable-column>Surname</th>
      <th class=datatable-column>Age</th>
      <th class=datatable-column>Manage record</th>
    </tr>
    <#list model["employeeList"] as employee>
      <tr>
        <td class=datatable-row>${employee.id}</td>
        <td class=datatable-row>${employee.profile.firstName}</td>
        <td class=datatable-row>${employee.profile.surname}</td>
        <td class=datatable-row>${employee.profile.age}</td>
        <td class=datatable-row>
             <form action="" method="delete">
                 <input type="submit" name="upvote" value="Upvote" />
             </form>
        <a href="${'/api/v2/employees/' + employee.id + '/edit'}">Edit</a> ▒║▒
        <a href="${'/api/v2/employees/' + employee.id + '/delete'}">Delete</a> </td>
      </tr>
    </#list>
  </table>
</div>
</body>
</html>