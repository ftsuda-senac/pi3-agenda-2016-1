<%-- 
    Document   : resultado
    Created on : 15/04/2016, 21:19:37
    Author     : fernando.tsuda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="<c:url value='resources/css/estilos.css' />" />
    <script src="<c:url value='resources/js/funcoes.js' />"></script>
  </head>
  <body onload="hello('${msg.texto}')">
    <h1>${msg.texto}</h1>
    <a href="AgendaServlet">Voltar</a>
  </body>
</html>
