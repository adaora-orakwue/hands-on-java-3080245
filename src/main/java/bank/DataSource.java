package bank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//this is how we connect to database and reading and writing from it
public class DataSource {

  public static Connection connect(){
    //declare path to database file
    String db_file = "jdbc:sqlite:resources/bank.db";

    //declare connection object
    Connection connection = null;

    //getConnection method throws an exception on error so we have to use a try catch
    try{
      connection = DriverManager.getConnection(db_file);
      System.out.println("were connected");
    }catch(SQLException e){
      e.printStackTrace();
    }
     //Success
     return connection;
  }


  //get customer infro from database
  public static Customer getCustomer(String username){

    //SQL query. We use question mark for data protection
    String sql = "select * from customers where username = ?";
    //java will close resource  after try block is commpleted
    Customer customer = null;
    try(Connection connection = connect(); //semicolon specifies additional resources
  
        PreparedStatement statement = connection.prepareStatement(sql)){
        //username type is string
        statement.setString(1, username);

        //execute query 
        try(ResultSet resultSet = statement.executeQuery()){
        //initialize customer here by passign in all the coloumn names
        customer = new Customer(
          resultSet.getInt("id"),
          resultSet.getString("name"),
          resultSet.getString("username"),
          resultSet.getString("password"),
          resultSet.getInt("account_id"));

        }
    
     
    }catch(SQLException e){
      e.printStackTrace();
    }
    
    return customer;
  }

  public static Account getAccount(int accountId){
    String sql = "select * from accounts where id = ?";
    Account account = null;
    try(Connection connection = connect();
      PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setInt(1, accountId);

        try(ResultSet resultSet = statement.executeQuery()){
          account = new Account(
            resultSet.getInt("id"),
            resultSet.getString("type"),
            resultSet.getDouble("balance"));
          
        }
      }catch(SQLException e){
        e.printStackTrace();
      }
  
    return account;
    }

  //we need to update the database when a customer makes changes
  public static void updateAccountBalance(int accountId, double balance){

    //query statement
    String sql = "update account set balance = ? where id = ?";

    //connect to database and prepare statemenet

    try(
    Connection connection = connect();
    PreparedStatement statement = connection.prepareStatement(sql);
    ){

      statement.setDouble(1, balance);
      statement.setInt(2, accountId);

      statement.executeUpdate();
    }catch(SQLException e){
      e.printStackTrace();
    }

    }

  }
   
  //main method to run our connection object
  
/* 
 public static void main(String [] args){

  


   //get the customer name from database using username
   Customer customer = getCustomer("bwailes4a@mac.com"); 
   Account account = getAccount(customer.getAccountId());
   // System.out.println(customer.getName());
   System.out.println(account.getBalance());
  }
*/

  







