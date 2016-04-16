<%-- 
    Document   : form.jsp
    Created on : 15/04/2016, 21:12:42
    Author     : fernando.tsuda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <form action="EntradaServlet" method="post">
      <div>
        <label for="txtnome">Nome</label>
        <input type="text" name="nomeparam" id="txtnome" />
      </div>
      <div>
        <label for="txtemail">E-mail</label>
        <input type="text" name="email" id="txtemail" />
      </div>
      <div>
        <label for="txttelefone">Telefone</label>
        <input type="text" name="telefone" id="txttelefone" />
      </div>
      <div>
        <input type="submit" value="Salvar" />
      </div>

    </form>
  </body>
</html>
