<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>

<security:authorize access="isAuthenticated()">
   <li class="show_user_box dropdown">
      <a href="#" data-toggle="dropdown" class="link_user">
         <c:if test="${not empty loginModel.imageUrl}">
            <img class="avt" src="<c:url value="${loginModel.imageUrl}" />">
         </c:if>
         <c:if test="${empty loginModel.imageUrl}">
            <img class="avt" src="<c:url value="/static/img/user/empty_avatar.jpg" />">
         </c:if>
		 <span>${loginModel.fullname}</span>
      </a>
      <ul class="dropdown-menu animated fadeInRight">
         <li><a href="#">Profile</a></li>
         <li><a href="#">Account</a></li>
         <li><a href="javascript:logout()">Log out</a></li>
      </ul>
   </li>
</security:authorize>