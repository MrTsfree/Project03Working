/*
 * Driver for calculating time/distance
 * Author: Anthony Estephan
 * Last Updated: Sprint03
 */
package Simulation.Drivers;

import Simulation.Address.Address;
import Simulation.Address.AddressIO;
import Simulation.AbstractAndInterfaces.RouteRightDistance;
import Simulation.AbstractAndInterfaces.RouteRightTime;
import Simulation.Nouns.Neighborhood;
import Simulation.Nouns.Truck;

import java.util.PriorityQueue;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        // Write 100 random addresses to a file
        AddressIO.writeAddresses(AddressIO.FILE, 100);

        // Read the addresses from the file and place them in a PriorityQueue
        PriorityQueue<Address> addresses = AddressIO.readAddresses(AddressIO.FILE);

        // Draw the neighborhood with the addresses and distribution center shown
        /*Neighborhood.drawNeighborhood(addresses);
        Neighborhood neighborhood = new Neighborhood();
        neighborhood.generateNeighborhood(addresses);
        neighborhood.printNeighborhood();*/
        Neighborhood neighborhood = new Neighborhood();

        Truck truck = new Truck(neighborhood);
        System.out.println(truck.route(addresses) + " " + truck.seeRoute());
        System.out.println(truck.routeTime(addresses) + " " + truck.seeRouteTime());

        truck.setRoute(new RouteRightDistance());
        truck.setRouteTime(new RouteRightTime());
        System.out.println(truck.route(addresses) + " " + truck.seeRoute());
        System.out.println(truck.routeTime(addresses) + " " + truck.seeRouteTime());

        /*
        gui.simulatea();

        while(!movementqueue.isempty){
            truck.move():
            trucvk.notify; -> observer gui.update -> delay, replace truck, repaint
        }
        */
    }
}

