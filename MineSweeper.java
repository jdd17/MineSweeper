import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.*;

class Box // Boxes on the grid
{
    int x;
    int y;
    int width;
    int height;
    int neighbors; // Tracks number of mines adjacent to each box
    Color color;
    boolean isMine; // Tracks if the box contains a mine
    boolean clicked; // Tracks if the box has been clicked
    boolean flagged; // Tracks if the box has been flagged

    public Box(int x, int y, int width, int height, Color color, int neighbors, boolean isMine, boolean clicked, boolean flagged)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.neighbors = neighbors;
        this.isMine = isMine;
        this.clicked = clicked;
        this.flagged = flagged;
    }
}
public class MineSweeper
{
    private static List<Box> boxes; // Array of boxes
    private static JPanel panel;
    private static boolean gameOver = false;
    private static boolean gameWon = false;
    private static int timeMin = 0;
    private static int timeSec = 0;
    private static int randMine = 0;
    private static int numFlags = 0; // Initially, zero flags are possible
    private static int numNotMines = 2500; // Initially, zero boxes have mines
    private static Timer timer;
    Color colorBox = new Color(194,194,194);

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            MineSweeper mineSweeper = new MineSweeper();
            mineSweeper.startGame();
        });
    }

    public void startGame()
    {
        JFrame frame = new JFrame("MineSweeper"); // MineSweeper windowo

        frame.setSize(780, 825);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boxes = new ArrayList<>();

        for (int i = 1; i <= 50; i++) // Create all of the boxes (50x50)
        {
            for (int j = 1; j <= 50; j++)
            {
                boxes.add(new Box((15 * j), (15 * i), 15, 15, colorBox, 0, false, false, false));
            }
        }

        Random rand = new Random();

        for (int i = 0; i <= 2499; i++)
        {
            randMine = rand.nextInt(100); // Assign each box some random number

            if (randMine <= 15) // Give each box a 15% chance of having a mine
            {
                boxes.get(i).isMine = true; // When needed, update the necessary flags
                numFlags++;
                numNotMines--;
            }
        }

        for (int i = 0; i <= 2499; i++) // For each box, determine how many adjacent boxes contain mines
        {
            if (i == 0) // Check three adjacent boxes for the top left box
            {
                if (boxes.get(i + 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 51).isMine)
                {
                    boxes.get(i).neighbors++;
                }
            }
            else if (i == 49) // Check three adjacent boxes for the top right box
            {
                if (boxes.get(i - 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 49).isMine)
                {
                    boxes.get(i).neighbors++;
                }
            }
            else if (i == 2450) // Check three adjacent boxes for the bottom left box
            {
                if (boxes.get(i + 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 49).isMine)
                {
                    boxes.get(i).neighbors++;
                }
            }
            else if (i == 2499) // Check three adjacent boxes for the bottom right box
            {
                if (boxes.get(i - 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 51).isMine)
                {
                    boxes.get(i).neighbors++;
                }
            }
            else if (i < 49) // Check five adjacent boxes for the top row of boxes
            {
                if (boxes.get(i - 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 49).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 51).isMine)
                {
                    boxes.get(i).neighbors++;
                }
            }
            else if ((i % 50) == 0) // Check five adjacent boxes for the leftmost column of boxes
            {
                if (boxes.get(i - 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 49).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 51).isMine)
                {
                    boxes.get(i).neighbors++;
                }
            }
            else if (((i + 1) % 50) == 0) // Check five adjacent boxes for the rightmost column of boxes
            {
                if (boxes.get(i - 51).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 49).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
            }
            else if (i > 2450) // Check five adjacent boxes for the bottom row of boxes
            {
                if (boxes.get(i - 51).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 49).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
            }
            else // Check eight adjacent boxes for all other boxes
            {
                if (boxes.get(i - 51).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 49).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 49).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 51).isMine)
                {
                    boxes.get(i).neighbors++;
                }
            }
        }

        panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                drawBoxes(g);
                drawEdges(g);
                drawFlags(g);
                drawMines(g);
                drawTimer(g);
                drawGameOver(g);
                drawMinesLeft(g);
                drawGameWon(g);
            }
        };

        frame.add(panel);

        panel.addMouseListener(new MouseAdapter() // Check for box clicks
        {
            @Override
            public void mouseClicked(MouseEvent e) // When the mouse is clicked
            {
                int clickedBox = getIndex(e.getX(), e.getY()); // Determine what box was clicked

                if (!gameOver && !gameWon) // If the game is still ongoing
                {
                    if (clickedBox != -1) // Check if the box exists
                    {
                        if (SwingUtilities.isLeftMouseButton(e)) // If it was a left mouse button
                        {
                            if (!boxes.get(clickedBox).clicked) // If the box is unclicked
                            {
                                leftClickChange(clickedBox); // Left-click the box
                            }
                        }
                        else if (SwingUtilities.isRightMouseButton(e)) // If it was a right mouse button
                        {
                            rightClickChange(clickedBox); // Right-click the box
                        }

                        panel.repaint(); // Update the panel
                    }
                }
            }
        });

        panel.setLayout(null);

        JButton restartButton = new JButton("Restart"); // Add a restart button
        restartButton.setBounds(10, 770, 100, 25);

        restartButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                restartGame();
            }
        });

        panel.add(restartButton);

        frame.setFocusable(true);
        frame.setVisible(true);

        // Have a timer run every second
        timer = new Timer(1000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) // Update the timer each second
            {
                if (!gameOver && !gameWon)
                {
                    timeSec++;

                    if (timeSec == 60)
                    {
                        timeSec = 0;
                        timeMin++;
                    }

                    // Trigger a repaint to reflect the color update
                    panel.repaint();
                }
                else // If the game is finished, stop the timer
                {
                    timer.stop();
                }
            }
        });

        timer.start();

        if (gameOver || gameWon)
        {
            timer.stop();
        }
    }

    private static void drawBoxes(Graphics g) // Logic for drawing each of the boxes
    {
        Color colorBorder = new Color(132, 132, 132); // Font colors
        Color color1 = new Color(8, 37, 244);
        Color color2 = new Color(61, 128, 41);
        Color color3 = new Color(235, 56, 42);
        Color color4 = new Color(8, 17, 126);
        Color color5 = new Color(122, 28, 22);
        Color color6 = new Color(60, 130, 132);
        Color color7 = new Color(8, 8, 8);
        Color color8 = new Color(132, 132, 132);
        Font numFont = new Font("Arial", Font.BOLD, 13);

        for (Box box : boxes)
        {
            g.setColor(box.color);
            g.fillRect(box.x, box.y, box.width, box.height);
            g.setColor(colorBorder);
            g.drawRect(box.x, box.y, box.width, box.height);

            if (box.clicked) // If the box is clicked
            {
                if (box.neighbors == 0) // And none of its adjacent boxes are mines
                {
                    if (box.isMine) // And this box is a mine
                    {
                        g.setColor(Color.RED); // Then color it red
                        g.fillRect(box.x, box.y, box.width, box.height);
                    }
                    else
                    {
                        g.setColor(box.color); // Otherwise maintain the same color
                        g.fillRect(box.x, box.y, box.width, box.height);
                    }

                    g.setColor(colorBorder); // Add the border
                    g.drawRect(box.x, box.y, box.width, box.height);
                }
                else // If some adjacent boxes are mines
                {
                    if (box.isMine) // If the box is a mine
                    {
                        g.setColor(Color.RED); // Color it red and add a border
                        g.fillRect(box.x, box.y, box.width, box.height);
                        g.setColor(colorBorder);
                        g.drawRect(box.x, box.y, box.width, box.height);
                    }
                    else // Otherwise determine its font color
                    {
                        switch (box.neighbors) // Font color depends on how many adjacent mines
                        {
                            case 1:
                                g.setColor(color1);
                                break;
                            case 2:
                                g.setColor(color2);
                                break;
                            case 3:
                                g.setColor(color3);
                                break;
                            case 4:
                                g.setColor(color4);
                                break;
                            case 5:
                                g.setColor(color5);
                                break;
                            case 6:
                                g.setColor(color6);
                                break;
                            case 7:
                                g.setColor(color7);
                                break;
                            case 8:
                                g.setColor(color8);
                                break;
                        }

                        g.setFont(numFont); // Set the font and display the number of adjacent mines
                        g.drawString(Integer.toString(box.neighbors), box.x + box.width / 2 - 3, box.y + box.height / 2 + 5);
                    }
                }
            }
        }
    }

    private void leftClickChange(int i) // Logic for left-clicking a box
    {
        if(boxes.get(i).isMine) // Game over if box is a mine
        {
            gameOver = true;
        }

        boxes.get(i).color = colorBox; // Otherwise color the box and update flags
        boxes.get(i).clicked = true;
        numNotMines--;

        if (numNotMines == 0) // If all non-mine boxes have been clicked, the game has been won
        {
            gameWon = true;
        }

        if (boxes.get(i).neighbors == 0) // If no neighbors
        {
            if ((i % 50) != 0) // If box is not on the leftmost column
            {
                if ((i - 51) >= 0) // If adjacent box exists
                {
                    if (boxes.get(i - 51).neighbors == 0) // If adjacent box also has no neighboring mines
                    {
                        if (!boxes.get(i - 51).clicked) // If adjacent box has not been clicked
                        {
                            if (!boxes.get(i - 51).isMine) // If adjacent box is not a mine
                            {
                                leftClickChange(i - 51); // Left-click that adjacent box
                            }
                        }
                    }
                    else // If adjacent box has neighboring mines
                    {
                        if (!boxes.get(i - 51).isMine) // If adjacent box is not a mine
                        {
                            if (!boxes.get(i - 51).clicked) // If adjacent box has not been clicked
                            {
                                boxes.get(i - 51).color = colorBox; // Change the color of that adjacent box
                                boxes.get(i - 51).clicked = true; // Consider that adjacent box clicked
                                numNotMines--; // Update flag
                            }
                        }
                    }
                }
            }
            if ((i - 50) >= 0) // If adjacent box exists
            {
                if (boxes.get(i - 50).neighbors == 0) // If adjacent box also has no neighboring mines
                {
                    if (!boxes.get(i - 50).clicked) // If adjacent box has not been clicked
                    {
                        if (!boxes.get(i - 50).isMine) // If adjacent box is not a mine
                        {
                            leftClickChange(i - 50); // Left-click that adjacent box
                        }
                    }
                }
                else // If adjacent box has neighboring mines
                {
                    if (!boxes.get(i - 50).isMine) // If adjacent box is not a mine
                    {
                        if (!boxes.get(i - 50).clicked) // If adjacent box has not been clicked
                        {
                            boxes.get(i - 50).color = colorBox; // Change the color of that adjacent box
                            boxes.get(i - 50).clicked = true; // Consider that adjacent box clicked
                            numNotMines--; // Update flag
                        }
                    }
                }
            }
            if (((i + 1) % 50) != 0) // If box is not on the rightmost column
            {
                if ((i - 49) >= 0) // If adjacent box exists
                {
                    if (boxes.get(i - 49).neighbors == 0) // If adjacent box also has no neighboring mines
                    {
                        if (!boxes.get(i - 49).clicked) // If adjacent box has not been clicked
                        {
                            if (!boxes.get(i - 49).isMine) // If adjacent box is not a mine
                            {
                                leftClickChange(i - 49); // Left-click that adjacent box
                            }
                        }
                    }
                    else // If adjacent box has neighboring mines
                    {
                        if (!boxes.get(i - 49).isMine) // If adjacent box is not a mine
                        {
                            if (!boxes.get(i - 49).clicked) // If adjacent box has not been clicked
                            {
                                boxes.get(i - 49).color = colorBox; // Change the color of that adjacent box
                                boxes.get(i - 49).clicked = true; // Consider that adjacent box clicked
                                numNotMines--; // Update flag
                            }
                        }
                    }
                }
            }
            if ((i % 50) != 0) // If box is not on the leftmost column
            {
                if ((i - 1) >= 0) // If adjacent box exists (always evaluates to true anyways)
                {
                    if (boxes.get(i - 1).neighbors == 0) // If adjacent box also has no neighboring mines
                    {
                        if (!boxes.get(i - 1).clicked) // If adjacent box has not been clicked
                        {
                            if (!boxes.get(i - 1).isMine) // If adjacent box is not a mine
                            {
                                leftClickChange(i - 1); // Left-click that adjacent box
                            }
                        }
                    }
                    else // If adjacent box has neighboring mines
                    {
                        if (!boxes.get(i - 1).isMine) // If adjacent box is not a mine
                        {
                            if (!boxes.get(i - 1).clicked) // If adjacent box has not been clicked
                            {
                                boxes.get(i - 1).color = colorBox; // Change the color of that adjacent box
                                boxes.get(i - 1).clicked = true; // Consider that adjacent box clicked
                                numNotMines--; // Update flag
                            }
                        }
                    }
                }
            }
            if (((i + 1) % 50) != 0) // If box is not on the rightmost column
            {
                if ((i + 1) <= 2499) // If adjacent box exists
                {
                    if (boxes.get(i + 1).neighbors == 0) // If adjacent box also has no neighboring mines
                    {
                        if (!boxes.get(i + 1).clicked) // If adjacent box has not been clicked
                        {
                            if (!boxes.get(i + 1).isMine) // If adjacent box is not a mine
                            {
                                leftClickChange(i + 1); // Left-click that adjacent box
                            }
                        }
                    }
                    else // If adjacent box has neighboring mines
                    {
                        if (!boxes.get(i + 1).isMine) // If adjacent box is not a mine
                        {
                            if (!boxes.get(i + 1).clicked) // If adjacent box has not been clicked
                            {
                                boxes.get(i + 1).color = colorBox; // Change the color of that adjacent box
                                boxes.get(i + 1).clicked = true; // Consider that adjacent box clicked
                                numNotMines--; // Update flag
                            }
                        }
                    }
                }
            }
            if ((i % 50) != 0) // If box is on not the leftmost column
            {
                if ((i + 49) <= 2499) // If adjacent box exists
                {
                    if (boxes.get(i + 49).neighbors == 0) // If adjacent box also has no neighboring mines
                    {
                        if (!boxes.get(i + 49).clicked) // If adjacent box has not been clicked
                        {
                            if (!boxes.get(i + 49).isMine) // If adjacent box is not a mine
                            {
                                leftClickChange(i + 49); // Left-click that adjacent box
                            }
                        }
                    }
                    else // If adjacent box has neighboring mines
                    {
                        if (!boxes.get(i + 49).isMine) // If adjacent box is not a mine
                        {
                            if (!boxes.get(i + 49).clicked) // If adjacent box has not been clicked
                            {
                                boxes.get(i + 49).color = colorBox; // Change the color of that adjacent box
                                boxes.get(i + 49).clicked = true; // Consider that adjacent box clicked
                                numNotMines--; // Update flag
                            }
                        }
                    }
                }
            }
            if ((i + 50) <= 2499)  // If adjacent box exists
            {
                if (boxes.get(i + 50).neighbors == 0) // If adjacent box also has no neighboring mines
                {
                    if (!boxes.get(i + 50).clicked) // If adjacent box has not been clicked
                    {
                        if (!boxes.get(i + 50).isMine) // If adjacent box is not a mine
                        {
                            leftClickChange(i + 50); // Left-click that adjacent box
                        }
                    }
                }
                else // If adjacent box has neighboring mines
                {
                    if (!boxes.get(i + 50).isMine) // If adjacent box is not a mine
                    {
                        if (!boxes.get(i + 50).clicked) // If adjacent box has not been clicked
                        {
                            boxes.get(i + 50).color = colorBox; // Change the color of that adjacent box
                            boxes.get(i + 50).clicked = true; // Consider that adjacent box clicked
                            numNotMines--; // Update flag
                        }
                    }
                }
            }
            if (((i + 1) % 50) != 0) // If box is not on the rightmost column
            {
                if ((i + 51) <= 2499) // If adjacent box exists
                {
                    if (boxes.get(i + 51).neighbors == 0) // If adjacent box also has no neighboring mines
                    {
                        if (!boxes.get(i + 51).clicked) // If adjacent box has not been clicked
                        {
                            if (!boxes.get(i + 51).isMine) // If adjacent box is not a mine
                            {
                                leftClickChange(i + 51); // Left-click that adjacent box
                            }
                        }
                    }
                    else // If adjacent box has neighboring mines
                    {
                        if (!boxes.get(i + 51).isMine) // If adjacent box is not a mine
                        {
                            if (!boxes.get(i + 51).clicked) // If adjacent box has not been clicked
                            {
                                boxes.get(i + 51).color = colorBox; // Change the color of that adjacent box
                                boxes.get(i + 51).clicked = true; // Consider that adjacent box clicked
                                numNotMines--; // Update flag
                            }
                        }
                    }
                }
            }
        }
    }

    private void rightClickChange(int i) // If a box was right-clicked
    {
        if(boxes.get(i).flagged) // is the box is flagged, remove the flag
        {
            boxes.get(i).flagged = false;
            numFlags++;
        }
        else // Otherwise flag the box
        {
            boxes.get(i).flagged = true;
            numFlags--;
        }
    }

    private int getIndex(int mouseX, int mouseY) // Function to determine which box was pressed
    {
        for (int i = 0; i < 2500; i++)
        {
            Box box = boxes.get(i);

            if (mouseX >= box.x && mouseX <= (box.x + box.width) && mouseY >= box.y && mouseY <= (box.y + box.height))
            {
                return i;
            }
        }
        return -1;
    }

    private static void drawTimer(Graphics g) // Display the timer for the game
    {
        Font numFont = new Font("Arial", Font.BOLD, 13);

        g.setFont(numFont);
        g.setColor(Color.BLACK);
        g.drawString("Time: " + timeMin + " Minutes " + timeSec + " Seconds", 345, 785);
    }

    private static void drawGameOver(Graphics g)
    {
        if(gameOver) // If the game is lost, display a message
        {
            Font numFont = new Font("Arial", Font.BOLD, 13);

            g.setFont(numFont);
            g.setColor(Color.BLACK);
            g.drawString("Game Over!", 145, 785);
        }
    }

    private static void drawMinesLeft(Graphics g) // Display the number of flags left
    {
        Font numFont = new Font("Arial", Font.BOLD, 13);

        g.setFont(numFont);
        g.setColor(Color.BLACK);
        g.drawString("Flags Left: " + numFlags, 615, 785);
    }

    private static void drawGameWon(Graphics g)
    {
        if(gameWon) // If the game is won, display a message
        {
            Font numFont = new Font("Arial", Font.BOLD, 13);

            g.setFont(numFont);
            g.setColor(Color.BLACK);
            g.drawString("CONGRATULATIONS!", 145, 785);
        }
    }

    private static void drawEdges(Graphics g) // Shading for each box (visual depth)
    {
        Color topEdge = new Color(255, 255, 255);
        Color bottomEdge = new Color(128, 128, 128);

        for (Box box : boxes)
        {
            if(!box.clicked)
            {
                g.setColor(topEdge);
                g.fillRect((box.x)+1, (box.y)+1, box.width-2, 2);
                g.fillRect((box.x)+1, (box.y+1), 2, box.height-2);
                g.setColor(bottomEdge);
                g.fillRect((box.x)+2, (box.y)+box.height-2, box.width-3, 2);
                g.fillRect((box.x)+box.width-2, (box.y)+2, 2, box.height-3);
            }
        }
    }

    private static void drawFlags(Graphics g)
    {
        Color flag = new Color(235, 56, 42); // Colors for flag graphic
        Color flagPole = new Color(8, 8, 8);

        if(gameOver || gameWon) // If the game is finished
        {
            for (Box box : boxes) // Unflag all boxes
            {
                box.flagged = false;
            }
        }

        for (Box box : boxes) // Otherwise, show visual flags for each flagged box
        {
            if (box.flagged)
            {
                g.setColor(flagPole);
                g.fillRect((box.x) + (box.width / 2) + 2, (box.y) + 3, 2, box.height - 5);
                g.setColor(flag);
                g.fillRect((box.x) + 4, (box.y) + 3, (box.width / 3), 4);
            }
        }
    }

    private static void drawMines(Graphics g)
    {
        Color sparkle = new Color(255, 255, 255); // Colors for mine graphic
        Color bomb = new Color(8, 8, 8);
        Color fuse = new Color(197, 167, 138);

        if(gameOver || gameWon) // If the game is finished
        {
            for (Box box : boxes)
            {
                if (box.isMine) // For each mine, display a mine visually
                {
                    g.setColor(bomb);
                    g.fillRect((box.x)+5, (box.y)+4, 5, 9);
                    g.fillRect((box.x)+4, (box.y)+5, 7, 7);
                    g.fillRect((box.x)+3, (box.y)+6, 9, 5);
                    g.setColor(fuse);
                    g.fillRect((box.x)+6, (box.y)+2, 3, 2);
                    g.setColor(sparkle);
                    g.fillRect((box.x)+5, (box.y)+6, 2, 2);
                }
            }
        }
    }

    private void restartGame() // Restart the game
    {
        gameOver = false; // Reset variables
        gameWon = false;
        timeMin = 0;
        timeSec = 0;
        numFlags = 0;
        numNotMines = 2500;
        timer.restart();

        for (Box box: boxes) // Reset box properties
        {
            box.isMine = false;
            box.neighbors = 0;
            box.clicked = false;
            box.flagged = false;
            box.color = colorBox;
        }

        Random rand = new Random();

        for (int i = 0; i <= 2499; i++)
        {
            randMine = rand.nextInt(100);

            if (randMine <= 15) // Give each box a chance to be a mine again
            {
                boxes.get(i).isMine = true;
                numFlags++;
                numNotMines--;
            }
        }

        for (int i = 0; i <= 2499; i++)
        {
            if (i == 0) // Count adjacent mines for top left box
            {
                if (boxes.get(i + 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 51).isMine)
                {
                    boxes.get(i).neighbors++;
                }
            }
            else if (i == 49) // Count adjacent mines for top right box
            {
                if (boxes.get(i - 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 49).isMine)
                {
                    boxes.get(i).neighbors++;
                }
            }
            else if (i == 2450) // Count adjacent mines for bottom left box
            {
                if (boxes.get(i + 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 49).isMine)
                {
                    boxes.get(i).neighbors++;
                }
            }
            else if (i == 2499) // Count adjacent mines for bottom right box
            {
                if (boxes.get(i - 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 51).isMine)
                {
                    boxes.get(i).neighbors++;
                }
            }
            else if (i < 49) // Count adjacent mines for top row of boxes
            {
                if (boxes.get(i - 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 49).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 51).isMine)
                {
                    boxes.get(i).neighbors++;
                }
            }
            else if ((i % 50) == 0) // Count adjacent mines for leftmost column of boxes
            {
                if (boxes.get(i - 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 49).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 51).isMine)
                {
                    boxes.get(i).neighbors++;
                }
            }
            else if (((i + 1) % 50) == 0) // Count adjacent mines for rightmost column of boxes
            {
                if (boxes.get(i - 51).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 49).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
            }
            else if (i > 2450) // Count adjacent mines for bottom row of boxes
            {
                if (boxes.get(i - 51).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 49).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
            }
            else // Count adjacent mines for all other boxes
            {
                if (boxes.get(i - 51).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 49).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i - 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 1).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 49).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 50).isMine)
                {
                    boxes.get(i).neighbors++;
                }
                if (boxes.get(i + 51).isMine)
                {
                    boxes.get(i).neighbors++;
                }
            }
        }

        panel.repaint(); // Update the panel
    }
}
