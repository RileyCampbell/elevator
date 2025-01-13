package elevator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ElevatorGUI {

    private Elevator elevator;
    private JLabel currentFloorLabel;
    private JTextArea outputTextArea;

    public ElevatorGUI(Elevator elevator) {
        this.elevator = elevator;
    }

    public void init() {
        JFrame frame = new JFrame("Elevator Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        currentFloorLabel = new JLabel("Current Floor: 1", JLabel.CENTER);
        panel.add(currentFloorLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();

        int totalFloors = elevator.getTotalFloors();
        int columns = Math.min(5, totalFloors);
        int rows = (int) Math.ceil((double) totalFloors / columns);

        buttonPanel.setLayout(new GridLayout(rows, columns));

        for (int i = 0; i < totalFloors; i++) {
            int floor = i + 1;
            String buttonText = (floor == 1)
                ? "Ground Floor"
                : "Floor " + floor; // Set first floor to "Ground Floor"

            JButton button = new JButton(buttonText);
            button.setPreferredSize(new Dimension(80, 40));
            button.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        elevator.addFloorToVisit(floor - 1);
                        currentFloorLabel.setText(
                            "Current Floor: " + elevator.getCurrentFloor()
                        );
                        elevator.visitFloors(ElevatorGUI.this);
                    }
                }
            );
            buttonPanel.add(button);
        }

        panel.add(buttonPanel, BorderLayout.CENTER);

        // Output Text Area for elevator actions
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        panel.add(scrollPane, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void updateFloor(int currentFloor) {
        currentFloorLabel.setText("Current Floor: " + currentFloor);
    }

    public void appendToOutput(String message) {
        outputTextArea.append(message + "\n");
        outputTextArea.setCaretPosition(
            outputTextArea.getDocument().getLength()
        );
    }
}
