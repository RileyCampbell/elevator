package elevator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ElevatorGUI {

    private Elevator elevator;
    private JLabel currentFloorLabel;
    private JLabel directionLabel;

    public ElevatorGUI(Elevator elevator) {
        this.elevator = elevator;
    }

    public void init() {
        JFrame frame = new JFrame("Elevator Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Current floor label
        currentFloorLabel = new JLabel("Current Floor: 1", JLabel.CENTER);
        panel.add(currentFloorLabel, BorderLayout.NORTH);

        // Direction label
        directionLabel = new JLabel("Direction: Stopped", JLabel.CENTER);
        panel.add(directionLabel, BorderLayout.SOUTH);

        // Buttons to control the elevator
        JPanel buttonPanel = new JPanel();
        int totalFloors = elevator.getTotalFloors();
        int columns = Math.min(5, totalFloors);
        int rows = (int) Math.ceil((double) totalFloors / columns);
        buttonPanel.setLayout(new GridLayout(rows, columns));

        for (int i = 0; i < totalFloors; i++) {
            int floor = i + 1;
            String buttonText = (floor == 1)
                ? "Ground Floor"
                : "Floor " + floor;

            JButton button = new JButton(buttonText);
            button.setPreferredSize(new Dimension(80, 40));
            button.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        elevator.addFloorToVisit(floor - 1);
                        elevator.visitFloors(ElevatorGUI.this);
                    }
                }
            );
            buttonPanel.add(button);
        }

        panel.add(buttonPanel, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);
    }

    public void updateFloor(int currentFloor) {
        currentFloorLabel.setText("Current Floor: " + currentFloor);
    }

    public void updateDirection(String direction) {
        directionLabel.setText("Direction: " + direction);
    }
}
