/*
 * Truck
 * Author: Anthony Estephan
 * Last Updated: Sprint03
 */
package Simulation.Nouns;

import Simulation.Address.Address;
import Simulation.Enumerators.Direction;
import Simulation.AbstractAndInterfaces.RouteDirectDistance;
import Simulation.AbstractAndInterfaces.RouteDirectTime;
import Simulation.AbstractAndInterfaces.RouteDistance;
import Simulation.AbstractAndInterfaces.RouteTime;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Truck extends Subject { // truck sends location to observers
    private int xLocation;
    private int yLocation;
    private Direction direction;
    private Neighborhood neighborhood;
    private RouteDistance route;
    private RouteTime routeTime;
    private ArrayList<LocationObserver> observers;

    public Truck(Neighborhood neighborhood){
        this.neighborhood = neighborhood;
        xLocation = neighborhood.getDistributionCenterStreet();
        yLocation = neighborhood.getDistributionCenterNum();
        direction = Direction.Null;
        route = new RouteDirectDistance();
        routeTime = new RouteDirectTime();
        observers = new ArrayList<LocationObserver>();
        try {
            registerObservers(neighborhood);
        }
        catch (InterruptedException e ) {
            System.out.println("Sleep failed");
        }
    }

    public int route(PriorityQueue<Address> addresses){
       return this.route.route(addresses,this);
    }

    public int routeTime(PriorityQueue<Address> addresses){
        return this.routeTime.route(addresses,this);
    }

    public int getXLocation() {
        return xLocation;
    }

    public void setXLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public int getYLocation() {
        return yLocation;
    }

    public void setYLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Neighborhood getNeighborhood(){
        return neighborhood;
    }

    public void setRoute(RouteDistance route){
        this.route = route;
    }

    public void setRouteTime(RouteTime route) { this.routeTime = route;}

    public String seeRoute(){
        return this.route.toString();
    }

    public String seeRouteTime(){ return this.routeTime.toString();}

    @Override
    public void notifyObservers() throws InterruptedException {
        for (int i = 0; i < observers.size(); i++) {
            try {
                observers.get(i).update(this.getXLocation(), this.getYLocation()); }
                catch (InterruptedException e){
                    System.out.println("Sleep failed");
                }
        }

    }

    public void registerObservers(LocationObserver o) throws InterruptedException{
        observers.add(o);
        notifyObservers();
    }

    public void removeObservers(LocationObserver o) {
        observers.remove(o);
    }

    @Override
    public void update(int x, int y){
        this.xLocation = x;
        this.yLocation = y;

    }

    /*public void move(){
        Direction d = move.pop();

        switch
            up down left right;
        break;

        notifyObservers();
    }*/

}
