<%-- 
    Document   : agenda
    Created on : 08/04/2016, 21:24:58
    Author     : fernando.tsuda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>JSP Page</title>
    <link rel="stylesheet" href="<c:url value='resources/css/estilos.css' />" />
    <script src="<c:url value='resources/js/funcoes.js' />"></script>
  </head>
  <body>
    <c:forEach items="${lista}" var="pes">
      <h1>${pes.nome}</h1>
      <p>Id: ${pes.id}</p>
      <p>Data de nascimento:
        <fmt:formatDate value="${pes.dtNascimento}" 
                        pattern="dd/MM/yyyy" />
      </p>
      <p>E-mail: ${pes.email}</p>
      <p>Telefone: ${pes.telefone}</p>
    </c:forEach>
  </body>
</html>
