/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.ftsuda82.agendaweb.servlet;

import br.senac.tads.pi3.ftsuda82.agendaweb.util.Mensagem;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ResultadoServlet", urlPatterns = {"/ResultadoServlet"})
public class ResultadoServlet extends HttpServlet {

  /**
   * Servlet que recupera a mensagem da sessão e apresenta na tela de resultado.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession();
    Mensagem mensagem = (Mensagem) session.getAttribute("msg");

    if (mensagem != null) {
      // Se existir mensagem, seta atributo para compor a tela com o resultado.jsp
      session.removeAttribute("msg");
      request.setAttribute("msg", mensagem);
      RequestDispatcher rd
              = request.getRequestDispatcher("/WEB-INF/jsp/resultado.jsp");
      rd.forward(request, response);
    } else {
      // Se não existir mensagem, redireciona para a tela com a lista de Pessoas
      response.sendRedirect("AgendaServlet");
    }

  }

}
