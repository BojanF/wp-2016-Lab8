package mk.ukim.finki.wp.persistence.impl;


import mk.ukim.finki.wp.model.Student;
import mk.ukim.finki.wp.persistence.IStudentRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by Bojan on 12/18/2016.
 */

@Repository
public class StudentRepository implements IStudentRepository {

    @PersistenceContext(name = "wp")
    private EntityManager entityManager1;

    @Transactional
    public Student save(Student student) {

        entityManager1.persist(student);
        entityManager1.flush();
        return student;
    }

    public List<Student> findAll() {
        CriteriaBuilder cb = entityManager1.getCriteriaBuilder();

        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> c = cq.from(Student.class);

        cq.select(c);
        TypedQuery<Student> q = entityManager1.createQuery(cq);
        return  q.getResultList();
    }

    @Transactional
    public void update(Integer id, Student student) {
        CriteriaBuilder cb = entityManager1.getCriteriaBuilder();
        // create update
        CriteriaUpdate<Student> update = cb.createCriteriaUpdate(Student.class);
        // set the root class
        Root<Student> c = update.from(Student.class);
        // set update

        update.set("name", student.getName());
        update.set("surname", student.getSurname());
        update.set("index", student.getIndex());
        //where clause
        update.where(cb.equal(c.get("id"), id));
        // perform update
        this.entityManager1.createQuery(update).executeUpdate();
    }

    @Transactional
    public void delete(Integer id) {
        CriteriaBuilder cb = entityManager1.getCriteriaBuilder();
        CriteriaDelete<Student> delete = cb.createCriteriaDelete(Student.class);

        Root<Student> c = delete.from(Student.class);
        delete.where(cb.equal(c.get("id"), id));
        this.entityManager1.createQuery(delete).executeUpdate();
    }
}
