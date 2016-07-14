<%--
  Created by IntelliJ IDEA.
  User: Revotech
  Date: 14.07.16
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
</head>
<body>
    <a href="authorization?action=signinpage">Вход</a>
    <form name="AuthorizationForm" action="authorization" method="post">
        <p class="author-input">Nickname: <input placeholder="Enter your nickname..." name="nickname"></p>
        <p class="date-input">Password: <input placeholder="Enter password..." name="password"></p>
        <p class="author-input">First Name: <input placeholder="Enter your first name..." name="firstName"></p>
        <p class="date-input">Last Name: <input placeholder="Enter yor last name..." name="lastName"></p>
        <input type="hidden" name="action" value="signup"/>
        <p><input type="submit" value="Отправить"></p>
    </form>
</body>
</html>
