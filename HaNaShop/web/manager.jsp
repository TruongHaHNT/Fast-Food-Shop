<%-- 
    Document   : Manager
    Created on : Jan 23, 2020, 9:57:49 AM
    Author     : SE130204
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Tool.css" type="text/css">
        <title>manager</title>
    </head>
    <body>
        <div class="containerMain">
            <c:set var="banner" value="${requestScope.BANNER}"/>
            <c:set var="logo" value="${requestScope.LOGO}"/>
            <c:set var="facebook" value="${requestScope.FACEBOOK}"/>
            <c:set var="google" value="${requestScope.GOOGLE}"/>
            <c:set var="twitter" value="${requestScope.TWITTER}"/>
            <c:set var="gmail" value="${requestScope.GMAIL}"/>
            
            <div class="info" style="background-image: url(${banner})">
                    <div class="logo">
                        
                        <img src="${logo}" width="380" height="100%"/>
                    </div>
                        
                        <form action="loginPage">
                            <div class="login">
                            <c:if test="${not empty sessionScope.NAME}">
                                <div class="logout">
                                    <button type="submit" value="Logout" name="btAction" style="width: auto">Logout</button>
                                    <button style="width: auto" disabled="">Welcome ${sessionScope.NAME}</button>
                                </div>  
                            </c:if>
                            </div> 
                        </form>   
            </div>
            <nav class="manager_menubar">
                <h1>WELCOME TO MANAGER PAGE!</h1>
            </nav>    
            <div class="body">
                <div class="manager">
                    <form action="updateAndCreate" method="POST">
                        <input type="hidden" name="txtAdSearch" value="">
                        <input type="hidden" name="cbAdCategory" value="empty">
                        <input type="hidden" name="cbAdStatus" value="Active">
                        <input class="text" type="submit" name="btAction" value="View and Update Products">
                    </form>
                </div>
                <div class="manager">
                    <form action="updateAndCreate" method="POST">
                        <input class="text" type="submit" name="btAction" value="Create Products">
                    </form>
                </div>
                <div class="manager">
                    <form action="updateAndCreate" method="POST">
                        <input type="hidden" name="txtUserName" value="">
                        <input class="text" type="submit" name="btAction" value="Customers management">
                    </form>
                </div>
                <div class="manager">
                    <form action="updateAndCreate" method="POST">
                        <input class="text" type="submit" name="btAction" value="Categories management">
                    </form>
                </div>
            </div>
                                                        
            <footer>
            <div class="footer_left">
                    <ul>
                            <h3>Help & Contact</h3>
                            <li>Contact us</li> 
                            <li>FAQ's</li>
                            <li>After Sales Support </li>
                            <li>Click & Collect </li>
                            <li>Delivery</li> 
                            <li>Payment</li> 
                            <li>Buying from us</li> 
                            <li>Trade card</li> 
                    </ul>
            </div>
            <div class="footer_center1">
                    <ul>
                            <h3>Company Information</h3>
                            <li>News</li>
                            <li>Modern Slavery Statement</li>
                            <li>Our Partners</li>
                            <li>Working for HaNa Shop</li> 
                            <li>About HaNa Shop </li>
                            <li>Diversity Reporting</li>
                    </ul>
            </div>
            <div class="footer_center2">
                    <ul>
                            <h3>Policies</h3>
                            <li>Terms of Business </li>
                            <li>Privacy </li>
                            <li>Cookies </li>	
                            <li>Carrier bags </li>
                    </ul>
            </div>
            <div class="footer_right">
              <img src="${facebook}" width="32px" height="32px"/>
              <img src="${google}" width="32px" height="32px"/>
              <img src="${twitter}" width="32px" height="32px"/>
              <img src="${gmail}" width="32px" height="32px"/>
            </div>
            </footer>
        </div>
    </body>
</html>
