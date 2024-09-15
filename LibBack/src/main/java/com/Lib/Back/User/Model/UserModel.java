package com.Lib.Back.User.Model;import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_genseq", initialValue = 100, allocationSize = 1)
    private Long id;
    
    @Column(unique = true, length = 16)
    private String userName;
    
    private String fullName;
    
    
    private String email;  
    
    private long moNum;
    
    private String passWord;

    

    public UserModel() {
    }

    public UserModel(Long id, String userName, String fullName, String email, long moNum, String passWord) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.moNum = moNum;
        this.passWord = passWord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {  // Update getter name to match 'email'
        return email;
    }

    public void setEmail(String email) {  // Update setter name
        this.email = email;
    }

    public long getMoNum() {
        return moNum;
    }

    public void setMoNum(long moNum) {
        this.moNum = moNum;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
