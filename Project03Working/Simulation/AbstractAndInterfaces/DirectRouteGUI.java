/*
 * Direct Route
 * Author: Jonah Beers
 * Last Updated: Sprint03
 */
package Simulation.AbstractAndInterfaces;

import Simulation.Address.Address;
import Simulation.Address.AddressIO;
import Simulation.Enumerators.Direction;
import Simulation.Nouns.Neighborhood;
import Simulation.Nouns.Truck;

import java.awt.*;
import java.util.PriorityQueue;

public class DirectRouteGUI extends RouteGUI {

    private static final int MARKER_SIZE = 5; // size of the houses and truck in the simulation
    private static final int BLOCK_DISTANCE = 40; // size of a block on the grid
    private static final int SLEEP_TIME = 100;

    private static int x, dX; // truck's current x location and x destination
    private static int y, dY; // truck's current y location and y destination
    private Address currentAddress; // stores the address for the current destination
    private PriorityQueue<Address> addresses = AddressIO.readAddresses(AddressIO.FILE); // read in addresses

    private Color green = new Color(50, 205, 50); // color of truck when in motion
    private Color red = new Color(205, 50, 50); // color of truck when at a stop
    private Color orange = new Color(255, 128, 0); // color of the distribution center
    private Color purple = new Color(255,0,255); // color of the current destination

    public void start() throws InterruptedException
    {
        Truck truck = new Truck(new Neighborhood());
        x = truck.getXLocation(); // initial x position of the truck
        y = truck.getYLocation(); // initial y position of the truck

        int partial = 0; // stores number of moves left to reach a corner
        Direction d = Direction.Null; // sets the initial direction to NULL

        for (Address address : addresses)
        {
            dY = address.getStreetNum() * 10;
            dX = address.getHouseNum() / 10;
            currentAddress = address;

            Thread.sleep(1000);

            while (x != dX || y != dY)
            {
                if(partial != 0){ //Finish moving to corner
                    if(d == Direction.North)
                        for(;partial > 0; partial--) {
                            y--;
                            repaint();
                            Thread.sleep(SLEEP_TIME);
                        }
                    else if (d == Direction.South)
                        for(;partial > 0; partial--) {
                            y++;
                            repaint();
                            Thread.sleep(SLEEP_TIME);
                        }
                    else if (d == Direction.East)
                        for(;partial > 0; partial--) {
                            x++;
                            repaint();
                            Thread.sleep(SLEEP_TIME);
                        }
                    else if (d == Direction.West)
                        for(;partial > 0; partial--) {
                            x--;
                            repaint();
                            Thread.sleep(SLEEP_TIME);
                        }

                }

                if(d == Direction.Null){
                    d = Direction.North;
                    y--;
                    repaint();
                    Thread.sleep(SLEEP_TIME);
                } else if(d == Direction.North){ // Y is correct (1), Y is above full (2), reverse (3), y is above partially (4)
                    if(y == dY){ //On this Y level *****1*****
                        if(x < dX) //To the right
                            d = Direction.East;
                        else if( x > dX) //To the left
                            d = Direction.West;
                    } else if( y - dY >= 10){ // Above this Y level by a full block or more *****2*****
                        for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                            y--;
                            repaint();
                            Thread.sleep(SLEEP_TIME);
                        }
                    } else if( y < dY){ // Need to be facing south *****3*****
                        if(x < dX) //To the right
                            d = Direction.East;
                        else if( x > dX) //To the left
                            d = Direction.West;
                        else { //It is on the correct X level already
                            if (!truck.getNeighborhood().getGridMarker(x + 1, y).equals("  ")) { // If the East block is not out of bounds
                                d = Direction.East;
                                for (int i = 0; i < 10; i++) { // Move a full block up (10 ticks)
                                    x++;
                                    repaint();
                                    Thread.sleep(SLEEP_TIME);
                                }
                            } else { //If the east block is out of bounds, the right block is not
                                d = Direction.West;
                                for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                                    x--;
                                    repaint();
                                    Thread.sleep(SLEEP_TIME);
                                }
                            }
                        }
                    } else if (y > dY){ // above this Y level by less than a block *****4*****
                        if(x < dX) //To the right
                            d = Direction.East;
                        else if( x > dX) //To the left
                            d = Direction.West;
                        else { // Y move partially up, logs into partial
                            for(int i = 0; y != dY; i++) {
                                y--;
                                repaint();
                                Thread.sleep(SLEEP_TIME);
                                partial = 9 - i;
                            }
                        }
                    }
                } else if(d == Direction.South){ // Y is correct (1), Y is below full (2), reverse (3), y is above partially (4)
                    if(y == dY) { //On this Y level *****1*****
                        if (x < dX) //To the right
                            d = Direction.East;
                        else if (x > dX) //To the left
                            d = Direction.West;
                    } else if (y - dY <= -10) { // Below this Y level by a full block or more *****2*****
                        for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                            y++;
                            repaint();
                            Thread.sleep(SLEEP_TIME);
                        }
                    } else if (y > dY) { // Need to be facing north *****3*****
                        if(x < dX) //To the right
                            d = Direction.East;
                        else if( x > dX) //To the left
                            d = Direction.West;
                        else { //It is on the correct X level already
                            if (!truck.getNeighborhood().getGridMarker(x + 1, y).equals("  ")) { // If the East block is not out of bounds
                                d = Direction.East;
                                for (int i = 0; i < 10; i++) { // Move a full block up (10 ticks)
                                    x++;
                                    repaint();
                                    Thread.sleep(SLEEP_TIME);
                                }
                            }
                            else { //If the east block is out of bounds, the right block is not
                                d = Direction.West;
                                for (int i = 0; i < 10; i++) { // Move a full block up (10 ticks)
                                    x--;
                                    repaint();
                                    Thread.sleep(SLEEP_TIME);
                                }
                            }
                        }
                    } else { // below this Y level by less than a block *****4*****
                        if(x < dX) //To the right
                            d = Direction.East;
                        else if( x > dX) //To the left
                            d = Direction.West;
                        else { // Y move partially up, logs into partial
                            for(int i = 0; y != dY; i++) {
                                y++;
                                repaint();
                                Thread.sleep(SLEEP_TIME);
                                partial = 9 - i;
                            }
                        }
                    }
                } else if(d == Direction.East){// X is correct (1), X is below full (2), reverse (3), X is below partially (4)
                    if(x == dX) { //On this X level *****1*****
                        if (y < dY) //To the right
                            d = Direction.South;
                        else if (y > dY) //To the left
                            d = Direction.North;
                    } else if (x - dX <= -10) { // Below this X level by a full block or more *****2*****
                        for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                            x++;
                            repaint();
                            Thread.sleep(SLEEP_TIME);
                        }
                    } else if (x > dX) { // Need to be facing West *****3*****
                        if(y < dY) //To the right
                            d = Direction.South;
                        else if( y > dY) //To the left
                            d = Direction.North;
                        else { //It is on the correct X level already
                            if (!truck.getNeighborhood().getGridMarker(x, y + 1).equals("  ")) { // If the East block is not out of bounds
                                d = Direction.South;
                                for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                                    y++;
                                    repaint();
                                    Thread.sleep(SLEEP_TIME);
                                }
                            }
                            else { //If the east block is out of bounds, the right block is not
                                d = Direction.North;
                                for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                                    y--;
                                    repaint();
                                    Thread.sleep(SLEEP_TIME);
                                }
                            }
                        }
                    } else { // below this X level by less than a block *****4*****
                        if (y < dY) //To the right
                            d = Direction.South;
                        else if (y > dY) //To the left
                            d = Direction.North;
                        else { // Y move partially up, logs into partial
                            for(int i = 0; x != dX; i++) {
                                x++;
                                repaint();
                                Thread.sleep(SLEEP_TIME);
                                partial = 9 - i;
                            }
                        }
                    }
                } else if(d == Direction.West){// X is correct (1), X is above full (2), reverse (3), X is above partially (4)
                    if(x == dX) { //On this X level *****1*****
                        if (y < dY) //To the right
                            d = Direction.South;
                        else if (y > dY) //To the left
                            d = Direction.North;
                    } else if (x - dX >= 10) { // Above this X level by a full block or more *****2*****
                        for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                            x--;
                            repaint();
                            Thread.sleep(SLEEP_TIME);
                        }
                    } else if (x < dX) { // Need to be facing West *****3*****
                        if(y < dY) //To the right
                            d = Direction.South;
                        else if( y > dY) //To the left
                            d = Direction.North;
                        else { //It is on the correct X level already
                            if (!truck.getNeighborhood().getGridMarker(x, y + 1).equals("  ")) { // If the East block is not out of bounds
                                d = Direction.South;
                                for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                                    y++;
                                    repaint();
                                    Thread.sleep(SLEEP_TIME);
                                }
                            }
                            else { //If the east block is out of bounds, the right block is not
                                d = Direction.North;
                                for(int i = 0; i < 10; i++){ // Move a full block up (10 ticks)
                                    y--;
                                    repaint();
                                    Thread.sleep(SLEEP_TIME);
                                }
                            }
                        }
                    } else { // above this X level by less than a block *****4*****
                        if (y < dY) //To the right
                            d = Direction.South;
                        else if (y > dY) //To the left
                            d = Direction.North;
                        else { // Y move partially up, logs into partial
                            for(int i = 0; x != dX; i++) {
                                x--;
                                repaint();
                                Thread.sleep(SLEEP_TIME);
                                partial = 9 - i;
                            }
                        }
                    }
                }
            }
        }

    }

    public void paint(Graphics g)
    {
        // draw streets
        g.setColor(Color.DARK_GRAY);
        for (int x = 0; x < 19; x++)
            for (int y = 0; y < 19; y++)
                g.drawRect(BLOCK_DISTANCE * x, BLOCK_DISTANCE * y, BLOCK_DISTANCE, BLOCK_DISTANCE);

        // draw deliveries
        for (Address address : addresses)
        {
            g.setColor(Color.BLUE);
            double y = (address.isDirection()) ? address.getHouseNum() / 100.0 : address.getStreetNum();
            double x = (!address.isDirection()) ? address.getHouseNum() / 100.0 : address.getStreetNum();
            if(address == currentAddress) // if the address is the current destination
                g.setColor(purple);
            g.fillOval(((int) x) * BLOCK_DISTANCE - 2 + (int) (40.0 * (x % 1)), ((int) y) * BLOCK_DISTANCE - 2 + (int) (40.0 * (y % 1)), MARKER_SIZE, MARKER_SIZE);
        }

        // draw distribution center
        g.setColor(orange);
        g.fillRect(9 * BLOCK_DISTANCE - 2, 9 * BLOCK_DISTANCE + 2, MARKER_SIZE, MARKER_SIZE);

        // draw truck
        if (x == dX && y == dY) // if the truck has reached its destination
        {
            g.setColor(red);
            g.fillOval(x * 4 - 2,y * 4 - 2, MARKER_SIZE, MARKER_SIZE);
        }
        else  // if the truck is still en route
        {
            g.setColor(green);
            g.fillOval(x * 4 - 2, y * 4 - 2, MARKER_SIZE, MARKER_SIZE);
        }
    }
}
