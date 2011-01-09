<%@ page import="com.blogspot.fuud.java.bugtracker.domain.User" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="menu" uri="http://struts-menu.sf.net/tag" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users list</title>
</head>

<body>
<menu:useMenuDisplayer name="TabbedMenuDisplayer">
    <menu:displayMenu name="Users Operations"/>
</menu:useMenuDisplayer>

<h1>Users list</h1>

<display:table name="usersList" export="true">
  <display:column sortable="true" property="login" title="Username"/>
  <display:column property="password"/>
</display:table>
</body>
</html>