package models;

import play.db.jpa.Model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: marky
 * Date: 9/26/12
 * Time: 5:15 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table
public class Brackets extends Model {

    @ElementCollection
    private Map<String,Bracket> brackets;

    @ManyToOne
    private Tournament usedBy;

    public void addBracket(Bracket bracket){
        if (!brackets.containsKey(bracket.getName())){
            brackets.put(bracket.getName(),bracket);
        }
    }
    public void replaceBracket(Bracket bracket){
        brackets.put(bracket.getName(),bracket);
    }

    //---Accessors and Modifiers-------
    public Map<String, Bracket> getBrackets() {
        return brackets;
    }

    public void setBrackets(Map<String, Bracket> brackets) {
        this.brackets = brackets;
    }

    public Tournament getUsedBy() {
        return usedBy;
    }

    public void setUsedBy(Tournament usedBy) {
        this.usedBy = usedBy;
    }
}
