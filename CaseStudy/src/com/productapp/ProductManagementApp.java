package com.productapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import com.bean.Product;
import com.dao.ProductManagementDao;

public class ProductManagementApp 
{
	//read input from keyboard
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	//call our data(Dao) method
	static ProductManagementDao pmd=new ProductManagementDao();
	static int option;
	
	public static void main(String[] args) throws Exception
	{
		do{
			System.out.println("*********************************");
			System.out.println("Product Management System");
			System.out.println("*********************************");
			System.out.println("1. View Product");
			System.out.println("2. Add Product");
			System.out.println("3. Update Products");
			System.out.println("4. Delete Product");
			System.out.println("5. Search Product");
			System.out.println("6. Exit");
			System.out.println("*********************************");
			System.out.println("Enter an option: ");
			System.out.println("*********************************");
			System.out.println("\n");			
			try {
				option = Integer.parseInt(br.readLine());
			}
			catch(NumberFormatException e) {
				System.out.println("enter a valid number");
				option=0;
			}
			switch(option)
			{
				case 1 : viewProduct();
						 break;
				case 2 : addProduct();
						 break;
				case 3 : updateProduct();
						 break;
				case 4 : deleteProduct();
						 break;
				case 5 : searchProduct();
						 break;
				case 6 : System.out.println("*************Thank You*************");
						 System.exit(0);
						 break;
				default: System.out.println("Invalid Option! Please enter again");
			}
		}while(option!=6);
	}	
	public static void viewProduct() {
		System.out.println("view all products:");
		//get all products to a product type productList
		List<Product> productList=pmd.getAllProducts();
		for(Product product: productList) {
			displayProduct(product);
		}
		System.out.println("\n");
	}
	
	public static void addProduct() throws Exception{
		System.out.println("add product details:");
		try{
			System.out.println("Enter product id:");
			int productId=Integer.parseInt(br.readLine());
			System.out.println("Enter product name:");
			String productName=br.readLine();
			System.out.println("Enter product price:");
			double productPrice=Double.parseDouble(br.readLine());
			//store in a product variable
			Product product=new Product(productId,productName,productPrice);
			int status=pmd.addProduct(product);
			if(status==1) {
				System.out.println("product added successfully");
			}else {
				System.out.println("please enter valid product details");
			}
		}catch(NumberFormatException e) {
			System.out.println("enter valid details of product");
		}
		System.out.println("\n");
	}
	
	public static void updateProduct() throws Exception{
		try {
		System.out.println("update product details:");
		System.out.println("Enter product id:");
		int productId=Integer.parseInt(br.readLine());
		System.out.println("Enter product name:");
		String productName=br.readLine();
		System.out.println("Enter product price:");
		double productPrice=Double.parseDouble(br.readLine());
		//store in a product variable
		Product product=new Product(productId,productName,productPrice);
		int status=pmd.updateProduct(product);
		if(status==1) {
			System.out.println("product updated successfully");
		}
		else {
			System.out.println("enter valid details of product");
		}
		System.out.println("\n");
		}catch(NumberFormatException e) {
			System.out.println("enter valid details");
			System.out.println("\n");
		}
	}
	
	public static void deleteProduct() throws Exception{
		try {
		System.out.println("delete product:");
		System.out.println("Enter product id:");
		int productId=Integer.parseInt(br.readLine());
		int status=pmd.deleteProduct(productId);
		if(status==1) {
		System.out.println("product deleted successfully");
		System.out.println("\n");
		}
		else {
			System.out.println("enter valid product id");
		}
		}catch(NumberFormatException e) {
			System.out.println("enter valid product id");
			System.out.println("\n");
		}
	}
	
	public static void searchProduct() throws Exception{
		try {
		System.out.println("search product");
		System.out.println("Enter product id:");
		int productId=Integer.parseInt(br.readLine());
		Product product=pmd.getProductById(productId);
		displayProduct(product);
		System.out.println("\n");
		}
		catch(NumberFormatException e) {
			System.out.println("enter valid product id");
		}
		catch(NullPointerException e) {
			System.out.println("enter a valid details");
			System.out.println("\n");
		}
	}
	
	public static void displayProduct(Product product) {
		System.out.println("display of product details");
		System.out.println("Product Id: "+product.getProductId());
		System.out.println("Product Name: "+product.getProductName());
		System.out.println("Product Price: "+product.getProductPrice());
		System.out.println("\n");
	}

}
