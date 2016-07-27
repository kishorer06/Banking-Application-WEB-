


/*
* Members: PayeeID, PayeeName, PayerAccountNumber, PayeeAddress, PayeePhoneNumber, CustomerID
* Methods: addPayee, updatePayee, deletePayee, getPayeeList
*/
package com.kishore.banking;
import java.util.*;
import java.lang.*; //including Java packages used by this program
import java.sql.*;
import com.kishore.banking.*;
import java.util.UUID;

public class Payee
{
	public String toString(){
		return "PayeeID:"+PayeeID+"Payee Name:"+PayeeName+"Payee Acct number:"+PayerAccountNumber+"Payee Address:"+PayeeAddress+"Payee Phone Number:"+PayeePhoneNumber+"Customer ID:"+CustomerID;
	}
	/* members */
	private UUID PayeeID;
	public String PayeeName, PayerAccountNumber, PayeeAddress, PayeePhoneNumber, CustomerID, p;
	 private Payee selctedPayee;
    private List <Payee> pl = new ArrayList<Payee>();

	/*constructors*/
	public Payee(String P_Name, String PA_Num, String P_Address, String PPh_Num, String Cust_ID) {
		PayeeName = P_Name;
		PayerAccountNumber = PA_Num;
		PayeeAddress = P_Address;
		PayeePhoneNumber = PPh_Num;
		CustomerID = Cust_ID;
	}
	public Payee(UUID P_Id, String P_Name, String PA_Num, String P_Address, String PPh_Num, String Cust_ID) {
		PayeeID = P_Id;
		PayeeName = P_Name;
		PayerAccountNumber = PA_Num;
		PayeeAddress = P_Address;
		PayeePhoneNumber = PPh_Num;
		CustomerID = Cust_ID;
	}
	public Payee(UUID PaID) {
		PayeeID = PaID;
	}
	public Payee() {
	}

	/* getters */
	public String getPayeeName(){
		return PayeeName;
	}
	public String getPayeePhoneNum(){
		return PayeePhoneNumber;
	}
	public String getPayeeAddress(){
		return PayeeAddress;
	}
	public UUID getPayeeId(){
		return PayeeID;
	}
	public String getPayerAcNum(){
		return PayerAccountNumber;
	}

	/* setters */
	public final void setPayeeName(String P_Name){
		PayeeName = P_Name;
	}
	public final void setPayeePhoneNum(String PPh_Num){
		PayeePhoneNumber = PPh_Num;
	}
	public final void setPayeeAddress(String P_Address){
		PayeeAddress = P_Address;
	}
	public final void setPayerAcNum(String PA_Num){
		PayerAccountNumber = PA_Num;
	}

	/* methods */
    public boolean addPayee(String CustomerID) {
		System.out.println("About to add Payee!!!"+this);
		boolean done = !"".equals(PayeeName) && !"".equals(PayerAccountNumber) && !"".equals(PayeeAddress) && !"".equals(PayeePhoneNumber) && !"".equals(CustomerID);
		try {
		    if (done) {
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        String SQL_Command = "SELECT PayeeName FROM Payee WHERE PayeeName ='"+PayeeName+"'"; //SQL query command
		        ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the payee exsits.
		        done = done && !Rslt.next();
		        if (done) {
		        	// Unique ID generation
		        	PayeeID =  UUID.randomUUID();
				    SQL_Command = "INSERT INTO Payee(PayeeID, PayeeName, PayeeAccountNumber, PayeeAddress, PayeePhoneNumber, CustomerID) VALUES ('"+PayeeID+ "','"+PayeeName+ "','"+PayerAccountNumber+"','"+PayeeAddress+"','"+PayeePhoneNumber+"','"+CustomerID+"')"; //Save the username, password and Name
				    Stmt.executeUpdate(SQL_Command);
			    }else {

					System.out.println("Payee Already Exists!!!");
				}
			    Stmt.close();
			    ToDB.closeConn();
			}else{
				System.out.println("Missing Mandatory Fields for Payee!!!");
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
	public boolean updatePayee() {
		boolean done = !PayeeName.equals("") && !PayerAccountNumber.equals("") && !PayeeAddress.equals("") && !PayeePhoneNumber.equals("");
		try {		//20
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        String SQL_Command = "SELECT * FROM Payee WHERE PayeeID ='"+PayeeID + "';"; //SQL query command
		        ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.
		        if (Rslt.next()) {
				    SQL_Command = "UPDATE Payee SET PayeeName='"+PayeeName+"', PayeeAddress='"+PayeeAddress+"', PayeePhoneNumber='"+PayeePhoneNumber+"', PayeeAccountNumber='"+PayerAccountNumber+"' WHERE PayeeID ='"+PayeeID+"';";
				    Stmt.executeUpdate(SQL_Command);
			        Stmt.close();
			        ToDB.closeConn();
                    done=true;
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
	public boolean deletePayee(){
		boolean done = false;
		try {		//20
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        String SQL_Command = "SELECT * FROM Payee WHERE PayeeID ='"+PayeeID+"'"; //SQL query command
		        ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.
		        if (Rslt.next()) {
				    SQL_Command = "DELETE FROM Payee WHERE PayeeID='"+PayeeID+"'"; //Save the username, password and Name
				    Stmt.executeUpdate(SQL_Command);
			        Stmt.close();
			        ToDB.closeConn();
                    done=true;
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
	public static List<Payee> getPayeeList(String Cust_ID){
		List <Payee> PayeeList = new ArrayList<Payee>();
		boolean done = false;
		try {		//20
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        String SQL_Command = "SELECT * FROM Payee WHERE CustomerID ='"+Cust_ID+"'"; //SQL query command
		        ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.
		        while (Rslt.next()) {

                	UUID P_Id = UUID.fromString(Rslt.getString("PayeeID"));
                	String P_Name = Rslt.getString("PayeeName"),
                	PA_Num =  Rslt.getString("PayeeAccountNumber"),
                	P_Address = Rslt.getString("PayeeAddress"),
                	PPh_Num = Rslt.getString("PayeePhoneNumber");
                	Cust_ID = Rslt.getString("CustomerID");

                	Payee payee = new Payee(P_Id, P_Name, PA_Num, P_Address, PPh_Num, Cust_ID); // Creating a new object
					PayeeList.add(payee);
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
	    return PayeeList;
	}

	public static Payee getPayeeInfo(UUID PayeeID){
		Payee P = new Payee();
		boolean done = false;
		try {		//20
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        String SQL_Command = "SELECT * FROM Payee WHERE PayeeID ='"+PayeeID+"'"; //SQL query command
		        ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.
		        while (Rslt.next()) {

                	UUID P_Id = UUID.fromString(Rslt.getString("PayeeID"));
                	String P_Name = Rslt.getString("PayeeName"),
                	PA_Num =  Rslt.getString("PayeeAccountNumber"),
                	P_Address = Rslt.getString("PayeeAddress"),
                	PPh_Num = Rslt.getString("PayeePhoneNumber"),
                	Cust_ID = Rslt.getString("CustomerID");

                	P = new Payee(P_Id, P_Name, PA_Num, P_Address, PPh_Num, Cust_ID); // Creating a new object
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
	    return P;
	}
}