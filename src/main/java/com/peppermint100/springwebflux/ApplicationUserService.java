package com.peppermint100.springwebflux;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ApplicationUserService {

    private final ApplicationUserRepository repository;

    public ApplicationUserService(ApplicationUserRepository repository) {
        this.repository = repository;
    }

    public Mono<ApplicationUser> getUserById(Long id) {
        return repository.findById(id);
    }

    public Flux<ApplicationUser> getAllUsers() {
        return repository.findAll().delayElements(Duration.ofSeconds(2));
    }

    public void addUser(ApplicationUser user) {
        repository.save(user).subscribe();
    }

    public Mono<ApplicationUser> updateUser(ApplicationUser user) {
        return repository.findById(user.getId())
            .switchIfEmpty(Mono.error(new RuntimeException("User Not Found When Updating")))
            .map(oldUser -> {
                if (user.getUsername() != null) oldUser.setUsername(user.getUsername());
                if (user.getEmail() != null) oldUser.setEmail(user.getEmail());
                if (user.getPassport() != null) oldUser.setPassport(user.getPassport());
                return oldUser;
            })
            .flatMap(repository::save);
    }

    public Mono<Void> deleteUser(Long id) {
        return repository.deleteById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("User Not Found When Deleting")));
    }
}
