/*
 * AddressIO
 * Author:
 * Last Updated: Sprint03
 */
package Simulation.Address;

import Simulation.Nouns.Order;
import Simulation.Nouns.Time;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class AddressIO // might be observer too
{
    public static final String FILE = "Simulation/Address/AddressList100.txt";

    public static PriorityQueue<Address> readAddresses(String filename)
    {
        PriorityQueue<Address> priorityQueue = new PriorityQueue<>();
        try
        {
            Scanner scanner = new Scanner(new File(filename));
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String value[] = line.split(" ");
                int houseNum = Integer.parseInt(value[0]);
                int streetNum = Integer.parseInt(value[2]);
                int orderTime = Integer.parseInt(value[3]);
                priorityQueue.add(new Address(houseNum, value[1].compareTo("East") == 0, streetNum, orderTime));
            }
        }
        catch (IOException e)
        {
            System.out.println("IOException encountered: " + e);
        }
        return priorityQueue;
    }

    public static void writeAddresses(String filename, int numberAddresses)
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)));
            for (int i = 0; i < numberAddresses; i++) //Added time alongside of address creation
            {
                Address address = new Address();
                Time time = new Time();
                Order order = new Order();
                writer.write(address.writeAddress() + " ");
                writer.write(order.toString() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException encountered: " + e);
        }
    }
}
