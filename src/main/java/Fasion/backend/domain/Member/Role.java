package Fasion.backend.domain.Member;

import lombok.Getter;

public enum Role {
    USER("ROLE_USER" , "USER"),
    ADMIN("ROLE_ADMIN" , "ADMIN");

    @Getter
    private final String key;
    private final String name;

    Role(String key, String name){
        this.key = key;
        this.name = name;
    }
}
