package com.practice2.service;

import com.practice2.entity.Teachers;
import com.practice2.payload.TeachersDto;

public interface TeacherService {
    public TeachersDto addTeachers(TeachersDto tDto);

    void deleteTeacherbyId(long id);
}
