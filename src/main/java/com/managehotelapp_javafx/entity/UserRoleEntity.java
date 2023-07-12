package com.managehotelapp_javafx.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_role")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tittle", length = 30, nullable = false, unique = true)
    private String tittle;

    @Column(name = "description", length =30, nullable = false)
    private String description;

    @OneToMany(mappedBy = "userRole")
    private Set<UserEntity> userEntities;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(Set<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }
}
