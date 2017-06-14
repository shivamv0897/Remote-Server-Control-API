
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.*;

/**
 *
 * @author shivam
 */
public class ServiceDetails extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           int x=0;
           String os = System.getProperty("os.name").toLowerCase();
           Process p;
           if(os.contains("windows"))
                   {   System.out.println("inside windoes!!!!!!!!!!!!!!!!!");
                       p=Runtime.getRuntime().exec("sc queryex type= service state= all");
                       executeWindows(p,out);
                   }
           else{
                System.out.println("inside other os");
                p=Runtime.getRuntime().exec("systemctl list-unit-files");
                executeLinux(p,out);
           }
           
          
        }
        catch (Exception err) {
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

    private void executeWindows(Process p, PrintWriter out) throws Exception {
         String line,a=""; 
         BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                a=a+line;
            }
            input.close();
            String s[]=a.split(":");
                int c=0;
                String j="",l="";
                String f[]=new String[s.length];
                for(int i=0;i<s.length;i++)
                {
                    if(!s[i].equals("")&&!s[i].equals(" "))
                        f[c++]=s[i];
                }
                 for(int i=0;i<c;i++)
                 {
                    int n=((9*i)+(i+1));
                    if(n<c)
                    {
                        j=j+f[n];
                    }
                    n=((10*i)+(4));
                    if(n<c)
                    {
                        l=l+f[n];
                    }
                }
                 String m[]=j.split("DISPLAY_NAME");
                 String n[]=l.split("  ");
                 String b="",k="";
                 int q=0,z=0;
                 for(int i=0;i<m.length;i++)
                 {
                      if(!m[i].equals("")&&!m[i].equals("DISPLAY_NAME")&&!m[i].equals(" "))
                         { if(q!=0)
                           {b=b+":"+m[i];}
                         else {b=m[i];}
                           q++;
                         }
                 }
                 for(int i=0;i<n.length;i++)
                 {
                     if(n[i].equalsIgnoreCase("RUNNING")||n[i].equalsIgnoreCase("STOPPED"))
                      { if(z!=0)
                        k=k+":"+n[i];
                        else
                         k=n[i]; 
                        z++;
                      }
                 }
                b=b.replaceAll("  ","");
                m=b.split(":");
                n=k.split(":");
                String x="";
                for(int i=0;i<m.length;i++)
                {
                    if(i<m.length-1)
                        x=x+m[i]+":"+n[i]+":";
                    else
                        x=x+m[i]+":"+n[i];
                }
                 out.print("<p>"+x+"</p>");
    }

    private void executeLinux(Process p, PrintWriter out) throws Exception {
         String line,a=""; 
         int x=0,n=0;
         BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                String s[]=line.split("\\s+");
                                String b="";
                                if(x>0)
                                {  b=s[0]+":"+s[1];
                                   if(n>0)
                                       a=a+"_"+b;
                                   else
                                       a=b;
                                   n++;
                                }
                                x++;
			}
                        out.print("<p>"+a+"</p>");
            
            input.close();
    }

}
