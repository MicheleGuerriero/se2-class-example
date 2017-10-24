package it.polimi.classexample.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
public class User implements Serializable {

    @Id
    @Column(nullable = false)
    private String userName;
    
    @Column(nullable = false)
    private String password;
	
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}
	
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
}
