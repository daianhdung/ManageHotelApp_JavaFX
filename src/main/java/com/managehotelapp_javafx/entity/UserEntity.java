package com.managehotelapp_javafx.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", length = 30, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length =30, nullable = false)
    private String password;

    @Column(name = "email", length = 30)
    private String email;

    @Column(name = "fullName", length =50)
    private String fullName;

    @Column(name = "gender", length =30)
    private String gender;

    @Column(name = "identity", length = 12, nullable = false)
    private String identity;

    @Column(name = "address", length =200)
    private String address;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "user_status_id")
    private UserStatusEntity userStatus;

    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private UserRoleEntity userRole;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public UserStatusEntity getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatusEntity userStatus) {
        this.userStatus = userStatus;
    }

    public UserRoleEntity getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleEntity userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", gender='" + gender + '\'' +
                ", identity='" + identity + '\'' +
                ", address='" + address + '\'' +
                ", createdAt=" + createdAt +
                ", userStatus=" + userStatus +
                ", userRole=" + userRole +
                '}';
    }
}
