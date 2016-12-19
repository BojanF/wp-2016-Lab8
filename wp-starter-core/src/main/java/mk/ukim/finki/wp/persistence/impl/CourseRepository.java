package mk.ukim.finki.wp.persistence.impl;

import mk.ukim.finki.wp.model.Course;
import mk.ukim.finki.wp.persistence.ICourseRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by Bojan on 12/19/2016.
 */
@Repository
public class CourseRepository implements ICourseRepository{

    @PersistenceContext(name = "wp")
    private EntityManager entityManager;

    @Transactional
    public Course save(Course course) {
        entityManager.persist(course);
        entityManager.flush();
        return course;
    }

    public List<Course> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> c = cq.from(Course.class);

        cq.select(c);
        TypedQuery<Course> q = entityManager.createQuery(cq);
        return  q.getResultList();
    }

    @Transactional
    public void update(Integer id, Course course) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        // create update

        CriteriaUpdate<Course> update = cb.createCriteriaUpdate(Course.class);
        // set the root class
        Root<Course> c = update.from(Course.class);

        // set update
        update.set("name", course.getName());
        update.set("course", course.getCourse());

        //where clause
        update.where(cb.equal(c.get("id"), id));
        // perform update
        this.entityManager.createQuery(update).executeUpdate();
    }

    @Transactional
    public void delete(Integer id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<Course> delete = cb.createCriteriaDelete(Course.class);

        Root<Course> c = delete.from(Course.class);
        delete.where(cb.equal(c.get("id"), id));
        this.entityManager.createQuery(delete).executeUpdate();
    }
}
