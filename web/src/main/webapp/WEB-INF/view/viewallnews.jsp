<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>News list</title>
    <c:url var="css" value="/css/newslist.css"/>
    <link href="${css}" rel="stylesheet">
</head>
<body>
<div class="wrapper">
    <div class="header"><h1>News manager</h1></div>
    <div class="nav-bar">
        <h3>News Menu</h3>
        <div>News list</div>
        <div><a href="controller?action=addNewsPage">Add news</a></div>
        <div><a href="authorization?action=logout">Logout</a></div>
    </div>
    <div class="news-list">
        <c:forEach var="news" items="${newsList}">
        <div class="item">
            <h2>${news.title}</h2>
            <div><span class="date">${news.date}</span><span class="author">${news.author}</span></div>
            <div class="item-content">
                <div class="newsImg"><img src="${news.imgUrl}"></div>
                <div class="newsContent">
                    <div>${news.content}
                    </div>
                </div>
                <form class="deleteButton" action="controller" method="get">
                    <input type="hidden" value="editNewsPage" name="action">
                    <input type="hidden" value="${news.id}" name="id">
                    <input type="submit" value="Edit">
                </form>
                <a href=""><button>Edit</button></a>
                <form class="deleteButton" action="controller" method="post">
                    <input type="hidden" value="delete" name="action">
                    <input type="hidden" value="${news.id}" name="id">
                    <input type="submit" value="Delete">
                </form>
            </div>
        </div>

        </c:forEach>
    </div>
</div>
</body>
</html>
