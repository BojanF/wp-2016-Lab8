package mk.ukim.finki.wp.web;

import mk.ukim.finki.wp.model.Course;
import mk.ukim.finki.wp.service.mk.ukim.finki.wp.service.impl.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bojan on 12/19/2016.
 */
@RestController
@RequestMapping(value="/api/courses", produces="application/json")
public class CourseController {
    
    @Autowired
    private CourseService courseService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Course> findAll(){
        System.out.println(courseService.findAll());
        return courseService.findAll();
    }



    @RequestMapping(method = RequestMethod.POST)
    public Course save(@RequestBody Course course){
        System.out.println("SAVEEEEEEEEEEEE");
        return courseService.save(course);

    }

    @RequestMapping(method = RequestMethod.PUT, value="/{id}")
    public void update(@PathVariable Integer id, @RequestBody Course course){
        courseService.update(id, course);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{id}")
    public void delete(@PathVariable Integer id){
        courseService.delete(id);
    }
}

