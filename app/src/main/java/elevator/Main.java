package elevator;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(
            "Enter the number of floors (between 2 and 163):"
        );
        int numFloors = Integer.parseInt(input);

        if (numFloors < 2 || numFloors > 163) {
            JOptionPane.showMessageDialog(
                null,
                "Please enter a number between 2 and 163."
            );
            return;
        }

        Elevator elevator = new Elevator(numFloors);
        ElevatorGUI gui = new ElevatorGUI(elevator);
        gui.init();
    }
}
