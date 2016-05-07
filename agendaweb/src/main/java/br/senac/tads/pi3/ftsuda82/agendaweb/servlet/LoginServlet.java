/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.ftsuda82.agendaweb.servlet;

import br.senac.tads.pi3.ftsuda82.agendaweb.entity.UsuarioSistema;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fernando
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

  // OBS: Usuarios mantidos em um mapa somente como exemplo.
  // A validação deve ser feita com os dados armazenados no BD.
  private static final Map<String, UsuarioSistema> USUARIOS_CADASTRADOS;

  static {
    USUARIOS_CADASTRADOS = new HashMap<String, UsuarioSistema>();
    USUARIOS_CADASTRADOS.put("fulano", new UsuarioSistema("fulano", "abcd1234", new String[]{"ADMIN", "BASICO"}));
    USUARIOS_CADASTRADOS.put("ciclano", new UsuarioSistema("ciclano", "abcd1234", new String[]{"BASICO"}));
  }

  /**
   * Apresenta a tela de login caso usuário não esteja autenticado. Caso
   * contrário, redireciona para a lista de contatos.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession sessao = request.getSession(false);
    if (sessao == null || sessao.getAttribute("usuario") == null) {
      RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
      rd.forward(request, response);

      return;
    }
    // Usar o request.getContextPath() para corrigir o caminho da URL.
    response.sendRedirect(request.getContextPath() + "/AgendaServlet");
  }

  /**
   * Efetua o login do usuário.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String nome = request.getParameter("nome");
    String senha = request.getParameter("senha");

    // Validar nome de usuário e senha.
    UsuarioSistema usuario = validar(nome, senha);
    if (usuario != null) {
      HttpSession sessao = request.getSession(false);
      if (sessao != null) {
        // Força invalidação da sessão anterior.
        sessao.invalidate();
      }
      sessao = request.getSession(true);
      sessao.setAttribute("usuario", usuario);
      response.sendRedirect(request.getContextPath() + "/AgendaServlet");
      return;
      // FIM CASO SUCESSO
    }
    response.sendRedirect(request.getContextPath() + "/erroLogin.jsp");

  }

  // Implementar aqui a validação do usuário com os dados
  // armazenados no banco de dados.
  private UsuarioSistema validar(String nome, String senha) {
    UsuarioSistema usuario = USUARIOS_CADASTRADOS.get(nome);
    if (usuario != null && usuario.autenticar(nome, senha)) {
      return usuario;
    }
    return null;
  }

}
