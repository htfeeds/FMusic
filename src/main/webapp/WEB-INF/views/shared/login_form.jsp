<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>

<!-- BEGIN # MODAL LOGIN -->
<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header" align="center">
            <img id="img_logo" src="<c:url value="/static/img/logo.png"/>">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <i class="fa fa-times"></i>
            </button>
         </div>
         <!-- Begin # DIV Form -->
         <div id="div-forms">
            <!-- Begin # Login Form -->
            <c:url var="loginUrl" value="/login" />
            <form id="login-form" action="${loginUrl}" method="post">
               <div class="modal-body">
                  <div id="div-login-msg">
                     <div id="icon-login-msg"><i class="fa fa-chevron-right"></i></div>
                     <security:authorize access="! isAuthenticated()">
	                     <span id="text-login-msg">Type your username and password.</span>                     
                     </security:authorize>
                     <c:if test="${param.error != null}">
                         <span>Invalid username and password.</span>
                     </c:if>
                     <c:if test="${param.logout != null}">
                         <span>You have been logged out.</span>
                     </c:if>
                  </div>
                  <input id="login_username" name="username" class="form-control" type="text" placeholder="Username (type ERROR for error effect)" required>
                  <input id="login_password" name="password" class="form-control" type="password" placeholder="Password" required>
                  <div class="checkbox">
                     <label><input type="checkbox" name="remember-me"> Remember me</label>
                  </div>
               </div>
               <div class="modal-footer">
                  <div>
                  	 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                     <button id="login_btn" type="submit" class="btn btn-primary btn-lg btn-block">Login</button>
                  </div>
                  <div>
                     <button id="login_lost_btn" type="button" class="btn btn-link">Lost Password?</button>
                     <button id="login_register_btn" type="button" class="btn btn-link">Register</button>
                  </div>
               </div>
            </form>
            <!-- End # Login Form -->
            <!-- Begin | Lost Password Form -->
            <form id="lost-form" style="display:none;">
               <div class="modal-body">
                  <div id="div-lost-msg">
                     <div id="icon-login-msg"><i class="fa fa-chevron-right"></i></div>
                     <span id="text-lost-msg">Type your e-mail.</span>
                  </div>
                  <input id="lost_email" class="form-control" type="text" placeholder="E-Mail (type ERROR for error effect)" required>
               </div>
               <div class="modal-footer">
                  <div>
                     <button id="send_btn" type="submit" class="btn btn-primary btn-lg btn-block">Send</button>
                  </div>
                  <div>
                     <button id="lost_login_btn" type="button" class="btn btn-link">Log In</button>
                     <button id="lost_register_btn" type="button" class="btn btn-link">Register</button>
                  </div>
               </div>
            </form>
            <!-- End | Lost Password Form -->
            <!-- Begin | Register Form -->
            <form id="register-form" style="display:none;">
               <div class="modal-body">
                  <div id="div-register-msg">
                     <div id="icon-login-msg"><i class="fa fa-chevron-right"></i></div>
                     <span id="text-register-msg">Register an account.</span>
                  </div>
                  <input id="register_username" class="form-control" type="text" placeholder="Username (type ERROR for error effect)" required>
                  <input id="register_email" class="form-control" type="text" placeholder="E-Mail" required>
                  <input id="register_password" class="form-control" type="password" placeholder="Password" required>
               </div>
               <div class="modal-footer">
                  <div>
                     <button id="register_login" type="submit" class="btn btn-primary btn-lg btn-block">Register</button>
                  </div>
                  <div>
                     <button id="register_login_btn" type="button" class="btn btn-link">Log In</button>
                     <button id="register_lost_btn" type="button" class="btn btn-link">Lost Password?</button>
                  </div>
               </div>
            </form>
            <!-- End | Register Form -->
         </div>
         <!-- End # DIV Form -->
      </div>
   </div>
</div>
<!-- END # MODAL LOGIN -->