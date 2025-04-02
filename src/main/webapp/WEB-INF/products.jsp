<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2 class="mb-4">Product Management</h2>
        <a href="products?action=add" class="btn btn-primary mb-3">Add Product</a>
        
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${listProducts}">
        	<tr>
                <td>${product.code}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td><img src="${product.imagePath}" class="img-thumbnail" style="width: 50px; height: 50px;"></td>
                <td>
                   <a href="products?action=update&code=${product.code}" class="btn btn-warning btn-sm">Edit</a>
                        <a href="products?action=delete&code=${product.code}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
  
                
            </tbody>
        </table>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


