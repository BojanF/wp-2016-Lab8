package mk.ukim.finki.wp.web;

import mk.ukim.finki.wp.model.Student;
import mk.ukim.finki.wp.service.mk.ukim.finki.wp.service.impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bojan on 12/19/2016.
 */
@RestController
@RequestMapping(value="/api/students", produces="application/json")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Student> findAll(){
        return studentService.findAll();
    }

    

    @RequestMapping(method = RequestMethod.POST)
    public Student save(@RequestBody Student student){
        System.out.println("SAVEEEEEEEEEEEE");
        return studentService.save(student);

    }

    @RequestMapping(method = RequestMethod.PUT, value="/{id}")
    public void update(@PathVariable Integer id, @RequestBody Student student){
        studentService.update(id, student);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{id}")
    public void delete(@PathVariable Integer id){
        studentService.delete(id);
    }
}
