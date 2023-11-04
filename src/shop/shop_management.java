package shop;
import java.io.IOException;
import java.util.Scanner;

public class shop_management {
	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		boolean caIKeepRunningTheProgram=true;
		System.out.println("@@@ Login Page @@@");
		System.out.println("Enter Username"); 
		String username=sc.next();
		System.out.println("Enter Password");
		String password=sc.next();
		if(user.user_management.login(username, password)) {
			System.out.println("Login Failed");
			return;
		}
		
		while(caIKeepRunningTheProgram==true) {
			System.out.println("@@@@ Welcome To Shop Managemet @@@@");
			System.out.println("1.User Management");
			System.out.println("2.Product Management");
			System.out.print("3.Quit");
			int a=sc.nextInt();
			if(a==1) {
				user.user_management.usermanagement(); 
			}else if(a==2) {
				product.product_management.productmanagemet();
			}else if(a==3) {
				caIKeepRunningTheProgram=false;
				System.out.println("Program closed");
			}
		}
	}

}
