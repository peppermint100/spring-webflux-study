package com.peppermint100.springwebflux;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;

@RestController
@RequestMapping("/api/users")
public class ApplicationUserController {

    private final ApplicationUserService service;

    public ApplicationUserController(ApplicationUserService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ApplicationUser> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<ApplicationUser> getUserById(
            @PathVariable("id") Long id
    ) {
        return service.getUserById(id);
    }

    @PostMapping
    public void addUser(@RequestBody ApplicationUser user) {
        service.addUser(user);
    }

    @PutMapping
    public Mono<ApplicationUser> editUser(@RequestBody ApplicationUser user) {
        return service.updateUser(user);
    }
}
