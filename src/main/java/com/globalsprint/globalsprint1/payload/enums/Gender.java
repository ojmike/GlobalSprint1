package com.globalsprint.globalsprint1.payload.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    Male,
    Female,
    Gender;

    @JsonCreator
    public static Gender jsonDecode(final String gender){
        return valueOf(gender);
    }

    @JsonValue
    public String jsonEncode(){
        return name();
    }
}
