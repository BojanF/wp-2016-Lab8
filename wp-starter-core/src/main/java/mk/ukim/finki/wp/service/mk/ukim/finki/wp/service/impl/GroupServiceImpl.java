package mk.ukim.finki.wp.service.mk.ukim.finki.wp.service.impl;

import mk.ukim.finki.wp.model.Group;
import mk.ukim.finki.wp.service.IGroupService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by Bojan on 12/1/2016.
 */
@Service
public class GroupServiceImpl implements IGroupService {
    public static Map<Integer,Group> idToGroup = new HashMap<Integer, Group>();;

    /*public GroupServiceImpl() {
        idToGroup.put((long)1, new Group((long)1, "G1", 20));

    }*/

    public List<Group> findAll() {
        //return null;
        /*List<Group> groups = new ArrayList<Group>();
        for(Map.Entry<Long, Group> group:idToGroup.entrySet()){
            groups.add(group.getValue());
        }
        return groups;*/
        return new ArrayList<Group>(idToGroup.values());
    }

    public Group findById(Integer id) {
        return idToGroup.get(id);
    }

    //treba da se sinhroniziraat
    public Group save(Group entity) {
        Random r = new Random();
        entity.setId(r.nextInt(Integer.MAX_VALUE));
        idToGroup.put(entity.getId(), entity);
        return entity;
    }

    public void update(Integer id, Group entity) {
        idToGroup.remove(id);
        idToGroup.put(id, entity);
    }

    public void delete(Integer id) {
        idToGroup.remove(id);
    }
}
