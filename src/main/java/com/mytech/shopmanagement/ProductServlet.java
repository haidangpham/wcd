package com.mytech.shopmanagement;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mytech.shopmanagement.dao.ProductDao;
import com.mytech.shopmanagement.dao.ProductJDBCDao;
import com.mytech.shopmanagement.helpers.ServletHelper;
import com.mytech.shopmanagement.models.Product;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/products")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDao productDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		productDao = new ProductDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if ("add".equals(action)) {
			ServletHelper.forward(request, response, "add_product");
		} else if ("update".equals(action)) {
			String code = request.getParameter("code");
			Product product = productDao.getProductByCode(code);
			if (product != null) {
				request.setAttribute("product", product);
				ServletHelper.forward(request, response, "edit_product");
			}else {
				ServletHelper.forward(request, response, "error");
			}

		}

		else {

			List<Product> listProducts = productDao.getProduct();
			request.setAttribute("listProducts", listProducts);

			for (Product product : listProducts) {

			}
			ServletHelper.forward(request, response, "products");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action= request.getParameter("action");
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String priceString = request.getParameter("price");
		double price = Double.parseDouble(priceString);
		// String imagePath= request.getParameter("imagePath");
		
		Product product = new Product(code, name, price, "");
		if("update".equals(action)) {
			productDao.updateProduct(product);
		}else {
			productDao.addProduct(product);
		}
		
		ServletHelper.redirect(request, response, "products");
	}

}
