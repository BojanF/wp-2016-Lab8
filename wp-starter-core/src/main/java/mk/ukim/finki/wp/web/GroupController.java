package mk.ukim.finki.wp.web;

import mk.ukim.finki.wp.model.Group;
import mk.ukim.finki.wp.service.IGroupService;
import mk.ukim.finki.wp.service.mk.ukim.finki.wp.service.impl.GroupService;
import mk.ukim.finki.wp.service.mk.ukim.finki.wp.service.impl.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bojan on 12/1/2016.
 */
@RestController
@RequestMapping(value="/api/groups", produces="application/json")
public class GroupController {

    @Autowired
    private GroupService groupService;


    /*Group g = new Group((long)1, "G1", 20);
    groupService.save(g);*/
    /*public GroupController(GroupServiceImpl groupService) {
        this.groupService = groupService;
    }*/


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody  List<Group> findAll(){
        return groupService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    public Group findById(@PathVariable Integer id){
        return groupService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Group save(@RequestBody Group group){
        System.out.println("SAVEEEEEEEEEEEE");
        return groupService.save(group);

    }

    @RequestMapping(method = RequestMethod.PUT, value="/{id}")
    public void update(@PathVariable Integer id, @RequestBody Group group){
        groupService.update(id, group);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{id}")
    public void delete(@PathVariable Integer id){
         groupService.delete(id);
     }
}
