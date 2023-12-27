// Prototype Backgammon Game
// Created by Eren Kahyaoglu, December 2023

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackgammonGame extends JFrame
{
    // Variable declaration
    private BackgammonBoard board;
    private JButton rollButton;
    private JLabel diceLabel;
    private boolean isPlayer1Turn;
    private JPanel controlPanel;
    
    public BackgammonGame()
    {
        setTitle("Backgammon Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLayout(new BorderLayout());

        controlPanel = new JPanel();

        initializeComponents();

        isPlayer1Turn = true;
        board = new BackgammonBoard();

        setVisible(true);
    }

    private void initializeComponents()
    {
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(2,12));

        for (int i = 0; i < 24; i++)
        {
            JButton boardSlot = new JButton(Integer.toString(i + 1));
            updateButton(boardSlot, i);
            boardPanel.add(boardSlot);
        }

        rollButton = new JButton("Roll Dice");
        rollButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (isPlayer1Turn)
                {
                    playerMove(1);
                } else {
                    playerMove(2);
                }
            }    
        });
        
        // Display dice results
        diceLabel = new JLabel("Dice: ");
        controlPanel.add(rollButton);
        controlPanel.add(diceLabel);

        add(boardPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    private void updateButton(JButton button, int point)
    {
        // Updating the GUI buttons based on the current state of the board
        if (board != null)
        {
            int[][] boardState = board.getBoard();
            int playerNumber = boardState[point][0];
            
            if (playerNumber == 1) {
                button.setBackground(Color.RED);
            } else if (playerNumber == 2) {
                button.setBackground(Color.BLUE);
            } else {
                button.setBackground(null);
            }
            button.setText(Integer.toString(board.getCheckerCount(point)));
        }
    }

    private void playerMove(int player)
    {
        int dice1 = (int) (Math.random() * 6) + 1;
        int dice2 = (int) (Math.random() * 6) + 1;
        int total = dice1 + dice2;

        diceLabel.setText("Dice: " + dice1 + " + " +dice2 + " = " + total);

        if (board.isLegalMove(player, total))
        {
            board.move(player, total);
            isPlayer1Turn = !isPlayer1Turn;
            updateBoard();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Move. Try Again.");
        }
    }

    private void updateBoard()
    {
        for (int i = 0; i < 24; i++)
        {
        JButton button = (JButton) ((JPanel) getContentPane().getComponent(0)).getComponent(i);
        button.setText(Integer.toString(board.getCheckerCount(i)));
        }
    }

    public static void main(String[] args)
{
    SwingUtilities.invokeLater(new Runnable()
    {
        @Override
        public void run()
        {
            new BackgammonGame();
        }
    });
}

}
