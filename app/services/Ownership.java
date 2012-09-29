package services;

import models.League;
import models.Tournament;
import models.User;

/**
 * Created with IntelliJ IDEA.
 * User: marky
 * Date: 9/28/12
 * Time: 8:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class Ownership {
    public static boolean isLeagueOwner(User user, League league){
        boolean isOwner = false;
        if (league != null) {
            isOwner = league.getOwner().getUsername().equals(user.getUsername());
        }
        return isOwner;
    }

    public static boolean isTournamentOwner(User user, Tournament tournament){
        boolean isOwner = false;
        if (tournament != null) {
            isOwner = tournament.getOwner().getUsername().equals(user.getUsername());
        }
        return isOwner;
    }
}
