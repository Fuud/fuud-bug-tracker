<%@ taglib prefix="menu" uri="http://struts-menu.sf.net/tag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new user</title>
</head>

<body>
<menu:useMenuDisplayer name="TabbedMenuDisplayer" >
    <menu:displayMenu name="Users Operations"/>
    <menu:displayMenu name="Projects Operations"/>
</menu:useMenuDisplayer>

<h1>Create new user</h1>
<%
    String message = (String) pageContext.findAttribute("message");
    if (message != null && !message.trim().isEmpty()) {
        out.println(message + "<br>");
    }
%>

<form method="post" action="">
    <table>
        <tr>
            <td>
                <label> Username:
                    <input type="text" name="username"/>
                </label>
            </td>
        </tr>
        <tr>
            <td>
                <label> Password:
                    <input type="text" name="password"/>
                </label>
            </td>
        </tr>
        <tr>
            <td>
                <label>
                    <input type="submit"/>
                </label>
            </td>
        </tr>
    </table>
</form>
</body>
</html>