package dev.shivam.spring_boot_neo4j.services;

import dev.shivam.spring_boot_neo4j.models.User;
import dev.shivam.spring_boot_neo4j.repositories.UserRepository;
import dev.shivam.spring_boot_neo4j.requests.CreateUserRequest;
import org.neo4j.cypherdsl.core.Use;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserRequest request){
        User user = new User();

        user.setName(request.getName());
        // TODO: make sure that this username doesn't exist
        user.setUsername(request.getUsername());
        user.setRoles(request.getRoles());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        return user;
    }
}
