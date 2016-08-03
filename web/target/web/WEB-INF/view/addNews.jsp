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
                                <c:url value="controller" var = "ruUrl">
                                    <c:param name="action" value="${param.action}"/>
                                    <c:param name="prevPage" value="${param.prevPage}"/>
                                    <c:param name="locale" value="ru"/>
                                    <c:param name="id" value="${param.id}"/>
                                </c:url>
                                <a href = "${ruUrl}">
                                    <fmt:message key="label.russian" bundle="${lang}"/>
                                </a>
                            </li>
                            <li <c:if test="${fn:contains(loc, 'en')}">class="active"</c:if>>
                                <c:url value="controller" var = "enUrl">
                                    <c:param name="action" value="${param.action}"/>
                                    <c:param name="prevPage" value="${param.prevPage}"/>
                                    <c:param name="locale" value="en"/>
                                    <c:param name="id" value="${param.id}"/>
                                </c:url>
                                <a href = "${enUrl}">
                                    <fmt:message key="label.english" bundle="${lang}"/>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

            <div class="row">
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
                        <li role="presentation" class="disabled active">
                            <a href="controller?action=addNewsPage">
                                <fmt:message key="label.addnews" bundle="${lang}"/>
                            </a>
                        </li>
                    </ul>

                </div>

                <div class="news-add col-lg-5 col-md-5 col-lg-offset-2 col-md-offset-2 row">
                    <c:if test="${not empty error}" >
                        <h2 class="bg-danger">
                            <fmt:message key="error.${error}" bundle="${lang}"/>
                        </h2>
                    </c:if>
                    <form name="AddNewsForm" action="controller" method="post" enctype="multipart/form-data">

                        <div class="form-group">
                            <label for="inputAuthor"><fmt:message key="label.author" bundle="${lang}"/>:</label>
                            <input type="text" name="author" required placeholder="<fmt:message key="input.enternickname" bundle="${lang}"/>"
                                   value="${news.author}" class="form-control" id="inputAuthor">
                        </div>

                        <div class="form-group">
                            <label for="inputDate"><fmt:message key="label.date" bundle="${lang}"/>:</label>
                            <input type="date" required name="date" value="${news.date}" class="form-control" id="inputDate">
                        </div>

                        <div class="form-group">
                            <label for="inputTitle"><fmt:message key="label.title" bundle="${lang}"/>:</label>
                            <input type="text" required name="title" placeholder="<fmt:message key="input.entertitle" bundle="${lang}"/>"
                                   value="${news.title}" class="form-control" id="inputTitle">
                        </div>

                        <div class="form-group">
                            <label for="inputContent"><fmt:message key="label.content" bundle="${lang}"/>:</label>
                            <textarea class="form-control" rows="10" required placeholder="<fmt:message key="input.entercontent" bundle="${lang}"/>"
                                     name="content" id="inputContent"><c:out value="${news.content}" escapeXml="true"/></textarea>
                        </div>

                        <div class="image-input">
                            <label for="inputImage"><fmt:message key="label.image" bundle="${lang}"/>:</label>
                            <input type="file" name="image"
                                   <c:if test="${param.action == 'addNewsPage'}">required</c:if>
                                   class="btn btn-default" accept="image/*" title="<fmt:message key="input.enterimage" bundle="${lang}"/>" id="inputImage" />
                            <output id="list"></output>
                            <c:if test="${not empty news.imgUrl}">
                                <div class="row newsImg">
                                    <img id="chosenImg" class="img-rounded" src="${news.imgUrl}"/>
                                </div>
                            </c:if>
                        </div>

                        <div class="col-md-6 col-lg-6 col-lg-offset-3 col-md-offset-3">
                            <div class="row">

                                <input type="hidden" name="id" value="${id}"/>
                                <input type="hidden" name="action" value="${nextAction}"/>
                                <input class="col-md-4 col-lg-4 btn btn-default" type="submit" value="<fmt:message key="label.save" bundle="${lang}"/>">

                                <a class="col-md-4 col-lg-4 col-md-2 col-lg-offset-2 btn btn-default" role="button">
                                    <fmt:message key="label.cancel" bundle="${lang}"/>
                                </a>

                            </div>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.file-input.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/script.js"></script>
</html>