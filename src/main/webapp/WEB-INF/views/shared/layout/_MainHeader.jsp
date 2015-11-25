<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ include file="../taglib.jsp"%>

<tilesx:useAttribute name="current" />

<div class="navbar-wrapper">
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar">
                    <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href='<spring:url value="/"/>'>FMUSIC</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="${current == 'home' ? 'active' : ''}"><a href='<spring:url value="/"/>'>Home</a></li>
                    <li class="${current == 'songs' ? 'active' : ''}"><a href='<spring:url value="/"/>'>Songs</a></li>
                    <li class="${current == 'albums' ? 'active' : ''}"><a href='<spring:url value="/"/>'>Albums</a></li>
                    <li class="${current == 'bxh' ? 'active' : ''}"><a href='<spring:url value="/"/>'>BXH</a></li>
                    <li><a href='<spring:url value="/"/>'>Search</a></li>
                    <security:authorize access="! isAuthenticated()">
                        <li><a data-toggle="modal" href="#login-form">Sign In/Up</a></li>
                    </security:authorize>
                    <security:authorize access="isAuthenticated()">
                        <li class="${current == 'account' ? 'active' : ''}">
                            <a href="#">
                                <c:out value="${loginModel.fullname}" />
                            </a>
                        </li>
                    </security:authorize>
                </ul>
            </div>
        </div>
    </nav>
</div>