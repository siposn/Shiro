package com.shiroapp.domain;
// Generated May 14, 2014 4:23:47 PM by Hibernate Tools 3.6.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Shirouser generated by hbm2java
 */
@Entity
@Table(name="shirouser"
    ,catalog="shiro_db"
    , uniqueConstraints = @UniqueConstraint(columnNames="username") 
)
public class Shirouser  implements java.io.Serializable {

     private Long id;
     private String password;
     private String username;

    public Shirouser() {
    }

    public Shirouser(String password, String username) {
       this.password = password;
       this.username = username;
    }
   
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="password", nullable=false)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="username", unique=true, nullable=false)
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
}


