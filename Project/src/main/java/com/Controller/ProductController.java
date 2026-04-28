//package com.Controller;
//
//import com.bean.product;
//import com.dao.ProductDao;
//import com.razorpay.Product;
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
//import com.bean.User;
//
//@WebServlet("/ProductController")
//@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 512, maxFileSize = 1024 * 1024 * 512,
//maxRequestSize = 1024 * 1024 * 512) // 512MB
//public class ProductController extends HttpServlet {
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
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String action=request.getParameter("action");
//		
//		if (action != null && action.equalsIgnoreCase("add Product"))	    {
//	        product p = new product();
//	        p.setProduct_catagory(request.getParameter("product_category"));
//	        p.setProduct_desc(request.getParameter("product_desc"));
//	        p.setProduct_name(request.getParameter("product_name"));
//	        p.setProduct_price(Integer.parseInt(request.getParameter("product_price")));
//	        
//	        HttpSession session = request.getSession();
//	        User u = (User) session.getAttribute("u");
//	        p.setUid(u.getUid());
//	        
//	        String savePath = "C:\\Users\\HETSI\\eclipse-workspace\\Product_picture";
//	        String savePath = getServletContext().getRealPath("/Product_image");
//			File fileSaveDir = new File(savePath);
//			if(!fileSaveDir.exists()) {
//				fileSaveDir.mkdir();
//			}
//			Part file1 = request.getPart("product_image");
//			String fileName = extractfilename(file1);
//			file1.write(savePath + File.separator + fileName);
//			String savePath2 = "C:\\Users\\HETSI\\eclipse-workspace\\Product_image";
//			File imgSaveDir = new File(savePath2);
//			if(!imgSaveDir.exists()) {
//				imgSaveDir.mkdir();
//			}
//            p.setProduct_image(fileName);
//            ProductDao.addProduct(p);
//            request.setAttribute("msg", "Product Added Successfully");
//            request.getRequestDispatcher("seller-add-product.jsp").forward(request, response);
//	    }
//		else if(action.equalsIgnoreCase("update product"))
//		{
//			product p = new product();
//			p.setPid(Integer.parseInt(request.getParameter("pid")));
//			p.setProduct_catagory(request.getParameter("product_catagory"));
//			p.setProduct_name(request.getParameter("product_name"));
//			p.setProduct_price(Integer.parseInt(request.getParameter("product_price")));
//			p.setProduct_desc(request.getParameter("product_desc"));
//			ProductDao.updateProduct(p);
//            request.getRequestDispatcher("seller-view-product.jsp").forward(request, response);
//		}
//		
//		
//	}
//
//}


package com.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

import com.bean.product;
import com.bean.User;
import com.bean.product;
import com.dao.ProductDao;

@WebServlet("/ProductController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 512 , maxRequestSize = 1024 * 1024 * 512  ) //512 mb data
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String extractfilename(Part file){
		String cd=file.getHeader("content-disposition");
		System.out.println(cd); // form-data; name="product_image"; filename="user.jpg";
		String[] items = cd.split(";");
		for(String string : items) {
			if(string.trim().startsWith("filename")) {
				return string.substring(string.indexOf("=") + 2,string.length() - 1 );
			}
		}
		
		
		
		return "";
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		 
		if(action.equalsIgnoreCase("add product")) {
			product p = new product();
			p.setProduct_catagory(request.getParameter("product_catagory"));
			p.setProduct_name(request.getParameter("product_name"));
			p.setProduct_price(Integer.parseInt(request.getParameter("product_price")));
			p.setProduct_desc(request.getParameter("product_desc"));
			
			HttpSession session = request.getSession();
			User u = (User)session.getAttribute("u");
			p.setUid(u.getUid());
			
			String savepath = getServletContext().getRealPath("/Product_image");		
			File fileSaveDir = new File(savepath);
			if(!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}
			Part file1 = request.getPart("product_image");
			String fileName = extractfilename(file1);
			file1.write(savepath + File.separator + fileName); 
			String savePath2 = "C:\\Users\\HETSI\\eclipse-workspace\\Product_image";
			File imgSaveDir =new File(savePath2);
			if(!imgSaveDir.exists()) {
				imgSaveDir.mkdir();
			}
			p.setProduct_image(fileName);
			ProductDao.addProduct(p);
			
			request.setAttribute("msg", "Product Added Successfully");
			request.getRequestDispatcher("seller-add-product.jsp").forward(request, response);
			
		}
		
		else if(action.equalsIgnoreCase("Update")) {
			
			product p = new product();
			p.setPid(Integer.parseInt(request.getParameter("pid")));
			p.setProduct_catagory(request.getParameter("product_category"));
			p.setProduct_name(request.getParameter("product_name"));
			p.setProduct_price(Integer.parseInt(request.getParameter("product_price")));
			p.setProduct_desc(request.getParameter("product_desc"));
			
			
			
			String savepath = getServletContext().getRealPath("/Product_image");		
			File fileSaveDir = new File(savepath);
			if(!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}
			Part file1 = request.getPart("product_image");
			String fileName = extractfilename(file1);
			String Fullpath =savepath + File.separator + fileName;
			File file = new File(Fullpath);
		    if (file.exists()) {
		        file.delete();
		    }
		    file1.write(Fullpath);
			String savePath2 = "C:\\Users\\HETSI\\eclipse-workspace\\Product_image";
			File imgSaveDir =new File(savePath2);
			if(imgSaveDir.exists()) {
				imgSaveDir.delete();
			}
			imgSaveDir.mkdir();
			
			p.setProduct_image(fileName);
			ProductDao.updateProduct(p);
			
			request.setAttribute("msg", "Product Updated Successfully");
			request.getRequestDispatcher("seller-index.jsp").forward(request, response);
			
			
		}
		}
		
	}
