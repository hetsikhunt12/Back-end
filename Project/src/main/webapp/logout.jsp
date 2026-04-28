<%
   session.removeAttribute("u");
   session.invalidate();
   request.setAttribute("msg", "User Logged Out Successfully");
   request.getRequestDispatcher("login.jsp").forward(request, response);
%>