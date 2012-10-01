package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marky
 * Date: 9/25/12
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Group extends Model {

    @ElementCollection
    private List<String> teamsInGroup;

    private String name;

    public String toString(){
        return name;
    }

    //---Accessors and Modifiers


    public List<String> getTeamsInGroup() {
        return teamsInGroup;
    }

    public void setTeamsInGroup(List<String> teamsInGroup) {
        this.teamsInGroup = teamsInGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
