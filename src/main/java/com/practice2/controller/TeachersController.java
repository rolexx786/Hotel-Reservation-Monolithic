package com.practice2.controller;

import com.practice2.entity.Teachers;
import com.practice2.payload.TeacherResponse;
import com.practice2.payload.TeachersDto;
import com.practice2.service.TeacherServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.List;

@RestController
@RequestMapping("/api/v3/Teachers")
public class TeachersController {
    private TeacherServiceImpl teacherService;

    public TeachersController(TeacherServiceImpl teacherService) {
        this.teacherService = teacherService;
    }

    //http://localhost:8080/api/v3/Teachers

@PostMapping
    public ResponseEntity<TeachersDto> addTeachers(@RequestBody TeachersDto tDto){
        TeachersDto teachers = teacherService.addTeachers(tDto);
        return new ResponseEntity<>(teachers, HttpStatus.CREATED);


    }

    //http:localhost:8080/api/v3/Teachers?id=
    @DeleteMapping
    public ResponseEntity<String> deleteTeachers(@RequestParam long id){
        teacherService.deleteTeacherbyId(id);
        return new ResponseEntity<>("Entity deleted",HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<TeachersDto> updateTeacher(@RequestBody TeachersDto dto, @RequestParam long id){
        TeachersDto teachersDto = teacherService.updateTeacher(dto, id);
        return new ResponseEntity<>(teachersDto, HttpStatus.OK);
    }

    //http://localhost:8080/api/v3/Teachers
    @GetMapping
    public ResponseEntity<TeacherResponse> getAllTeachers(
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "3", required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc",required = false) String sortDir



    ){
        TeacherResponse allTeachers = teacherService.getAllTeachers(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<TeacherResponse>(allTeachers,HttpStatus.OK);
    }

}

