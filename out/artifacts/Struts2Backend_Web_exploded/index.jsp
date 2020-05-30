<%--
  Created by IntelliJ IDEA.
  User: Shaheer
  Date: 4/16/2020
  Time: 4:22 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h1>Hello World From Struts2</h1>
    <form action = "login.action" method="post">
    <label>
      Id <br/>
      <input type = "text" name = "Username"/>
    </label><br/>
    <label>
    password <br/>
    <input type = "password" name = "password"/>
    </label>
    <br/>
      <label>
        Role<br/>
        <input type="number" value="1" name="Role"/>
      </label>
      <input type = "submit" value = "Login"/>
    </form>
  </body>
</html>
