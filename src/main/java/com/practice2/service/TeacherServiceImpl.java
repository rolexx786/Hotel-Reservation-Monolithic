package com.practice2.service;

import com.practice2.entity.Teachers;
import com.practice2.payload.TeacherResponse;
import com.practice2.payload.TeachersDto;
import com.practice2.repository.TeachersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService{
    private TeachersRepository teachersRepository;

    public TeacherServiceImpl(TeachersRepository teachersRepository) {
        this.teachersRepository = teachersRepository;
    }

    @Override
    public TeachersDto addTeachers(TeachersDto tDto) {
        Teachers teachers = mapToEntity(tDto);
        Teachers save = teachersRepository.save(teachers);
        TeachersDto teachersDto = mapToDto(teachers);
        return teachersDto;
    }

    @Override
    public void deleteTeacherbyId(long id) {
        teachersRepository.deleteById(id);

    }

    Teachers mapToEntity(TeachersDto tDto){
        Teachers t = new Teachers();
        t.setName(tDto.getName());
        t.setEmail(tDto.getEmail());
        t.setMobile(tDto.getMobile());

        return t;
}
    TeachersDto mapToDto(Teachers t){
        TeachersDto tdto = new TeachersDto();
        tdto.setId(t.getId());
        tdto.setName(t.getName());
        tdto.setEmail(t.getEmail());
        tdto.setMobile(t.getMobile());

        return tdto;
    }

    public TeachersDto updateTeacher(TeachersDto dto, long id){
        Optional<Teachers> t = teachersRepository.findById(id);
        Teachers teachers = t.get();
        teachers.setName(dto.getName());
        teachers.setEmail(dto.getEmail());
        teachers.setMobile(dto.getMobile());
        Teachers save = teachersRepository.save(teachers);


        return mapToDto(save);
    }

    public TeacherResponse getAllTeachers(Integer pageNumber, Integer pageSize, String sortBy, String sortDir){
        Sort orders = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(Sort.Direction.ASC, sortBy) : Sort.by(Sort.Direction.DESC, sortBy);

        Pageable p = PageRequest.of(pageNumber,pageSize,orders);
        Page<Teachers> all = teachersRepository.findAll(p);
        List<Teachers> content = all.getContent();
        List<TeachersDto> tdtos = content.stream().map(t -> mapToDto(t)).collect(Collectors.toList());

        TeacherResponse t = new TeacherResponse();
        t.setTdto(tdtos);
        t.setTotalPages(all.getTotalPages());
        t.setPageSize(all.getSize());
        t.setTotalElements(all.getTotalElements());
        t.setLastPage(all.isLast());
        t.setPageNumber(all.getNumber());

        return t;

    }
}
