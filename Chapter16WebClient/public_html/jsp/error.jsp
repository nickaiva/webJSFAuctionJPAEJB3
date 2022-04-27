<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>EJB 3 In Action: Chapter 16 Code Examples </title>
  <LINK href="../css/blaf.css" type=text/css rel=stylesheet>
  </head>
  <body>
  <h1> EJB 3 In Action: Chapter 16 Code Examples</h1>
  [<a href="../index.html">  Home Page </A>] <br> <br>
  <h3>Error Occurred</h3>
  <%
  try
  {
    String error =(String) (request.getAttribute("error"));
    out.println("<pre>Message:"+error+"</pre><br>");
  }
  catch(Throwable ex)
  {
    ex.printStackTrace();
  }
  %>
  
  </body>
</html>