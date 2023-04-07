package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import bank.exceptions.AmountException;

public class Menu {
  //to recieve input from text council we need to use the Scanner class

  private Scanner scanner;





  private Customer authenticateUser(){
    System.out.println("Please enter your username");

    //scanner.next reads a string from the console
    String username = scanner.next();
    System.out.println("Please sener your password"); 
    String password = scanner.next();

    //we need to use a try catch because login throws an exception
    Customer customer = null;
    try{
      customer = Authenticator.login(username, password);
    }catch(LoginException e){
      System.out.println("There was an error: " +e.getMessage());
  }

  return customer;

  }
  

  private void showMenu(Customer customer, Account account){
    int selection = 0;

    
    while(selection != 4 && customer.isAuthenticated()){
        System.out.println("============================================");
        System.out.println("Please  select one of the following options:");
        System.out.println("1:Deposit");
        System.out.println("2:Withdraw");
        System.out.println("3:Check Balance");
        System.out.println("4:Exit");
        System.out.println("==============================================");

        selection = scanner.nextInt();
        double amount = 0;

        //when should we use switch?
        switch(selection){
          case 1:
          System.out.println("How much would you like to deposit?");
          amount = scanner.nextDouble();
          try{
          account.deposit(amount);
          }catch(AmountException e){
            System.out.println(e.getMessage());
            System.out.println("Please try again.");
          }
          
          break;

          case 2:
          System.out.println("How much would you like to withdraw?");
          amount = scanner.nextDouble();
          try{
            account.withdraw(amount);
          }catch(AmountException e){
            System.out.println(e.getMessage());
            System.out.println("Please try again.");
          }
          break;

          case 3:
          System.out.println("Current balance: " + account.getBalance());
          break;

          case 4:
          Authenticator.logout(customer);
          System.out.println("Thanks for banking at Baddie Bank");
          break;

          default:
          System.out.println("Invalid option, Please try again");
          break;


        }

    }
  }

public static void main(String[] args){
    System.out.println("Welcome to Baddie Bank International");




    //need to create a manu object to access Scanner
    Menu menu = new Menu();
    
    menu.scanner = new Scanner(System.in);

    Customer customer = menu.authenticateUser();

    if(customer!= null){
      Account account = DataSource.getAccount(customer.getAccountId());
      menu.showMenu(customer,account);
    }
  



    menu.scanner.close();
  }
}
