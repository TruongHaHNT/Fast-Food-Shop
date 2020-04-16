<%-- 
    Document   : PurchaseHistory
    Created on : Feb 12, 2020, 6:14:35 PM
    Author     : SE130204
--%>

<%@page import="java.util.List" %>
<%@page import="truonghn.purchaseHistory.PurchaseHistoryDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Tool.css" type="text/css">
        <link rel="stylesheet" href="css/pagging.css" type="text/css">
        <link rel="stylesheet" href="css/PurchaseHistory.css" type="text/css">
        <link rel="stylesheet" href="js/Notification.js" type="text/javascript">
        <link rel="stylesheet" href="js/changePage.js" type="text/javascript">
        <title>Purchase History</title>
    </head>
    <body onload="getMaxDateToday('minDate', 'maxDate', '${param.txtMinDate}', '${param.txtMaxDate}')">
        <div class="containerMain">
            <c:set var="banner" value="${requestScope.BANNER}"/>
            <c:set var="searchicon" value="${requestScope.SEARCHICON}"/>
            <c:set var="logo" value="${requestScope.LOGO}"/>
            <c:set var="facebook" value="${requestScope.FACEBOOK}"/>
            <c:set var="google" value="${requestScope.GOOGLE}"/>
            <c:set var="twitter" value="${requestScope.TWITTER}"/>
            <c:set var="gmail" value="${requestScope.GMAIL}"/>
            <c:set var="cart" value="${requestScope.CARTICON}"/>
            
            <div class="info" style="background-image: url(${banner})">
                <!--logo==========================================================================-->
                    <div class="logo">
                        <img src="${logo}" width="380" height="100%"/>
                    </div>
                <!--search engine====================================================================================-->
                    <div class="search">
                        <form action="HistorySearch" method="POST">
                        <!--search box========================================================================-->
                                <div class="advSearchBox">
                                    <!--search bar============================-->
                                    <input id="txtsearch" type="text" name="txtSearchHistory" value="${param.txtSearchHistory}" placeholder="Search Food/Drink"/>
                                    <input id="searchButton" type="submit" name="" value="" style="background-image: url(${searchicon})"/><br>
           
                                    <!--dat range===================================================--> 
                                    <input class="txtDate" id="minDate" type="date" name="txtMinDate" value="${param.txtMinDate}" 
                                           placeholder="1753-01-01" min="1753-01-01" max=""
                                                oninput="checkValidDate('minDate', 'maxDate', 'errorDateRange', 'searchButton')"/>
                                        <d>---</d>
                                        <input class="txtDate" id="maxDate" type="date" name="txtMaxDate" value="${param.txtMaxDate}" 
                                               placeholder="" min="1753-01-01" max=""
                                                oninput="checkValidDate('minDate', 'maxDate', 'errorDateRange', 'searchButton')"/><br>
                                        <d id="errorDateRange"></d>
                                        
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
                    <a href="Home">Home</a>
                    <a href="Product">Product</a>
                    <div style="background-image: url(${cart})" onclick="window.location.href='viewCart'">
                            <span class="tooltiptext">View your cart</span>
                    </div>
                    <a class="history" href="History">Purchase history</a>
            </nav>
            
            <div class="HistoryPage">
                <c:set var="itemlist" value="${requestScope.PURCHASEHISTORYLIST}"/>
                <c:if test="${not empty itemlist}">
                    <h2 style="margin: 20px">Purchase History</h2><br>
                    <div class="itemTable">
                        <table border="1" cellpadding="5">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Product Image</th>
                                    <th>Product Name</th>
                                    <th>Product Amount</th>
                                    <th>Product Price/item</th>
                                    <th>Product Total</th>
                                    <th>Purchase Date</th>
                                    <th>view Product (Product may have been updated)</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${itemlist}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>
                                            <img src="${dto.image}">
                                        </td>
                                        <td>
                                            ${dto.name}
                                        </td>
                                        <td>
                                            ${dto.amount}
                                        </td>
                                        <td>
                                            $${dto.price}
                                        </td>
                                        <td>
                                            <fmt:formatNumber var="totalItems" maxFractionDigits="2" value="${dto.amount*dto.price}"/>
                                            $${totalItems}
                                        </td>
                                        <td>
                                            ${dto.date}
                                        </td>
                                        <td>
                                            <form action="ViewItem" method="POST">
                                                <input type="hidden" name="txtItemID" value="${dto.foodID}">
                                                <input type="submit" name="btAction" value="View Product in store">
                                            </form>
                                        </td>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="Pagging">
                        <c:set var="lastPage" value="${requestScope.TOTALPAGE_ORDER_BY_DATE_PURHISTORY}"/>
                        <c:set var="Page" value="${requestScope.CUR_PAGE_PURHISTORY}"/>

                        <c:set var="pagelist" value="${lastPage>=7 ? 7:lastPage}"/>
                        <c:set var="offset" value="${1}"/>
                        <div class="pagecontainer">
                            <form action="HistorySearch" method="POST">
                                <input type="hidden" name="txtSearchHistory" value="${param.txtSearchHistory}"/>
                                <input type="hidden" name="txtMinDate" value="${param.txtMinDate}"/>
                                <input type="hidden" name="txtMaxDate" value="${param.txtMaxDate}"/>
            <!--===============================================================================================--> 
                                <input id="fistPage" type="submit" name="curHistoryPage" onclick="changeValue_PH(${1},'fistPage')" value="<<" ${Page>1 ?'':'disabled=""'}>
                                <input id="prev" type="submit" name="curHistoryPage" onclick="changeValue_PH(${Page-1},'prev')" value="<" ${Page>1 ?'':'disabled=""'}>
            <!--===============================================================================================-->                    
                                <c:if test="${Page>4 and lastPage > 7}">
                                    <input type="button" value="..." disabled=""/>
                                </c:if>
            <!--===============================================================================================-->                        
                                    <c:set var="count" value="${(lastPage <= 7 or Page-3<=1) ? 1:(lastPage-3 >=Page ? Page-3:lastPage-6)}"/>
                                    <c:forEach begin="${offset}" end="${pagelist}" step="${1}">
                                        <input type="submit" name="curHistoryPage" value="${count}" ${count==Page ? 'id="selected"':''}>
                                        <c:set var="count" value="${count + 1}"/>
                                    </c:forEach>
            <!--===============================================================================================-->                        
                                <c:if test="${lastPage>7 and Page<(lastPage-3)}">
                                    <input type="button" value="..." disabled=""/>
                                </c:if>
            <!--===============================================================================================-->                    
                                <input id="next" type="submit" name="curHistoryPage" onclick="changeValue_PH(${Page+1},'next')" value=">" ${Page<lastPage ?'':'disabled=""'}>
                                <input id="lastPage" type="submit" name="curHistoryPage" onclick="changeValue_PH(${lastPage},'lastPage')" value=">>" ${Page<lastPage ?'':'disabled=""'}>
                                <input id="displayPage" type="button" disabled="" value="${Page}/${lastPage}">
                            </form>
                            <script src="js/changePage.js"></script>
                        </div>
                    </div>
                </c:if>
            </div>
            <script src="js/Notification.js"></script>
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
