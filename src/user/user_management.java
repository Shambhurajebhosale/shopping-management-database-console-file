package user;
	import java.util.Iterator;
	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.File;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.Scanner;

	import com.mysql.cj.xdevapi.PreparableStatement;

	import db_operations.DBUtils;

	public class user_management {
		static ArrayList<user> al=new ArrayList();
		public static void usermanagement() throws IOException {
			Scanner sc=new Scanner(System.in);
			boolean caniKeepRunningTheProgram=true;
			while(caniKeepRunningTheProgram==true) {
				System.out.println("** Welcome To User Management **");
				//System.out.println("\n");
				System.out.println("1.Add User");
				System.out.println("2.Search User");
				System.out.println("3.Edit User");
				System.out.println("4.Delete user");
				System.out.println("5.Quit User");
				int option=sc.nextInt();
				if(option==1) {
					adduser();
					System.out.println("\n");
				}else if(option==2) {
					System.out.println("Enter Name Which You Want To search");
					sc.nextLine();
					String s=sc.nextLine();
					serachUser(s);
				}else if(option==3) {
					System.out.println("Enter Name Which You Want To Edit");
					sc.nextLine();
					String e=sc.nextLine();
					editUser(e);
				}else if(option==4) {
					System.out.println("Enter Name which You Want To delete :");
					sc.nextLine();
					String d=sc.next();
					deleteUser(d);
				}else if(option==5) {				
					System.out.println(" Program Closed ");
					caniKeepRunningTheProgram=false;
				}
			}
		}
		public static void adduser() {
			Scanner sc=new Scanner(System.in);
			user u=new user();
			System.out.println("Enter Username :");
			u.username=sc.nextLine();
			System.out.println("Enter Password :");
			u.password=sc.nextLine();
			System.out.println("Enter Address :");
			u.address=sc.nextLine();
			System.out.println("Enter College Name :");
			u.college=sc.nextLine();
			
			System.out.println("Username :"+u.username);
			System.out.println("Password :"+u.password);
			System.out.println("Address :"+u.address);
			System.out.println("College :"+u.college);
				
			String query="INSERT INTO user(username,password,address,college) Values ('"+u.username+"','"+u.password+"','"+u.address+"','"+u.college+"')";
			DBUtils.executeQuery(query);
		}
		public static void serachUser(String username) {
			ResultSet rs=DBUtils.executeQueryGetResult("select * from user where username="+username);
			try {
				while(rs.next()) {
					user u=new user();
					u.username=rs.getString(1);
					u.password=rs.getString(2);
					u.address=rs.getString(3);
					u.college=rs.getString(4);
					
					System.out.println("Username :"+u.username);
					System.out.println("Password :"+u.password);
					System.out.println("Address :"+u.address);
					System.out.println("College :"+u.college);
					return;
				}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			System.out.println("User Not Found");		    
		}
		public static void editUser(String username) {
			
			String Query = "select * from User where Username = '"+username+"'";
			ResultSet rs = DBUtils.executeQueryGetResult(Query);
			try {

				if(rs.next()) {
					Scanner sc = new Scanner(System.in);

					System.out.print("New UserName is :");
					String newUsername = sc.nextLine();

					System.out.print("New Password is :");
					String newPassword = sc.nextLine();

					System.out.print("New Address is :");
					String newAddress = sc.nextLine();

					System.out.print("New College is :");
					String newCollege = sc.nextLine();
					
					String query = "UPDATE User SET username = '"+newUsername+"', "
						    + "password = '" + newPassword + "', " 
						    + "address = '"+newAddress+"', "
						    + "college = '"+newCollege+"'"
						    + "Where Username = '"+username+"'";

					DBUtils.executeQuery(query);

					System.out.println("User updated successfully.");

				}
				else {
					System.out.println("USER NOT FOUND");
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		
		}
		public static void deleteUser(String username) {
			String query = "Delete From User Where Username = '"+username+"'";

			DBUtils.executeQuery(query);

			int rowsDeleted = DBUtils.getRowsDeleted();
			
			if(rowsDeleted > 0) {
				System.out.println("User " + username + " has been deleted");
				return;
			}
			else {
		        System.out.println(" USER NOT FOUND ");
		    }

			
		}

		public static boolean login(String username,String password) throws IOException {
			
			String query = " Select * from User where username='"+username+"' and password = '"+password+"' ";

			ResultSet rs = DBUtils.executeQueryGetResult(query);
			try {
				if(rs.next()) {
					return true;
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return  false;
		}

	}

