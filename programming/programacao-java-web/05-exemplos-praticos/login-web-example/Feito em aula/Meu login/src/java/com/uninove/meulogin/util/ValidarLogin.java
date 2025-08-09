package com.uninove.meulogin.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidarLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    Connection con;
    Statement statement;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Para simplificar o nosso desenvolvimento, estamos utilizando o try-with-resources
        // Isso evita muitos problemas, como fechar a conexão.
        try (PrintWriter out = response.getWriter()) {
            
            // Pega as informações do usuário que foram passadas por GET ou POST
            String usuario = request.getParameter("usuario");
            String senha = request.getParameter("senha");
            
            // Informa qual é o caminho para a classe Driver            
            Class.forName("com.mysql.cj.jdbc.Driver");
            /*
            O objeto 'con' pega a referência da conexão feita pela classe DriverManager
            A string de conexão é formada pelo caminho informado da conexão feito na 
            aba serviços do NetBeans, junto a isso, é passado por paramêtro o usuário e a
            senha desse usuário.
            No nosso caso, utilizando Wamp/Xamp um usuário padrão e o root e não possui senha.
            */
            con = DriverManager.getConnection("jdbc:mysql://localhost:3303/usuario?useTimezone=true&serverTimezone=UTC", "root", "");
            
            // O objeto statement obtem por referência as propriedades do método createStatement
            statement = con.createStatement();
            
            // Uma consulta simples para verificar se há um usuário especifico.
            String strSQL = "SELECT NOME FROM REGISTRO WHERE USUARIO = '"+ usuario + 
                    "' AND SENHA = '" + senha + "';";
            
            // Pega as informação do resultado da consulta
            ResultSet resultado = statement.executeQuery(strSQL);
            
            //Verifica se há valor
            if(resultado.next()) {
                response.sendRedirect("inicial.jsp?user=" + resultado.getString(1));
            } else {
                response.sendRedirect("inicial.jsp?user=Visitando");
            }
                
            
        } catch (SQLException ex) {
            // A classe Logger gera um log como informação
             Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ClassNotFoundException ex) {
            Logger.getLogger(ValidarLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
    }// </editor-fold>

}
