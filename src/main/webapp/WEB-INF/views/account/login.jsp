<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="loginColumns animated fadeInDown">
    <div class="row">

        <div class="col-md-6">
            <h2 class="font-bold">Welcome to FMusic</h2>

            <p>Perfectly designed and precisely prepared admin theme with over 50 pages with extra new web app views.</p>

            <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.</p>

            <p>When an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>

            <p>
                <small>It has survived not only five centuries, but also the leap into electronic typesetting, remaining
					essentially unchanged.</small>
            </p>

        </div>
        <div class="col-md-6">
            <div class="ibox-content">
                <c:url var="loginUrl" value="/login" />
                <form class="m-t" role="form" action="${loginUrl}" method="post">
                    <c:if test="${param.error != null}">
                        <p>Invalid username and password.</p>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <p>You have been logged out.</p>
                    </c:if>
                    <div class="form-group">
                        <input type="text" name="username" class="form-control" placeholder="Username" required>
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" class="form-control" placeholder="Password" required>
                    </div>
                    <div class="form-group">
                        <div class="checkbox i-checks">
                            <label style="padding: 0">
                                <input type="checkbox" name="remember-me"><i></i> Remember me </label>
                        </div>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <button type="submit" class="btn btn-primary block full-width m-b">Login</button>

                    <a href="#"> <small>Forgot password?</small>
                    </a>

                    <p class="text-muted text-center">
                        <small>Do not have an account?</small>
                    </p>
                    <a class="btn btn-sm btn-white btn-block" href="register.html">Create an account</a>
                </form>
                <p class="m-t">
                    <small>Register from today to get more fun on FMusic with Free VIP.</small>
                </p>
            </div>
        </div>
    </div>
    <hr />
    <div class="row">
        <div class="col-md-6">FMusic</div>
        <div class="col-md-6 text-right">
            <small>© 2015-2016</small>
        </div>
    </div>
</div>