package com.practice2.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResponse {
    private List<TeachersDto> tdto;
    private Integer totalPages;
    private Integer pageNumber;
    private Long totalElements;
    private boolean lastPage ;
    private Integer pageSize;
}
