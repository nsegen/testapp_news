<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>login</title>
</head>
<body>
    <a href="authorization?action=signuppage">Регистрация</a>
    <h2 class="error">${action}</h2>
    <form name="AuthorizationForm" action="authorization" method="post">
        <p class="author-input">Nickname: <input placeholder="Enter your nickname..." name="nickname"></p>
        <p class="date-input">Password: <input placeholder="Enter password..." name="password"></p>
        <input type="hidden" name="action" value="signin"/>
        <p><input type="submit" value="Отправить"></p>
    </form>
</body>
</html>
