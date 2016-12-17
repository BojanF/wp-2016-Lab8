package mk.ukim.finki.wp.persistence.impl;

import mk.ukim.finki.wp.model.Group;
import mk.ukim.finki.wp.persistence.IGroupRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by Bojan on 12/15/2016.
 */
@Repository
public class GroupRepositoryImpl implements IGroupRepository {

    @PersistenceContext(name = "wp")
    private EntityManager entityManager;

    public List<Group> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Group> cq = cb.createQuery(Group.class);
        Root<Group> c = cq.from(Group.class);

        cq.select(c);
        TypedQuery<Group> q = entityManager.createQuery(cq);
        return  q.getResultList();
    }

    public Group findById(Integer id) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Group> cq = cb.createQuery(Group.class);
        Root<Group> c = cq.from(Group.class);
        cq.where(cb.equal(c.get("id"), id));
        TypedQuery<Group> q = entityManager.createQuery(cq);
        return q.getSingleResult();
    }

    @Transactional
    public Group save(Group entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Transactional
    public void update(Integer id, Group entity) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        // create update
        CriteriaUpdate<Group> update = cb.createCriteriaUpdate(Group.class);
        // set the root class
        Root<Group> c = update.from(Group.class);
        // set update

        update.set("name", entity.getName());
        update.set("groupSize", entity.getGroupSize());
        update.set("terms", entity.getTerms());
        //where clause
        update.where(cb.equal(c.get("id"), id));
        // perform update
        this.entityManager.createQuery(update).executeUpdate();
    }

    @Transactional
    public void delete(Integer id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<Group> delete = cb.createCriteriaDelete(Group.class);

        Root<Group> c = delete.from(Group.class);
        delete.where(cb.equal(c.get("id"), id));
        this.entityManager.createQuery(delete).executeUpdate();
    }
}
