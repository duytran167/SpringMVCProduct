<%-- 
    Document   : edit_product
    Created on : Jan 7, 2021, 12:30:42 AM
    Author     : HMT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
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
                     <c:if test="${action=='edit'}">
                        <h2>Edit Product</h2>
                     </c:if>
                </div>
            </div>
            <mvc:form action="${pageContext.request.contextPath}/${action}"
                      method="post" modelAttribute="product" enctype="multipart/form-data">
                <c:if test="${action == 'edit'}">
                        <input type="text" name="id" value="${product.id}" hidden />
                </c:if>
                <div class="row">
                    <div class="col-md-12 col-lg-12" >
                        <div class="col-md-6 col-lg-6" >
                             <div class="form-group">
                                <label for="name">Name</label>
                                <input type="text" id="name"
                                       class="form-control" name="name" value="${product.name}"/>
                            </div>
                        </div>
                        <div class="col-md-6 col-lg-6" >
                             <div class="form-group">
                                <label>Category</label>
                                <select name="category.id" class="form-control">
                                    <c:forEach var="c" items="${categories}">
                                        <c:if test="${product.category.id == c.id}">
                                            <option value="${c.id}" selected>
                                                ${c.name}
                                            </option>
                                        </c:if> 
                                        <c:if test="${product.category.id != c.id}">
                                            <option value="${c.id}">
                                                ${c.name}
                                            </option>
                                        </c:if> 
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                     </div>
                </div>
                <div class="row">
                    <div class="col-md-12 col-lg-12" >
                        <div class="col-md-6 col-lg-6" >
                             <div class="form-group">
                                <label for="price">Price</label>
                                <input type="text" id="price"
                                       class="form-control" name="price" value="${product.price}" />
                            </div>
                        </div>
                        <div class="col-md-6 col-lg-6" >
                             <div class="form-group">
                                <label for="quantity">Quantity</label>
                                <input type="text" id="quantity"
                                       class="form-control" name="quantity" value="${product.quantity}"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 col-lg-12" >
                        <div class="col-md-6 col-lg-6" >
                             <div class="form-group">
                                <label for="publishDate">Publish Date</label>
                                <input type="date" id="publishDate"
                                       class="form-control" name="publishDate" value="${product.publishDate}"/>
                            </div>
                        </div>
                        <div class="col-md-6 col-lg-6" >
                             <div class="form-group">
                                <label for="description">Description</label>
                                <textarea  id="description"
                                       class="form-control" name="description" value="${product.description}">
                                </textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 col-lg-12">
                        <div class="plugin">
                            <ul>
                                <c:forEach items="${product.image}" var="image">
                                    <li> 
                                        <img src="<c:url value="/resources/images/${image.name}"/>" width="50" height="50">
                                    </li>
                                </c:forEach>
                            </ul>                                                    
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 col-lg-12">
                        <label for="file">Upload Image</label>
                        <input name="file" id="file" type="file" accept="image/png, image/jpg" multiple/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 col-lg-12" style="text-align: center">
                        <button type="submit" class="btn btn-success">
                            Submit
                        </button>
                         
                    </div>
                </div>
                
            </mvc:form>
        </div>
    </body>
</html>
