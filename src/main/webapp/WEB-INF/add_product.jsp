<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Product</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
	<h2>Add New Product</h2>
	<form action="products" method="post">
		<label for="code">Product ID:</label>
		<input type="text" id="code"
			name="code" required><br>
			<label for="name">Product Name:</label>
			<input type="text" id="name" name="name" required><br>

		<label for="price">Price:</label> <input type="number" id="price"
			name="price" step="0.01" required><br>


		<button type="submit">Add Product</button>
	</form>