package com.mytech.shopmanagement.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ShopCart")
public class ShopCart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long cartId;
	@OneToOne
	Customer customer;
	@OneToMany(mappedBy = "shopCart")
	List<CartLine> cartLines;
	
	public ShopCart() {
        this.cartLines = new ArrayList<>(); // 🔹 Khởi tạo danh sách rỗng ngay từ đầu
    }
	double total;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<CartLine> getCartLines() {
		return cartLines;
	}

	public void setCartLines(List<CartLine> cartLines) {
		this.cartLines = cartLines;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
