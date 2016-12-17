package mk.ukim.finki.wp.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by Bojan on 12/1/2016.
 */
@Entity
@Table(name = "lab_groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;
    private Integer groupSize;
    @NotNull
    @Min(value = 0)
    private Long terms;



    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getGroupSize() {
        return groupSize;
    }

    public Long getTerms() {
        return terms;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroupSize(Integer groupSize) {
        this.groupSize = groupSize;
    }

    public void setTerms(Long terms) {
        this.terms = terms;
    }


    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", groupSize=" + groupSize +
                ", terms=" + terms +
                '}';
    }
}
