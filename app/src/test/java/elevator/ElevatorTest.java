package elevator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ElevatorTest {

    private Elevator elevator;
    private ElevatorGUI mockGui;

    @BeforeEach
    void setup() {
        elevator = new Elevator(10);
        mockGui = mock(ElevatorGUI.class);
    }

    @Test
    void testInitialConditions() {
        assertEquals(
            1,
            elevator.getCurrentFloor(),
            "Elevator should start at floor 1."
        );
        assertEquals(
            "stopped",
            elevator.getDirection(),
            "Elevator should initially be stopped."
        );
        assertTrue(
            elevator.getFloorsToVisit().isEmpty(),
            "Floors to visit should initially be empty."
        );
    }

    @Test
    void testAddFloorToVisit() {
        elevator.addFloorToVisit(3);
        elevator.addFloorToVisit(5);

        List<Integer> floorsToVisit = elevator.getFloorsToVisit();
        assertEquals(
            2,
            floorsToVisit.size(),
            "Floors to visit should contain two entries."
        );
        assertTrue(
            floorsToVisit.contains(3),
            "Floors to visit should include floor 3."
        );
        assertTrue(
            floorsToVisit.contains(5),
            "Floors to visit should include floor 5."
        );
    }

    @Test
    void testAddInvalidFloor() {
        elevator.addFloorToVisit(-1);
        elevator.addFloorToVisit(10);

        assertTrue(
            elevator.getFloorsToVisit().isEmpty(),
            "Invalid floors should not be added."
        );
    }

    @Test
    void testMoveUp() throws InterruptedException {
        elevator.addFloorToVisit(3);
        elevator.visitFloors(mockGui);

        Thread.sleep(2000);

        assertEquals(
            4,
            elevator.getCurrentFloor(),
            "Elevator should move to the 4th floor."
        );
        assertEquals(
            "stopped",
            elevator.getDirection(),
            "Elevator should stop after moving."
        );
        verify(mockGui, atLeastOnce()).updateDirection("up");
        verify(mockGui, atLeastOnce()).updateDirection("stopped");
        verify(mockGui, atLeastOnce()).updateFloor(4);
    }

    @Test
    void testMoveDown() throws InterruptedException {
        elevator.addFloorToVisit(5);
        elevator.visitFloors(mockGui);

        Thread.sleep(3000);

        elevator.addFloorToVisit(2);
        Thread.sleep(4000);

        assertEquals(
            3,
            elevator.getCurrentFloor(),
            "Elevator should move to the 3rd floor."
        );
        assertEquals(
            "stopped",
            elevator.getDirection(),
            "Elevator should stop after moving."
        );
        verify(mockGui, atLeastOnce()).updateDirection("down");
        verify(mockGui, atLeastOnce()).updateFloor(3);
    }

    @Test
    void testConcurrentFloorAddition() throws InterruptedException {
        elevator.addFloorToVisit(4);
        elevator.visitFloors(mockGui);

        Thread.sleep(1000);

        elevator.addFloorToVisit(7);
        Thread.sleep(5000);

        assertEquals(
            8,
            elevator.getCurrentFloor(),
            "Elevator should move to the 8th floor."
        );
        verify(mockGui, atLeastOnce()).updateFloor(8);
    }

    @Test
    void testStopAtFloor() throws InterruptedException {
        elevator.addFloorToVisit(2);
        elevator.visitFloors(mockGui);

        Thread.sleep(3000);
        assertEquals(
            3,
            elevator.getCurrentFloor(),
            "Elevator should stop at the 3rd floor."
        );
        assertEquals(
            "stopped",
            elevator.getDirection(),
            "Elevator should be stopped."
        );
        verify(mockGui, atLeastOnce()).updateDirection("stopped");
    }
}
