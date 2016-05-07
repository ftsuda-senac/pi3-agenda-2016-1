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
    <p>Usuário logado: <c:out value="${sessionScope.usuario.nome}" /></p>
    <table>
      <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Data nascimento</th>
        <th>E-mail</th>
        <th>Telefone</th>
        <th>&nbsp;</th>
      </tr>
      <c:forEach items="${lista}" var="pes">
        <tr>
          <td>${pes.id}</td>
          <td>${pes.nome}</td>
          <td>
            <fmt:formatDate value="${pes.dtNascimento}" 
                        pattern="dd/MM/yyyy" />
          </td>
          <td>${pes.email}</td>
          <td>${pes.telefone}</td>
          <td><a href="${pageContext.request.contextPath}/protegido/TesteServlet?id=${pes.id}">Tela de teste</a></td>
        </tr>
      </c:forEach>
    </table>
    <p><a href="EntradaServlet">EntradaServlet</a></p>
    <p><a href="Logout">Sair</a></p>
  </body>
</html>
