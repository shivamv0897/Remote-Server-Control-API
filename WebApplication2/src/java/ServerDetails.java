
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class ServerDetails extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
//            out.println("<p>Server's ip is " +  request.getLocalAddr() + "</p>");
//            out.println("<p>Server receiving the request is " +  request.getLocalName() + "</p>");
//            out.println("<p>Server's name is " +  request.getServerName() + "</p>");
//            out.println("<p>port on which request received " +  request.getLocalPort() + "</p>");
//            out.println("<p>port on which request request was send " +  request.getServerPort() + "</p>");
//            out.println("<p>path that identifies application " +  request.getContextPath() + "</p>");
            String a=request.getLocalAddr()+"_"+request.getLocalName()+"_"+request.getServerName()+"_"+request.getLocalPort()+"_"+request.getServerPort()+"_"+request.getContextPath();
            out.print("<p>"+a+"</a>");
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
