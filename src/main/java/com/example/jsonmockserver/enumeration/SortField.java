package com.example.jsonmockserver.enumeration;

import com.example.jsonmockserver.common.exception.InvalidDataException;
import lombok.Getter;

@Getter
public enum SortField {
    ID("id", 0),
    VIEWS("views", 1),
    REVIEWS("reviews", 2);


    private String key;
    private Integer value;

    SortField(String key, int value) {
        this.key = key;
        this.value = value;
    }


    public static SortField getSortFieldByKey(String key) throws InvalidDataException {
        for (SortField sortField : values()) {
            if (sortField.getKey().equals(key))
                return sortField;
        }

        throw new InvalidDataException("Invalid Sort Field| Only Support views and reviews and id" + key);
    }
}
