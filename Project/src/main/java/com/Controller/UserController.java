//package com.Controller;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.MultipartConfig;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import jakarta.servlet.http.Part;
//
//import java.io.File;
//import java.io.IOException;
//
//import com.bean.User;
//import com.dao.UserDao;
//
//@WebServlet("/UserController")
//@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 512, maxFileSize = 1024 * 1024 * 512,
//maxRequestSize = 1024 * 1024 * 512) // 512MB
//public class UserController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	private String extractfilename(Part file) {
//	    String cd= file.getHeader("content-disposition");
//	    System.out.println(cd);
//	    String[] items = cd.split(";");
//	    for(String string : items) {
//	    	if(string.trim().startsWith("filename")) {
//	    		return string.substring(string.indexOf("=")+2, string.length() - 1);
//	    	}
//	    }
//	    return "";
//	    		
//	}
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
//	        throws ServletException, IOException {
//
//	    String action = request.getParameter("action");
//	    
////		if(action.equalsIgnoreCase("sign up"))
//	    if(action != null && action.equalsIgnoreCase("sign up"))
//		{
//			boolean flag=UserDao.checkEmail(request.getParameter("email"));
//			if(flag==false)
//			{
//				if(request.getParameter("password").equalsIgnoreCase(request.getParameter("cpassword")))
//				{
//					User u=new User();
//					u.setUsertype(request.getParameter("usertype"));
//					u.setFname(request.getParameter("fname"));
//					u.setLname(request.getParameter("lname"));
//					u.setEmail(request.getParameter("email"));
//					u.setMobile(Long.parseLong(request.getParameter("mobile")));
//					u.setAddress(request.getParameter("address"));
//					u.setPassword(request.getParameter("password"));
//					
//					String savePath = "C:\\Users\\HETSI\\eclipse-workspace\\Profile_picture";
//					File fileSaveDir = new File(savePath);
//					if(!fileSaveDir.exists()) {
//						fileSaveDir.mkdir();
//					}
//					Part file1 = request.getPart("profile_picture");
//					String fileName = extractfilename(file1);
//					file1.write(savePath + File.separator + fileName);
//					String savePath2 = "C:\\Users\\HETSI\\eclipse-workspace\\Profile_picture";
//					File imgSaveDir = new File(savePath2);
//					if(!imgSaveDir.exists()) {
//						imgSaveDir.mkdir();
//					}
//                    u.setProfile_picture(fileName);
//					UserDao.signupUser(u);
//					request.setAttribute("msg", "User Sign Up Successfully");
//					request.getRequestDispatcher("login.jsp").forward(request, response);
//				}
//				else
//				{
//					request.setAttribute("msg", "Password & confirm password Does Not Matched");
//					request.getRequestDispatcher("signup.jsp").forward(request, response);
//				}
//			   }
//				else
//				{
//					request.setAttribute("msg", "Email Already Registred");
//					request.getRequestDispatcher("signup.jsp").forward(request, response);
//				}
//		}
//	    else if(action.equalsIgnoreCase("login"))
//	    {
//	    	User u=UserDao.loginUser(request.getParameter("email"));
//	    	if(u==null)
//	    	{
//	    		request.setAttribute("msg", "Email Not Registred");
//	    		request.getRequestDispatcher("login.jsp").forward(request, response);
//	    	}
//	    	else
//	    	{
//	    		if(u.getPassword().equals(request.getParameter("password")))
//	    		{
//	    			
//	    			HttpSession session=request.getSession();
//	    			session.setAttribute("u", u);
//	    			if(u.getUsertype().equals("Buyer"))
//	    			{
//	    			   request.getRequestDispatcher("index.jsp").forward(request, response);
//	    			}
//	    			else
//	    			{
//		    		   request.getRequestDispatcher("seller-index.jsp").forward(request, response);
//	    			}
//	    		}
//	    		else
//	    		{
//	    			request.setAttribute("msg", "Incorrect Password");
//		    		request.getRequestDispatcher("login.jsp").forward(request, response);
//	    		}
//	    	}
//	    }
//	    else if(action.equalsIgnoreCase("update profile"))
//	    {
//	    	HttpSession session=request.getSession();
//	    	User u=(User) session.getAttribute("u");
//	    	u.setFname(request.getParameter("fname"));
//	    	u.setLname(request.getParameter("lname"));
//	    	u.setEmail(request.getParameter("email"));
//	    	u.setMobile(Long.parseLong(request.getParameter("mobile")));
//	    	u.setAddress(request.getParameter("address"));
//            UserDao.updateProfile(u);
//            request.setAttribute("msg", "Profile Updated Successfully");
//            session.setAttribute("u", u);
//            request.getRequestDispatcher("profile.jsp").forward(request, response);
//	    }
//	    else if(action.equalsIgnoreCase("change password"))
//	    {
//	    	HttpSession session=request.getSession();
//	    	User u=(User) session.getAttribute("u");
//	    	
//	    	 if(u == null)
//	    	    {
//	    	        response.sendRedirect("login.jsp");
//	    	        return;
//	    	    }
//	    	 
//	    	if(u.getPassword().equals(request.getParameter("old_password")))
//	    	{
//	    		if(request.getParameter("new_password").equals(request.getParameter("cnew_password")))
//	    		{
//	    			UserDao.changePassword(u.getEmail(), request.getParameter("new_password"));
//	    			session.removeAttribute("u");
//	    			session.invalidate();
//	    	        request.setAttribute("msg", "Password Change Successfully");
//	    	        request.getRequestDispatcher("login.jsp").forward(request, response);
//	    		}
//	    		else
//	    		{
//	    			request.setAttribute("msg", "New password can't be from old password");
//	    			if(u.getUsertype().equals("Buyer"))
//	    			{
//		    	        request.getRequestDispatcher("change-password.jsp").forward(request, response);
//
//	    			}
//	    	        request.getRequestDispatcher("seller-change-password.jsp").forward(request, response);
//	    		}
//	    	}
//	    	else
//	    	{
//	    		request.setAttribute("msg", "New password & confirm New password Does not mathced");
//	    		if(u.getUsertype().equals("Buyer"))
//    			{
//	    	        request.getRequestDispatcher("change-password.jsp").forward(request, response);
//
//    			}
//    	        request.getRequestDispatcher("seller-change-password.jsp").forward(request, response);
//    		}
//	    }
//	    else
//	    {
//	        HttpSession session = request.getSession();
//	        User u = (User) session.getAttribute("u");
//
//	        request.setAttribute("msg", "Old password Does not matched");
//
//	        if(u != null && u.getUsertype().equals("Buyer"))
//	        {
//	            request.getRequestDispatcher("change-password.jsp").forward(request, response);
//	        }
//	        else
//	        {
//	            request.getRequestDispatcher("seller-change-password.jsp").forward(request, response);
//	        }
//	    }
//	}
//
//}


package com.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;

import com.bean.User;
import com.dao.UserDao;

@WebServlet("/UserController")
@MultipartConfig
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String extractfilename(Part file) {
        String cd = file.getHeader("content-disposition");
        String[] items = cd.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // ================= SIGN UP =================
        if (action != null && action.equalsIgnoreCase("sign up")) {

            String email = request.getParameter("email").trim();

            boolean flag = UserDao.checkEmail(email);

            if (!flag) {
                String password = request.getParameter("password");
                String cpassword = request.getParameter("cpassword");

                if (password.equals(cpassword)) {

                    User u = new User();
                    u.setUsertype(request.getParameter("usertype"));
                    u.setFname(request.getParameter("fname"));
                    u.setLname(request.getParameter("lname"));
                    u.setEmail(email);
                    u.setMobile(Long.parseLong(request.getParameter("mobile")));
                    u.setAddress(request.getParameter("address"));
                    u.setPassword(password);

                    String savePath = "C:\\Users\\HETSI\\eclipse-workspace\\Profile_picture";
                    File dir = new File(savePath);
                    if (!dir.exists()) dir.mkdir();

                    Part file = request.getPart("profile_picture");
                    String fileName = extractfilename(file);
                    file.write(savePath + File.separator + fileName);

                    u.setProfile_picture(fileName);

                    UserDao.signupUser(u);

                    request.setAttribute("msg", "Signup Successful");
                    request.getRequestDispatcher("login.jsp").forward(request, response);

                } else {
                    request.setAttribute("msg", "Password not matched");
                    request.getRequestDispatcher("signup.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("msg", "Email already registered");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }
        }

        // ================= LOGIN =================
        else if (action != null && action.equalsIgnoreCase("login")) {

            String email = request.getParameter("email").trim();
            String password = request.getParameter("password").trim();

            User u = UserDao.loginUser(email);

            if (u == null) {
                request.setAttribute("msg", "Email Not Registered");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                if (u.getPassword().equals(password)) {

                    HttpSession session = request.getSession();
                    session.setAttribute("u", u);

                    if (u.getUsertype().equalsIgnoreCase("Buyer")) {
                        response.sendRedirect("index.jsp");
                    } else {
                        response.sendRedirect("seller-index.jsp");
                    }

                } else {
                    request.setAttribute("msg", "Incorrect Password");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
        }

        // ================= UPDATE PROFILE =================
        else if (action.equalsIgnoreCase("update profile")) {

            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("u");

            u.setFname(request.getParameter("fname"));
            u.setLname(request.getParameter("lname"));
            u.setMobile(Long.parseLong(request.getParameter("mobile")));
            u.setAddress(request.getParameter("address"));

            UserDao.updateProfile(u);

            session.setAttribute("u", u);
            request.setAttribute("msg", "Profile Updated");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }

        // ================= CHANGE PASSWORD =================
        else if (action.equalsIgnoreCase("change password")) {

            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("u");

            if (u == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String oldPass = request.getParameter("old_password");
            String newPass = request.getParameter("new_password");
            String confirmPass = request.getParameter("cnew_password");

            if (u.getPassword().equals(oldPass)) {

                if (newPass.equals(confirmPass)) {

                    UserDao.changePassword(u.getEmail(), newPass);

                    session.invalidate();

                    request.setAttribute("msg", "Password Changed Successfully");
                    request.getRequestDispatcher("login.jsp").forward(request, response);

                } else {
                    request.setAttribute("msg", "New password not matched");
                }

            } else {
                request.setAttribute("msg", "Old password incorrect");
            }

            if (u.getUsertype().equals("Buyer")) {
                request.getRequestDispatcher("change-password.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("seller-change-password.jsp").forward(request, response);
            }
        }
    }
}
