package fantasy.nfl.tool.myapplication.Models;

import java.io.Serializable;

/**
 * Created by Darko on 10/14/2016.
 */

public class Player implements Serializable {
    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    private String name, position, team, game;
    private double ppg, projectedPoints, percent;
    private int id, shares, price;

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public Player(String name, double projectedPoints, int price){
        this.name = name;
        this.projectedPoints = projectedPoints;
        this.price = price;
    }

    public Player(String name, int price, double ppg, String game, String team, String position){
        this.name = name;
        this.price = price;
        this.team = team;
        this.ppg = ppg;
        this.game = game;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {return name;}

    public double getPpg() {return ppg;}

    public int getPrice() {
        return price;
    }

    public double getProjectedPoints() {
        return projectedPoints;
    }

    public double getAverage() {
        return projectedPoints/price;
    }

    public String getOpponent() {
        game = game.replace(team, "");
        game = game.replace("@", "");
        String[] opponent = game.split(" ");
        return opponent[0];
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProjectedPoints(double projectedPoints) {
        this.projectedPoints = projectedPoints;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
