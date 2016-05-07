<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Agenda Web - PI3</title>
    <meta charset="UTF-8">
    <c:url var="pathResources" value="/resources" /> <%-- CORRIGE URL DO SISTEMA PARA ACESSAR O DIRETÓRIO RESOURCES --%>
    <link href="${pathResources}/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="${pathResources}/css/estilos.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <h1>Agenda Web</h1>
    <div>
      <form action="Login" method="post" accept-charset="UTF-8" enctype="application/x-www-form-urlencoded">
        <p><label>Nome de usuário:</label><input type="text" name="nome" /></p>
        <p><label>Senha:</label><input type="password" name="senha" /></p>
        <p><input type="submit" value="Entrar" /></p>
      </form>
    </div>
  </body>
</html>
