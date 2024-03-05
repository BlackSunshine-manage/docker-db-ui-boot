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
    </tr>
    <#list model["employeeList"] as employee>
      <tr>
        <td>${employee.id}</td>
      </tr>
    </#list>
  </table>
</div>
</head>