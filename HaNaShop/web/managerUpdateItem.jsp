<%-- 
    Document   : ManagerUpdateItem
    Created on : Feb 17, 2020, 9:38:31 AM
    Author     : SE130204
--%>

<%@page import="java.util.List"%>
<%@page import="truonghn.itemStorage.ItemStorageDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Tool.css" type="text/css">
        <link rel="stylesheet" href="css/UpdateManagement.css" type="text/css">
        <link rel="stylesheet" href="js/CreateFunction.js" type="text/javascript">
        <link rel="stylesheet" href="js/ManagerNotification.js" type="text/javascript">
        <script src="js/CreateFunction.js"></script>
        <script src="js/ManagerNotification.js"></script>
        <title>Update Page</title>
    </head>
    <body onload="loadWindow('alterItemStatusWindow_detail','confirmAlter','${requestScope.ITEMINFO.status}')">
        <div class="containerMain">
            <c:set var="banner" value="${requestScope.BANNER}"/>
            <c:set var="searchicon" value="${requestScope.SEARCHICON}"/>
            <c:set var="logo" value="${requestScope.LOGO}"/>
            <c:set var="facebook" value="${requestScope.FACEBOOK}"/>
            <c:set var="google" value="${requestScope.GOOGLE}"/>
            <c:set var="twitter" value="${requestScope.TWITTER}"/>
            <c:set var="gmail" value="${requestScope.GMAIL}"/>
            <c:set var="view" value="${requestScope.VIEWICON}"/>
            <c:set var="up" value="${requestScope.UPICON}"/>
            <c:set var="down" value="${requestScope.DOWNICON}"/>
            
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
                                    <input id="txtsearch" type="text" name="txtAdSearch" value="${param.txtAdSearch}" placeholder="Search Food/Drink"/>
                                    <input id="searchButton" type="submit" name="btAction" value="" onclick="changeValue('View and Update Products','searchButton')" style="background-image: url(${searchicon})"/><br>
                                    <!--category box=========================================-->
                                    <c:set var="categorylist" value="${requestScope.ADCATEGORY}"/>
                                    <select id="cat" name="cbAdCategory">
                                        <option value="empty">Food/Drink all categories</option>
                                        <c:if test="${not empty categorylist}">
                                            <c:forEach var="item" items="${categorylist}" varStatus="counter">
                                                <option value="${item.category}" ${param.cbAdCategory == item.category ? 'selected=""':''}>${item.category}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select><br>
                                    <!--status box===================================================--> 
                                    <c:set var="statuslist" value="${requestScope.ADSTATUSLIST}"/>
                                    <select id="status" name="cbAdStatus">
                                        <c:forEach var="statuss" items="${statuslist}" varStatus="counter">
                                            <option value="${statuss.status}" ${param.cbAdStatus == statuss.status ? 'selected=""':''}>${statuss.status}</option>
                                        </c:forEach>
                                    </select>
                        <!--submit=======================================================================================-->
                                </div>
                            </form>
                    </div>
                        <form action="loginPage">
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
                <h1>Products management</h1>
            </nav>
            <div class="ManagerProductPage">
                
                <c:set var="itemInfo" value="${requestScope.ITEMINFO}"/>
                <c:set var="itemCat" value="${requestScope.ITEMCATEGORY}"/>
                <c:set var="isActive" value="${requestScope.ISACTIVE}"/>
                <c:if test="${not empty itemInfo}">
                    <form action="updateAndCreate" enctype="multipart/form-data" method="POST">
                        <!--search value========***********************************==========================-->
                        <!--|--><input type="hidden" name="txtAdSearch" value="${param.txtAdSearch}"/>  <!--|-->
                        <!--|--><input type="hidden" name="cbAdCategory" value="${param.cbAdCategory}"/><!--|-->
                        <!--|--><input type="hidden" name="cbAdStatus" value="${param.cbAdStatus}"/>    <!--|-->
                        <!--|--><input type="hidden" name="curAdPage" value="${param.curAdPage}"/>      <!--|-->
                        <!--=================================================================================-->
                        <input type="hidden" name="cbAlter" value="${itemInfo.id}"/>
                        <input type="hidden" name="txtfoodID" value="${itemInfo.id}"/>
                        <div class="ItemContainer"> 
                            <div class="itemDetail">
                                <div class="itemDetail_category"><d>${itemCat}</d></div>
                                <img class="itemDetail_image" src="${itemInfo.image}"><br>
                                <d class="itemDetail_name">${itemInfo.foodname}</d><br>
                                <textarea class="itemDetail_description" readonly="true">Description: ${itemInfo.description}</textarea><br>
                                <d class="itemDetail_price">Price: $${itemInfo.price}</d><br>
                                <d class="itemDetail_quantity">Quantity: ${itemInfo.quantity}</d><br>
                            </div>
                            <c:set var="itemstatus" value="${itemInfo.status}"/>
                            <div class="updateform_container">
                                <c:if test="${itemstatus == 'Active'}">
                                    <input type="button" class="window_alter_button" id="update_Button" name="" value="Update this Product" onclick="popupAlterwindow('updateformID')"><br>
                                    <input type="button" class="window_alter_button" id="delete_Button" name="" value="Delete this Product" onclick="popupAlterwindow('alterItemStatusWindow')"><br>
                                    <div id="updateformID" class="updateform">
                                        <table>
                                            <tbody>
                                                <c:set var="errors" value="${requestScope.CREATEERRORS}" />
                                                <tr>
                                                    <td>
                                                        Food/Drink name
                                                    </td>
                                                    <td>
                                                        <input id="update_name" type="text" name="txtName" value="${itemInfo.foodname}"/><br>
                                                        <c:if test="${not empty errors.itemNameLengthError}">
                                                            <font color="red">
                                                                ${errors.itemNameLengthError}
                                                            </font><br/>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        Food/Drink price
                                                    </td>
                                                    <td>
                                                        <input id="update_price" type="number" name="txtPrice" min="0.01" step="0.01" value="${itemInfo.price}" rerequired=""/><br>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        Food/Drink category
                                                    </td>
                                                    <td>
                                                        <select id="cat2" name="cbCategory">
                                                            <option value="empty">Choose</option>
                                                            <c:if test="${not empty categorylist}">
                                                                <c:forEach var="updateitem" items="${categorylist}" varStatus="counter">
                                                                    <option value="${updateitem.category}" ${itemCat == updateitem.category ? 'selected=""':''}>${updateitem.category}</option>
                                                                </c:forEach>
                                                            </c:if>
                                                        </select>
                                                            <c:if test="${not empty errors.itemCategoryError}">
                                                                <font color="red">
                                                                    ${errors.itemCategoryError}
                                                                </font><br/>
                                                            </c:if>
                                                            
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        Food/Drink quantity
                                                    </td>
                                                    <td>
                                                        <input id="update_quantity" type="number" name="txtQuantity" min="1" step="1" value="${itemInfo.quantity}" required=""/><br>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        Food/Drink description
                                                    </td>
                                                    <td>
                                                        <textArea id="update_description" name="txtdescription" value="${itemInfo.description}">${itemInfo.description}</textArea><br>
                                                        <c:if test="${not empty errors.itemDescriptionLengthError}">
                                                            <font color="red">
                                                                ${errors.itemDescriptionLengthError}
                                                            </font><br/>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        Food/Drink image
                                                    </td>
                                                    <td>
                                                        <input type="hidden" name="txtfile" value="${itemInfo.image}">
                                                        <img id="uploadImg" width="200" height="100" src="${itemInfo.image}"/>
                                                        <input id="update_Image" type="file" name="fileImage" value=""><br>
                                                        <input class="submit" type="button" name="" value="reset image" onclick="onResetImg('uploadImg','update_Image','4','${itemInfo.image}')">
                                                        <c:if test="${not empty errors.imagePathError}">
                                                            <font color="red">
                                                                ${errors.imagePathError}
                                                            </font><br/>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td><input class="button" type="button" name="" value="Cancle" onclick="closeAlterwindow('updateformID')"></td>
                                                    <td>
                                                        <input id="submitButtonID" class="submitButton" type="button" name="" value="Update" onclick="popupAlterwindow('WarningwindowContainer')">
                                                    </td>
                                                </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                    
                                </c:if>
                                <c:if test="${itemstatus == 'Inactive'}">
                                    <input type="button" class="window_alter_button" id="reactive_Button" name="btAction" value="Reactivate this Product" onclick="popupAlterwindow('alterItemStatusWindow')"><br>
                                </c:if>
                                    <input type="submit" class="window_alter_button" id="return_button" name="btAction" value="Return to Products Management Page">
                                    
                                    <div id="alterItemStatusWindow" class="updateform">
                                        <h2 id="alterItemStatusWindow_detail"></h2><br>
                                        <div id="alterItemStatusWindow_button">
                                            <input type="button" class="cancelButton" id="cancelAlter" name="" value="Cancel" onclick="closeAlterwindow('alterItemStatusWindow')">
                                            <input type="submit" class="submitButton" id="confirmAlter" name="btAction" value="">    
                                        </div>
                   
                                    </div>    
                            </div>
                                    
                        <!--popup window=========================================================================-->
                            <div id="WarningwindowContainer">
                                <div id="AlterWindow">
                                    <h2 id="alterText">Do you want to Update this Product?</h2><br>
                                    <input id="expText" type="hidden" name="checkUpdateMessage" value="Updated:date;">
                                    <div>
                                        <input class="cancelButton" id="cancellation" type="button" name="" value="Cancle" onclick="closeAlterwindow('WarningwindowContainer')">
                                        <input class="submitButton" id="confirm" type="submit" name="btAction" value="Confirm update" onclick="getCheckMessage('expText')">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
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
