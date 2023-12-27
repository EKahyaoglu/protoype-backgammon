import java.util.ArrayList;
import java.util.List;


public class BackgammonBoard
{
    private List<Integer> points;
    private int[][] board; // Creating a 2D array to represent the board

    public BackgammonBoard()
    {
        // Initialize the game board
        points = new ArrayList<>();
        board = new int[24][];

        for (int i = 0; i < 24; i++)
        {
            board[i] = new int[2];
            board[i][0] = 0; // Player
            board[i][1] = 0; // Checker Count       
        }
    }

    public boolean isLegalMove(int player, int steps)
    {
        return true;
    }

    public void move(int player, int steps)
    {
        for (int i = 0; i < 24; i++)
        {
            if (points.get(i) == player)
            {
                points.set(i,0);
                points.set(i + steps, player);
                break;
            }
        }
    }

    public int[][] getBoard()
    {
        return board;
    }

    public int getCheckerCount(int point)
    {
        return board[point][1];
    }
}

