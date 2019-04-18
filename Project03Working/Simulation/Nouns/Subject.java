package Simulation.Nouns;

import Simulation.AbstractAndInterfaces.RouteDistance;
import Simulation.AbstractAndInterfaces.RouteTime;
import Simulation.Enumerators.Direction;

import java.util.ArrayList;

abstract class Subject {

    public abstract void notifyObservers() throws InterruptedException;

    public abstract void update(int x, int y);
}
