<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<tiles:importAttribute name="stylesheets" />
<tiles:importAttribute name="javascripts" />

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="description" content="EProject Sem 4 - Softech Aptech, Da Nang">
    <meta name="author" content="HTFeeds">

    <title>
        <tiles:getAsString name="title" />
    </title>

    <c:forEach var="css" items="${stylesheets}">
        <link rel="stylesheet" type="text/css" href="<c:url value=" ${css} "/>">
    </c:forEach>

    <script src="<c:url value="/static/js/jquery-2.1.1.js"/>"></script>
    <script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>

</head>

<body class="<tiles:getAsString name="bodyClass" />">

    <tiles:insertAttribute name="header" />

    <tiles:insertAttribute name="body" />

    <tiles:insertAttribute name="footer" />

    <security:authorize access="! isAuthenticated()">
        <%@ include file="../login_form.jsp"%>
    </security:authorize>

    <security:authorize access="isAuthenticated()">
        <%@ include file="../logout_form.jsp"%>
    </security:authorize>

    <c:forEach var="script" items="${javascripts}">
        <script src="<c:url value="${script}"/>"></script>
    </c:forEach>

    <script>
        $(document).ready(function() {
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
        });
    </script>

</body>

</html>