package ejb3inaction.example;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless(name = "HelloUserBean", mappedName = "ejb3inaction-Chapter1-HelloUserBean")

public class HelloUserBean implements HelloUser {
    public HelloUserBean() {
    }

    public void sayHello(String name) {
      System.out.println("Hello " + name + " welcome to EJB 3 In Action!");
    }
}
