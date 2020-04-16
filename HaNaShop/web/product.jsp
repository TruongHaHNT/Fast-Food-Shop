<%-- 
    Document   : Search
    Created on : Jan 25, 2020, 9:18:00 PM
    Author     : SE130204
--%>
<%@page import="truonghn.itemStorage.ItemStorageDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Tool.css" type="text/css">
        <link rel="stylesheet" href="css/pagging.css" type="text/css">
        <link rel="stylesheet" href="css/Product.css" type="text/css">
        <link rel="stylesheet" href="js/changePage.js" type="text/javascript">
        <link rel="stylesheet" href="js/Notification.js" type="text/javascript">
        <script src="js/Notification.js"></script>
        <title>Search</title>
    </head>
    <body onload="showNotification('${requestScope.NOTIFICATIONS_ADDTOCART}','productnotify')">
        <div class="containerMain">
            <c:set var="banner" value="${requestScope.BANNER}"/>
            <c:set var="searchicon" value="${requestScope.SEARCHICON}"/>
            <c:set var="logo" value="${requestScope.LOGO}"/>
            <c:set var="facebook" value="${requestScope.FACEBOOK}"/>
            <c:set var="google" value="${requestScope.GOOGLE}"/>
            <c:set var="twitter" value="${requestScope.TWITTER}"/>
            <c:set var="gmail" value="${requestScope.GMAIL}"/>
            <c:set var="view" value="${requestScope.VIEWICON}"/>
            <c:set var="cart" value="${requestScope.CARTICON}"/>
            
            <div class="info" style="background-image: url(${banner})">
                <!--logo==========================================================================-->
                    <div class="logo">
                        <img src="${logo}" width="380" height="100%"/>
                    </div>
                <!--search engine====================================================================================-->
                    <div class="search">
                        <form action="CustomerSearch" method="POST">
                        <!--search box========================================================================-->
                                <div class="advSearchBox">
                                    <!--search bar============================-->
                                    <input id="txtsearch" type="text" name="txtSearch" value="${param.txtSearch}" placeholder="Search Food/Drink"/>
                                    <input id="searchButton" type="submit" name="" value="" style="background-image: url(${searchicon})"/><br>
                                    <!--category box=========================================-->
                                    <c:set var="categorylist" value="${requestScope.CATEGORY}"/>
                                    <select id="cat" name="cbCategory">
                                        <option value="empty">Food/Drink all categories</option>
                                        <c:if test="${not empty categorylist}">
                                            <c:forEach var="item" items="${categorylist}" varStatus="counter">
                                                <option value="${item.category}" ${param.cbCategory == item.category ? 'selected=""':''}>${item.category}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select><br>
                                    <!--money range===================================================-->
                                    <d>Range of money</d><br> 
                                    <input class="txtMoney" id="minMoney" type="number" name="txtMin" value="${param.txtMin}" 
                                            step="0.01" min="0" placeholder="0($)" 
                                                oninput="checkValidMoney('minMoney', 'maxMoney', 'errorMoneyRange', 'searchButton')"/>
                                        <d>---</d>
                                        <input class="txtMoney" id="maxMoney" type="number" name="txtMax" value="${param.txtMax}" 
                                            step="0.01" min="0" placeholder="Max($)" 
                                                oninput="checkValidMoney('minMoney', 'maxMoney', 'errorMoneyRange', 'searchButton')"/>
                                        <d id="errorMoneyRange">Invalid money range!</d>
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
                            
            <div class="ProductPage">
                <c:set var="LIST" value="${requestScope.ITEMLIST}"/>
                <c:if test="${not empty LIST}">
        <!--list all item in page=============================================================-->
                    <div class="itemsList">
                        <c:set var="idButton" value="${1}"/>
                        <c:forEach var="dto" items="${LIST}" varStatus="counter">
                            <div class="item">
                            <!--image============================-->
                                <div class="itemImage">
                                    <img src="${dto.image}"/>
                                </div>
                            <!--content============================-->
                                <div class="itemContent">
                                    <div id="itemTittle">
                                        <d>${dto.foodname}</d>    
                                    </div>
                                    <div id="itemPrice">
                                        <d>$${dto.price}</d>
                                    </div>
                                    <div id="itemQuantity">
                                        <d>Available: ${dto.quantity}</d><br>
                                        <d>Sold: ${dto.sold}</d>
                                    </div>
                                </div>
                            <!--view item or add to cart=========================-->        
                                <div class="overlay">
                                    <form action="viewOrAddToCartItem" method="POST">
                                        <input type="hidden" name="txtItemID" value="${dto.id}">
                                        <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                                        <input type="hidden" name="cbCategory" value="${param.cbCategory}"/>
                                        <input type="hidden" name="txtMin" value="${param.txtMin}"/>
                                        <input type="hidden" name="txtMax" value="${param.txtMax}"/>
                                        <input id="curPage" type="hidden" name="curPage" value="${param.curPage}"/>
                                        <c:set var="idview" value="${'viewitem'}${idButton}"/>
                                        <c:set var="idcart" value="${'cartitem'}${idButton}"/>
                                        
                                        <input id="${idview}" type="submit" name="btAction" value="" onclick="changeValue_PH('viewItem','${idview}')" style="background-image: url(${view})">
                                        <input id="${idcart}" type="submit" name="btAction" value="" onclick="changeValue_PH('Add to cart','${idcart}')" style="background-image: url(${cart})">
                                    </form>
                                </div>
                            </div>
                                    <c:set var="idButton" value="${idButton+1}"/>
                        </c:forEach>
                    </div>
<!--Pagging=================================================================-->
                    <div class="Pagging">
                        <c:set var="lastPage" value="${requestScope.TOTALPAGE_ORDER_BY_DATE}"/>
                        <c:set var="Page" value="${requestScope.CUR_PAGE}"/>

                        <c:set var="pagelist" value="${lastPage>=7 ? 7:lastPage}"/>
                        <c:set var="offset" value="${1}"/>
                        <div class="pagecontainer">
                            <form action="CustomerSearch" method="POST">
                                <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                                <input type="hidden" name="cbCategory" value="${param.cbCategory}"/>
                                <input type="hidden" name="txtMin" value="${param.txtMin}"/>
                                <input type="hidden" name="txtMax" value="${param.txtMax}"/>
            <!--===============================================================================================--> 
                                <input id="fistPage" type="submit" name="curPage" onclick="changeValue_PH(${1},'fistPage')" value="<<" ${Page>1 ?'':'disabled=""'}>
                                <input id="prev" type="submit" name="curPage" onclick="changeValue_PH(${Page-1},'prev')" value="<" ${Page>1 ?'':'disabled=""'}>
            <!--===============================================================================================-->                    
                                <c:if test="${Page>4 and lastPage > 7}">
                                    <input type="button" value="..." disabled=""/>
                                </c:if>
            <!--===============================================================================================-->                        
                                    <c:set var="count" value="${(lastPage <= 7 or Page-3<=1) ? 1:(lastPage-3 >=Page ? Page-3:lastPage-6)}"/>
                                    <c:forEach begin="${offset}" end="${pagelist}" step="${1}">
                                        <input type="submit" name="curPage" value="${count}" ${count==Page ? 'id="selected"':''}>
                                        <c:set var="count" value="${count + 1}"/>
                                    </c:forEach>
            <!--===============================================================================================-->                        
                                <c:if test="${lastPage>7 and Page<(lastPage-3)}">
                                    <input type="button" value="..." disabled=""/>
                                </c:if>
            <!--===============================================================================================-->                    
                                <input id="next" type="submit" name="curPage" onclick="changeValue_PH(${Page+1},'next')" value=">" ${Page<lastPage ?'':'disabled=""'}>
                                <input id="lastPage" type="submit" name="curPage" onclick="changeValue_PH(${lastPage},'lastPage')" value=">>" ${Page<lastPage ?'':'disabled=""'}>
                                <input id="displayPage" type="button" disabled="" value="${Page}/${lastPage}">
                            </form>
                            <script src="js/changePage.js"></script>
                        </div>
                    </div>  
                </c:if>
                
                <c:if test="${not empty errors.moneyRangeErr}">
                    <h1>Error loading page!</h1>
                </c:if>
                
                <c:if test="${empty LIST and empty errors.moneyRangeErr}">
                    <h1>We could not find any product by keyword '${param.txtSearch}'</h1>
                </c:if>
                
                
                    <div id="productnotify">
                        <h1>${requestScope.NOTIFICATIONS_ADDTOCART}</h1>
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
