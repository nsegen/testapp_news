<%--
  Created by IntelliJ IDEA.
  User: Revotech
  Date: 23.06.16
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>login</title>
</head>
<body>
    <a href="authorization?action=siguppage">Регистрация</a>
    <form name="AuthorizationForm" action="authorization" method="post">
        <p class="author-input">Nickname: <input placeholder="Enter your nickname..." name="nickname"></p>
        <p class="date-input">Password: <input placeholder="Enter password..." name="password"></p>
        <input type="hidden" name="action" value="signin"/>
        <p><input type="submit" value="Отправить"></p>
    </form>
</body>
</html>
