package actionbazaar.persistence;

import java.io.Serializable;

public class LoginInfo implements Serializable {
  protected String username;

    protected String password;

    public LoginInfo() {
        super();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
