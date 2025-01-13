package elevator;

import java.util.ArrayList;
import java.util.List;

public class Elevator {

    private int currentFloor;
    private int totalFloors;
    private List<Integer> floorsToVisit;
    private String direction;

    public Elevator(int totalFloors) {
        this.totalFloors = totalFloors;
        this.currentFloor = 0; // Start at ground floor (index 0)
        this.floorsToVisit = new ArrayList<>();
        this.direction = "stopped";
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public int getCurrentFloor() {
        return currentFloor + 1;
    }

    public String getDirection() {
        return direction;
    }

    public void addFloorToVisit(int floor) {
        if (
            floor >= 0 && floor < totalFloors && !floorsToVisit.contains(floor)
        ) {
            floorsToVisit.add(floor);
        }
    }

    public void visitFloors(ElevatorGUI gui) {
        while (!floorsToVisit.isEmpty()) {
            int nextFloor = floorsToVisit.remove(0);
            move(nextFloor, gui);
        }
    }

    private void move(int targetFloor, ElevatorGUI gui) {
        if (targetFloor > currentFloor) {
            direction = "up";
        } else if (targetFloor < currentFloor) {
            direction = "down";
        } else {
            direction = "stopped";
        }

        gui.appendToOutput(
            "Moving " + direction + " to floor: " + (targetFloor + 1)
        );

        while (currentFloor != targetFloor) {
            try {
                // Simulate elevator moving
                Thread.sleep(500);

                if (direction.equals("up")) {
                    currentFloor++;
                } else if (direction.equals("down")) {
                    currentFloor--;
                }

                gui.updateFloor(getCurrentFloor());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // After reaching the destination, update GUI
        gui.updateFloor(getCurrentFloor());
        gui.appendToOutput("Stopped at floor: " + (currentFloor + 1));
    }
}
