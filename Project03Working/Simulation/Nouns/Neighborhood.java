/*
 * Neighborhood
 * Author: Anthony Estephan
 * Last Updated: Sprint03
 */
package Simulation.Nouns;

import Simulation.Address.Address;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Neighborhood implements LocationObserver
{
    private static final int NEIGHBORHOOD_DIMENSIONS = 201;
    private static final int DISTRIBUTION_CENTER_STREET = 90;
    private static final int DISTRIBUTION_CENTER_NUM = 91;

    private String[][] grid;
    private Truck truck[]; // multiple trucks can be owned

    public Neighborhood()
    {
        grid = new String[NEIGHBORHOOD_DIMENSIONS][NEIGHBORHOOD_DIMENSIONS];
    }

    public void generateNeighborhood()
    {
        // Location of houses, represented as "o"; crossroads as "-"
        for (int x = 0; x < NEIGHBORHOOD_DIMENSIONS; x++)
        {
            for (int y = 0; y < NEIGHBORHOOD_DIMENSIONS; y++)
            {
                if (x % 10 == 0)
                {
                    if (y % 10 == 0)
                        grid[x][y] = "- ";
                    else
                        grid[x][y] = "o ";
                }
                if (x % 10 != 0)
                {
                    if (y % 10 == 0)
                        grid[x][y] = "o ";
                    else
                        grid[x][y] = "  ";
                }
            }
        }

        // Location of the distribution center, represented as "&"
        grid[91][90] = "& ";
    }

    public String getGridMarker(int x,int y) {
        return grid[x][y];
    }

    public void generateNeighborhood(String filename)
    {
        generateNeighborhood();

        // Read file, create new address and add location to neighborhood map
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String address[] = line.split(" ");
                int houseNum = Integer.parseInt(address[0]);
                int streetNum = Integer.parseInt(address[1]);
                int orderTime = Integer.parseInt(address[2]);
                add(new Address(houseNum,address[1].compareTo("East") == 0, streetNum, orderTime));
            }
        }
        catch (IOException e)
        {
            System.out.println("IOException encountered: " + e);
        }

        // Location of the distribution center, represented as "&"
        grid[DISTRIBUTION_CENTER_NUM][DISTRIBUTION_CENTER_NUM] = "& ";

    }

    public void generateNeighborhood(PriorityQueue<Address> addresses)
    {
        generateNeighborhood();

        // Add locations to neighborhood map
        Iterator<Address> iterator = addresses.iterator();
        while (iterator.hasNext())
            add(iterator.next());

        // Location of the distribution center, represented as "&"
        grid[DISTRIBUTION_CENTER_NUM][DISTRIBUTION_CENTER_STREET] = "& ";

    }

    public void add(Address ad)
    {
        // Location of an address, represented as "x"
        if (!ad.isDirection())
            grid[ad.getHouseNum()/10][ad.getStreetNum()*10] = "x ";
        else
            grid[ad.getStreetNum()*10][ad.getHouseNum()/10] = "x ";
    }

    public void addTruck(Truck truck){
        this.truck[0] = truck;
    }

    public void printNeighborhood()
    {
        // Print neighborhood
        for (int x = 0; x < NEIGHBORHOOD_DIMENSIONS; x++) {
            for (int y = 0; y < NEIGHBORHOOD_DIMENSIONS; y++)
                System.out.print(grid[x][y]);
            System.out.println();
        }
    }

    public int getDistributionCenterNum(){return DISTRIBUTION_CENTER_NUM;}

    public int getDistributionCenterStreet(){return DISTRIBUTION_CENTER_STREET;}

    @Override
    public void update(int x, int y) {

    }


}
