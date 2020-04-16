<%-- 
    Document   : ViewItem
    Created on : Feb 6, 2020, 4:51:08 PM
    Author     : SE130204
--%>

<%@page import="truonghn.itemStorage.ItemStorageDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Tool.css" type="text/css">
        <link rel="stylesheet" href="css/ProductInfo.css" type="text/css">
        <link rel="stylesheet" href="css/Recommender.css" type="text/css">
        <link rel="stylesheet" href="js/ViewItem.js" type="text/javascript">
        <link rel="stylesheet" href="js/Notification.js" type="text/javascript">
        <script src="js/ViewItem.js"></script>
        <title>view item</title>
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
            
            <div class="ItemInfoPage">
                <!--image===============================================================-->
                <c:set var="dto" value="${requestScope.ITEMINFO}"/>
                <c:if test="${not empty dto}">
                    <form action="viewOrAddToCartItem" method="POST">
                    <div class="itemImage">
                            <img src="${dto.image}"/><br>
                            <!--name=================================================-->
                            <div class="itemname">
                                <d id="name">1</d><d>x ${dto.foodname}</d>    
                            </div>
                            <!--price==================================================-->
                            <div id="numberOfItems">
                                <input id="sub" type="button" name="sub" onclick="decrementValue(document.getElementById('amount').value, ${dto.price})" value="-">
                                <input id="amount" type="number" name="txtAmount" oninput="changeValueOnInput(document.getElementById('amount').value, ${dto.price})" value="1" step="1" min="1" required="">
                                <input id="sum" type="button" name="sum" onclick="incrementValue(document.getElementById('amount').value, ${dto.price})" value="+">
                                <div class="pricesymble">                       
                                    <d>$</d><d id="price">${dto.price}</d>
                                </div>
                            </div>
                            <d id="tittle">Total</d>
                            <div class="totalpricesymble">
                                <d>$</d><d id="total">${dto.price}</d>
                            </div>
                 <!--================================================================================================-->
                            <input id="itemID" type="hidden" name="txtItemID" value="${dto.id}">
                            <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                            <input type="hidden" name="cbCategory" value="${param.cbCategory}"/>
                            <input type="hidden" name="txtMin" value="${param.txtMin}"/>
                            <input type="hidden" name="txtMax" value="${param.txtMax}"/>
                            <input type="hidden" name="curPage" value="${param.curPage}"/>
                 <!--===============================================================================================-->
                            <input class="addToCart" type="submit" name="btAction" value="Add to cart"><br>
                            <input class="return" type="submit" name="btAction" value="Return to product page">
                        
                    </div>
                    <!--content=============================================================-->
                    <div class="itemInfo">
                        <div class="iteminfo_content">
                            <div class="item_tittle">
                                <d>${requestScope.ITEMCATEGORY}</d>
                            </div>
                            <d class="item_name">${dto.foodname}</d>
                        </div>
                        <div class="iteminfo_content">
                            <div class="item_tittle">
                                <d>Description</d>
                            </div>
                            <textarea class="item_des" readonly="true">${dto.description}</textarea>
                        </div>
                        <div class="iteminfo_stock">
                            <d style="float: left">Available:</d>   <d style="float: right">${dto.quantity}</d>
                        </div>
                    </div>
                    <div class="recommemder">
                        <div class="recTittle"><h2>Customers also bought.</h2></div>
                        <c:set var="recList" value="${requestScope.RECOMMENDED}"/>
                        <c:if test="${not empty recList}">
                            <c:forEach var="rec" items="${recList}">
                                <div class="recItem">
                                    <img src="${rec.image}"><br>
                                    <d>${rec.foodname}</d><br>
                                    <d class="recPrice">price: $${rec.price}</d><br>
                                    <d>purchase attached: </d><input type="checkbox" name="cbItemID" value="${rec.id}">
                                    <input type="submit" name="btAction" value="detail" onclick="changeItemID('itemID','${rec.id}')">
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty recList}">
                            <h1>No record has been found!</h1>
                        </c:if>
                    </div>
                    </form>
                </c:if>
                <c:if test="${empty dto}">
                    <h1 style="float: left; margin: 30px">Product is not available at this moment!</h1>
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
