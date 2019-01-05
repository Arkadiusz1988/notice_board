package com.example.notice_board.repositories;

import com.example.notice_board.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);

  User findUserByUsername(String username);

  User findUserByEmail(String email);

  List<User> findAllById(Long id);
}
