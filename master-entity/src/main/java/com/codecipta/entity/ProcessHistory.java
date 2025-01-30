package com.codecipta.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "process_history")
public class ProcessHistory {
    @Id
    private String id;
    @Column(name = "p_name")
    private String pName;

    public ProcessHistory() {
    }

    public ProcessHistory(String id, String pName) {
        this.id = id;
        this.pName = pName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    @Override
    public String toString() {
        return "ProcessHistory{" +
                "id='" + id + '\'' +
                ", pName='" + pName + '\'' +
                '}';
    }
}
