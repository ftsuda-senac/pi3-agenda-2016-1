/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.ftsuda82.agendaweb.dao;

import br.senac.tads.pi3.ftsuda82.agendaweb.entity.Pessoa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando.tsuda
 */
public class AgendaDAO {

  private Connection obterConexao() throws SQLException, ClassNotFoundException {
    Connection conn = null;
    // Passo 1: Registrar driver JDBC.
    Class.forName("org.apache.derby.jdbc.ClientDataSource");

    // Passo 2: Abrir a conexão
    conn = DriverManager.getConnection(
            "jdbc:derby://localhost:1527/agendabd;SecurityMechanism=3",
            "app", // usuario
            "app"); // senha
    return conn;
  }

  public Pessoa obterPessoa(long idPessoa) {
    PreparedStatement stmt = null;
    Connection conn = null;
    Pessoa p = null;

    String sql = "SELECT ID_CONTATO, NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL "
            + "FROM TB_CONTATO WHERE ID_CONTATO = ?";
    try {
      conn = obterConexao();
      stmt = conn.prepareStatement(sql);
      stmt.setLong(1, idPessoa);
      ResultSet resultados = stmt.executeQuery();

      while (resultados.next()) {
        Long id = resultados.getLong("ID_CONTATO");
        String nome = resultados.getString("NM_CONTATO");
        Date dataNasc = resultados.getDate("DT_NASCIMENTO");
        String email = resultados.getString("VL_EMAIL");
        String telefone = resultados.getString("VL_TELEFONE");
        p = new Pessoa(id, nome, dataNasc, email, telefone);
        break;
      }
    } catch (SQLException ex) {
      Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      // Código colocado aqui para garantir que a conexão com o banco
      // seja sempre fechada, independentemente se executado com sucesso
      // ou erro.
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException ex) {
          Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return p;
  }

  public List<Pessoa> listarPessoas() {
    Statement stmt = null;
    Connection conn = null;

    String sql = "SELECT ID_CONTATO, NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL "
            + "FROM TB_CONTATO";

    List<Pessoa> lista = new ArrayList<Pessoa>();
    try {
      conn = obterConexao();
      stmt = conn.createStatement();
      ResultSet resultados = stmt.executeQuery(sql);

      DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");

      while (resultados.next()) {
        Long id = resultados.getLong("ID_CONTATO");
        String nome = resultados.getString("NM_CONTATO");
        Date dataNasc = resultados.getDate("DT_NASCIMENTO");
        String email = resultados.getString("VL_EMAIL");
        String telefone = resultados.getString("VL_TELEFONE");
//                System.out.println(String.valueOf(id) + ", " + nome + ", "
//                        + formatadorData.format(dataNasc) + ", " + email + ", "
//                        + telefone);
        Pessoa p = new Pessoa(id, nome, dataNasc,
                email, telefone);
        lista.add(p);
      }

    } catch (SQLException ex) {
      Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      // Código colocado aqui para garantir que a conexão com o banco
      // seja sempre fechada, independentemente se executado com sucesso
      // ou erro.
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException ex) {
          Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return lista;
  }

  // http://stackoverflow.com/questions/17459094/getting-id-after-insert-within-a-transaction-oracle
  // http://www.mkyong.com/jdbc/jdbc-transaction-example/
  public void incluirPessoaComTransacao(Pessoa pessoa) {
    PreparedStatement stmt = null;
    Connection conn = null;

    String sql = "INSERT INTO TB_CONTATO "
            + "(NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL, DT_CADASTRO) "
            + "VALUES (?, ?, ?, ?, ?)";
    try {
      conn = obterConexao();

      conn.setAutoCommit(false); // Permite usar transacoes para multiplos comandos no banco de dados
      stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1, pessoa.getNome());
      stmt.setDate(2, new java.sql.Date(pessoa.getDtNascimento().getTime()));
      stmt.setString(3, pessoa.getTelefone());
      stmt.setString(4, pessoa.getEmail());
      stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
      
      stmt.executeUpdate();
      
      // ResultSet para recuperar o ID gerado automaticamente no banco de dados.
      // Neste exemplo, o ID é gerado automaticamente.
      ResultSet generatedKeys = stmt.getGeneratedKeys();
      if (generatedKeys.next()) {
        long idNovo = generatedKeys.getLong(1);
        System.out.println("***** ID NOVO CADASTRADO: " + String.valueOf(idNovo));
        
        // Executar próximos INSERTs USANDO O ID novo.
      }
      conn.commit();
    } catch (SQLException ex) {
      try {
        // Caso ocorra algum erro, tenta desfazer todas as ações realizadas no BD.
        if (conn != null & !conn.isClosed()) {
          conn.rollback();
        }
      } catch (SQLException ex1) {
        Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex1);
      }
      Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
      try {
        // Caso ocorra algum erro, tenta desfazer todas as ações realizadas no BD.
        if (conn != null & !conn.isClosed()) {
          conn.rollback();
        }
      } catch (SQLException ex1) {
        Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex1);
      }
      Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException ex) {
          Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
  }

  public void incluirPessoa(Pessoa pessoa) {
    PreparedStatement stmt = null;
    Connection conn = null;

    String sql = "INSERT INTO TB_CONTATO "
            + "(NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL, DT_CADASTRO) "
            + "VALUES (?, ?, ?, ?, ?)";
    try {
      conn = obterConexao();
      stmt = conn.prepareStatement(sql);
      stmt.setString(1, pessoa.getNome());
      stmt.setDate(2, new java.sql.Date(pessoa.getDtNascimento().getTime()));
      stmt.setString(3, pessoa.getTelefone());
      stmt.setString(4, pessoa.getEmail());
      stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
      stmt.executeUpdate();
      //System.out.println("Registro incluido com sucesso.");

    } catch (SQLException ex) {
      Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException ex) {
          Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
  }

  public static void main(String[] args) {
    AgendaDAO instancia = new AgendaDAO();
    Scanner entrada = new Scanner(System.in);
    do {
      System.out.println("********** DIGITE UMA OPÇÃO **********");
      System.out.println("(1) Listar agenda");
      System.out.println("(2) Incluir registro");
      System.out.println("(3) Alterar registro");
      System.out.println("(4) Excluir registro");
      System.out.println("(9) SAIR");
      System.out.print("Opção: ");
      int opcao = entrada.nextInt();

      switch (opcao) {
        case 1:
          instancia.listarPessoas();
          break;
        case 2:
          String nome;
          Date dataNasc;
          String email;
          String telefone;

          // ENTRADA DE DADOS
          System.out.print("Digite o nome da pessoa: ");
          nome = entrada.nextLine();

          System.out.print("Digite a data de nascimento no formato dd/mm/aaaa: ");
          String strDataNasc = entrada.nextLine();
          DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
          try {
            dataNasc = formatadorData.parse(strDataNasc);
          } catch (ParseException ex) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            dataNasc = new Date();
          }
          System.out.print("Digite o telefone no formato 99 99999-9999: ");
          telefone = entrada.nextLine();

          System.out.print("Digite o e-mail: ");
          email = entrada.nextLine();

          //instancia.incluirPessoa(nome, dataNasc, telefone, email);
          break;
        case 3:
          break;
        case 4:
          break;
        case 9:
          System.exit(0);
        default:
          System.out.println("OPÇÃO INVÁLIDA.");
      }

    } while (true);
  }

}
