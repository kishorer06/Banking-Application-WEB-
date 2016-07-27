/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: September, 2012													  *
*******************************************************************************/

import java.io.*;
import javax.servlet.*;  //package for GenericServlet
import javax.servlet.http.*;  //package for HttpServlet
import java.util.*;
import com.kishore.banking.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class InquireTransactionServlet extends HttpServlet {
	private String startdate, enddate;
	private PrintWriter output;


	//a method called automatically to initialize the servlet
	public void init( ServletConfig config ) throws ServletException{
		super.init( config );
	}
	public void doGet ( HttpServletRequest req, HttpServletResponse res )throws ServletException, IOException{
		doPost(req, res);
	}

	public void doPost ( HttpServletRequest req, HttpServletResponse res )throws ServletException, IOException{
		output = res.getWriter();
		res.setContentType( "text/html" );
		HttpSession ses  =   req.getSession();
		String uname = (String) ses.getAttribute("uname");
		startdate = req.getParameter( "startdate" );
		enddate = req.getParameter( "enddate" );
		System.out.println("value......................"+enddate);
		try {
			DBConnection ToDB = new DBConnection(); // Have a connection to the
			Connection DBConn = ToDB.openConn();
			Statement Stmt = DBConn.createStatement();
			String SQL_Command = "SELECT * FROM Transactions WHERE TransactionDate >='"+startdate+"' AND TransactionDate <='"+enddate+"' AND CustomerID ='"+uname+"'";
			ResultSet rs = Stmt.executeQuery(SQL_Command);
			if (rs!= null) {
				StringBuffer Buf = new StringBuffer();

	Buf.append("<HTML>");
	Buf.append("<HEAD>");
	  Buf.append("<link href='https://fonts.googleapis.com/css?family=Lato:400,300,700,900' rel='stylesheet' type='text/css'>");
	Buf.append("</HEAD>");
	Buf.append("<BODY>");
	    Buf.append("<div id='Container'>");
	    Buf.append("<FORM NAME=\"display\"  METHOD =\"POST\">\n");
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				Buf.append("<TABLE cellPadding='3' ALIGN='center'>\n");
				Buf.append("<TR bgcolor='#ECFAEB'>\n");
				for (int i = 0; i < columnCount; i++) {
					Buf.append("<TH>" + rsmd.getColumnLabel(i + 1) + "</TH>");
				}
				Buf.append("</TR>\n");
				while (rs.next()) {
					Buf.append("<TR>");
					for (int i = 0; i < columnCount; i++) {
						System.out.println(rs.getString(i + 1));
						Buf.append("<TD>" + rs.getString(i + 1) + "</TD>");
					}
					Buf.append("</TR>");
				}
				Buf.append("</TABLE>\n");
		Buf.append("</FORM>\n");
		Buf.append("<p Align='CENTER'><A HREF='/project4/AccountOverview.jsp' STYLE='TEXT-DECORATION:NONE;'><button>Back</button></A></CENTER>");
	    Buf.append("</div>");
	    Buf.append("</BODY>");
	Buf.append("</HTML>");

				output.println(Buf.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//this "cleanup" method is called when a servlet is terminated by the server
	public void destroy() {
	output.close();
	}
}