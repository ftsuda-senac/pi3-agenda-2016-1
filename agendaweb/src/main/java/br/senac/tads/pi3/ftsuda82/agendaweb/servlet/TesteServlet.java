/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.ftsuda82.agendaweb.servlet;

import br.senac.tads.pi3.ftsuda82.agendaweb.dao.AgendaDAO;
import br.senac.tads.pi3.ftsuda82.agendaweb.entity.Pessoa;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fernando.tsuda
 */
@WebServlet(name = "TesteServlet", urlPatterns = {"/protegido/TesteServlet"})
public class TesteServlet extends HttpServlet {

  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String strId = request.getParameter("id");
    long id = Long.parseLong(strId);
    AgendaDAO dao = new AgendaDAO();
    Pessoa p = dao.obterPessoa(id);
    
    HttpSession sessao = request.getSession(true);
    sessao.setAttribute("pessoa", p);
    request.getRequestDispatcher("/WEB-INF/jsp/teste.jsp").forward(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    
  }


}
