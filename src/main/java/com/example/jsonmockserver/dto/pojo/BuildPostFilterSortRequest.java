package com.example.jsonmockserver.dto.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildPostFilterSortRequest {
    /**
     * <>Filtering Fields</>
     */
    private String title;
    private String author;

    /**
     * <>Sorting Fields</>
     */
    private String sortType;
    private String sortField;
}
