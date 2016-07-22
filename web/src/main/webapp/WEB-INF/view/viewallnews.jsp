<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <fmt:requestEncoding value="utf-8" />
        <fmt:setLocale value="${sessionScope.locale}"/>
        <fmt:setBundle basename="label" var="lang"/>
        <title><fmt:message key="label.newslist" bundle="${lang}"/></title>

        <c:url var="css" value="/css/newslist.css"/>
        <link href="${css}" rel="stylesheet">
    </head>
    <body>
        <div class="wrapper">
            <header class="header">
                <div>
                    <fmt:message key="label.management" bundle="${lang}"/>
                </div>
                <div>
                    <a href = "controller?action=viewAll&locale=ru">
                        <fmt:message key="label.russian" bundle="${lang}"/>
                    </a>
                    <a href = "controller?action=viewAll&locale=en">
                        <fmt:message key="label.english" bundle="${lang}"/>
                    </a>
                </div>
            </header>
            <div class="nav-bar">
                <h3><fmt:message key="label.newsmenu" bundle="${lang}"/></h3>
                <div><fmt:message key="label.newslist" bundle="${lang}"/></div>
                <div><a href="controller?action=addNewsPage"><fmt:message key="label.addnews" bundle="${lang}"/></a></div>
                <%--<div><a href="authorization?action=logout">Logout</a></div>--%>
            </div>
            <div class="news-list">
                <form class="deleteButton" action="controller" method="post">
                    <c:forEach var="news" items="${newsList}">
                        <div class="item">
                            <h2>${news.title}</h2>
                            <div>
                                <span class="date">
                                    <c:out value="${news.date}"/>
                                </span>
                                <span class="author">${news.author}</span>
                            </div>
                            <div class="item-content">
                                <div class="newsImg"><img src="${news.imgUrl}"></div>
                                <div class="newsContent">
                                    <div>${news.content}</div>
                                </div>

                                <a class="editButton" href="controller?action=editNewsPage&id=${news.id}">
                                    <fmt:message key="label.edit" bundle="${lang}"/>
                                </a>
                                <a class="viewButton" href="controller?action=viewNews&id=${news.id}">
                                    <fmt:message key="label.view" bundle="${lang}"/>
                                </a>

                                <input type="checkbox" name="ids" value="${news.id}">
                            </div>
                        </div>
                    </c:forEach>
                    <input type="hidden" value="delete" name="action">
                    <input type="submit" value="<fmt:message key="label.delete" bundle="${lang}"/>">
                </form>
            </div>
        </div>
    </body>
</html>
