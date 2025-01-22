package elevator;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

public class Elevator {

    private int currentFloor;
    private int totalFloors;
    private List<Integer> floorsToVisit;
    private String direction;
    private boolean isMoving;

    public Elevator(int totalFloors) {
        this.totalFloors = totalFloors;
        this.currentFloor = 0;
        this.floorsToVisit = new ArrayList<>();
        this.direction = "stopped";
        this.isMoving = false;
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

    public List<Integer> getFloorsToVisit() {
        return floorsToVisit;
    }

    public synchronized void addFloorToVisit(int floor) {
        if (
            floor >= 0 && floor < totalFloors && !floorsToVisit.contains(floor)
        ) {
            floorsToVisit.add(floor);
            System.out.println("Add floor to visit: " + (floor + 1));
        }
    }

    public void visitFloors(ElevatorGUI gui) {
        new Thread(() -> {
            while (true) {
                int nextFloor;

                synchronized (this) {
                    if (floorsToVisit.isEmpty() || isMoving) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                        continue;
                    }

                    nextFloor = floorsToVisit.remove(0);
                    isMoving = true; // Lock movement
                }

                move(nextFloor, gui);

                synchronized (this) {
                    isMoving = false; // Unlock movement
                    notifyAll();
                }
            }
        }).start();
    }

    private void move(int targetFloor, ElevatorGUI gui) {
        if (targetFloor > currentFloor) {
            direction = "up";
        } else if (targetFloor < currentFloor) {
            direction = "down";
        } else {
            direction = "stopped";
        }

        gui.updateDirection(direction);

        System.out.println(
            "Moving " + direction + " to floor: " + (targetFloor + 1)
        );

        while (currentFloor != targetFloor) {
            try {
                Thread.sleep(500);

                if (direction.equals("up")) {
                    currentFloor++;
                } else if (direction.equals("down")) {
                    currentFloor--;
                }

                gui.updateFloor(getCurrentFloor());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        stopAtFloor(gui);
    }

    private void stopAtFloor(ElevatorGUI gui) {
        direction = "stopped";

        gui.updateDirection(direction);
        gui.updateFloor(getCurrentFloor());

        System.out.println("Stopped at floor: " + getCurrentFloor());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
