package controllers;

import models.League;
import models.Tournament;
import models.User;
import play.data.validation.Valid;
import play.data.validation.Validation;
import play.mvc.Controller;
import services.Ownership;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Marky
 * Date: 9/25/12
 * Time: 1:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class Information extends Controller{
    public static void index() {
        if (session.contains("username")){
            User user = User.find("byUsername",session.get("username")).first();
            render("Application/dashboard.html",user);
        }
        boolean isIndex = true;
        boolean isDashboard = true;
        render(isIndex, isDashboard);
    }

    public static void login() {
        boolean isIndex = true;
        render(isIndex);
    }

    public static void processLogin(String username, String password){
        User user = User.find("byUserName",username).first();

        if(user == null){
            Validation.addError("username", "User not found");
        }

        else if (!user.getPassword().equals(password)) {
            Validation.addError("username", "Invalid Username and Password Combination");
        }

        if(validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            login();
        }
        else {
            session.put("username",user.getUsername());
            render("Application/dashboard.html",user);
        }

    }

    public static void about() {
        render("about.html");
    }

    public static void viewAllLeagues(){
        List<League> leagues = League.findAll();
        render("Leagues/all.html",leagues);
    }

    public static void createUser(){
        render();
    }

    public static void processCreateUser(@Valid User user){
        if(validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            createUser();
        }
        User newUser = new User(user);
        newUser.save();
        session.put("username",newUser.getUsername());
        render("Application/dashboard.html", user);
    }

    public static void viewLeague(String leagueName){
        League league = League.find("byName",leagueName).first();
        render("Leagues/view.html",league);
    }

    public static void viewTournament(String tournamentName){
        Tournament tournament = Tournament.find("byName",tournamentName).first();
        if (session.contains("username")){
            User user = User.find("byUsername",session.get("username")).first();
            boolean isOwner = Ownership.isTournamentOwner(user,tournament);
            render("Tournaments/view.html",tournament, isOwner);
        }
        else {
            render("Tournaments/view.html",tournament);
        }
    }


    public static void viewAllTournaments(){
        List<Tournament> tournaments = Tournament.findAll();
        render("Tournaments/all.html");
    }


}
