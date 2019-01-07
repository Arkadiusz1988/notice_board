package com.example.notice_board.controllers;

import com.example.notice_board.dto.UserDto;
import com.example.notice_board.entities.User;
import com.example.notice_board.enums.EnumsRoles;
import com.example.notice_board.exception.UserUnauthenticated;
import com.example.notice_board.repositories.UserRepository;
import com.example.notice_board.service.AuthService;
import com.example.notice_board.user.LoginAttemptValidationGroup;
import com.example.notice_board.user.RegistrationAttemptValidationGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {


  private final AuthService authService;
  private final UserRepository userRepository;

  public UserController(AuthService authService, UserRepository userRepository) {
    this.authService = authService;
    this.userRepository = userRepository;
  }


  @GetMapping
  public User getBasicInfo() {
    if (!authService.isUserLoggedIn()) {
      throw new UserUnauthenticated("user not logged");
    }
    return authService.getUser();
  }

//
//  @GetMapping("/login")
//  public String showLoginForm(Model model) {
//    if (authService.isUserLoggedIn()) {
//      return "redirect:/";
//    }
//    model.addAttribute("user", new UserDto());
//    return "forms/login";
//  }

  @PostMapping("/login")
  public User login(
      @Validated(LoginAttemptValidationGroup.class) @RequestBody UserDto loginAttempt) {
      return  authService.setUser(userRepository.findByEmail(loginAttempt.getEmail()));
  }

  @PostMapping("/register")
  public User register(@Validated(RegistrationAttemptValidationGroup.class) @RequestBody UserDto registrationAttempt) {
    User newUser = userRepository.save(registrationAttempt.toUser());
    authService.setUser(newUser);
    return newUser;
  }

  @PostMapping("/logout")
  public void logout() {
    authService.logout();
  }

  @DeleteMapping("/delete/{id}")
  public User deleteUser(@PathVariable Long id){
    User user = userRepository.getOne(id);
    if (!(authService.isUserLoggedIn()) || !(user.getEnumsRoles().equals(EnumsRoles.ADMIN))) {
      throw new UserUnauthenticated("user not logged or is not Admin");
    }
    userRepository.delete(user);
    return user;
  }

}
