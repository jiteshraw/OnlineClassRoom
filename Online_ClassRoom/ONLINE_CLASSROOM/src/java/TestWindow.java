/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class TestWindow extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String sub = request.getParameter("choose");
        if (sub.equalsIgnoreCase("select")) {
            out.println("<h2>please select valid topic</h2>");
            out.println("<h2>Click Here to Student Home Page:<a href=\"testpage.jsp\">&nbsp;&nbsp;click here</a></h2>");
        } else {
            out.println("Welcome In:");
            out.println(sub);
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/virtualclass", "root", "root");
                PreparedStatement st = con.prepareStatement("select * from questionbank where SubjectName=?");
                st.setString(1, sub);
                ResultSet rs = st.executeQuery();
                out.println("<br><br><br>");
                out.println("<center>");
                
                out.println("<form action='Result' method='post'>");
                out.println("<table border=\"1\" bgcolor=\"cyan\" height=\"50\" width=\"1000\">");

                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>");
                    out.println(rs.getString("QuestionNo"));
                    out.println("&nbsp");
                    out.println(rs.getString("Question"));
                    out.println("<br>");
                    int i = rs.getInt("QuestionNo");
                    out.println("<input type='radio' name="+i+" value=\"a\">");
                    out.println(rs.getString("OptionA"));
                    out.println("<br>");
                    out.println("<input type='radio' name="+i+" value=\"b\">");
                    out.println(rs.getString("OptionB"));
                    out.println("<br>");
                    out.println("<input type='radio' name="+i+" value=\"c\">");
                    out.println(rs.getString("OptionC"));
                    out.println("<br>");
                    out.println("<input type='radio' name="+i+" value=\"d\">");
                    out.println(rs.getString("OptionD"));
                    out.println("</td>");
                    

                }
                out.println("<br>");
                out.println("<br>");
                out.println("</table>");
                out.println("<br>");
                out.println("<br>");
                out.println("<input type='submit' name='submit' value='submit'>");
                out.println("<input type='reset' name='reset' value='reset'>");

                out.println("</form>");
                out.println("<br><br><br>");
                out.println("Click Here to Student Home Page:<a href=\"studenthome.jsp\">click here</a>");

                out.println("</center>");

            } catch (Exception e) {
                System.out.println(e);
            }

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
