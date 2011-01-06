<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Please Login</title>
</head>
<body><h1>Please Login</h1>

<form method="post" action="j_security_check">
    <p><label>Username:</label> <input type="text" name="j_username"/></p>
    <p><label>Password:</label> <input type="password" name="j_password"/></p>
    <input type="submit" value="Login"/>
</form>
</body>
</html>