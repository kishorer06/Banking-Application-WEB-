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

public class LogoutServlet extends HttpServlet {
   private String Username, Password, Re_enterPassword, CustomerName;
   private PrintWriter output;

   //a method called automatically to initialize the servlet
   public void init( ServletConfig config )
      throws ServletException
   {
      super.init( config );
     /* Username = new String("");
      Password = new String("");
      Re_enterPassword = new String("");
      CustomerName = new String("");*/

   }

   //a method included in class HttpServlet to respond
   //to post requests from a client.
   public void doGet ( HttpServletRequest req, HttpServletResponse res )
      throws ServletException, IOException
   {	doPost(req, res);

		 HttpSession ses = req.getSession(false);

	        if (ses != null) {
	            ses.invalidate();
	        output.println("<h4 ALIGN='center'>You have sucessfully logged out of the System. Thank you! You can login at any time...</h4>");
	req.getRequestDispatcher("/project4/login.html").include(req, res);
		}
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
          // showSuccess();
   }

  /* public void showSuccess()
   {
        StringBuffer Buf = new StringBuffer();
		Buf.append("<HTML><HEAD></HEAD>\n");
				Buf.append("<h4 ALIGN='center'>You have sucessfully logged out of the System. Thank you! You can login at any time...</h4>\n");
				Buf.append("<b>&nbsp</b><br>");
		Buf.append("<BODY bgcolor='#F1F1FD'>\n");
		Buf.append("<p align='RIGHT'><A style='TEXT-DECORATION:NONE;' HREF='/project4/login.html'><BUTTON>Home</BUTTON></p>");
			Buf.append("<FORM NAME='LoginPage' ACTION='/OnlineLoginServlet' METHOD ='POST'>\n");
			Buf.append("<CENTER>\n");
			   Buf.append("<TABLE cellPadding='3' ALIGN='center'>\n");
					Buf.append("<TR bgcolor='#ECFAEB'>\n");
					   Buf.append("<TD>USERNAME:</TD>\n");
					  Buf.append("<TD>\n");
						  Buf.append("<INPUT TYPE='text' NAME='Username' Value=\""+Username+"\" SIZE='15' focused>\n");
					  Buf.append("</TD>\n");
					Buf.append("</TR>\n");
					Buf.append("<TR bgcolor='#ECFAEB'>\n");
					  Buf.append("<TD>PASSWORD:</TD>\n");
					  Buf.append("<TD>\n");
						   Buf.append("<INPUT TYPE='password' NAME='PassWord' Value='' SIZE='15'>\n");
					  Buf.append("</TD>\n");
					Buf.append("</TR>\n");
				Buf.append("</TABLE>\n");
						   Buf.append("<INPUT TYPE='button' VALUE='Login' onClick='checkInputs()'>\n");
		      Buf.append("</CENTER>\n");
		   Buf.append("</FORM>\n");
		Buf.append("</BODY>\n");
		Buf.append("<SCRIPT LANGUAGE='JavaScript'>\n");
		Buf.append("document.LoginPage.Username.focus();\n");
		   Buf.append("function checkInputs()\n");
		   Buf.append("{\n");
			   Buf.append("var Prompts = \"\";\n");
			   Buf.append("Username = window.document.LoginPage.Username.value;\n");
			   Buf.append("Password = window.document.LoginPage.PassWord.value;\n");
			   Buf.append("if (Username == \"\" || Password == \"\") {\n");
				  Buf.append("if (Username == \"\")\n");
					 Buf.append("Prompts +=\"Please enter your username!\\n\";\n");
				  Buf.append("if (Password == \"\")\n");
					 Buf.append("Prompts +=\"Please enter your password!\\n\";\n");
				  Buf.append("if (Prompts != \"\")\n");
					 Buf.append("window.alert(Prompts);\n");
			   Buf.append("} else {\n");
				  Buf.append("document.LoginPage.submit();\n");
			   Buf.append("}\n");
		   Buf.append("}\n");
		Buf.append("</SCRIPT>\n");
		Buf.append("</HTML>\n");
		output.println(Buf.toString());
   }
*/
   //this "cleanup" method is called when a servlet is terminated by the server
   public void destroy() {
       output.close();
   }
}