<%-- 
    Document   : SignUp
    Created on : Jan 22, 2020, 4:16:02 PM
    Author     : SE130204
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Tool.css" type="text/css">
        <title>Sign Up</title>
    </head>
    <body>
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
                <form action="SignUp" method="POST">
                    <c:set var="errors" value="${requestScope.CREATEERRORS}" />
                    
                  <div class="form-group">
                     <label>Mail*</label><br>
                     <input type="text" name="txtEmail" value="${param.txtEmail}" class="form-control" placeholder="User Mail"><br/>
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
                <c:if test="${not empty errors.emailExistedErr}">
                    <font color="red">
                    ${errors.emailExistedErr}
                    </font><br/>
                </c:if>
                  </div>
                    <div class="form-group">
                     <label>User Name</label><br>
                     <input type="text" name="txtUserName" value="${param.txtUserName}" class="form-control" placeholder="User Name"><br/>
                        <c:if test="${not empty errors.nameLengthErr}">
                            <font color="red">
                            ${errors.nameLengthErr}
                            </font><br/>
                        </c:if>
                        <c:if test="${not empty errors.nameFormatErr}">
                            <font color="red">
                            ${errors.nameFormatErr}
                            </font><br/>
                        </c:if>
                  </div>
                  <div class="form-group">
                     <label>Password</label><br>
                     <input type="password" name="txtPassword" value="" class="form-control" placeholder="Password"><br/>
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
                  </div>
                  
                    <div class="form-group">
                     <label>Confirm</label><br>
                     <input type="password" name="txtConfirm" value="" class="form-control" placeholder="Confirm"><br/>
                <c:if test="${not empty errors.confirmPasswordErr}">
                    <font color="red">
                    ${errors.confirmPasswordErr}
                    </font><br/>
                </c:if>
                  </div>
                    
                    <input type="submit" name="btAction" value="Sign Up" class="btn btn-black"/>
                    <input type="reset" value="Reset"/>
                    <input type="hidden" name="txtRole" value="Customer"/>
                    <input type="hidden" name="txtStatus" value="New"/>
               </form><br>
                <a href="ShowLogin">Login</a>
            </div>
         </div>
      </div>
    </body>
</html>
