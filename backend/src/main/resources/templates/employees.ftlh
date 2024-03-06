<!DOCTYPE HTML>
<html>
<head>
<div id="header">
  <h2>Procedure adding new employee ~</h2>
</div>
<div id="content">
  <br/>
  <table class="datatable">
    <tr>
      <th>Id</th>
      <th>Name</th>
      <th>Surname</th>
      <th>Age</th>
    </tr>
    <#list model["employeeList"] as employee>
      <tr>
        <td>${employee.id}</td>
        <td>${employee.profile.firstName}</td>
        <td>${employee.profile.surname}</td>
        <td>${employee.profile.age}</td>
      </tr>
    </#list>
  </table>
</div>
</head>