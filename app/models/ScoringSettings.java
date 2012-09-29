package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Holds the scoring criteria for comparing predictions to results. There will be one settings object per league per tournament, and held in a list in the league object.
 *
 * Created with IntelliJ IDEA.
 * User: marky
 * Date: 9/28/12
 * Time: 12:45 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class ScoringSettings extends Model {

}
