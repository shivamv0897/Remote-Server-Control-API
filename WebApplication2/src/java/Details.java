import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.*;

/**
 *
 * @author shivam
 */
public class Details extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String os = System.getProperty("os.name").toLowerCase();
            System.out.println("---------" + os);
            Process p;
            if (os.contains("windows")) {
                System.out.println(")))))))))))");
                p = Runtime.getRuntime().exec("tasklist");
                windowsOutput(p, out);
            } else {
                p = Runtime.getRuntime().exec("ps -e");
                linuxOutput(p, out);
            }

        } catch (Exception err) {
            err.printStackTrace();
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

    private void windowsOutput(Process p, PrintWriter out) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        int x = 0,n=0;
        String d="";
        while ((line = input.readLine()) != null) {
            String a = "";
            String b = "";
            int m = 0;
            if (x > 4) {
                String[] s = line.split(" ");
                String f[] = new String[100];
                int c = 0;
                for (int o = 0; o < s.length; o++) {
                    if (!s[o].equals("")) {
                        f[c++] = s[o];
                    }
                }
                for (int u = 0; u < c; u++) {
                    System.out.println("f[" + u + "] " + f[u]);
                }
                for (m = 0; m < c; m++) {
                    if (f[m].matches(".\\d+.*")) {
                        break;
                    } else {
                        b = b + f[m];
                    }
                }
                b = b + ":" + f[m] + ":" + f[m + 3];
                if(n==0)
                 d=b;
                else
                    d=d+"_"+b;
                n++;
            }

            x++;
        }
         out.print("<p>" + d + "</p>");
        input.close();

    }

    private void linuxOutput(Process p, PrintWriter out) throws Exception {
        int x = 0,n=0;
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String sCurrentLine,w="";

        while ((sCurrentLine = br.readLine()) != null) {
            System.out.println(sCurrentLine);
            if (x > 0) {
                String[] s = sCurrentLine.split(" ");
                int c = 0;
                String d[] = new String[s.length];
                for (int j = 0; j < s.length; j++) {
                    if (!s[j].equals("")) {
                        d[c++] = s[j];
                    }
                }
                String a = "";
                a = d[9] + ":" + d[1] + ":" + d[3];
                if(n==0)
                    w=a;
                else
                    w=w+"_"+a;
                n++;
            }

            x++;
        }
        out.print("<p>" + w + "</p>");
        br.close();

    }

}
