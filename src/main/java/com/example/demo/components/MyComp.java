/*
package com.example.demo.components;


import com.example.demo.model.Instructor;
import com.example.demo.model.InstructorDetail;
import com.example.demo.repository.DetailRepository;
import com.example.demo.repository.InstructorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyComp implements CommandLineRunner{
    Logger logger = LoggerFactory.getLogger(MyComp.class);

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    DetailRepository detailRepository;

    @Override
    public void run(String... strings) throws Exception {
     */
/*   logger.info("hello");
        InstructorDetail detail = new InstructorDetail("hobby","MyChannel");
        Instructor instructor = new Instructor("Alex","Nyansus", "aNyansus@gmail.com");
        instructor.setInstructorDetail(detail);
        instructorRepository.save(instructor);
        logger.info("saved");*//*



     logger.info("Э1 поехали");
        List<Instructor> list = instructorRepository.findAll();
        for (Instructor instructor: list){
            System.out.println(instructor);
        }

    }

    private void populateDb(){
        Instructor instructor1 = new Instructor("fname","lName","email");
        Instructor instructor2 = new Instructor("fname2","lName2","email");
        Instructor instructor3 = new Instructor("fname3","lName3","email");
        Instructor instructor4 = new Instructor("fname4","lName4","email");
        Instructor instructor5 = new Instructor("fname5","lName5","email");
        instructor1.setInstructorDetail(new InstructorDetail("hobby1", "Ychannel1"));
        instructor1.setInstructorDetail(new InstructorDetail("hobby2", "Ychannel1"));
        instructor1.setInstructorDetail(new InstructorDetail("hobby3", "Ychannel1"));
        instructor1.setInstructorDetail(new InstructorDetail("hobby4", "Ychannel1"));
        instructor1.setInstructorDetail(new InstructorDetail("hobby5", "Ychannel1"));


        instructorRepository.save(instructor1);
        instructorRepository.save(instructor2);
        instructorRepository.save(instructor3);
        instructorRepository.save(instructor4);
        instructorRepository.save(instructor5);
    }
}
*/
