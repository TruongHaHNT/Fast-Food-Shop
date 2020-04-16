<%-- 
    Document   : Login
    Created on : Jan 22, 2020, 6:47:46 AM
    Author     : SE130204
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Tool.css" type="text/css">
        <title>Login Page</title>
    </head>
    <body>
        <c:set var="errors" value="${requestScope.CREATEERRORS}" />
        <div class="sidenav">
            <div class="login-main-text">
            <h2>HaNa Shop<br> Login Page</h2>
            <p>Login or register from here to access.</p>
         </div>
      </div>
      <div class="main">
         <div class="col-md-6 col-sm-12">
            <div class="login-form">
                <a href="homePage">Go back to Home Page</a><br>
                <form action="Login" method="POST">
                <div class="form-group">
                    <label>Mail</label><br>
                    <input type="text" name="txtEmail" value="${param.txtEmail}" class="form-control" placeholder="User Mail">
                    <br> 
                    <c:if test="${not empty errors.emailLengthErr}">
                        <font color="red">
                        ${errors.emailLengthErr}
                        </font><br/>
                    </c:if>
                    <c:if test="${not empty errors.emailFormatErr}">
                        <font color="red">
                        ${errors.emailFormatErr}
                        </font><br/>
                    </c:if>
                        <c:if test="${not empty errors.notExistErr}">
                    <font color="red">
                    ${errors.notExistErr}
                    </font><br/>
                </c:if>
                  </div>
                  <div class="form-group">
                    <label>Password</label><br>
                    <input type="password" name="txtPassword" value="" class="form-control" placeholder="Password">
                    <br>
                    <c:if test="${not empty errors.passwordLengthErr}">
                        <font color="red">
                        ${errors.passwordLengthErr}
                        </font><br/>
                    </c:if>
                    <c:if test="${not empty errors.passwordFormatErr}">
                        <font color="red">
                        ${errors.passwordFormatErr}
                        </font><br/>
                    </c:if>
                    <c:if test="${not empty errors.incorrectPassword}">
                        <font color="red">
                        ${errors.incorrectPassword}
                        </font><br/>
                    </c:if>
                  </div>
                    <input type="submit" name="btAction" value="Login" class="btn btn-black"/>
                    <input type="reset" value="Reset"/>
               </form><br>
                <a href="SignUpPage">Sign up</a>
            </div>
         </div>
      </div>
    </body>
</html>
