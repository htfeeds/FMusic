<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../shared/taglib.jsp"%>

<div class="middle-box text-center loginscreen   animated fadeInDown">
    <div>
        <h3>Register to FMusic</h3>
        <p>Create account to see it in action.</p>

        <form:form method="POST" modelAttribute="user" class="m-t" id="registerForm">
            <form:input type="hidden" path="id" />
            <div class="form-group">
                <form:input type="text" path="username" class="form-control" placeholder="Username" />
                <form:errors path="username" cssClass="error" />
            </div>
            <div class="form-group">
                <form:input type="text" path="fullname" class="form-control" placeholder="Full name" />
                <form:errors path="fullname" cssClass="error" />
            </div>
            <div class="form-group">
                <form:input type="email" path="email" class="form-control" placeholder="Email" />
                <form:errors path="email" cssClass="error" />
            </div>
            <div class="form-group">
                <form:input type="password" path="password" class="form-control" placeholder="Password" />
                <form:errors path="password" cssClass="error" />
            </div>
            <div class="form-group">
                <input type="password" id="repeat_password" name="repeat_password" class="form-control" placeholder="Re-enter Password">
            </div>

            <p class="text-muted text-center">
                <small>By clicking Sign Up, you agree to our Terms and that you have read our Data Policy, including our
					Cookie Use.</small>
            </p>
            <button type="submit" class="btn btn-primary block full-width m-b">Sign Up</button>

            <p class="text-muted text-center">
                <small>Already have an account?</small>
            </p>
            <a class="btn btn-sm btn-white btn-block" href='<spring:url value="/login"/>'>Login</a>
        </form:form>
        <p class="m-t">
            <small>Sign in to enjoy more features.</small>
        </p>
    </div>
</div>

<script>
    $(document).ready(function() {
        $("#registerForm").validate({
            rules: {
                fullname: {
                    required: true,
                    minlength: 6,
                },
                username: {
                    required: true,
                    minlength: 6,
                    remote: {
                        url: "<spring:url value='/rest/available/username' />",
                        type: "get",
                        data: {
                            username: function() {
                                return $("#username").val();
                            },
                            id: function() {
                                return $("#id").val();
                            }
                        }
                    }
                },
                password: {
                    required: true,
                    minlength: 6
                },
                repeat_password: {
                	required: true,
                	minlength: 6,
                	equalTo : password
                },
                email: {
                    required: true,
                    email: true,
                    remote: {
                        url: "<spring:url value='/rest/available/email' />",
                        type: "get",
                        data: {
                            username: function() {
                                return $("#email").val();
                            },
                            id: function() {
                                return $("#id").val();
                            }
                        }
                    }
                }
            }
        });
    });
</script>