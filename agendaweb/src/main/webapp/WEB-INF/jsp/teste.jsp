<%-- 
    Document   : teste.jsp
    Created on : 29/04/2016, 21:44:50
    Author     : fernando.tsuda
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <h1>${sessionScope.pessoa.nome}</h1>
    <h2>ID: ${sessionScope.pessoa.id}</h2>
    <c:choose>
      <c:when test="${sessionScope.pessoa.id == 2}">
        <p>Somente usuário com ID = 2 pode ver essa mensagem</p>
      </c:when>
      <c:when test="${sessionScope.pessoa.id == 4}">
        <h2>Somente usuário com ID = 4 pode ver isso</h2>
      </c:when>
      <c:otherwise>
        <p>Usuários que tem ids != 4 e != 2 estão vendo essa msg</p>
      </c:otherwise>
    </c:choose>
    <p>Mensagem genérica</p>
    <a href="${pageContext.request.contextPath}/AgendaServlet">Voltar</a>
  </body>
</html>
