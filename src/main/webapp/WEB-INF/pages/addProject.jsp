<%@ taglib prefix="menu" uri="http://struts-menu.sf.net/tag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new project</title>
</head>

<body>
<menu:useMenuDisplayer name="TabbedMenuDisplayer" >
    <menu:displayMenu name="Users Operations"/>
    <menu:displayMenu name="Projects Operations"/>
</menu:useMenuDisplayer>

<h1>Create new project</h1>
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
                <label> Title:
                    <input type="text" name="title"/>
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