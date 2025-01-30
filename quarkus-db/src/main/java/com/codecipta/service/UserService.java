package com.codecipta.service;

import com.codecipta.config.response.ResponseApi;
import com.codecipta.entity.User;
import com.codecipta.helper.GenerateId;
import com.codecipta.repository.UserRepository;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.core.eventbus.EventBus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;
    @Inject
    EventBus eventBus;

    static final Logger log = Logger.getLogger(UserService.class);

    public Uni<ResponseApi> getUsers() {
        log.info("Start getUsers service");
        String uuid = GenerateId.generateUUID();
        JsonObject json = new JsonObject();
        json.put("pId", uuid);
        json.put("pName", "getUsers");
        return Uni.createFrom().item(() -> {
            List<User> users = userRepository.findAll().list();
            log.infof("processId: " + uuid + ", List users: " + users.toString());
            eventBus.send("history", json);
            return new ResponseApi(HttpResponseStatus.OK.code(), "Success", users);
        })
        .runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }

    public Uni<ResponseApi> getUserFromUsername(String username) {
        log.info("Start getUserFromUsername service");
        String uuid = GenerateId.generateUUID();
        JsonObject json = new JsonObject();
        json.put("pId", uuid);
        json.put("pName", "getUserFromUsername");
        return Uni.createFrom().item(() -> {
            User user = userRepository.find("username", username).firstResult();
            log.info("processId: " + uuid + ", user found: " + user);
            eventBus.send("history", json);
            return new ResponseApi(HttpResponseStatus.OK.code(), "Success", user);
        })
        .runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }
}
