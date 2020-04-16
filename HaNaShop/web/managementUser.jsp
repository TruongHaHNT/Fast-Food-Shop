<%-- 
    Document   : ManagementUser
    Created on : Mar 1, 2020, 8:30:46 AM
    Author     : SE130204
--%>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Tool.css" type="text/css">
        <link rel="stylesheet" href="css/pagging.css" type="text/css">
        <link rel="stylesheet" href="css/UserManagement.css" type="text/css">
        <link rel="stylesheet" href="js/changePage.js" type="text/javascript">
        <script src="js/changePage.js"></script>
        <title>Users Management</title>
    </head>
    <body>
        <div class="containerMain">
            <c:set var="banner" value="${requestScope.BANNER}"/>
            <c:set var="searchicon" value="${requestScope.SEARCHICON}"/>
            <c:set var="logo" value="${requestScope.LOGO}"/>
            <c:set var="facebook" value="${requestScope.FACEBOOK}"/>
            <c:set var="google" value="${requestScope.GOOGLE}"/>
            <c:set var="twitter" value="${requestScope.TWITTER}"/>
            <c:set var="gmail" value="${requestScope.GMAIL}"/>
            <c:set var="view" value="${requestScope.VIEWICON}"/>
            
            <div class="info" style="background-image: url(${banner})">
                <!--logo==========================================================================-->
                    <div class="logo">
                        <img src="${logo}" width="380" height="100%"/>
                    </div>
                <!--search engine====================================================================================-->
                    <div class="search">
                           <form action="updateAndCreate" method="POST">
                        <!--search box========================================================================-->
                                <div class="advSearchBox">
                                    <!--search bar============================-->
                                    <input id="txtsearch" type="text" name="txtUserName" value="${param.txtUserName}" placeholder="Search User's name"/>
                                    <input id="searchButton" type="submit" name="btAction" value="" onclick="changeValue_PH('Customers management','searchButton')" style="background-image: url(${searchicon})"/><br>
                        <!--submit=======================================================================================-->
                                </div>
                            </form>
                    </div>
                        
                        <form action="loginPage" method="POST">
                            <div class="login">
                            <c:if test="${empty sessionScope.NAME}">
                                    
                                    <button type="submit" value="Login" name="btAction" style="width: auto">Login</button>
                                    <button type="submit" value="Sign up" name="btAction" style="width: auto">Sign Up</button>
                                   
                            </c:if>  
                            <c:if test="${not empty sessionScope.NAME}">
                                <div class="logout">
                                    <button type="submit" value="Logout" name="btAction" style="width: auto">Logout</button>
                                    <button style="width: auto" disabled="">Welcome ${sessionScope.NAME}</button>
                                </div>  
                            </c:if>
                            </div> 
                        </form>   
                    
            </div>
            <nav class="menubar">
                <form action="updateAndCreate" method="POST">
                    <input class="navReturn" type="submit" name="btAction" value="Return to manager Page">
                </form>
                <h1>Users management</h1>
            </nav>
            <div class="ManagerUserPage">
                
                <c:set var="userlist" value="${requestScope.USERLIST}"/>
                <c:if test="${not empty userlist}">
                    <table border="1" style="table-layout: fixed">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>User's name</th>
                                <th>User's email</th>
                                <th>User's role</th>
                                <th>User's status</th>
                                <th>view Purchase history</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${userlist}" varStatus="counters">
                                <tr>
                                    <td>${counters.count}</td>
                                    <td>${dto.name}</td>
                                    <td>${dto.mail}</td>
                                    <td>${dto.role}</td>
                                    <td>${dto.status}</td>
                                    <td><c:if test="${dto.role == 'Manager'}">
                                            <h3 style="color: red">Admin</h3>
                                        </c:if>
                                        <c:if test="${dto.role != 'Manager'}">
                                            <c:if test="${dto.status == 'Active'}">
                                                <form action="updateAndCreate" method="POST">
                                                    <input type="hidden" name="txtAdUserID" value="${dto.mail}"/>
                                                    <input type="hidden" name="txtAdSearchHistory" value=""/>
                                                    <input type="submit" name="btAction" value="View User History">
                                                </form>
                                            </c:if>
                                            <c:if test="${dto.status != 'Active'}">
                                                <h3>InActive Account</h3>
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                                
                    <div class="Pagging">
                        <c:set var="lastPage" value="${requestScope.TOTALPAGE}"/>
                        <c:set var="Page" value="${requestScope.CUR_PAGE}"/>
                        <c:set var="pagelist" value="${lastPage>=7 ? 7:lastPage}"/>
                        <c:set var="offset" value="${1}"/>
                        <div class="pagecontainer">
                            <form action="updateAndCreate" method="POST">
                                <input type="hidden" name="txtUserName" value="${param.txtUserName}"/>
                                <input type="hidden" name="btAction" value="Customers management"/>
            <!--===============================================================================================--> 
                                <input id="fistPage" type="submit" name="curAdPage" onclick="changeValue_PH(${1},'fistPage')" value="<<" ${Page>1 ?'':'disabled=""'}>
                                <input id="prev" type="submit" name="curAdPage" onclick="changeValue_PH(${Page-1},'prev')" value="<" ${Page>1 ?'':'disabled=""'}>
            <!--===============================================================================================-->                    
                                <c:if test="${Page>4 and lastPage > 7}">
                                    <input type="button" value="..." disabled=""/>
                                </c:if>
            <!--===============================================================================================-->                        
                                    <c:set var="count" value="${(lastPage <= 7 or Page-3<=1) ? 1:(lastPage-3 >=Page ? Page-3:lastPage-6)}"/>
                                    <c:forEach begin="${offset}" end="${pagelist}" step="${1}">
                                        <input type="submit" name="curAdPage" value="${count}" ${count==Page ? 'id="selected"':''}>
                                        <c:set var="count" value="${count + 1}"/>
                                    </c:forEach>
            <!--===============================================================================================-->                        
                                <c:if test="${lastPage>7 and Page<(lastPage-3)}">
                                    <input type="button" value="..." disabled=""/>
                                </c:if>
            <!--===============================================================================================-->                    
                                <input id="next" type="submit" name="curAdPage" onclick="changeValue_PH(${Page+1},'next')" value=">" ${Page<lastPage ?'':'disabled=""'}>
                                <input id="lastPage" type="submit" name="curAdPage" onclick="changeValue_PH(${lastPage},'lastPage')" value=">>" ${Page<lastPage ?'':'disabled=""'}>
                                <input id="displayPage" type="button" disabled="" value="${Page}/${lastPage}">
                            </form>
                        </div>
                    </div>
                </c:if>
                <c:if test="${empty userlist}">
                    <h1>Could not find any User by keyword '${param.txtAdSearch}'</h1>
                </c:if>  
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
