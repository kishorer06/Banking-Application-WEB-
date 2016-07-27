

/*
* Members: TransactionID, TransactionType, TransactionDate, Amount, FromAccount, ToAccount, CustomerID
* Methods: recordTransaction, getTransactionList
*/

package com.kishore.banking;

import java.util.*;
import java.lang.*; //including Java packages used by this program
import java.sql.*;
import com.kishore.banking.*;
import java.util.UUID;

public class Transaction
{
	/* members */
	private UUID TransactionID;
	private java.util.Date TransactionDate;
	private float Amount;
	private String TransactionType, FromAccount, ToAccount, CustomerID;

	/*constructors*/
	public Transaction(java.util.Date T_Date, float Amt, String T_Type, String From_Acc, String To_Acc, String Cust_ID) {
		TransactionDate = T_Date;
		Amount = Amt;
		TransactionType = T_Type;
		FromAccount = From_Acc;
		ToAccount = To_Acc;
		CustomerID = Cust_ID;
	}
	public Transaction(UUID T_Id, java.util.Date T_Date, float Amt, String T_Type, String From_Acc, String To_Acc, String Cust_ID) {
		TransactionID = T_Id;
		TransactionDate = T_Date;
		Amount = Amt;
		TransactionType = T_Type;
		FromAccount = From_Acc;
		ToAccount = To_Acc;
		CustomerID = Cust_ID;
	}
	public Transaction() {
	}

	/* getters */
	public UUID getTransactionID(){
		return TransactionID;
	}
	public java.util.Date getTransactionDate(){
		return TransactionDate;
	}
	public float getAmount(){
		return Amount;
	}
	public String getTransactionType(){
		return TransactionType;
	}
	public String getFromAccount(){
		return FromAccount;
	}
	public String getToAccount(){
		return ToAccount;
	}
	public String getCustomerID(){
		return CustomerID;
	}

	/* setters */
	public void setTransactionDate(java.util.Date T_Date){
		TransactionDate = T_Date;
	}
	public void setAmount(float Amt){
		Amount = Amt;
	}
	public void setTransactionType(String T_Type){
		TransactionType = T_Type;
	}
	public void setFromAccount(String From_Acc){
		FromAccount = From_Acc;
	}
	public void setToAccount(String To_Acc){
		ToAccount = To_Acc;
	}

	/* methods */
    public boolean recordTransaction() {
		boolean done = !TransactionDate.equals("") && !TransactionType.equals("") && !CustomerID.equals("");
		try {
		    if (done) {
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        if (done) {
		        	// Unique ID generation
		        	TransactionID =  UUID.randomUUID();
		        	//converting java date to sql date
		        	java.sql.Date SQ_TransactionDate = new java.sql.Date(TransactionDate.getTime());
				    String SQL_Command = "INSERT INTO Transactions(TransactionID, TransactionDate, Amount, TransactionType, FromAccount, ToAccount, CustomerID) VALUES ('"+TransactionID+ "','"+SQ_TransactionDate+"','"+Amount+"','"+TransactionType+"','"+FromAccount+"','"+ToAccount+"','"+CustomerID+"');";
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
	public List<Transaction> getTransactionList(String Cust_ID){
		List <Transaction> TransactionList = new ArrayList<Transaction>();
		boolean done = false;
		try {		//20
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        String SQL_Command = "SELECT * FROM Transactions WHERE CustomerID ='"+Cust_ID+"';"; //SQL query command
		        ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.
		        while (Rslt.next()) {

                	UUID T_Id = UUID.fromString(Rslt.getString("TransactionID"));
                	float Amt = Float.parseFloat(Rslt.getString("Amount"));
                	//formating string to date
					java.sql.Date SQ_T_Date = Rslt.getDate("TransactionDate");
					java.util.Date T_Date = new java.util.Date(SQ_T_Date.getTime());
                	String T_Type = Rslt.getString("TransactionType"),
                	From_Acc =  Rslt.getString("FromAccount"),
                	To_Acc = Rslt.getString("ToAccount"),
                	Cus_ID = Rslt.getString("CustomerID");

                	Transaction Transaction = new Transaction(T_Id, T_Date, Amt, T_Type, To_Acc, From_Acc, Cus_ID); // Creating a new object
					TransactionList.add(Transaction);
                }
			        Stmt.close();
			        ToDB.closeConn();
                    done=true;
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
	    return TransactionList;
	}
}