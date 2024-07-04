import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Skrzynka extends JFrame {

    private JLabel[] stateLabels = new JLabel[4];
    private JLabel rewardLabel;
    private JButton[] userButtons = new JButton[4];
    private boolean[] state = new boolean[4];
    private boolean[] solution = new boolean[4];

    private void generateRandomSolution() {
        Random random = new Random();
        for (int i = 0; i < solution.length; i++) {
            solution[i] = random.nextBoolean();
        }
    }

    public Skrzynka() {
        setTitle("Skrzynka Znajdźka");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
        setSize(500, 500);
        generateRandomSolution();
        setResizable(false);

        JPanel statePanel = new JPanel();
        statePanel.setLayout(new FlowLayout());
        for (int i = 0; i < stateLabels.length; i++) {
            stateLabels[i] = new JLabel("0", SwingConstants.CENTER);
            stateLabels[i].setPreferredSize(new Dimension(50, 50));
            stateLabels[i].setOpaque(true);
            stateLabels[i].setBackground(Color.RED);
            stateLabels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            statePanel.add(stateLabels[i]);
        }
        add(statePanel);

        JPanel rewardPanel = new JPanel();
        rewardPanel.setLayout(new FlowLayout());
        rewardLabel = new JLabel("Próbuj dalej", SwingConstants.CENTER);
        rewardLabel.setPreferredSize(new Dimension(200, 50));
        rewardLabel.setOpaque(true);
        rewardLabel.setBackground(Color.LIGHT_GRAY);
        rewardLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        rewardPanel.add(rewardLabel);
        add(rewardPanel);

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new FlowLayout());
        for (int i = 0; i < userButtons.length; i++) {
            final int index = i;
            userButtons[i] = new JButton("Zmień");
            userButtons[i].setPreferredSize(new Dimension(100, 50));
            userButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toggleState(index);
                }
            });
            userPanel.add(userButtons[i]);
        }
        add(userPanel);
        setVisible(true);
    }

    private void toggleState(int index) {
        state[index] = !state[index];
        stateLabels[index].setText(state[index] ? "1" : "0");
        stateLabels[index].setBackground(state[index] ? Color.GREEN : Color.RED);
        checkSolution();
    }

    private void checkSolution() {
        boolean solved = true;
        boolean easterEgg = true;
        for (int i = 0; i < state.length; i++) {
            if (state[i] != solution[i]) {
                solved = false;
            }
            if (!state[i]) {
                easterEgg = false;
            }
        }
        if (solved) {
            rewardLabel.setText("Brawo, wygrałeś!");
            rewardLabel.setBackground(Color.GREEN);
        } else if (easterEgg) {
            rewardLabel.setText("BOOM!");
            rewardLabel.setBackground(Color.ORANGE);
        } else {
            rewardLabel.setText("Próbuj dalej");
            rewardLabel.setBackground(Color.LIGHT_GRAY);
        }
    }

}
