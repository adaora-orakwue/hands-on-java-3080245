package bank;

public class Customer {

  //these fields cannot be accesed outside of this class
  //we copied them from the Customer database columns
  private int id;
  private String name;
  private String username;
  private String password;
  private int accountId;

  //a flag that indicates a customer has been authenticated
  private boolean authenticated;

  public Customer(int id, String name,String username, String password, int accountId){
    setId(id);
    setName(name);
    setUserName(username);
    setPassword(password);
    setAccountId(accountId);
    setAuthenticated(false);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUserName(String username) {
    this.username= username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getAccountId() {
    return this.accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public boolean isAuthenticated(){
    return this.authenticated;
  }

  public void setAuthenticated(boolean authenticated){
    this.authenticated= authenticated;
  }

  

}
