<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: Revotech
  Date: 20.06.16
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add news</title>
        <c:url var="css" value="/css/newslist.css"/>
        <link href="${css}" rel="stylesheet">
    </head>
    <body>
        <div class="nav-bar">
            <h3>News Menu</h3>
            <div><a href="controller?action=viewAll">News list</a></div>
            <div>Add news</div>
            <%--<div><a href="authorization?action=logout">Logout</a></div>--%>
        </div>
        <div class="news-add">
            <h2>${error}</h2>
            <form name="AddNewsForm" action="controller" method="post" enctype="multipart/form-data">
                <p class="author-input">Author: <input placeholder="Enter your nick name..." name="author"></p>
                <p class="date-input">Date: <input placeholder="Enter date..." type="date" name="date"></p>
                <p class="title-input">Title: <input placeholder="Enter title..." name="title"></p>
                <p class="content-input">Content: <input type="text" placeholder="Введите текст..." name="content">
                <p class="image-input">Image: <input type="file" name="image"></p>
                <input type="hidden" name="action" value="addNews"/>
                <p><input type="submit" value="Отправить"></p>
            </form>

        </div>
    </body>
</html>