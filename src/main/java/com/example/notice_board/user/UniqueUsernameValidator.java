package com.example.notice_board.user;

import com.example.notice_board.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

  @Autowired
  UserRepository userRepository;

  @Override
  public void initialize(UniqueUsername constraintAnnotation) {}

  public boolean isValid(String username, ConstraintValidatorContext context) {
    return username == null || userRepository.findUserByUsername(username) == null;
  }
}
