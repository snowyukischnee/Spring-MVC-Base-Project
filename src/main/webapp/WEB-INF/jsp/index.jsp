<%--
  Created by IntelliJ IDEA.
  User: TuanNH
  Date: 6/12/2018
  Time: 3:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="true"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Welcome ${pageContext.request.userPrincipal.name}
    <form method="POST" action="upload" enctype="multipart/form-data">
        Upload<input type="file" name="files" />
        <input type="submit" value="Submit" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
    <br>
    <form action="logout" method="POST">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="submit" name="submit" value="logout"/>
    </form>
    Changepass
    <form action="changepass" method='POST'>
        User:<input type='text' name='username' value=''>
        Password:<input type='password' name='password' />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input name="submit" type="submit" value="submit" />
    </form>
</body>
</html>
