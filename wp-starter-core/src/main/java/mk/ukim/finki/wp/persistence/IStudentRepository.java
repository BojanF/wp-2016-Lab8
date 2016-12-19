package mk.ukim.finki.wp.persistence;

import mk.ukim.finki.wp.model.Student;

import java.util.List;

/**
 * Created by Bojan on 12/18/2016.
 */
public interface IStudentRepository {
    Student save(Student student);
    List<Student> findAll();
    void update(Integer id, Student student);
    void delete(Integer id);
}
