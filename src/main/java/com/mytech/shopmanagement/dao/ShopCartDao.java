package com.mytech.shopmanagement.dao;

import com.mytech.shopmanagement.db.DbConnector;
import com.mytech.shopmanagement.models.Customer;
import com.mytech.shopmanagement.models.Product;

import jakarta.persistence.EntityManager;

public class ShopCartDao {
	public boolean addShopCart(Customer customer, Product product, int quantity) {
		EntityManager entityManager= DbConnector.getEntityManager();
		//Lấy giỏ hàng --> tạo cartLine từ Product với quantity --> Thêm cartline vào shopcart --> lưu shopcart
		return true;
	}

}
