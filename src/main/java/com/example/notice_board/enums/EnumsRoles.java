package com.example.notice_board.enums;

public enum EnumsRoles {

    ADMIN("admin"),
    GUEST("guest"),
    USER("user");

    private String val;

    EnumsRoles(String val) {
        this.val = val;
    }

}
