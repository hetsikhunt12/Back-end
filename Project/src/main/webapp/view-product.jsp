<%@ page import="com.dao.ProductDao" %>
<%@ page import="com.bean.product" %>
<%@ page import="com.bean.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="seller-header.jsp" %>

<%
   // Session check
   if(u == null){
       response.sendRedirect("../login.jsp");
       return;
   }

   // ✅ SAFE PID HANDLE
   String pidStr = request.getParameter("pid");

   if(pidStr == null){
       out.println("<h3 style='color:red'>Product ID missing</h3>");
       return;
   }

   int pid = 0;
   try{
       pid = Integer.parseInt(pidStr);
   }catch(Exception e){
       out.println("<h3 style='color:red'>Invalid Product ID</h3>");
       return;
   }

   // Get product
   product p = ProductDao.getProduct(pid);

   if(p == null){
       out.println("<h3 style='color:red'>Product not found</h3>");
       return;
   }
%>

<!DOCTYPE html>
<html>
<head>
    <title>View Product</title>
</head>
<body>

<div class="container mt-5">
    <div class="row">
    
        <!-- Product Image -->
        <div class="col-md-6">
            <img src="<%=request.getContextPath()%>/Product_image/<%=p.getProduct_image()%>" 
                 style="width:100%; height:400px;">
        </div>

        <!-- Product Details -->
        <div class="col-md-6">
            <h2><%=p.getProduct_name()%></h2>
            <h4>Price: ₹ <%=p.getProduct_price()%></h4>
            <p><%=p.getProduct_desc()%></p>

            <!-- Buttons -->
            <a href="edit-product.jsp?pid=<%=p.getPid()%>" class="btn btn-primary">Edit</a>
            
            <a href="<%=request.getContextPath()%>/ProductController?action=delete&pid=<%=p.getPid()%>" 
               class="btn btn-danger"
               onclick="return confirm('Are you sure?')">
               Delete
            </a>
        </div>

    </div>
</div>

</body>
</html>