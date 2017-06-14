
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.*;
public class Load extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
         String os = System.getProperty("os.name").toLowerCase();
            System.out.println("---------" + os);
            Process p;
            String a,b;
            if (os.contains("windows")) {
                System.out.println(")))))))))))");
                 p = Runtime.getRuntime().exec("wmic cpu get loadpercentage");
                 a=windowsOutput(p, out);
                 p = Runtime.getRuntime().exec("wmic ComputerSystem get TotalPhysicalMemory");
                 a=a+":"+windowsOutput(p, out);
                 p = Runtime.getRuntime().exec(" wmic OS get FreePhysicalMemory");
                 a=a+":"+windowsOutput(p, out);
                 p=Runtime.getRuntime().exec("wmic logicaldisk get size,freespace,caption");
                 a=a+":"+windowHardDrive(p);
                 out.print("<p>"+a+"</p>");
            } else {
                p = Runtime.getRuntime().exec("top -bn1 | grep \"Cpu(s)\" | \\sed \"s/.*, *\\([0-9.]*\\)%* id.*/\\1/\" | \\awk '{print 100 -$1\"%\"}' ");
                b=linuxOutput(p, out);
                p = Runtime.getRuntime().exec("cat /proc/meminfo | grep MemTotal");
                b=b+":"+linuxOutput(p,out);
                p = Runtime.getRuntime().exec("cat /proc/meminfo | grep MemFree");
                b=b+":"+linuxOutput(p,out);
                out.print("<p>"+b+"</p>");
                
                //TODO: write method of fetching details of hardware in linux.
            }
        }
        catch(Exception e)
        {
            
        }
    }
private String windowsOutput(Process p, PrintWriter out) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line,a="";
        int x = 0;
        while ((line = input.readLine()) != null) {
                if(line.matches(".\\d+.*"))
                    a=line;
     }     
        input.close();
        return a;
 
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
    private String linuxOutput(Process p, PrintWriter out) throws Exception{
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line,a="";
        int x = 0;
        while ((line = input.readLine()) != null) {
                if(line.contains(":"))
                {   String[] s=line.split(":");
                    a=s[1];
                }
                else a=line;
     }     
        input.close();
        return a;
    }

    private String windowHardDrive(Process p) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line,a="";
        int i=0,x=0;
        while((line=input.readLine())!=null)
        {
           i++;
            if(i>1 && !line.equals("") && !line.equals(" "))
            {
                String s[]=line.split(" ");
                for(int j=0;j<s.length;j++)
                {   
                    if(!s[j].equals("")&&!s[j].equals(" "))
                    {   if(s[j].contains(":"))
                          { s[j]=s[j].replace(":", "");
                          }
                        if(x==0)
                         a=s[j];
                       else
                        a=a+":"+s[j];
                     x++;
                    }
                }
            }
        }
        return a;
    }
}
