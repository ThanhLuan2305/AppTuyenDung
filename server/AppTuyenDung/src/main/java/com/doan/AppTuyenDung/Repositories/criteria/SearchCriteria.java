package com.doan.AppTuyenDung.Repositories.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
    private String key;// colum : cột
    private String operation; // toán tử so sánh gt lt
    private Object value; // plenty type of
}
