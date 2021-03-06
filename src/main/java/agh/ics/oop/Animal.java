package agh.ics.oop;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class Animal extends MapObject {
    private MapDirection orientation = MapDirection.NORTH;
    private IWorldMap map;
    private List<IPositionChangeObserver> observerList = new ArrayList<>();


    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
    }

    @Override
    public String getInputStream() {
        return switch (this.orientation) {
            case NORTH -> "src/main/resources/up.png";
            case SOUTH -> "src/main/resources/down.png";
            case EAST -> "src/main/resources/right.png";
            case WEST -> "src/main/resources/left.png";

        };
    }

    protected void addObserver(IPositionChangeObserver observer) {
        this.observerList.add(observer);
    }

    private void removeObserver(IPositionChangeObserver observer) {
        this.observerList.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observerList: this.observerList){
            observerList.positionChanged(oldPosition, newPosition);
        }
    }


    public String toString(){
        return switch (this.orientation) {
            case NORTH -> "N";
            case SOUTH -> "S";
            case EAST -> "E";
            case WEST -> "W";
        };
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        Vector2d newPositionA, newPositionB;
        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> {
            newPositionA = this.position.add(this.orientation.toUnitVector());
                if (this.map.canMoveTo(newPositionA)) {
                    this.positionChanged(this.position, newPositionA);
                    this.position = newPositionA;
                }
            }
            case BACKWARD -> {
                newPositionB = this.position.subtract(this.orientation.toUnitVector());
                if (this.map.canMoveTo(newPositionB)) {
                    this.positionChanged(this.position, newPositionB);
                    this.position = newPositionB;
                }
            }
        }
    }

}
