package com.codecipta.service;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.entity.User;
import org.acme.repository.UserRepository;

import java.util.List;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    public Uni<List<User>> getUsers() {
        return Uni.createFrom().item(() -> userRepository.listAll())
                .runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }

    public Uni<User> getUserFromUsername(String username) {
        return Uni.createFrom().item(() -> userRepository.findByUsername(username))
                .runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }
}
