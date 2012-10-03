package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Marky
 * Date: 9/25/12
 * Time: 3:20 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class ElimBracket extends Bracket {

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

    public ElimBracket(String name){
        this.name = name;
    }

    //----- Accessors and Modifiers





}
