/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: September, 2012													  *
*******************************************************************************/

import java.io.*;
import javax.servlet.*;  //package for GenericServlet
import javax.servlet.http.*;  //package for HttpServlet
import java.util.*;
import com.kishore.banking.*;
import javax.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {
     private String Username, Password;
     private PrintWriter output;
     private String CheckingAccountNum;
     private String SavingsAccountNum;

   //a method called automatically to initialize the servlet
   public void init( ServletConfig config )
      throws ServletException
   {
      super.init( config );
      Username = new String("");
      Password = new String("");
   }

   //a method included in class HttpServlet to respond
   //to post requests from a client.
   public void doGet ( HttpServletRequest req, HttpServletResponse res )
      throws ServletException, IOException
   {	doPost(req, res);


   }

   //a method included in class HttpServlet to respond
   //to post requests from a client.
   public void doPost ( HttpServletRequest req, HttpServletResponse res )
      throws ServletException, IOException
   {
      //obtains a character-based output stream that enables
      //text data to be sent to the client
      output = res.getWriter();

      //specifies the MIME type of the response to the browser
      res.setContentType( "text/html" );

//returns the value associated with a parameter sent to
      //the servlet as part of a post request
        Username = req.getParameter( "Username" );
        Password = req.getParameter( "PassWord" );
		HttpSession ses  =   req.getSession();
		ses.setAttribute("uname", Username);

      //returns the value associated with a parameter sent to
      //the servlet as part of a post request

      Account Acct = new Account(Username, Password);

      Acct = Acct.signIn(Username, Password);

      String Name = Acct.getName();
     	if(!Name.equals("")) {

			res.sendRedirect("/project4/AccountOverview.jsp");
	 	}
	       else{
	            output.println("<h4 align='center'>Account creation failed because of Invalid Username and Password. Please try again!</h4>");
	    		output.println("<H4 ALIGN='CENTER'>   Please Go <a href='/project4/login.html' style='text-decoration:none'><button> BACK </button> </A> !!! </H4>");
	    }

   }

   //this "cleanup" method is called when a servlet is terminated by the server
   public void destroy() {
       output.close();
   }
}