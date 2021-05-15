<%-- 
    Document   : home
    Created on : Jan 6, 2021, 10:55:31 PM
    Author     : HMT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value="/webjars/bootstrap/4.5.3/css/bootstrap.min.css"/>"
              type="text/css" rel="stylesheet"/>
        
    </head>
    <body>
        <div class="container">
            <div class="row">
                 <div class="col-md-12 col-lg-12" 
                     style="text-align: center">
                    <h2>List Product</h2>
                </div>
            </div>
            
            <div class="row" style="padding-bottom: 10px">
                <div class="col-md-12 col-lg-12">
                    <button onclick="location.href= '<c:url value="/add-product"/>' "
                        class="btn btn-info">
                        Add Product
                     </button> 
                
                    <form action="${pageContext.request.contextPath}/search"
                          method="post" class="form-inline float-right">
                        <div class="form-group" >
                            <input type="text" name="strSearch" class="form-control" />
                            <input type="submit" value="Search" class="btn btn-default"/>
                        </div>
                    </form>    
                </div>          
            </div>
            
            <div class="row">              
            <c:if test="${mes != null && mes!=''}">           
            
                <div class="col-md-12 col-lg-12">
                    <c:if test="${mesType!=null && mesType=='success'}">
                        <div class="alert alert-success" role="alert">
                            ${mes}
                        </div>
                    </c:if>
                    
                    <c:if test="${mesType!=null && mesType=='error'}">
                        <div class="alert alert-danger" role="alert">
                            ${mes}
                        </div>
                    </c:if>
                </div>
                
            </c:if>
            </div>
            <div class="row">
                <div class="col-md-12 col-lg-12" >
                    <!-- table-->
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <tr>
                                <th>Name</th>
                                <th>Category</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Publish Date</th>
                                <th>Image</th>
                                <th>Action</th>
                            </tr>
                            <c:if test="${products != null && fn:length(products)>0}">
                            <c:forEach var="p" items="${products}">
                                <tr>
                                    <td>${p.name}</td>
                                    <td>${p.category.name}</td>
                                    <td>
                                        <fmt:setLocale value="vi_VN" scope="session"/>
                                        <fmt:formatNumber type="currency"
                                                          value="${p.price}"
                                                          currencySymbol="VND"/>
                                    </td>
                                    <td>${p.quantity}</td>
                                    <td>
                                        <fmt:formatDate value="${p.publishDate}"
                                                        pattern="dd/MM/yyyy"/>
                                    </td>
                                    <td>   
                                        <c:forEach items="${p.image}" var="image">              
                                            <img src="<c:url value="/resources/images/${image.name}"/>"  style="height: 50px; width: 50px"/>                                               
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <button onclick="location.href= '<c:url value="/edit-product/${p.id}"/>' "
                                            class="btn btn-info">
                                            Edit Product
                                        </button>
                                        <button onclick="location.href= '<c:url value="/delete-product/${p.id}"/>' "
                                            class="btn btn-danger">
                                              Delete
                                        </button>     
                                    </td>
                                </tr>
                            </c:forEach>
                            </c:if>
                            <c:if test="${products == null || fn:length(products)<=0}">
                                <tr>
                                    <td style="color: red" colspan="9">Not Found!!!</td>
                                </tr>
                            </c:if>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
