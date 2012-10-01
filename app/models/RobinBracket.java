package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Marky
 * Date: 9/25/12
 * Time: 2:53 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table
public class RobinBracket extends Bracket{

    @ManyToMany
    private List<Group> groups;

    @Override
    public int scoreVs(Bracket bracket, ScoringSettings scoringSettings) {
        int score = 0;
        for (int j=0; j < matchResults.size(); j++){
            if (matchResults.get(j).getHasPlayed()){
                List<Result> otherResults = bracket.getMatchResults();
                score += otherResults.get(j).compareWithPrediction(matchResults.get(j));
            }
        }
        return score;
    }

    @Override
    public List<Result> getMatchResults(){
        ArrayList<Result> results = new ArrayList<Result>();

        return results;
    }
    //------ Accessors and Modifiers ------
    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
