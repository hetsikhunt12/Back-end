package jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.ResultSet;

public class JDBCDemo {

	static int id;
	static String fname,lname,email;
	static long mobile;
	
	public static Connection createConnection()
	{
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3307/java_4_new","root","");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void insertData()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter First Name : ");
		fname=sc.next();
		System.out.print("Enter Last Name : ");
		lname=sc.next();
		System.out.print("Enter email : ");
		email=sc.next();
		System.out.print("Enter mobile : ");
		mobile=sc.nextLong();
		try {
			Connection conn=createConnection();
			String sql1="select * from student where email=?";
			String sql="insert into student(fname,lname,email,mobile) values(?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			PreparedStatement pst1=conn.prepareStatement(sql1);
			pst1.setString(1, email);
			ResultSet rs=pst1.executeQuery();
			if(rs.next())
			{
				System.out.println("Email Id Already Registered.");
			}
			else 
			{
				pst.setString(1, fname);
				pst.setString(2, lname);
				pst.setString(3, email);
				pst.setLong(4, mobile);
				pst.executeUpdate();
				System.out.println("Student Data Inserted Successfully");

			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	public static void searchstudent()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter student ID : ");
		id=sc.nextInt();
		try {
			Connection conn=createConnection();
			String sql="select * from student where id=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				System.out.println("ID : "+rs.getInt("id"));
				System.out.println("First name : "+rs.getString("fname"));
				System.out.println("Last name : "+rs.getString("lname"));
				System.out.println("Email : "+rs.getString("email"));
				System.out.println("Mobile : "+rs.getLong("mobile"));
			}
			else
			{
				System.out.println("ID not Registerd");
			}
		} catch (Exception e) {
			e.printStackTrace();		}
	}
	public static void updatestudent()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter student ID : ");
		id=sc.nextInt();
		try {
			Connection conn=createConnection();
			String sql="select * from student where id=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				System.out.println("ID : "+rs.getInt("id"));
				System.out.println("First name : "+rs.getString("fname"));
				System.out.println("Last name : "+rs.getString("lname"));
				System.out.println("Email : "+rs.getString("email"));
				System.out.println("Mobile : "+rs.getLong("mobile"));
				System.out.println("\n\nNow Enter New Details But You can't change Your Email ID");
				System.out.print("Enter First Name : ");
				fname=sc.next();
				System.out.print("Enter Last Name : ");
				lname=sc.next();
				System.out.print("Enter Mobile : ");
				mobile=sc.nextLong();
				String sql1="update student set fname=?,lname=?,mobile=? where id=?";
				PreparedStatement pst1=conn.prepareStatement(sql1);
				pst1.setString(1, fname);
				pst1.setString(2, lname);
				pst1.setLong(3, mobile);
				pst1.setInt(4, id);
				pst1.executeUpdate();
				System.out.println("Data Updated succesfully");
			}
			else
			{
				System.out.println("ID not Registerd");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void deletestudent()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter student ID : ");
		id=sc.nextInt();
		try {
			Connection conn=createConnection();
			String sql="delete from student where id=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void showAllstudent()
	{
		try {
			Connection conn=createConnection();
			String sql="select * from student";
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				System.out.println("********************************");
				System.out.println("ID : "+rs.getInt("id"));
				System.out.println("First name : "+rs.getString("fname"));
				System.out.println("Last name : "+rs.getString("lname"));
				System.out.println("Email : "+rs.getString("email"));
				System.out.println("Mobile : "+rs.getLong("mobile"));
				System.out.println("********************************");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		while(true)
		{
			System.out.println("********************************");
			System.out.println("1.Insert Student Data");
			System.out.println("2.search Student");
			System.out.println("3.Update Student");
			System.out.println("4.Delete Student");
			System.out.println("5.show All Student");
			System.out.println("6.Exit");
			System.out.println("********************************");
			System.out.print("Enter Your Choice : ");
			Scanner sc=new Scanner(System.in);
			int choice=sc.nextInt();
			
			if(choice==1)
			{
				insertData();
			}
			else if(choice==2)
			{
				searchstudent();
			}
			else if(choice==3)
			{
				updatestudent();
			}
			else if(choice==4)
			{
				deletestudent();
			}
			else if(choice==5)
			{
				showAllstudent();
			}
			else if(choice==6)
			{
				System.out.println("Thank you..");
				System.out.println("********************************");
				break;
			}
		}
	}
}
