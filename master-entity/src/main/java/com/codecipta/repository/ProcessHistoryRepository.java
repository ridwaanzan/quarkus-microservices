package com.codecipta.repository;

import com.codecipta.entity.ProcessHistory;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ProcessHistoryRepository implements PanacheRepository<ProcessHistory> {
    public List<ProcessHistory> getAll() {
        return listAll();
    }

    public ProcessHistory findByPName(String pName) {
        return find("pName", pName).firstResult();
    }
}
