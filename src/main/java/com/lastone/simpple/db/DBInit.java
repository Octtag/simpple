package com.lastone.simpple.db;

import com.lastone.simpple.model.Role;
import com.lastone.simpple.model.SimpleUser;
import com.lastone.simpple.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@AllArgsConstructor
public class DBInit implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        String hashedPassword = passwordEncoder.encode("password");

        userRepository.save(new SimpleUser("diego@digital.com", "diego10", "316516846","526516151",hashedPassword,true, Role.ADMIN));
    }
}
