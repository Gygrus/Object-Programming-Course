package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected HashMap<Vector2d, Animal> animals = new HashMap<Vector2d, Animal>();
    protected MapVisualizer visualizer = new MapVisualizer(this);

//    protected boolean containsValue(final ArrayList<Animal> list, Vector2d vec){
//        return list.stream().anyMatch(o -> o.isAt(vec));
//    }

    public String toString() {return visualizer.draw(lLeftGet(), uRightGet());}

    protected abstract Vector2d lLeftGet();

    protected abstract Vector2d uRightGet();

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal movedAnimal = this.animals.remove(oldPosition);
        this.animals.put(newPosition, movedAnimal);
    }

    public boolean canMoveTo (Vector2d position) {
        return this.animals.get(position) == null;
    }

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animal.addObserver(this);
            this.animals.put(animal.getPosition(), animal);
            return true;
        } else {
            return false;
        }
    }

    public boolean isOccupied(Vector2d position) {
        return this.animals.get(position) != null;
    }

    public Object objectAt(Vector2d position) {
        return this.animals.get(position);
    }
}
