package mk.ukim.finki.wp.service.mk.ukim.finki.wp.service.impl;

import mk.ukim.finki.wp.model.Group;
import mk.ukim.finki.wp.persistence.IGroupRepository;
import mk.ukim.finki.wp.persistence.impl.GroupRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Bojan on 12/15/2016.
 */
@Service
public class GroupService implements IGroupRepository {

    @Autowired
    public IGroupRepository groupRepository;

    public List<Group> findAll() {
        //return null;
        return groupRepository.findAll();
    }

    public Group findById(Integer id) {
        return groupRepository.findById(id);
    }

    public Group save(Group entity) {
        groupRepository.save(entity);
        return entity;
    }

    public void update(Integer id, Group entity) {
        groupRepository.update(id, entity);
    }

    public void delete(Integer id) {
        groupRepository.delete(id);
    }
}
