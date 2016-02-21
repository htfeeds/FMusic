<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ include file="../taglib.jsp"%>

<tilesx:useAttribute name="current" />

<!-- TOP-BAR -->
<div id="top-bar">
	<div class="container">
		<div id="site-description">Welcome to FMUSIC site.</div>
		<ul id="top-links">
			<security:authorize access="! isAuthenticated()">
            	<li class="login"><a href="#" role="button" data-toggle="modal" data-target="#login-modal"><i class="fa fa-lock"></i><span>Login / Register</span></a></li>
			</security:authorize>
            <security:authorize access="isAuthenticated()">
            	<li class="show_user_box dropdown">
					<a href="#" class="link_user">
						<c:if test="${not empty loginModel.imageUrl}">
							<img class="avt" src="<c:url value="${loginModel.imageUrl}" />"><span>HTFeeds</span>
						</c:if>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#">Profile</a></li>
						<li><a href="#">Account</a></li>
						<li><a href="javascript:logout()">Log out</a></li>
					</ul>
				</li>
            </security:authorize>
			<li class="search">
				<input type="text" value="" name="search" id="top-search" />		
				<a href="#"></a>
			</li>
		</ul><!-- end top-links -->
	</div><!-- end container -->
</div> <!-- end top-bar -->

<header>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container">
			<!-- Logo and toggle for navigation on mobile devices -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#main-nav">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html"><img style="display:initial" src="<c:url value="/static/img/logo.png"/>" alt="FMUSIC" /></a>
			</div>

			<!-- Navigation links -->
			<div class="collapse navbar-collapse" id="main-nav">
				<ul class="nav navbar-nav navbar-right">
					<li class="${current == 'home' ? 'current' : ''}"><a href="index.html">Home</a></li>
					<li><a href="contact.html">Song</a></li>
					<li><a href="albums.html">Album</a></li>
					<li><a href="contact.html">Top 10</a></li>
					<li><a href="artists.html">Artist</a></li>
					<li><a href="contact.html">Contact Us</a></li>
				</ul>
			</div><!-- /.navbar-collapse -->
		</div>
	</nav>
</header>