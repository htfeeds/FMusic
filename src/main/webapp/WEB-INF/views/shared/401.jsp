<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../shared/taglib.jsp"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="icon" type="image/png" href="<c:url value="/static/favicon.png" />" />

	<title>401 Error | FMusic</title>
    
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/font-awesome/css/font-awesome.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/animate.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/style.css"/>">

</head>

<body class="gray-bg">

    <div class="middle-box text-center animated fadeInDown">
        <h1>401</h1>
        <h3 class="font-bold">Access Denied</h3>

        <div class="error-desc">
            You are not authorized to access this page.<br/>
            You can go back to main page: <br/><a href="<c:url value="/" />" class="btn btn-primary m-t">Homepage</a>
        </div>
    </div>

</body>

</html>