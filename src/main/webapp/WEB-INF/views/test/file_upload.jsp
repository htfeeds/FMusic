<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../shared/taglib.jsp"%>

<html>

<body>
    <form:form method="POST" modelAttribute="user" enctype="multipart/form-data">
        <input type="file" id="file" name="file">
        <br />
        <input type="submit" value="Upload">
    </form:form>
</body>

</html>