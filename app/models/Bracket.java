package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marky
 * Date: 9/25/12
 * Time: 4:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table
abstract public class Bracket extends Model {
    protected String name;

    @ManyToMany
    protected List<Result> matchResults;

    @ElementCollection
    protected List<String> teams;

    /**
     * The purpose is to find how close a prediction is to the actual results, based on the league's score settings
     * @param bracket the results to compare the prediction against
     * @param settings the league's scoring settings
     * @return The score representing how good a prediction was
     */
    abstract public int scoreVs(Bracket bracket, ScoringSettings settings);

    public String toString(){
        return name;
    }

    //----- Accessors and Modifiers

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTeams() {
        return teams;
    }

    public void setTeams(List<String> teams) {
        this.teams = teams;
    }
    public List<Result> getMatchResults() {
        return matchResults;
    }

    public void setMatchResults(List<Result> matchResults) {
        this.matchResults = matchResults;
    }

}
