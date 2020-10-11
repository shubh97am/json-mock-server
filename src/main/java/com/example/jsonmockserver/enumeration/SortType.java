package com.example.jsonmockserver.enumeration;

import com.example.jsonmockserver.common.exception.InvalidDataException;
import lombok.Getter;

@Getter
public enum SortType {
    ASC("asc", 0),
    DESC("desc", 1);

    private String key;
    private Integer value;

    SortType(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public static SortType getSortTypeByKey(String key) throws InvalidDataException {
        for (SortType sortType : values()) {
            if (sortType.getKey().equals(key))
                return sortType;
        }

        throw new InvalidDataException("Invalid Sort Type| Only Support asc and desc" + key);
    }
}
