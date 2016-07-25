<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <fmt:requestEncoding value="utf-8" />
        <fmt:setLocale value="${sessionScope.locale}"/>
        <fmt:setBundle basename="label" var="lang"/>
        <title><fmt:message key="label.newslist" bundle="${lang}"/></title>

        <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/css/newslist.css" rel="stylesheet">

    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="controller?action=viewAll"><fmt:message key="label.management" bundle="${lang}"/></a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <c:set value="${sessionScope.locale}" var="loc"/>
                            <li <c:if test="${fn:contains(loc, 'ru')}">class="active"</c:if>>
                                <a href = "controller?action=viewAll&locale=ru">
                                    <fmt:message key="label.russian" bundle="${lang}"/>
                                </a>
                            </li>
                            <li <c:if test="${fn:contains(loc, 'en')}">class="active"</c:if>>
                                <a href = "controller?action=viewAll&locale=en">
                                    <fmt:message key="label.english" bundle="${lang}"/>
                                </a>
                            </li>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div><!--/.container-fluid -->
            </nav>

            <div class="row">
                <div class="nav-bar col-lg-3 col-md-3">

                    <h3>
                        <fmt:message key="label.newsmenu" bundle="${lang}"/>
                    </h3>

                    <ul class="nav nav-pills nav-stacked">
                        <li role="presentation" class="disabled active">
                            <a href="controller?action=viewAll">
                                <fmt:message key="label.newslist" bundle="${lang}"/>
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="controller?action=addNewsPage">
                                <fmt:message key="label.addnews" bundle="${lang}"/>
                            </a>
                        </li>
                    </ul>

                </div>
                <div class="news-list col-lg-9 col-md-9">
                    <form action="controller" method="post">
                        <c:forEach var="news" items="${newsList}">
                            <div class="item row">
                                <div class="col-lg-10 col-md-10">
                                    <h2 class="row">${news.title}</h2>
                                    <div class="row">
                                        <span class="date">
                                            <c:out value="${news.date}"/>
                                        </span>
                                        <span class="author">${news.author}</span>
                                    </div>
                                    <div class="item-content row">
                                        <div class="newsImg col-md-3 col-lg-3">
                                            <img src="${news.imgUrl}" class="img-rounded">
                                        </div>
                                        <div class="newsContent col-md-8 col-lg-8 col-md-offset-1 col-lg-offset-1">
                                            <div>${news.content}</div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-2 col-lg-2 manage">
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
                        <div class="row">
                            <input class="col-md-2 col-lg-2 col-lg-offset-10 col-md-offset-10 btn btn-primary"
                                   type="submit" value="<fmt:message key="label.delete" bundle="${lang}"/>">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
