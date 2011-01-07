<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Please Login</title>
</head>
<body><h1>Please Login</h1>

<form method="post" action="j_spring_security_check">
    <label>Username:<input type="text" name="j_username"/></label><br>
    <label>Password:<input type="password" name="j_password"/></label><br>
    <label> <input type='checkbox' name='_spring_security_remember_me'/>Remember me on this computer. </label><br>
    <input type="submit" value="Login"/>
</form>
</body>
</html>
