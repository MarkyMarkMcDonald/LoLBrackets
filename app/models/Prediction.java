package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: marky
 * Date: 9/26/12
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Prediction extends Model {

    @ManyToOne
    private Tournament forTournament;

    @ManyToOne
    private User madeBy;

    private Brackets prediction;


    /**
     * Creates an blank prediction based off of the initial tournament set up.
     *
     * Then the user can fill in the brackets with their prediction
     *
     * @param tournament the tournament to base the prediction off of
     * @param user the user making the prediction
     * @return a new prediction with empty brackets for the user to fill in
     */
    public static Prediction createFromTournamentBrackets(Tournament tournament, User user){
        Prediction prediction = new Prediction();
        prediction.setForTournament(tournament);
        prediction.setMadeBy(user);
        prediction.setPrediction(tournament.getBracketsTemplate());
        return prediction;
    }

    //-----Accessors and Modifiers---
    public Tournament getForTournament() {
        return forTournament;
    }

    public void setForTournament(Tournament forTournament) {
        this.forTournament = forTournament;
    }

    public User getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(User madeBy) {
        this.madeBy = madeBy;
    }

    public Brackets getPrediction() {
        return prediction;
    }

    public void setPrediction(Brackets prediction) {
        this.prediction = prediction;
    }
}
