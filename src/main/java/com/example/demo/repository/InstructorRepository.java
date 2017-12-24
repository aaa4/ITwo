package com.example.demo.repository;


import com.example.demo.model.Instructor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InstructorRepository extends CrudRepository<Instructor, Integer>{

    List<Instructor> findAll();
}
