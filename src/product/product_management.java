package product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import db_operations.DBUtils;
import user.user;

public class product_management {
		static ArrayList<product> al=new ArrayList();

		public static void productmanagemet() throws IOException {
			Scanner sc=new Scanner(System.in);
			boolean canIKeepRunningTheProgram=true;
			while(canIKeepRunningTheProgram==true) {
				System.out.println("** Welcome To Product Managementt ***");
				System.out.println("1.Add Product");
				System.out.println("2.Search Product");
				System.out.println("3.Edit Product");
				System.out.println("4.Delete Product");
				System.out.println("5.Quit Product");
				int option=sc.nextInt();
				if(option==1) {
					addProduct();
					System.out.println("\n");
				}else if(option==2) {
					System.out.println("Enter the name which you To Search");
					sc.nextLine();
					String s=sc.next();
					serachProduct(s);
				}else if(option==3) {
					System.out.println("Enter the name which you To Edit");
					sc.nextLine();
					String editName=sc.next();
					editProduct(editName);
				}else if(option==4) {
					System.out.println("Enter the name which you To Delete");
					sc.nextLine();
					String deleteProduct=sc.next();
					deleteProduct(deleteProduct);
				}else if(option==5) {				 
					canIKeepRunningTheProgram=false;
					System.out.print("Program Closed");
				}
			}
		}
		public static void editProduct(String editName) {
			String Query = "select * from product where Name = '"+editName+"'";
			ResultSet rs = DBUtils.executeQueryGetResult(Query);
			try {

				if(rs.next()) {
					Scanner sc = new Scanner(System.in);

					System.out.print("New ID is :");
					String ID = sc.nextLine();

					System.out.print("New Name is :");
					String Name = sc.nextLine();

					System.out.print("New Price is :");
					String Price = sc.nextLine();

					System.out.print("New Quantity is :");
					String Quantity = sc.nextLine();
					
					String query = "UPDATE product SET ID = '"+ID+"', "
						    + "Name = '" + Name + "', " 
						    + "Price = '"+Price+"', "
						    + "Quantity = '"+Quantity+"'"
						    + "WHERE Name = '"+editName+"'";

					DBUtils.executeQuery(query);

					System.out.println("User updated successfully.");

				}
				else {
					System.out.println("!!!!USER NOT FOUND!!!!!");
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}

		public static void serachProduct(String s) {
			ResultSet rs=DBUtils.executeQueryGetResult("select * from product where Name="+s);
			try {
				while(rs.next()) {
					product p=new product();
					p.ID=rs.getString(1);
					p.name=rs.getString(2);
					p.price=rs.getString(3);
					p.quantity=rs.getString(4);
					
					System.out.println("ID :"+p.ID);
					System.out.println("Name :"+p.name);
					System.out.println("Price :"+p.price);
					System.out.println("Quantity :"+p.quantity);
					return;
				}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			System.out.println("User Not Found");
		    
		}
			

		public static void addProduct() {
			Scanner sc=new Scanner(System.in);
			product u=new product();
			System.out.println("Enter ID :");
			u.ID=sc.nextLine();
			System.out.println("Enter Name :");
			u.name=sc.nextLine();
			System.out.println("Enter Price :");
			u.price=sc.nextLine();
			System.out.println("Enter Quantity :");
			u.quantity=sc.nextLine();
			
			System.out.println("ID :"+u.ID);
			System.out.println("Name :"+u.name);
			System.out.println("Price :"+u.price);
			System.out.println("Quantity :"+u.quantity);
			
			String query="INSERT INTO product(ID,Name,Price,Quantity) Values ('"+u.ID+"','"+u.name+"','"+u.price+"','"+u.quantity+"')";
			DBUtils.executeQuery(query);
			}
		public static void deleteProduct(String deleteProduct) {
			String query = "DELETE FROM product WHERE Name = '"+deleteProduct+"'";

			DBUtils.executeQuery(query);

			int rowsDeleted = DBUtils.getRowsDeleted();
			
			if(rowsDeleted > 0) {
				System.out.println("User " + deleteProduct + " has been deleted");
				return;
			}else {
				System.out.println(" User Not Found");		
		    }
		

	}
	}

