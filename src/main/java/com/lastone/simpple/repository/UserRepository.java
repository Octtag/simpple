package com.lastone.simpple.repository;

import com.lastone.simpple.model.SimpleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<SimpleUser, Long> {

    Optional<SimpleUser> findByEmail(String email);
}
