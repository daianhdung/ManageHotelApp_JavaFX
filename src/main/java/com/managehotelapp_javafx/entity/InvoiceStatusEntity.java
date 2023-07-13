package com.managehotelapp_javafx.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "invoice_status")
public class InvoiceStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", length = 30, nullable = false, unique = true)
    private String title;

    @Column(name = "description", length =30, nullable = false)
    private String description;

    @OneToMany(mappedBy = "invoiceStatus")
    private Set<InvoiceEntity> invoiceEntities;

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

    public Set<InvoiceEntity> getInvoiceEntities() {
        return invoiceEntities;
    }

    public void setInvoiceEntities(Set<InvoiceEntity> invoiceEntities) {
        this.invoiceEntities = invoiceEntities;
    }
}
