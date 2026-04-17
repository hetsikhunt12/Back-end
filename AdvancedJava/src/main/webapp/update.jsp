<%@page import="com.bean.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<style type="text/css">
tr,td{
padding : 10px;
}
</style>
</head>
<body>
<b style = "color:blue; text-align:center;">
<%
	 Student  s = (Student)request.getAttribute("s");
%>

</b>
<form method="post" action="StudentController">
   <table>
   <tr>
       <td><input type="hidden" name="id" value="<%=s.getId()%>"></td>
   </tr>
   <tr>
       <td>First Name : </td>
       <td> <input type="text" name="fname" value="<%=s.getFname()%>"></td>
   </tr>
   <tr>
       <td>Last Name : </td>
       <td> <input type="text" name="lname"value="<%=s.getLname()%>" ></td>
   </tr>
   <tr> 
       <td>Email</td>
       <td> <input type="text" name="email" value="<%=s.getEmail()%>"></td>
   </tr>
   <tr>
       <td>Mobile :  </td>
       <td> <input type="text" name="mobile"  value="<%=s.getMobile()%>"></td>
   </tr>
   <tr>
       <td>Address : </td>
       <td> <textarea rows="5" cols="22" name="address"><%=s.getAddress()%>"</textarea></td>
  </tr>  

  <%
   if(s.getGender().equalsIgnoreCase("Male")){
  %>
	<td>Gender :  </td>
	   <td> <input type="radio" name="gender" value="male" checked="checked" >Male  </td>
	   <td> <input type="radio" name="gender" value="female">Female  </td>
	</tr>
  <% 
}

else{
	%>
	<td>Gender :  </td>
	   <td> <input type="radio" name="gender" value="male"  >Male  </td>
	   <td> <input type="radio" name="gender" value="female" checked="checked">Female  </td>
	</tr>
	<% 	
}
%>

   <td colspan="2" align="center">
      <input type="submit" name= "action" value="update" class="btn btn-primary">
   </td>

</table>
</form>
</body>
</html>