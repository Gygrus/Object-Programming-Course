package agh.ics.oop;
import agh.ics.oop.gui.App;
import javafx.application.Application;

import static java.lang.System.out;
import java.util.ArrayList;
public class World {
    public static void main(String[] args){
        try {
//            String[] list = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
//            MoveDirection[] directions = new OptionsParser().parse(args);
//            IWorldMap map1 = new GrassField(5);
//            IWorldMap map2 = new RectangularMap(10, 10);
//            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
//            IEngine engine = new SimulationEngine(directions, map2, positions);
//            engine.run();
            Application.launch(App.class, args);
        } catch (IllegalArgumentException ex) {
            out.println(ex);
        }
    }


}
