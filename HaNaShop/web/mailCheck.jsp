<%-- 
    Document   : mailCheck
    Created on : Jan 11, 2020, 5:00:57 AM
    Author     : SE130204
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify your Account</title>
    </head>
    <body>
        <c:set var="mail" value="${requestScope.EMAIL}"/>
        <c:set var="verifyCode" value="${requestScope.VERIFYCODE}"/>
        <c:set var="password" value="${requestScope.PASSWORD}"/>
        <c:set var="name" value="${requestScope.NAME}"/>
            
        <h1>Verify your Blog Account</h1><br>
        <d>welcome ${mail} Enter verify code we sent in your mail to active your Account.</d><br>
        <br>
        <form action="VerifyProccess" method="POST">
            Verify Code <input type="text" name="txtVerify" value="" /><br>
            <input type="hidden" name="txtVerifyCode" value="${verifyCode}" />
            <input type="hidden" name="txtMail" value="${mail}" />
            <input type="hidden" name="txtPassword" value="${password}" />
            <input type="hidden" name="txtName" value="${name}" />
            <c:set var="errors" value="${requestScope.ERROR}" />
            <font color="red"><br>
            <c:if test="${not empty errors.invalidVerifyCode}">
                ${errors.invalidVerifyCode}<br>
            </c:if>
            </font><br>
            <input type="submit" name="btAction" value="Verify" />
            <input type="submit" name="btAction" value="Cancel" />
            
            <br>Can not receive verify code? <input type="submit" name="btAction" value="Click here to re-send verify code" /> 
        </form><br>
    </body>
</html>
