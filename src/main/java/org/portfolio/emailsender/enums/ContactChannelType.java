package org.portfolio.emailsender.enums;


import lombok.Getter;

@Getter
public enum ContactChannelType {

    EMAIL("Email"),
    ;

    private String type;

    ContactChannelType(String type) {
        this.type = type;
    }

}
