package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marky
 * Date: 9/25/12
 * Time: 3:48 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table

public class Tournament extends Model {


    @ManyToMany
    private List<League> forLeagues;

    private String name;

    private Brackets bracketsTemplate;

    private Brackets results;

    @OneToMany(mappedBy = "forTournament")
    private List<Prediction> predictions;

    @ManyToOne
    private User owner;

    public String toString(){
        return name;
    }

    public Brackets getBracketsTemplate() {
        return bracketsTemplate;
    }

    public void setBracketsTemplate(Brackets bracketsTemplate) {
        this.bracketsTemplate = bracketsTemplate;
    }

    public int getNumLeaguesUsedIn(){
        return forLeagues.size();
    }

    public void addLeague(League league){
        forLeagues.add(league);
    }

    public static Tournament findByName(String name){
        return Tournament.find("byName",name).first();
    }

    //------- Accessors and Modifiers ------
    public User getOwner() {
        return owner;
    }

    public List<League> getForLeagues() {
        return forLeagues;
    }

    public void setForLeagues(List<League> forLeagues) {
        this.forLeagues = forLeagues;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brackets getResults() {
        return results;
    }

    public void setResults(Brackets results) {
        this.results = results;
    }

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }
}
