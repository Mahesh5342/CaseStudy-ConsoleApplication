package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.Product;
import com.daoconnec.DatabaseConnection;

public class ProductManagementDao {
	Connection connection=null;
	Statement statement=null;
	ResultSet resultSet=null;
	PreparedStatement ps=null;
	//get all products method
	public List<Product> getAllProducts() 
	{
		List<Product> productList=new ArrayList<Product>();
		try
		{
			connection=DatabaseConnection.getConnection1();
			statement=connection.createStatement();
			resultSet=statement.executeQuery("Select PRODID,PRODNAME,PRODPRICE from product");
			while(resultSet.next()) {
		    Product product=new Product(resultSet.getInt("PRODID"), resultSet.getString("PRODNAME"), resultSet.getInt("PRODPRICE"));
				productList.add(product);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		return productList;
		
	}
	
	//add product method
	public int addProduct(Product product)
	{
		int status=0;
		try
		{
			connection=DatabaseConnection.getConnection1();
			ps=connection.prepareStatement("insert into product values(?,?,?)");
			ps.setInt(1, product.getProductId());
			ps.setString(2, product.getProductName());
			ps.setDouble(3, product.getProductPrice());
			status=ps.executeUpdate();
		}
		catch(SQLIntegrityConstraintViolationException e) {
			System.out.println("enter a valid product id");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
		
	}
	//update product details method
	public int updateProduct(Product product) 
	{
		int status=0;
		try
		{
			connection=DatabaseConnection.getConnection1();
			ps=connection.prepareStatement("update product set PRODNAME=?,PRODPRICE=?,where PRODID=?");
			ps.setString(1, product.getProductName());
			ps.setDouble(2, product.getProductPrice());
			ps.setInt(3, product.getProductId());
			status =ps.executeUpdate();
		}
		catch(SQLSyntaxErrorException e) {
			System.out.println("enter valid product id");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return status;
	}
	//delete product method
	public int deleteProduct(Integer productId)
	{
		int status=0;
		try
		{
			connection=DatabaseConnection.getConnection1();
			ps=connection.prepareStatement("delete from product where PRODID=?");
			ps.setInt(1, productId);
			status=ps.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return status;
	}
	//get product by productId method
	public Product getProductById(int productId)
	{
		Product product=null;
		try {
			connection=DatabaseConnection.getConnection1();
			ps= connection.prepareStatement("select PRODNAME,PRODPRICE from product where PRODID=?");
			ps.setInt(1, productId);
			ResultSet resultSet=ps.executeQuery();
			while(resultSet.next())
			{
				product=new Product(resultSet.getInt("PRODID"), resultSet.getString("PRODNAME"), resultSet.getInt("PRODPRICE"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
					connection.close();
				} catch (SQLException e) {
						e.printStackTrace();
				}
			}
		return product;
		}

}
