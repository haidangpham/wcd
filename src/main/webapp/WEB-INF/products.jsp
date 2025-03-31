<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Management</title>
    <style>
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid black; padding: 8px; text-align: center; }
        img { width: 50px; height: 50px; }
        .btn { padding: 5px 10px; cursor: pointer; }
    </style>
</head>
<body>
    <h2>Product List</h2>
    <table>
        <tr>
            <th>Code</th>
            <th>Product Name</th>
            <th>Image</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="product" items="${listProducts}">
        	<tr>
                <td>${product.code}</td>
                <td>${product.name}</td>
                <td><img src="${product.imagePath}" class="img-thumbnail" style="width: 50px; height: 50px;"></td>
                <td>${product.price}</td>
                <td>
                    <a href="products?action=addCart?code=${product.code}">Add to Cart</a>
                    <a href="products?action=update?code=${product.code}">Edit</a>
                    <a href="products?action=delete?code=${product.code}">Delete</a>
                </td>
            </tr>
        </c:forEach>
            
            
        <tr>
            <td>P001</td>
            <td>Product 1</td>
            <td><img src="images/sp1.jpg" alt="Product 1"></td>
            <td>$10.00</td>
            <td>
                <button class="btn">Edit</button>
                <button class="btn">Delete</button>
            </td>
        </tr>
        <tr>
            <td>P002</td>
            <td>Product 2</td>
            <td><img src="images/sp2.jpg" alt="Product 2"></td>
            <td>$15.00</td>
            <td>
                <button class="btn">Edit</button>
                <button class="btn">Delete</button>
            </td>
        </tr>
    </table>
    
    <h2>Add New Product</h2>
    <form action="addProduct.jsp" method="post" enctype="multipart/form-data">
        <label for="code">Product Code:</label>
        <input type="text" id="code" name="code" required><br>
        <label for="name">Product Name:</label>
        <input type="text" id="name" name="name" required><br>
        <label for="image">Image:</label>
        <input type="file" id="image" name="image" required><br>
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" step="0.01" required><br>
        <button type="submit" class="btn">Add Product</button>
    </form>
</body>
</html>
