package com.mytech.shopmanagement.dao;

import java.util.List;

import com.mytech.shopmanagement.db.DbConnector;
import com.mytech.shopmanagement.models.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class ProductDao {

	public List<Product> getProduct() {
		EntityManager entityManager = DbConnector.getEntityManager();
		Query query = entityManager.createNamedQuery("Product.findAll", Product.class);
		return query.getResultList();
	}

	// GET PRODUCT ID
	public Product getProductByCode(String code) {
		EntityManager entityManager = DbConnector.getEntityManager();
		Query query = entityManager.createNamedQuery("Product.findByCode");
		query.setParameter("code", code);

		return (Product) query.getSingleResult();
	}

	// GET PRODUCT NAME
	public Product getProductByName(String name) {
		EntityManager entityManager = DbConnector.getEntityManager();
		Query query = entityManager.createNamedQuery("Product.findByName");
		query.setParameter("name", "%" + name + "%");

		return (Product) query.getSingleResult();
	}

	// ADD PRODUCT
	public boolean addProduct(Product product) {
		EntityManager entityManager = DbConnector.getEntityManager();
		var trans = entityManager.getTransaction();
		trans.begin();
		entityManager.persist(product);
		trans.commit();
		return true;
	}

	// UPDATE PRODUCT
	public boolean updateProduct(Product product) {
		EntityManager entityManager = DbConnector.getEntityManager();
		var trans = entityManager.getTransaction();
		trans.begin();

		Product updProduct = entityManager.find(Product.class, product.getCode());
		if (updProduct != null) {
			updProduct.setName(product.getName());
			updProduct.setPrice(product.getPrice());
			updProduct.setImagePath(product.getImagePath());
			entityManager.merge(updProduct);
		}

		trans.commit();

		return true;
	}

	// DELETE PRODUCT
	public boolean deleteProductByCode(Product code) {
		EntityManager entityManager = DbConnector.getEntityManager();
		Product delProduct = entityManager.find(Product.class, code);

		entityManager.remove(delProduct);
		return true;
	}
}