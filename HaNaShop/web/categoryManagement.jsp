<%-- 
    Document   : CategoryManagement
    Created on : Feb 29, 2020, 5:30:25 PM
    Author     : SE130204
--%>
<%@page import="truonghn.itemCategory.ItemCategoryDTO" %>
<%@page import="java.util.LinkedHashMap" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Tool.css" type="text/css">
        <link rel="stylesheet" href="css/CategoryManagement.css" type="text/css">
        <link rel="stylesheet" href="js/CategoryManagement.js" type="text/javascript">
        <script src="js/CategoryManagement.js"></script>
        <title>Categories management</title>
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
            <nav class="menubar">
                <form action="updateAndCreate" method="POST">
                    <input class="navReturn" type="submit" name="btAction" value="Return to manager Page">
                </form>
                <h1>Categories management</h1>
            </nav>   
            <div class="body">
                <div class="categoryManager">
                    <c:set var="list" value="${requestScope.CATEGORYLIST}"/>
                    <form action="updateAndCreate" method="POST">
                        <c:if test="${not empty list}">
                            <table id="category_table" border="1">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>Categories name</th>
                                        <th>Existing products</th>
                                        <th>Delete (you can only delete empty Category)</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <input id="checker1" name="check1" type="hidden" value="${0}">
                                    <input id="checker2" type="hidden" name="check2" value="${0}"> 
                                    <c:set var="count" value="${1}"/>
                                    <c:forEach var="dto" items="${list}" varStatus="counter">
                                        <tr>
                                            <td>${counter.count}</td>
                                            
                                            <c:set var="CatID" value="cat${count}"/>
                                            <c:set var="checkerID" value="check${count}"/>
                                            <c:set var="CategoryID" value="catID${count}"/>
                                            <input id="${checkerID}" type="hidden" value="${0}">
                                            <input id="${CategoryID}" type="hidden" name="" value="${dto.key.categoryID};;;;;${dto.key.category}">
                                            <td><input id="${CatID}" type="text" name="" value="${dto.key.category}" required="" oninput="checkChangeCatValue('createCat','${checkerID}','${dto.key.category}','${dto.key.categoryID}','${CatID}','${CategoryID}','checker1')"></td>
                                            <td>${dto.value}</td>
                                            <td>
                                                <c:set var="deleteCatID" value="deletecat${count}"/>
                                                <input id="${deleteCatID}" type="checkbox" name="cbCategoryID" value="${dto.key.categoryID}"
                                                       <c:if test="${dto.value > 0}">disabled=""</c:if> onchange="checkChange('deleteCat','${deleteCatID}','checker2')">
                                                <c:if test="${dto.value > 0}"><d>Can not delete</d></c:if>
                                            </td>
                                        </tr>
                                        <c:set var="count" value="${count + 1}"/>
                                    </c:forEach>
                                        <tr>
                                            <td colspan="2">
                                                <input id="removerowbt" type="button" value="Delete New Category" onclick="deleteRow('category_table',${count-1},'checker1','createCat')">
                                            </td>
                                            <td colspan="2">
                                                <input id="createrowbt" type="button" value="Create New Category" onclick="createNewRow('category_table','checker1','createCat')">
                                            </td>
                                        </tr>
                                </tbody>
                            </table>

                            
                        </c:if>
                        <c:if test="${empty list}">
                            <h1>No category available!!!</h1>
                        </c:if>
                        <div id="submitBox">
                            <input id="deleteCat" type="submit" name="btAction" value="Delete Categories" disabled="">
                            <input id="createCat" type="submit" name="btAction" value="Save Change" disabled="">
                        </div>
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
