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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fernando.tsuda
 */
@WebServlet(name = "AgendaServlet", urlPatterns = {"/AgendaServlet"})
public class AgendaServlet extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet AgendaServlet</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet AgendaServlet at " + request.getContextPath() + "</h1>");
      out.println("</body>");
      out.println("</html>");
    }
  }

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
//        Pessoa p = new Pessoa(1L, "Fulano da Silva", new Date(),
//                "fulano@zmail.com", "(11) 99999-9999");
//        Pessoa p2 = new Pessoa(2L, "Ciclano de Souza", new Date(),
//                "ciclano@zmail.com", "(11) 88888-8888");
//        List<Pessoa> lista = new ArrayList<Pessoa>();
//        lista.add(p);
//        lista.add(p2);
    AgendaDAO dao = new AgendaDAO();
    List<Pessoa> lista = dao.listarPessoas();

    //request.setAttribute("pessoa", p);
    request.setAttribute("lista", lista);

    RequestDispatcher rd
            = request.getRequestDispatcher("/WEB-INF/jsp/agenda.jsp");
    rd.forward(request, response);

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
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }

}
