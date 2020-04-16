<%-- 
    Document   : ManagerProduct
    Created on : Feb 14, 2020, 10:14:51 AM
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
        <link rel="stylesheet" href="css/pagging.css" type="text/css">
        <link rel="stylesheet" href="css/productManagement.css" type="text/css">
        <link rel="stylesheet" href="js/productManagement.js" type="text/javascript">
        <link rel="stylesheet" href="js/changePage.js" type="text/javascript">
        <script src="js/productManagement.js"></script>
        <title>Product management</title>
    </head>
    <body onload="loadWarningWindow('${requestScope.ISACTIVE}'); showNotification('${requestScope.NOTIFICATION}','notifyContainer');">
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
                    <h1>Products management</h1>
                </form>
                
            </nav>
            <div class="ManagerProductPage">
                
                <c:set var="itemlist" value="${requestScope.PRODUCTLIST}"/>
                <c:set var="isActive" value="${requestScope.ISACTIVE}"/>
                <c:if test="${not empty itemlist}">
                    <div class="itemTable">
                        <form action="updateAndCreate" method="POST">
                            <table border="1" style="table-layout: fixed">
                                <thead>
                                    <tr>
                                        <th>ID.</th>
                                        <th>Product Image</th>
                                        <th>Product Name</th>
                                        <th>Product Price/item</th>
                                        <th>Product Quantity</th>
                                        <th>Sold</th>
                                        <th>Update/Create Date</th>
                                        <th>Update detail</th>
                                        <c:if test="${not empty isActive}">
                                            <th>Delete</th>
                                        </c:if>
                                        <c:if test="${empty isActive}">
                                            <th>Re-Activate</th>
                                        </c:if>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="counts" value="${1}"/>
                                    <input id="itemFoodID" type="hidden" name="txtfoodID" value="">
                                    <c:forEach var="dto" items="${itemlist}" varStatus="counters">
                                        <tr>
                                            <td>${counters.count}.${dto.id}</td>
                                            <td>
                                                <img class="itemImg" src="${dto.image}">
                                            </td>
                                            <td>
                                                ${dto.foodname}
                                            </td>
                                            <td>
                                                $${dto.price}
                                            </td>
                                            <td>
                                                ${dto.quantity}
                                            </td>
                                            <td>
                                                ${dto.sold}
                                            </td>
                                            <td>
                                                ${dto.date}
                                            </td>
                                            <td>
                                                <input class="detailBox" type="submit" name="btAction" value="detail" onclick="setItemID('itemFoodID','${dto.id}')">
                                            </td>
                                            <td>
                                                <c:set var="AlterItemID" value="alterItem${counts}"/>
                                                <c:if test="${not empty isActive}">
                  
                                                    <input class="Alterbox" id="${AlterItemID}" type="checkbox" name="cbAlter" value="${dto.id}" onclick="checkAlter('${AlterItemID}')">
                                                </c:if>
                                                <c:if test="${empty isActive}">
                                                    <input class="Alterbox" id="${AlterItemID}" type="checkbox" name="cbAlter" value="${dto.id}" onclick="checkAlter('${AlterItemID}')">
                                                </c:if>
                                            </td>
                                        </tr>
                                        <c:set var="counts" value="${counts+1}"/>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="buttonContainer">
                                <input type="hidden" id="countAlterRow" value="${0}">
                                <input type="hidden" name="txtAdSearch" value="${param.txtAdSearch}"/>
                                <input type="hidden" name="cbAdCategory" value="${param.cbAdCategory}"/>
                                <input type="hidden" name="cbAdStatus" value="${param.cbAdStatus}"/>
                                <input type="hidden" name="curAdPage" value="${param.curAdPage}"/>
                                <c:if test="${not empty isActive}">
                                    <input id="btAlterBox" type="button" value="Delete" onclick="popupAlterwindow('WarningwindowContainer')" disabled="true">
                                </c:if>
                                <c:if test="${empty isActive}">
                                    <input id="btAlterBox" type="button" value="Reactivate" onclick="popupAlterwindow('WarningwindowContainer')" disabled="true">
                                </c:if>
                            </div>
                        <!--popup window=========================================================================-->
                        <div id="WarningwindowContainer">
                                <div id="AlterWindow">
                                    <h2 id="alterText"></h2><br>
                                    <div>
                                        <input id="cancellation" type="button" name="" value="Cancle" onclick="closeAlterwindow('WarningwindowContainer')">
                                        <input id="confirm" type="submit" name="btAction" value="">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                                
                    <div class="Pagging">
                        <c:set var="lastPage" value="${requestScope.TOTALPAGE_ORDER_BY_DATE}"/>
                        <c:set var="Page" value="${requestScope.CUR_PAGE}"/>
                        <c:set var="pagelist" value="${lastPage>=7 ? 7:lastPage}"/>
                        <c:set var="offset" value="${1}"/>
                        <div class="pagecontainer">
                            <form action="updateAndCreate" method="POST">
                                <input type="hidden" name="txtAdSearch" value="${param.txtAdSearch}"/>
                                <input type="hidden" name="btAction" value="View and Update Products"/>
                                <input type="hidden" name="cbAdCategory" value="${param.cbAdCategory}"/>
                                <input type="hidden" name="cbAdStatus" value="${param.cbAdStatus}"/>
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
                            <script src="js/changePage.js"></script>
                        </div>
                    </div>
                </c:if>
                <c:if test="${empty itemlist}">
                    <h1>Could not find any product by keyword '${param.txtAdSearch}'</h1>
                </c:if>
                <div id="notifyContainer">
                        <h2 id="notifyText">${requestScope.NOTIFICATION}</h2><br>
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
