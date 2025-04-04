package com.mytech.shopmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import com.mytech.shopmanagement.db.DbConnector;
import com.mytech.shopmanagement.models.CartLine;
import com.mytech.shopmanagement.models.Customer;
import com.mytech.shopmanagement.models.Product;
import com.mytech.shopmanagement.models.ShopCart;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class ShopCartDao {
    public boolean addShopCart(Customer customer, Product product, int quantity) {
        EntityManager entityManager = DbConnector.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            // 🔹 1. Kiểm tra giỏ hàng của khách hàng
            TypedQuery<ShopCart> query = entityManager.createQuery(
                "SELECT sc FROM ShopCart sc WHERE sc.customer = :customer", ShopCart.class);
            query.setParameter("customer", customer);
            List<ShopCart> resultList = query.getResultList();

            ShopCart shopCart;
            if (!resultList.isEmpty()) {
                shopCart = resultList.get(0); // Lấy giỏ hàng có sẵn
            } else {
                shopCart = new ShopCart();
                shopCart.setCustomer(customer);
                shopCart.setTotal(0);
                entityManager.persist(shopCart);
            }

            // 🔹 2. Kiểm tra nếu sản phẩm đã có trong giỏ hàng
            CartLine existingCartLine = null;
            for (CartLine cl : shopCart.getCartLines()) {
                if (cl.getProduct().getProductCode().equals(product.getProductCode())) {
                    existingCartLine = cl;
                    break;
                }
            }

            if (existingCartLine != null) {
                // Nếu sản phẩm đã có, cập nhật số lượng
                existingCartLine.setQuantity(existingCartLine.getQuantity() + quantity);
                existingCartLine.setPrice(existingCartLine.getQuantity() * product.getPrice());
            } else {
                // 🔹 3. Tạo CartLine mới
                CartLine newCartLine = new CartLine(product, quantity);
                newCartLine.setShopCart(shopCart);
                newCartLine.setPrice(quantity * product.getPrice());

                entityManager.persist(newCartLine);
                shopCart.getCartLines().add(newCartLine);
            }

            // 🔹 4. Cập nhật tổng tiền giỏ hàng
            double total = 0;
            for (CartLine cl : shopCart.getCartLines()) {
                total += cl.getPrice();
            }
            shopCart.setTotal(total);

            // 🔹 5. Lưu giỏ hàng vào database
            entityManager.merge(shopCart);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }
}
