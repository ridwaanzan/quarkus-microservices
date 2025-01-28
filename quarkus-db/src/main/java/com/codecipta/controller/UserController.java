package com.codecipta.controller;

import com.codecipta.config.response.ResponseApi;
import com.codecipta.entity.User;
import com.codecipta.service.UserService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import java.util.List;

@Path("/v1")
public class UserController {

    @Inject
    UserService userService;

    @GET
    @Path("/users")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Uni<ResponseApi> getUserData() {
        return userService.getUsers();
    }

    @GET
    @Path("/users/{username}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Uni<ResponseApi> getUserByUsername(@PathParam("username") String username) {
        return userService.getUserFromUsername(username);
    }
}
