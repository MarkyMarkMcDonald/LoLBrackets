package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Marky
 * Date: 9/25/12
 * Time: 3:06 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Result extends Model {
    private String teamA;
    private int scoreA;
    private String teamB;
    private int scoreB;
    private Boolean hasPlayed = false;


    public String getWinner(){
        if (scoreA > scoreB){
            return teamA;
        }
        else if (scoreB > scoreA){
            return teamB;
        }

        return "tie";
    }

    // Give 1 point for guessing the complete score, another for the outcome
    public int compareWithPrediction(Result result){
        int score = 0;
        if (scoreA == result.getScoreA() && scoreB == result.getScoreB()){
            score++;
        }
        else if (getWinner().equals(result.getWinner())){
            score++;
        }
        return score;
    }


    //------ Accessors and Modifiers ---
    public Boolean getHasPlayed() {
        return hasPlayed;
    }

    public void setHasPlayed(Boolean hasPlayed) {
        this.hasPlayed = hasPlayed;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public int getScoreA() {
        return scoreA;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public int getScoreB() {
        return scoreB;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }
}
