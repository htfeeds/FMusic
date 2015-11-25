<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>

<div id="login-form" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-6 b-r">
                        <h3 class="m-t-none m-b">Sign in</h3>
                        <p>Sign in today for more expirience.</p>
                        <c:url var="loginUrl" value="/login" />
                        <form role="form" action="${loginUrl}" method="post">
                            <c:if test="${param.error != null}">
                                <p>Invalid username and password.</p>
                            </c:if>
                            <c:if test="${param.logout != null}">
                                <p>You have been logged out.</p>
                            </c:if>
                            <div class="form-group">
                                <label>Username</label>
                                <input type="text" name="username" class="form-control" placeholder="Username" required>
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input type="password" name="password" class="form-control" placeholder="Password" required>
                            </div>
                            <div>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <button class="btn btn-sm btn-primary pull-right m-t-n-xs" type="submit">
                                    <strong>Log in</strong>
                                </button>
                                <label>
                                    <input type="checkbox" name="remember-me" class="i-checks"> Remember me </label>
                            </div>
                        </form>
                    </div>
                    <div class="col-sm-6">
                        <h4>Not a member?</h4>
                        <p>You can create an account:</p>
                        <p class="text-center">
                            <a href='<spring:url value="/register"/>'><i class="fa fa-sign-in big-icon"></i></a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>