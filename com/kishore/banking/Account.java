
/*
* Members: Username, Password, Password1, Name
* Methods: signUp, signIn, changePassword
*/

package com.kishore.banking;

import java.lang.*;
import java.sql.*;
import java.util.Vector;
import com.kishore.banking.*;

public class Account
{
	/*Members*/
	private String Username, Password, Password1, Name;

	/*constructors*/
	public Account(String UN, String PassW, String PassW1, String NM) {
		Username = UN;
		Password = PassW;
		Password1 = PassW1;
		Name = NM;
	}
	public Account(String UN, String PassW) {
		Username = UN;
		Password = PassW;
	}
	public Account(String UN, String PassW, String name) {
		Username = UN;
		Password = PassW;
		Name = name;
	}
	public Account(String UN) {
		Username = UN;
	}
	public Account(){}

	/* getters */
	public String getName(){
		return Name;
	}
	/* setters */
	public final void setUsername(String UName){
		Username = UName;
	}
	public final void setPassword(String PassW){
		Password = PassW;
	}
	public final void setName(String name){
		Name = name;
	}

	/*methods*/
    public boolean signUp() {
		boolean done = !Username.equals("") && !Password.equals("") && !Password1.equals("") && Password.equals(Password1);
		try {
		    if (done) {
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        System.out.println(Username);
		        String SQL_Command = "SELECT Username FROM Account WHERE Username ='"+Username+"'"; //SQL query command
		        ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.
		        done = done && !Rslt.next();
		        if (done) {
				    SQL_Command = "INSERT INTO Account(Username, Password, Name) VALUES ('"+Username+ "','"+Password+"','"+Name+"')"; //Save the username, password and Name
				    Stmt.executeUpdate(SQL_Command);
			    }
			    Stmt.close();
			    ToDB.closeConn();
			}
		}
	    catch(java.sql.SQLException e)
	    {         done = false;
				 System.out.println("SQLException: " + e);
				 while (e != null)
				 {   System.out.println("SQLState: " + e.getSQLState());
					 System.out.println("Message: " + e.getMessage());
					 System.out.println("Vendor: " + e.getErrorCode());
					 e = e.getNextException();
					 System.out.println("");
				 }
	    }
	    catch (java.lang.Exception e)
	    {         done = false;
				 System.out.println("Exception: " + e);
				 e.printStackTrace ();
	    }
	    return done;
	}
	public Account signIn(String uname, String pwd){
		Account acc = new Account();
		boolean done = !uname.equals("") && !pwd.equals("");
		try {		//20
				if(done){
			        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
			        Connection DBConn = ToDB.openConn();
			        Statement Stmt = DBConn.createStatement();
			        String SQL_Command = "SELECT * FROM Account WHERE Username ='"+uname+"' AND Password ='"+pwd+"';"; //SQL query command
			        ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.
			        if (Rslt.next()) {
	            	    acc.setUsername(Rslt.getString("Username"));
	                	acc.setPassword(Rslt.getString("Password"));
	                	acc.setName(Rslt.getString("Name"));
	                }else{
	                	acc.setName("");
	                }
				        Stmt.close();
				        ToDB.closeConn();
				}
		}
	    catch(java.sql.SQLException e)		//5
	    {
				 System.out.println("SQLException: " + e);
				 while (e != null)
				 {   System.out.println("SQLState: " + e.getSQLState());
					 System.out.println("Message: " + e.getMessage());
					 System.out.println("Vendor: " + e.getErrorCode());
					 e = e.getNextException();
					 System.out.println("");
				 }
	    }
	    catch (java.lang.Exception e)
	    {
				 System.out.println("Exception: " + e);
				 e.printStackTrace ();
	    }
	    return acc;
	}
	public boolean SignIn(){
		boolean result = false;
		try {		//20
				if(!Username.equals("") && !Password.equals("")){
			        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
			        Connection DBConn = ToDB.openConn();
			        Statement Stmt = DBConn.createStatement();
			        String SQL_Command = "SELECT * FROM Account WHERE Username ='"+Username+"' AND Password ='"+Password+"';"; //SQL query command
			        ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.
			        if (Rslt.next()) {
	            	    result = true;
	                }
	              		Stmt.close();
				        ToDB.closeConn();
				}
		}
	    catch(java.sql.SQLException e)		//5
	    {
				 System.out.println("SQLException: " + e);
				 while (e != null)
				 {   System.out.println("SQLState: " + e.getSQLState());
					 System.out.println("Message: " + e.getMessage());
					 System.out.println("Vendor: " + e.getErrorCode());
					 e = e.getNextException();
					 System.out.println("");
				 }
	    }
	    catch (java.lang.Exception e)
	    {
				 System.out.println("Exception: " + e);
				 e.printStackTrace ();
	    }
	    return result;
	}
	public boolean changePassword(String NewPassword) {
		boolean done = false;
		try {		//20
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        String SQL_Command = "SELECT * FROM Account WHERE Username ='"+Username+ "'AND Password ='"+Password+"'"; //SQL query command
		        ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.
		        if (Rslt.next()) {
				    SQL_Command = "UPDATE Account SET Password='"+NewPassword+"' WHERE Username ='"+Username+"'"; //Save the username, password and Name
				    Stmt.executeUpdate(SQL_Command);
			        Stmt.close();
			        ToDB.closeConn();
                    done=true;
				}
		}
	    catch(java.sql.SQLException e)		//5
	    {         //done = false;
				 System.out.println("SQLException: " + e);
				 while (e != null)
				 {   System.out.println("SQLState: " + e.getSQLState());
					 System.out.println("Message: " + e.getMessage());
					 System.out.println("Vendor: " + e.getErrorCode());
					 e = e.getNextException();
					 System.out.println("");
				 }
	    }
	    catch (java.lang.Exception e)
	    {        // done = false;
				 System.out.println("Exception: " + e);
				 e.printStackTrace ();
	    }
	    return done;
	}

	public Vector[] Inquire(String str1, String str2) {
		Vector[] vectors = new Vector[2];
		Vector columnNames = new Vector();
		Vector data = new Vector();

		try {
			DBConnection ToDB = new DBConnection(); // Have a connection to the
			Connection DBConn = ToDB.openConn();
			Statement Stmt = DBConn.createStatement();

			String SQL_Command = "SELECT * FROM Transactions WHERE TransactionDate >='"+str1+"' And TransactionDate <='"+str2+"' AND CustomerID ='"+Username+"'";

			// System.out.println("query: "+SQL_Command);
			ResultSet rs = Stmt.executeQuery(SQL_Command);
			if(null == rs)
				System.out.println("result set is null");
			ResultSetMetaData metaData = rs.getMetaData();
			int columns = metaData.getColumnCount();
			for (int i = 1; i <= columns; i++) {
				// System.out.println(metaData.getColumnName(i));
				// System.out.println(metaData.getColumnTypeName(i));
				columnNames.addElement(metaData.getColumnName(i));
			}

			while (rs.next()) {
				Vector row = new Vector(columns);
				// System.out.println(columns);
				for (int i = 1; i <= columns; i++) {
					// System.out.println(i);
					row.addElement(rs.getObject(i));
				}
				data.addElement(row);
			}
			Stmt.close();
			ToDB.closeConn();
		}

		catch (java.sql.SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException: " + e);
			while (e != null) {
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("Message: " + e.getMessage());
				System.out.println("Vendor: " + e.getErrorCode());
				e = e.getNextException();
				System.out.println("");
			}
		} catch (java.lang.Exception e) {
			System.out.println("Exception: " + e);
			e.printStackTrace();
		}
		vectors[0] = columnNames;
		vectors[1] = data;
			return vectors;
			// return data;
	}
}