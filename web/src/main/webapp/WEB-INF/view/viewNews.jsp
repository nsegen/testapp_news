<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <fmt:requestEncoding value="utf-8" />
        <fmt:setLocale value="${sessionScope.locale}"/>
        <fmt:setBundle basename="label" var="lang"/>
        <title><fmt:message key="label.view" bundle="${lang}"/></title>
        <c:url var="css" value="/css/newslist.css"/>
        <link href="${css}" rel="stylesheet">
    </head>
    <body>
        <header class="header">
            <div>
                <fmt:message key="label.management" bundle="${lang}"/>
            </div>
            <div>
                <a href = "controller?action=viewNews&id=${news.id}&locale=ru">
                    <fmt:message key="label.russian" bundle="${lang}"/>
                </a>
                <a href = "controller?action=viewNews&id=${news.id}&locale=en">
                    <fmt:message key="label.english" bundle="${lang}"/>
                </a>
            </div>
        </header>
        <div class="nav-bar">
            <h3><fmt:message key="label.newsmenu" bundle="${lang}"/></h3>
            <div><a href="controller?action=viewAll"><fmt:message key="label.newslist" bundle="${lang}"/></a></div>
            <div><a href="controller?action=addNewsPage"><fmt:message key="label.addnews" bundle="${lang}"/></a></div>
            <%--<div><a href="authorization?action=logout">Logout</a></div>--%>
        </div>
        <div class="news-add">
            <div class="author">
                <fmt:message key="label.author" bundle="${lang}"/>:
                <div>${news.author}</div>
            </div>
            <div class="date">
                <fmt:message key="label.date" bundle="${lang}"/>:
                <div><fmt:formatDate value="${news.date}"/></div>
            </div>
            <div class="title">
                <fmt:message key="label.title" bundle="${lang}"/>:
                <div>${news.title}</div>
            </div>
            <div class="content">
                <fmt:message key="label.content" bundle="${lang}"/>:
                <div>${news.content}</div>
            </div>
            <div class="image">Image: <img src="${news.imgUrl}"></div>
        </div>
        <form class="deleteButton" action="controller" method="post">
            <input type="hidden" value="delete" name="action">
            <input type="hidden" value="${news.id}" name="ids">
            <input type="submit" value="<fmt:message key="label.delete" bundle="${lang}"/>">
        </form>
        <a class="editButton" href="controller?action=editNewsPage&id=${news.id}">
            <button>
                <fmt:message key="label.edit" bundle="${lang}"/>
            </button>
        </a>
    </body>
</html>
