

/*
* Members: SavingsAccountNumber, CustomerName, Balance, CustomerID
* Methods: openAcct, deposit, withdraw
*/
package com.kishore.banking;

import java.lang.*; //including Java packages used by this program
import java.sql.*;
import com.kishore.banking.*;

public class SavingsAccount
{
	/*Members*/
	String SavingsAccountNumber, CustomerName, CustomerID;
	float Balance;

	/*Constructors*/
	public SavingsAccount(String SaAcNu, String CuNa, String CuID, float Bal){
		SavingsAccountNumber = SaAcNu;
		CustomerName = CuNa;
		CustomerID = CuID;
		Balance = Bal;
	}
	// public SavingsAccount(String SA_Num) {
	// 	SavingsAccountNumber = SA_Num;
	// }
	public SavingsAccount(String Cust_ID) {
		CustomerID = Cust_ID;
	}
	public SavingsAccount(){}
	/*getters*/
	public float getBal(){
		return Balance;
	}
	public String getSANum(){
		return SavingsAccountNumber;
	}

	/*setters*/
	public void setBalance(float Bal){
		Balance = Bal;
	}
	public void setSName(String SName){
		CustomerName = SName;
	}
	public void setSANum(String SA_Num){
		SavingsAccountNumber = SA_Num;
	}

	/*Methods*/
	public boolean openAcct() {
	    boolean done = false;
				try {
				    if (!done) {
				        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
				        Connection DBConn = ToDB.openConn();
				        Statement Stmt = DBConn.createStatement();
				        String SQL_Command = "SELECT SavingsAccountNumber FROM SavingsAccount WHERE SavingsAccountNumber ='"+SavingsAccountNumber+"'"; //SQL query command
				        ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.
				        done = !Rslt.next();
				        if (done) {
						    SQL_Command = "INSERT INTO SavingsAccount(SavingsAccountNumber, CustomerName, Balance, CustomerID)"+
						                  " VALUES ('"+SavingsAccountNumber+"','"+CustomerName+"',"+Balance+", '"+CustomerID+"')"; //Save the username, password and Name
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
	public double getBalance() {  //Method to return a SavingsAccount balance
		try {
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        String SQL_Command = "SELECT Balance FROM SavingsAccount WHERE SavingsAccountNumber ='"+SavingsAccountNumber+"'"; //SQL query command for Balance
		        ResultSet Rslt = Stmt.executeQuery(SQL_Command);

		        while (Rslt.next()) {
					Balance = Rslt.getFloat(1);
			    }
			    Stmt.close();
			    ToDB.closeConn();
		}
	    catch(java.sql.SQLException e)
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
	    return Balance;
	}
    public double getBalance(String SavngsAcct) {  //Method to return a SavingsAccount balance
		try {
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        String SQL_Command = "SELECT Balance FROM SavingsAccount WHERE SavingsAccountNumber ='"+SavingsAccountNumber+"'"; //SQL query command for Balance
		        ResultSet Rslt = Stmt.executeQuery(SQL_Command);

		        while (Rslt.next()) {
					Balance = Rslt.getFloat(1);
			    }
			    Stmt.close();
			    ToDB.closeConn();
		}
	    catch(java.sql.SQLException e)
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
	    return Balance;
	}
	public boolean updateBalance(){
		boolean done = !SavingsAccountNumber.equals("") && !CustomerName.equals("") && !CustomerID.equals("");
		try {		//20
		        if(done){
			        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
			        Connection DBConn = ToDB.openConn();
			        Statement Stmt = DBConn.createStatement();
			        String SQL_Command = "SELECT * FROM SavingsAccount WHERE SavingsAccountNumber ='"+SavingsAccountNumber + "';"; //SQL query command
			        ResultSet Rslt = Stmt.executeQuery(SQL_Command);
			        if (Rslt.next()) {
					    SQL_Command = "UPDATE SavingsAccount SET Balance='"+Balance+"';";
					    Stmt.executeUpdate(SQL_Command);
				        Stmt.close();
				        ToDB.closeConn();
					}
		        }
		}
	    catch(java.sql.SQLException e)		//5
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
	public SavingsAccount getAccountInfo(String UName){
		SavingsAccount SAcc = new SavingsAccount();
		try {		//20
	        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
	        Connection DBConn = ToDB.openConn();
	        Statement Stmt = DBConn.createStatement();
	        String SQL_Command = "SELECT * FROM SavingsAccount WHERE CustomerID ='"+UName + "';"; //SQL query command
	        ResultSet Rslt = Stmt.executeQuery(SQL_Command);
	        if (Rslt.next()) {
	        	SAcc.setBalance(Float.parseFloat(Rslt.getString("Balance")));
	        	SAcc.setSANum(Rslt.getString("SavingsAccountNumber"));
	        	SAcc.setSName(Rslt.getString("CustomerName"));
		        Stmt.close();
		        ToDB.closeConn();
			}else{
				SAcc.setSANum("");
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
	    return SAcc;
	}

	public boolean Withdraw(String SAcctNumber, double amount) {
		boolean done = !SAcctNumber.equals("");
		double balance = getBalance(SAcctNumber);
		System.out.println("The current balance in saving account is " + Balance);
		if (Double.compare(balance - amount, amount) < 0) {
			done = false;
		}
		double newSKBalance = balance - amount;
		try {
			if (done) {
				DBConnection ToDB = new DBConnection(); // Have a connection to
														// the DB
				Connection DBConn = ToDB.openConn();
				Statement Stmt = DBConn.createStatement();
				String SQL_Command = "SELECT SavingsAccountNumber FROM SavingsAccount WHERE SavingsAccountNumber ='"+ SAcctNumber + "'"; // SQL query command
				ResultSet Rslt = Stmt.executeQuery(SQL_Command);
				done = done && Rslt.next();
				if (done) {
					SQL_Command = "UPDATE SavingsAccount SET Balance = '"
							+ newSKBalance
							+ "'  WHERE SavingsAccountNumber = '" + SAcctNumber
							+ "'";
					Stmt.executeUpdate(SQL_Command);
					System.out.println("The current balance in savings account after withdraw is " + Balance);
				}
				Stmt.close();
				ToDB.closeConn();
			}
		} catch (java.sql.SQLException e) {
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
		return done;
	}

	public boolean deposit(String SAcctNumber, float amount) {
		float newBalance;
		try {
			DBConnection ToDB = new DBConnection(); // Have a connection to the
													// DB
			Connection DBConn = ToDB.openConn();
			Statement Stmt = DBConn.createStatement();
			String SQL_Command = "SELECT Balance FROM SavingsAccount WHERE SavingsAccountNumber ='"
					+ SAcctNumber + "'"; // SQL query command for Balance
			ResultSet Rslt = Stmt.executeQuery(SQL_Command);

			while (Rslt.next()) {
				Balance = Rslt.getFloat(1);
				System.out.println("The current balancein Saving account is "
						+ Balance);
				Balance = Balance + amount;
				Statement Stmt2 = DBConn.createStatement();
				String sqlcmd = "update SavingsAccount set Balance = "
						+ Balance + " where SavingsAccountNumber='"
						+ SAcctNumber + "'";
				Stmt2.executeUpdate(sqlcmd);
				System.out
						.println("The present balancein Saving account after depositing is "
								+ Balance);

			}
			Stmt.close();
			ToDB.closeConn();
		} catch (java.sql.SQLException e) {
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

		return true;
	}

	public boolean SavingsAccountExists(String UName){
		boolean exsits = false;
		try {		//20
	        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
	        Connection DBConn = ToDB.openConn();
	        Statement Stmt = DBConn.createStatement();
	        String SQL_Command = "SELECT * FROM SavingsAccount WHERE CustomerID ='"+UName + "';"; //SQL query command
	        ResultSet Rslt = Stmt.executeQuery(SQL_Command);
	        if (Rslt.next()) {
	        	exsits = true;
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
	    return exsits;
	}

}