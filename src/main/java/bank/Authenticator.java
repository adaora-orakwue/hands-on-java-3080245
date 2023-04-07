package bank;

import javax.security.auth.login.LoginException;

//securing bank account 
public class Authenticator {
  
  public static Customer login (String username, String password) throws LoginException{

  //since it getCustomer static we dont have to instantiate a datasource object
  //to access the method
  Customer customer = DataSource.getCustomer(username);

  //make sure customer is not null
  if(customer == null){
    throw new LoginException("Username not found");

  }

  //remeber when comparing objects its safe to use .equals to compare value 
  //rather than memeory location
  if(password.equals(customer.getPassword())){
      customer.setAuthenticated(true);
      return customer;
  }
  else throw new LoginException("Incorrect password");

  }
  public static void logout(Customer customer){

    customer.setAuthenticated(false);
  }

}
