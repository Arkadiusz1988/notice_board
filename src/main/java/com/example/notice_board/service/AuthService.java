package com.example.notice_board.service;

import com.example.notice_board.entities.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthService {

  private User user;

  public boolean isUserLoggedIn() {
    return user != null;
  }

  public User setUser(User user) {
    this.user = user;
      return user;
  }

  public User getUser() {
    return user;
  }

  public void logout() {
    this.user = null;
  }

}
