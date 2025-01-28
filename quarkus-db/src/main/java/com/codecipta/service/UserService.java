package com.codecipta.service;

import com.codecipta.config.response.ResponseApi;
import com.codecipta.entity.User;
import com.codecipta.repository.UserRepository;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    static final Logger log = Logger.getLogger(UserService.class);

    public Uni<ResponseApi> getUsers() {
        log.info("Start getUsers service");
        return Uni.createFrom().item(() -> {
            List<User> users = userRepository.findAll().list();
            log.infof("List users: " + users.toString());
            return new ResponseApi(HttpResponseStatus.OK.code(), "Success", users);
        })
        .runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }

    public Uni<ResponseApi> getUserFromUsername(String username) {
        log.info("Start getUserFromUsername service");
        return Uni.createFrom().item(() -> {
            User user = userRepository.find("username", username).firstResult();
            log.info("user found: " + user);
            return new ResponseApi(HttpResponseStatus.OK.code(), "Success", user);
        })
        .runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }
}
