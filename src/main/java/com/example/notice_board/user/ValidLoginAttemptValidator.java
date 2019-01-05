package com.example.notice_board.user;

import com.example.notice_board.dto.UserDto;
import com.example.notice_board.entities.User;
import com.example.notice_board.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidLoginAttemptValidator implements ConstraintValidator<ValidLoginAttempt, UserDto> {

  @Autowired
  UserRepository userRepository;

  public void initialize(ValidLoginAttempt constraint) {}

  public boolean isValid(UserDto loginAttempt, ConstraintValidatorContext context) {
    User user = userRepository.findByEmail(loginAttempt.getEmail());
    return user != null && user.passwordMatches(loginAttempt.getPassword());
  }
}
