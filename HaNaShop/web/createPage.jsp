<%-- 
    Document   : CreatePage
    Created on : Jan 23, 2020, 5:58:15 PM
    Author     : SE130204
--%>
<%@page import="java.util.List"%>
<%@page import="truonghn.itemCategory.ItemCategoryDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new product</title>
        <link rel="stylesheet" href="js/ManagerNotification.js" type="text/javascript">
        <link rel="stylesheet" href="css/Tool.css" type="text/css">
        <link rel="stylesheet" href="css/managerCreate.css" type="text/css">
    </head>
    <body onload="showAdNotification('${requestScope.NOTIFICATION}','Managernotify','${requestScope.ERRORNOTIFY}','Errornotify')">
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
                        
                    <form action="loginPage" method="POST">
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
                <h1>Creating new Product</h1>
            </nav>
                    
            <div class="body">
                <div class="createform">
                        <c:set var="errors" value="${requestScope.CREATEERRORS}" />
                        <c:set var="categorylist" value="${requestScope.CATEGORY}"/>
                        <h1>Create new Food/Drink in menu.</h1><br>
                        <form name="productForm" id="productForm" action="updateAndCreate" enctype="multipart/form-data" method="POST">
                            <table>
                                <tbody>
                                    <tr>
                                        <td>
                                            Food/Drink ID
                                        </td>
                                        <td>
                                            <input type="text" name="txtID" value="${param.txtID}"/><br>
                                            <c:if test="${not empty errors.itemIDLengthError}">
                                                <font color="red">
                                                ${errors.itemIDLengthError}
                                                </font><br/>
                                            </c:if>
                                            <c:if test="${not empty errors.itemIDExisted}">
                                                <font color="red">
                                                ${errors.itemIDExisted}
                                                </font><br/>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Food/Drink name
                                        </td>
                                        <td>
                                            <input type="text" name="txtName" value="${param.txtName}"/><br>
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
                                            <input class="requireID" type="number" name="txtPrice" min="0.01" step="0.01" value="${param.txtPrice}" required=""/><br>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Food/Drink category
                                        </td>
                                        <td>
                                            <select id="cat" name="cbCategory">
                                                <option value="empty">Choose</option>
                                                <c:if test="${not empty categorylist}">
                                                    <c:forEach var="item" items="${categorylist}" varStatus="counter">
                                                        <option value="${item.category}" ${param.cbCategory == item.category ? 'selected=""':''}>${item.category}</option>
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
                                            <input class="requireID" type="number" name="txtQuantity" min="1" step="1" required="" value="${txtQuantity}"/><br>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Food/Drink description
                                        </td>
                                        <td>
                                            <textarea type="text" name="txtdescription" value="${param.txtdescription}">${param.txtdescription}</textarea><br>
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
                                            <img id="uploadImg" width="200" height="100" />
                                            <input type="file" name="fileImage" value="${param.fileImage}" placeholder="Please select an image."
                                                   onchange="document.getElementById('uploadImg').src = window.URL.createObjectURL(this.files[0])"/><br>
                                            <c:if test="${not empty errors.imagePathError}">
                                                <font color="red">
                                                ${errors.imagePathError}
                                                </font><br/>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <input class="submit" type="submit" name="btAction" value="Create new">
                                          
                                        </td>
                                    </tr>
                                    
                                </tbody>
                            </table>
                        </form>    
                    
                </div>
                <div id="Managernotify">
                    <h1>${requestScope.NOTIFICATION}</h1>
                </div>
                <div id="Errornotify">
                    <h1>${requestScope.ERRORNOTIFY}</h1>
                </div>
                <script src="js/ManagerNotification.js"></script>
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
