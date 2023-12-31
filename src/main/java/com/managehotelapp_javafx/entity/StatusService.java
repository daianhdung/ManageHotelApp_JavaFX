package com.managehotelapp_javafx.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "status_service")
public class StatusService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", length = 30, nullable = false, unique = true)
    private String title;

    @Column(name = "description", length =30, nullable = false)
    private String description;

    @OneToMany(mappedBy = "statusService")
    private Set<ServiceEntity> serviceEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ServiceEntity> getServiceEntities() {
        return serviceEntities;
    }

    public void setServiceEntities(Set<ServiceEntity> serviceEntities) {
        this.serviceEntities = serviceEntities;
    }
}
