<%@ taglib prefix="menu" uri="http://struts-menu.sf.net/tag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Create new user</title>
    </head>

    <body>
        <menu:useMenuDisplayer name="TabbedMenuDisplayer" permissions="rolesAdapter">
            <menu:displayMenu name="Users Operations"/>
            <menu:displayMenu name="Projects Operations"/>
        </menu:useMenuDisplayer>

        <sec:authorize access="isAnonymous()">
            <c:redirect url="login.jsp"/>
        </sec:authorize>
    </body>
</html>