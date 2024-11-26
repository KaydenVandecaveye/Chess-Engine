package CSCI1933P2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChessBoardFrame extends JFrame{
    public ChessBoardFrame() {
        setTitle("Chess");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(1000, 1000));
        setLayout(new BorderLayout());
    }
    // adds Chess board to the swing frame
    public void addPanel(ChessBoardPanel panel) {
        JPanel paddingPanel = new JPanel();
        paddingPanel.setLayout(new GridBagLayout());
        paddingPanel.add(panel);

        add(paddingPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        ChessBoardPanel panel = new ChessBoardPanel();
        ChessBoardFrame frame = new ChessBoardFrame();
        frame.addPanel(panel);

        JTextField textField = new JTextField("");
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText();
                String[] inputs = input.split(" ");
                int startRow = Integer.parseInt(inputs[0]);
                int startCol = Integer.parseInt(inputs[1]);
                int endRow = Integer.parseInt(inputs[2]);
                int endCol = Integer.parseInt(inputs[3]);

                panel.movePiece(startRow, startCol, endRow, endCol);
            }
        });

        frame.add(textField, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
}
