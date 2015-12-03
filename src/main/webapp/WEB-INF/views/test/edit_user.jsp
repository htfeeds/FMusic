<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../shared/taglib.jsp"%>

<html>

<body>
	<form:form method="POST" modelAttribute="user">
		<form:hidden path="id" />
		<form:hidden path="password" />
		<form:input path="fullname" />
		<form:input path="username" />
		<form:input path="email" />
		<br />
		<form:input path="birthDate" />
		<form:input path="phoneNumber" />
		<form:input path="sex" />
		<br />
		<form:input path="imageUrl" />
		<form:input path="state" />
		<form:input path="roles" />
		<br />
		<input name="save" type="submit" value="Save">
		<input name="save" type="submit" value="Save and Continue">
	</form:form>
</body>

</html>