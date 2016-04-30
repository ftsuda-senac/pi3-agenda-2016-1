/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.ftsuda82.agendaweb.servlet;

import br.senac.tads.pi3.ftsuda82.agendaweb.dao.AgendaDAO;
import br.senac.tads.pi3.ftsuda82.agendaweb.entity.Pessoa;
import br.senac.tads.pi3.ftsuda82.agendaweb.util.Mensagem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "EntradaServlet", urlPatterns = {"/EntradaServlet"})
public class EntradaServlet extends HttpServlet {

  /**
   * Recebe a requisição e encaminha para obter o HTML a partir do entrada.jsp
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    //processRequest(request, response);
    RequestDispatcher rd
            = request.getRequestDispatcher("/WEB-INF/jsp/entrada.jsp");
    rd.forward(request, response);
  }

  /**
   * Recebe os dados preenchidos pelo usuário no form de entrada.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    //processRequest(request, response);
    String nome = request.getParameter("nomeparam");
    String email = request.getParameter("email");
    String telefone = request.getParameter("telefone");
    
    //VALIDAR DADOS!!!

    Pessoa pessoa = new Pessoa(nome, new Date(), email, telefone);
    AgendaDAO dao = new AgendaDAO();
    dao.incluirPessoaComTransacao(pessoa);

//    request.setAttribute("pessoa", pessoa);
//    RequestDispatcher rd
//            = request.getRequestDispatcher("/WEB-INF/jsp/resultado.jsp");
//    rd.forward(request, response);

    // *** Em vez do forward, uso do POST-REDIRECT-GET (Por que???) ***
    HttpSession sessao = request.getSession(true);
    String texto = "'" + nome + "' incluido com sucesso";
    sessao.setAttribute("msg", new Mensagem(texto, "success"));
    response.sendRedirect("ResultadoServlet");
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
