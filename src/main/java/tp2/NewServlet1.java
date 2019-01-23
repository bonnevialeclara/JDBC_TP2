/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.*;

/**
 *
 * @author cbonnevi
 */
@WebServlet(name = "NewServlet1", urlPatterns = {"/ShowClientsInState"})
public class NewServlet1 extends HttpServlet {

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
            String val = request.getParameter("state");
				if (val == null) {
					throw new Exception("La paramètre state n'a pas été transmis");
				}
            DAO dao = new DAO(DataSourceFactory.getDataSource());
            String st=(val);
            List<CustomerEntity> result = dao.customersInState(st);
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<table>");
                        out.println("<tr>");
                            out.println("<th>Id</th>");
                            out.println("<th>Name</th>");
                            out.println("<th>Address</th>");
                        out.println("</tr>");
                        for(CustomerEntity customerEntity : result){
                            out.println("<tr>");
                                out.println("<td style=\"border: 1px solid black\">"+customerEntity.getCustomerId()+"</td>");
                                out.println("<td style=\"border: 1px solid black\">"+customerEntity.getName()+"</td>");
                                out.println("<td style=\"border: 1px solid black\">"+customerEntity.getAddressLine1()+"</td>");
                            out.println("</tr>");
                        }
            out.println("</table>");
            //out.printf("<hr><a href='%s'>Retour au menu</a>", request.getContextPath());
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            Logger.getLogger(NewServlet1.class.getName()).log(Level.SEVERE, null, ex);
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
