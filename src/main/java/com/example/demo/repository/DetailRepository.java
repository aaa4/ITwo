package com.example.demo.repository;


import com.example.demo.model.InstructorDetail;
import org.springframework.data.repository.CrudRepository;

public interface DetailRepository extends CrudRepository<InstructorDetail, Integer>{

    InstructorDetail getInstructorDetailById(Integer id);
}
