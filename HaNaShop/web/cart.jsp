<%-- 
    Document   : Cart
    Created on : Feb 10, 2020, 4:59:42 PM
    Author     : SE130204
--%>
<%@page import="java.util.LinkedHashMap" %>
<%@page import="truonghn.itemStorage.ItemStorageDTO" %>
<%@page import="truonghn.cart.CartDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Tool.css" type="text/css">
        <link rel="stylesheet" href="css/Cart.css" type="text/css">
        <link rel="stylesheet" href="js/CustomerCart.js" type="text/javascript">
        <link rel="stylesheet" href="js/Notification.js" type="text/javascript">
        <script src="js/CustomerCart.js"></script>
        <script src="js/Notification.js"></script>
        <title>Your Cart</title>
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
                    <a href="About">About</a>
                    <a href="Product">Product</a>
                    <a href="News">News</a>
                    <div style="background-image: url(${cart})" onclick="window.location.href='viewCart'">
                            <span class="tooltiptext">View your cart</span>
                    </div>
                    <a class="history" href="History">Purchase history</a>
            </nav>
                            
            <div class="CartPage">
                <c:set var="removedItemList" value="${requestScope.REMOVELIST}"/>
                <c:set var="amountexceedItemList" value="${requestScope.AMOUNTEXCEEDLIST}"/>
                <c:set var="cart" value="${requestScope.CARTLIST}"/>
                    <!--check exist cart=======================================================-->
                <c:if test="${not empty cart}">
                <form action="cartHandler" method="POST">                    
                    <c:set var="count" value="${1}"/>
                    <fmt:formatNumber var="totalAllItem" type="number" maxFractionDigits="2" value="${0}"/>
                    <div class="itemContainer">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>image</th>
                                    <th>name</th>
                                    <th>amount</th>
                                    <th>price/item</th>
                                    <th>total</th>
                                    <th>detail</th>
                                    <th>(-_-).(delete)</th>
                                </tr>
                            </thead>
                            <tbody>
                                <input id="itemIDViewThisProduct" type="hidden" name="txtItemID" value="">
                                <c:forEach var="dto" items="${cart}" varStatus="counter">
                                    <tr>
                                        <td>
                                            ${counter.count}
                                        </td>
                                        <td>
                                            <img src="${dto.value.image}">
                                        </td>
                                        <td>
                                            <d>${dto.value.foodname}</d>
                                        </td>
                                        <!--amount====================================================-->
                                        <td>
                                            <c:set var="amount" value="${'amount'}${count}"/>
                                            <c:set var="defaultamount" value="${'defaultamount'}${count}"/>
                                            <c:set var="prevamount" value="${'prevamount'}${count}"/>
                                            <c:set var="total" value="${'total'}${count}"/>
                                            <c:set var="updateAmount" value="${'upamount'}${count}"/>
                                            <c:set var="change" value="${'change'}${count}"/>

                                            <input id="${change}" name="" value="${0}" type="hidden">
                                            <input id="${prevamount}" name="" value="${dto.key.amount}" type="hidden">
                                            <input id="${defaultamount}" name="" value="${dto.key.amount}" type="hidden">
                                            <!--change amount,(amount, previous amount: check change or not) change total money, change value forward to servlet-->
                                            <input class="sub" type="button" name="sub" onclick="decrementValueCS('${amount}', '${defaultamount}', '${prevamount}',
                                                                                                '${total}', '${updateAmount}', '${change}', 
                                                                                                ${dto.value.price}, '${dto.key.itemID}', '${dto.value.foodname}')" value="-">
                                            <input class="amount" id="${amount}" type="number" oninput="changeValueOnInputCS('${amount}', '${defaultamount}', '${prevamount}',
                                                                                                        '${total}', '${updateAmount}', '${change}', 
                                                                                                        ${dto.value.price}, '${dto.key.itemID}', '${dto.value.foodname}')" value="${dto.key.amount}" step="1" min="1" required="">
                                            <input class="sum" type="button" name="sum" onclick="incrementValueCS('${amount}', '${defaultamount}', '${prevamount}', 
                                                                                                '${total}', '${updateAmount}', '${change}', 
                                                                                                ${dto.value.price}, '${dto.key.itemID}', '${dto.value.foodname}')" value="+">

                                        </td>
                                        <td>
                                            <d>Price $</d><d>${dto.value.price}</d>
                                        </td>
                                        <td>
                                            <d>Total $</d><d id="${total}">
                                                <fmt:formatNumber var="totalprice" type="number" groupingUsed = "false" maxFractionDigits="2" value="${dto.value.price*dto.key.amount}"/>
                                                ${totalprice}
                                            </d>
                                        </td>
                                        <td>
                                            <input class="viewLink" name="btAction" value="View this product in store" type="submit" onclick="setItemID('itemIDViewThisProduct','${dto.key.itemID}')">
                                        </td>
                                        <td>
                                            <c:set var="deleteItem" value="${'deleteItem'}${count}"/>
                                            <input class="checkbox" id="${deleteItem}" type="checkbox" name="chkItem" value="${dto.key.itemID}" onclick="checkDelete('${deleteItem}')"/>
                                        </td>

                                    </tr>
                                    <input id="${updateAmount}" type="hidden" name="listCodeAndAmount" value="${dto.key.itemID};;;;;${dto.value.foodname};;;;;${dto.key.amount}">

                                    <c:set var="count" value="${count + 1}"/>

                                    <fmt:formatNumber var="totalAllItem" type="number" groupingUsed = "false" maxFractionDigits="2" value="${totalAllItem+totalprice}"/>
                                </c:forEach>
                                    <input id="checkChangeAmount" name="" value="${0}" type="hidden">
                                    <input id="checkDeleteItems" name="" value="${0}" type="hidden">
                                    <tr>
                                        <td></td>
                                        <td colspan="7"><d>Total:</d><input id="totalAllItem" type="button" value="${totalAllItem}"></td>
                                    </tr>
                            </tbody>
                        </table>
                    </div>
                       
                    <!--submit button=========================================================================-->
                    <div id="CartSubmitButton">
                        <input id="delete" type="button" name="" value="Delete" onclick="popupDeletewindow('deleteWindowContainer')" disabled="true">
                        <input id="confirm" type="button" name="" value="Purchase" onclick="popupConfirmwindow('purchaseWindowContainer')">
                    </div>
                    <div class="warningMessage" id="deleteWindowContainer">
                        <div class="warningMessage_content">
                            <div id="deleteWindow">
                                <h2>Do you want to remove selected items</h2><br>
                                <div>
                                    <input class="cancellation" type="button" name="" value="Cancel delete" onclick="closeDeletewindow('deleteWindowContainer')">
                                    <input class="confirm" type="submit" name="btAction" value="Confirm delete">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="warningMessage" id="purchaseWindowContainer">
                        <div class="warningMessage_content">
                            <div id="confirmWindow">
                                <h2>Click confirm to purchase.</h2><br>
                                <div>
                                    <input class="cancellation" type="button" name="" value="Cancel purchase" onclick="closeConfirmwindow('purchaseWindowContainer')">
                                    <input class="confirm" type="submit" name="btAction" value="Confirm purchase">
                                </div>
                            </div>
                        </div>
                    </div>    
                </form>
                    
                </c:if>
                    <!--show removed item due to error===========================================-->
                <c:if test="${not empty removedItemList}">
                    <div id="removedItem">
                        <div>
                            <h2>Some products in cart are no longer available (deleted)!</h2>
                            <c:forEach var="removedItem" items="${removedItemList}" varStatus="counter">
                                <d style="color: red">${counter.count}</d><d>${removedItem}</d><br>
                            </c:forEach>
                                <input type="button" name="" value="Ok" onclick="CloseItemWindow('removedItem')">
                        </div>
                    </div>
                </c:if>    
                <c:if test="${not empty amountexceedItemList}">
                    <div id="amountExceedItem">
                        <div>
                            <c:forEach var="amountexceedItem" items="${amountexceedItemList}" varStatus="counter">
                                <d style="color: red">${counter.count}</d><d>${amountexceedItem}</d><br>
                            </c:forEach>
                                <input type="button" name="" value="Ok" onclick="CloseItemWindow('amountExceedItem')">
                        </div>
                    </div>
                    
                </c:if>
                <c:if test="${empty cart}">
                    <h1>You currently have no items in your shopping cart!</h1>
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
