<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <fmt:requestEncoding value="utf-8" />
        <fmt:setLocale value="${sessionScope.locale}"/>
        <fmt:setBundle basename="label" var="lang"/>
        <title><fmt:message key="label.view" bundle="${lang}"/></title>
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/css/newslist.css" rel="stylesheet">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/bootstrap.file-input.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/script.js"></script>
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
                                <a href = "controller?action=viewNews&id=${news.id}&locale=ru">
                                    <fmt:message key="label.russian" bundle="${lang}"/>
                                </a>
                            </li>
                            <li <c:if test="${fn:contains(loc, 'en')}">class="active"</c:if>>
                                <a href = "controller?action=viewNews&id=${news.id}&locale=en">
                                    <fmt:message key="label.english" bundle="${lang}"/>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <div class="row page-content">
                <div class="nav-bar col-lg-3 col-md-3">

                    <h3>
                        <fmt:message key="label.newsmenu" bundle="${lang}"/>
                    </h3>

                    <ul class="nav nav-pills nav-stacked">
                        <li role="presentation">
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
                <div class="news-view col-lg-9 col-md-9 row">
                    <div class="author row">
                        <div class="col-lg-2 col-md-2"><fmt:message key="label.author" bundle="${lang}"/>:</div>
                        <div class="col-lg-10 col-md-10">
                            <c:out value="${news.author}" escapeXml="true"/>
                        </div>
                    </div>
                    <div class="date row">
                        <div class="col-lg-2 col-md-2"><fmt:message key="label.date" bundle="${lang}"/>:</div>
                        <div class="col-lg-10 col-md-10">
                            <fmt:formatDate type="date" value="${news.getUtilDate()}" />
                        </div>
                    </div>
                    <div class="title row">
                        <div class="col-lg-2 col-md-2"><fmt:message key="label.title" bundle="${lang}"/>:</div>
                        <div class="col-lg-10 col-md-10">
                            <c:out value="${news.title}" escapeXml="true"/>
                        </div>
                    </div>
                    <div class="content row">
                        <div class="col-lg-2 col-md-2"><fmt:message key="label.content" bundle="${lang}"/>:</div>
                        <div class="col-lg-10 col-md-10">
                            <c:out value="${news.content}" escapeXml="true"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-2 col-md-2"><fmt:message key="label.image" bundle="${lang}"/>:</div>
                        <div class="col-lg-10 col-md-10 newsImg"><img class="img-rounded" src="<c:out value="${news.imgUrl}" escapeXml="true"/>"></div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-lg-offset-3 col-md-offset-3">
                        <div class="row">
                            <form class="col-md-6 col-lg-6" action="controller" method="post">
                                <input type="hidden" value="delete" name="action">
                                <input type="hidden" value="${news.id}" name="ids">
                                <input class="btn btn-default" type="submit" value="<fmt:message key="label.delete" bundle="${lang}"/>">
                            </form>
                            <a class="col-md-6 col-lg-6 btn btn-default" role="button" href="controller?action=editNewsPage&id=${news.id}">
                                <fmt:message key="label.edit" bundle="${lang}"/>
                            </a>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>
