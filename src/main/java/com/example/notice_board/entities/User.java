package com.example.notice_board.entities;

import com.example.notice_board.enums.EnumsRoles;
import com.example.notice_board.user.LoginAttemptValidationGroup;
import com.example.notice_board.user.ValidLoginAttempt;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Entity
@ValidLoginAttempt(groups = LoginAttemptValidationGroup.class)
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty
  private String username;

  @NotEmpty
  private String password;

  // domyslny regex w tej adnotacji to .*,co oznacza że validacja przepuszczałaby pusty string.
  @Email(regexp = ".+")
  private String email;

  @OneToMany(mappedBy = "user" ,cascade = {CascadeType.ALL})
  private List<Notice> notices = new ArrayList<>();

  @OneToMany(mappedBy = "user1",cascade = {CascadeType.ALL})
  private List<Comment> comments = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  private EnumsRoles enumsRoles;



  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    // w przypadku gdy hasło jest pustym stringiem/nullem, funkcja hashpw zadziała i wygeneruje nie-nullowego hasha
    this.password =
        password == null || password.isEmpty()
            ? password
            : BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public boolean passwordMatches(String plainTextPassword) {
    return BCrypt.checkpw(plainTextPassword, this.password);
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Notice> getNotices() {
    return notices;
  }

  public void setNotices(List<Notice> notices) {
    this.notices = notices;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public EnumsRoles getEnumsRoles() {
    return enumsRoles;
  }

  public void setEnumsRoles(EnumsRoles enumsRoles) {
    this.enumsRoles = enumsRoles;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("username='" + username + "'")
            .add("password='" + password + "'")
            .add("email='" + email + "'")
            .toString();
  }

}
