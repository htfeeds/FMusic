<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/logout" var="logoutUrl" />
<form action="${logoutUrl}" method="post" id="logout-form" style="display:none;">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<script>
	function logout() {
		$("#logout-form").submit();
	}
</script>