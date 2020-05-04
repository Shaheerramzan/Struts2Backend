<%--
  Created by IntelliJ IDEA.
  User: Shaheer
  Date: 4/16/2020
  Time: 4:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h1>Hello World From Struts2</h1>
  <form action = "donor.action" method="post">
    <label>
      Id <br/>
      <input type = "number" name = "Id"/>
    </label><br/>
    <label>
    password <br/>
    <input type = "password" name = "password"/>
  </label>
  <br/>
    <input type = "submit" value = "Login"/>
  </body>
</html>
