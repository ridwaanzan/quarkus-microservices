package com.codecipta.repository;

import com.codecipta.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public List<User> getAll() {
        return listAll();
    }

    public User findByUsername(String username) {
        return find("username", username).firstResult();
    }

    public User findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public User findByUsernameAndPassword(String username, String password) {
        return find("username = ?1 and password = ?2", username, password).firstResult();
    }

    public User findByEmailAndPassword(String email, String password) {
        return find("email = ?1 and password = ?2", email, password).firstResult();
    }
}
