package com.example.notice_board.controllers;


import com.example.notice_board.entities.User;
import com.example.notice_board.exception.UserUnauthenticated;
import com.example.notice_board.repositories.NoticeRepository;
import com.example.notice_board.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    private final AuthService authService;
    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeController(AuthService authService, NoticeRepository noticeRepository) {
        this.authService = authService;
        this.noticeRepository = noticeRepository;
    }

    @PostMapping("/add")
    public User noticeAdd() {// ||
        if (!authService.isUserLoggedIn()) {
            throw new UserUnauthenticated("user not logged");
        }
        return authService.getUser();
    }




}
