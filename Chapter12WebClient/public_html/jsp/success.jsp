<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>EJB 3 In Action: Chapter 12 Code Examples</title>
  <LINK href="blaf.css" type=text/css rel=stylesheet>
  </head>
  <body><h1> EJB 3 In Action: Chapter 12 Code Examples</h1>       
  [<a href="index.html">  Home Page </A>] <br> <br>
  <h3> Bid Successfully submitted</h3>
  <%
    try
    {
      	Long bidId = (Long ) request.getAttribute("bidId");
     	     out.println("<b>Bid Id:"+ bidId+"</b><br>");
    }
    catch(Throwable ex)
    {
      ex.printStackTrace();
    }
  %>            
</body>
</html>