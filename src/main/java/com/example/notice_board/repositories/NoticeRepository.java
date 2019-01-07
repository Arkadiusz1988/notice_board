package com.example.notice_board.repositories;

import com.example.notice_board.entities.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
