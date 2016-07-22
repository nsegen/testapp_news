<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <fmt:requestEncoding value="utf-8" />
        <fmt:setLocale value="${sessionScope.locale}"/>
        <fmt:setBundle basename="label" var="lang"/>
        <title><fmt:message key="label.edit" bundle="${lang}"/></title>
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
                    <a href = "controller?action=addNewsPage&locale=ru">
                        <fmt:message key="label.russian" bundle="${lang}"/>
                    </a>
                    <a href = "controller?action=addNewsPage&locale=en">
                        <fmt:message key="label.english" bundle="${lang}"/>
                    </a>
                </div>
            </header>
            <div class="nav-bar">
                <h3><fmt:message key="label.newsmenu" bundle="${lang}"/></h3>
                <div><a href="controller?action=viewAll"><fmt:message key="label.newslist" bundle="${lang}"/></a></div>
                <div><fmt:message key="label.addnews" bundle="${lang}"/></div>
                <%--<div><a href="authorization?action=logout">Logout</a></div>--%>
            </div>
            <div class="news-add">
                <c:if test="${not empty error}" >
                    <h2>
                        <fmt:message key="error.${error}" bundle="${lang}"/>
                    </h2>
                </c:if>
                <form name="AddNewsForm" action="controller" method="post" enctype="multipart/form-data">
                    <p class="author-input">
                        <fmt:message key="label.author" bundle="${lang}"/>:
                        <input placeholder="<fmt:message key="input.enternickname" bundle="${lang}"/>" name="author">
                    </p>
                    <p class="date-input">
                        <fmt:message key="label.date" bundle="${lang}"/>:
                        <input type="date" name="date">
                    </p>
                    <p class="title-input">
                        <fmt:message key="label.title" bundle="${lang}"/>:
                        <input placeholder="<fmt:message key="input.entertitle" bundle="${lang}"/>" name="title">
                    </p>
                    <p class="content-input">
                        <fmt:message key="label.content" bundle="${lang}"/>:
                        <input type="text" placeholder="<fmt:message key="input.entercontent" bundle="${lang}"/>" name="content">
                    </p>
                    <p class="image-input">
                        <fmt:message key="label.image" bundle="${lang}"/>:
                        <input type="file" name="image">
                    </p>
                    <input type="hidden" name="id" value="${id}"/>
                    <input type="hidden" name="action" value="${nextAction}"/>
                    <p>
                        <input type="submit" value="<fmt:message key="label.save" bundle="${lang}"/>">
                    </p>
                </form>
                <a href="controller?action=viewAll">
                    <button>
                        <fmt:message key="label.cancel" bundle="${lang}"/>
                    </button>
                </a>

            </div>
        </div>
    </body>
</html>