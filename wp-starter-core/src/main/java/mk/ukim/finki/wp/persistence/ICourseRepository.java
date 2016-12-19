package mk.ukim.finki.wp.persistence;

import mk.ukim.finki.wp.model.Course;

import java.util.List;

/**
 * Created by Bojan on 12/19/2016.
 */
public interface ICourseRepository {
    Course save(Course course);
    List<Course> findAll();
    void update(Integer id, Course course);
    void delete(Integer id);
}
