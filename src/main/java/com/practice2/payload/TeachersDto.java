package com.practice2.payload;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeachersDto {

    private Long id;


    private String name;


    private String email;


    private String mobile;


}