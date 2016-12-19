package mk.ukim.finki.wp.service.mk.ukim.finki.wp.service.impl;

import mk.ukim.finki.wp.model.Student;
import mk.ukim.finki.wp.persistence.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Bojan on 12/18/2016.
 */
@Service
public class StudentService implements IStudentRepository {

    @Autowired
    public IStudentRepository studentRepository;

    public Student save(Student student) {
        studentRepository.save(student);
        return student;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public void update(Integer id, Student student) {
        studentRepository.update(id, student);
    }

    public void delete(Integer id) {
        studentRepository.delete(id);
    }
}
