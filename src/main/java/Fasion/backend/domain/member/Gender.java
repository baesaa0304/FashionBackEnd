package Fasion.backend.domain.member;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
    MALE, FEMALE, NONE;

    @JsonCreator // 소문자도 허용하는! female , male, none
    public static Gender from(String value) {
        return Gender.valueOf(value.toUpperCase());
    }
}
