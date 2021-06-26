package servltes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        
        // Por padrão iremos chamar a página index e passamos por parametro erro=1
        String pagina = "index.jsp?erro=1";
        
        // Validando se o usuário é igual a admin e senha = 123
        /* 
            Aqui estamos fazendo um exemplo, mas nunca devemos colocar o usuário 
            senha numa classe Java, geralmente utilizamos XML ou um banco de dados
        */
        
        String oAcao = request.getParameter("acao");
        
        if(oAcao.equals("login")) {
            if(request.getParameter("login").equals("admin")) {
                if(request.getParameter("senha").equals("123")) {
                    // Criar objeto para obter sessãoi do JSP
                    HttpSession sessao = request.getSession();
                    
                    // Defininado um atriuto de sessão
                    sessao.setAttribute("login", request.getParameter("login"));
                    
                    // Se obtiver sucesso, vamos chamar a página principal
                    pagina = "index.jsp";
                }
            }
        } else if (oAcao.equals("logout")) {
            // Ação de sair 
            HttpSession sessao = request.getSession();
            sessao.invalidate();
            
            // Chamar novamente a página principal
            pagina = "index.jsp";
        }
        response.sendRedirect(pagina);
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
