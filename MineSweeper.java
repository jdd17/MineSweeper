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

class Box
{
    int x;
    int y;
    int width;
    int height;
    int neighbors;
    Color color;
    boolean isMine;
    boolean clicked;
    boolean flagged;

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
    private static List<Box> boxes;
    private static JPanel panel;
    private static boolean gameOver = false;
    private static boolean gameWon = false;
    private static int timeMin = 0;
    private static int timeSec = 0;
    private static int randMine = 0;
    private static int numFlags = 0;
    private static int numNotMines = 2500;
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
        JFrame frame = new JFrame("MineSweeper");

        frame.setSize(780, 825);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boxes = new ArrayList<>();

        for (int i = 1; i <= 50; i++)
        {
            for (int j = 1; j <= 50; j++)
            {
                boxes.add(new Box((15 * j), (15 * i), 15, 15, colorBox, 0, false, false, false));
            }
        }

        Random rand = new Random();

        for (int i = 0; i <= 2499; i++)
        {
            randMine = rand.nextInt(100);

            if (randMine <= 15)
            {
                boxes.get(i).isMine = true;
                numFlags++;
                numNotMines--;
            }
        }

        for (int i = 0; i <= 2499; i++)
        {
            if (i == 0)
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
            else if (i == 49)
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
            else if (i == 2450)
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
            else if (i == 2499)
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
            else if (i < 49)
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
            else if ((i % 50) == 0)
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
            else if (((i + 1) % 50) == 0)
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
            else if (i > 2450)
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
            else
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
                //drawNotMinesLeft(g);
                drawGameWon(g);
            }
        };

        frame.add(panel);

        panel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int clickedBox = getIndex(e.getX(), e.getY());

                if (!gameOver && !gameWon)
                {
                    if (clickedBox != -1)
                    {
                        if (SwingUtilities.isLeftMouseButton(e))
                        {
                            if (!boxes.get(clickedBox).clicked)
                            {
                                leftClickChange(clickedBox);
                            }
                        }
                        else if (SwingUtilities.isRightMouseButton(e))
                        {
                            rightClickChange(clickedBox);
                        }

                        panel.repaint();
                    }
                }
            }
        });

        panel.setLayout(null);

        JButton restartButton = new JButton("Restart");
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

        // Set up a Timer to repaint every second
        timer = new Timer(1000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
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
                else
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

    private static void drawBoxes(Graphics g)
    {
        Color colorBorder = new Color(132, 132, 132);
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

            if (box.clicked)
            {
                if (box.neighbors == 0)
                {
                    if (box.isMine)
                    {
                        g.setColor(Color.RED);
                        g.fillRect(box.x, box.y, box.width, box.height);
                    }
                    else
                    {
                        g.setColor(box.color);
                        g.fillRect(box.x, box.y, box.width, box.height);
                    }

                    g.setColor(colorBorder);
                    g.drawRect(box.x, box.y, box.width, box.height);
                }
                else
                {
                    if (box.isMine)
                    {
                        g.setColor(Color.RED);
                        g.fillRect(box.x, box.y, box.width, box.height);
                        g.setColor(colorBorder);
                        g.drawRect(box.x, box.y, box.width, box.height);
                    }
                    else
                    {
                        switch (box.neighbors)
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

                        g.setFont(numFont);
                        g.drawString(Integer.toString(box.neighbors), box.x + box.width / 2 - 3, box.y + box.height / 2 + 5);
                    }
                }
            }
        }
    }

    private void leftClickChange(int i)
    {
        if(boxes.get(i).isMine)
        {
            gameOver = true;
        }

        boxes.get(i).color = colorBox;
        boxes.get(i).clicked = true;
        numNotMines--;

        if (numNotMines == 0)
        {
            gameWon = true;
        }

        if (boxes.get(i).neighbors == 0)
        {
            if ((i % 50) != 0)
            {
                if ((i - 51) >= 0)
                {
                    if (boxes.get(i - 51).neighbors == 0)
                    {
                        if (!boxes.get(i - 51).clicked)
                        {
                            if (!boxes.get(i - 51).isMine)
                            {
                                leftClickChange(i - 51);
                            }
                        }
                    }
                    else
                    {
                        if (!boxes.get(i - 51).isMine)
                        {
                            if (!boxes.get(i - 51).clicked)
                            {
                                boxes.get(i - 51).color = colorBox;
                                boxes.get(i - 51).clicked = true;
                                numNotMines--;
                            }
                        }
                    }
                }
            }
            if ((i - 50) >= 0)
            {
                if (boxes.get(i - 50).neighbors == 0)
                {
                    if (!boxes.get(i - 50).clicked)
                    {
                        if (!boxes.get(i - 50).isMine)
                        {
                            leftClickChange(i - 50);
                        }
                    }
                }
                else
                {
                    if (!boxes.get(i - 50).isMine)
                    {
                        if (!boxes.get(i - 50).clicked)
                        {
                            boxes.get(i - 50).color = colorBox;
                            boxes.get(i - 50).clicked = true;
                            numNotMines--;
                        }
                    }
                }
            }
            if (((i + 1) % 50) != 0)
            {
                if ((i - 49) >= 0)
                {
                    if (boxes.get(i - 49).neighbors == 0)
                    {
                        if (!boxes.get(i - 49).clicked)
                        {
                            if (!boxes.get(i - 49).isMine)
                            {
                                leftClickChange(i - 49);
                            }
                        }
                    }
                    else
                    {
                        if (!boxes.get(i - 49).isMine)
                        {
                            if (!boxes.get(i - 49).clicked)
                            {
                                boxes.get(i - 49).color = colorBox;
                                boxes.get(i - 49).clicked = true;
                                numNotMines--;
                            }
                        }
                    }
                }
            }
            if ((i % 50) != 0)
            {
                if ((i - 1) >= 0) // always evaluates to true anyways
                {
                    if (boxes.get(i - 1).neighbors == 0)
                    {
                        if (!boxes.get(i - 1).clicked)
                        {
                            if (!boxes.get(i - 1).isMine)
                            {
                                leftClickChange(i - 1);
                            }
                        }
                    }
                    else
                    {
                        if (!boxes.get(i - 1).isMine)
                        {
                            if (!boxes.get(i - 1).clicked)
                            {
                                boxes.get(i - 1).color = colorBox;
                                boxes.get(i - 1).clicked = true;
                                numNotMines--;
                            }
                        }
                    }
                }
            }
            if (((i + 1) % 50) != 0)
            {
                if ((i + 1) <= 2499)
                {
                    if (boxes.get(i + 1).neighbors == 0)
                    {
                        if (!boxes.get(i + 1).clicked)
                        {
                            if (!boxes.get(i + 1).isMine)
                            {
                                leftClickChange(i + 1);
                            }
                        }
                    }
                    else
                    {
                        if (!boxes.get(i + 1).isMine)
                        {
                            if (!boxes.get(i + 1).clicked)
                            {
                                boxes.get(i + 1).color = colorBox;
                                boxes.get(i + 1).clicked = true;
                                numNotMines--;
                            }
                        }
                    }
                }
            }
            if ((i % 50) != 0)
            {
                if ((i + 49) <= 2499)
                {
                    if (boxes.get(i + 49).neighbors == 0)
                    {
                        if (!boxes.get(i + 49).clicked)
                        {
                            if (!boxes.get(i + 49).isMine)
                            {
                                leftClickChange(i + 49);
                            }
                        }
                    }
                    else
                    {
                        if (!boxes.get(i + 49).isMine)
                        {
                            if (!boxes.get(i + 49).clicked)
                            {
                                boxes.get(i + 49).color = colorBox;
                                boxes.get(i + 49).clicked = true;
                                numNotMines--;
                            }
                        }
                    }
                }
            }
            if ((i + 50) <= 2499)
            {
                if (boxes.get(i + 50).neighbors == 0)
                {
                    if (!boxes.get(i + 50).clicked)
                    {
                        if (!boxes.get(i + 50).isMine)
                        {
                            leftClickChange(i + 50);
                        }
                    }
                }
                else
                {
                    if (!boxes.get(i + 50).isMine)
                    {
                        if (!boxes.get(i + 50).clicked)
                        {
                            boxes.get(i + 50).color = colorBox;
                            boxes.get(i + 50).clicked = true;
                            numNotMines--;
                        }
                    }
                }
            }
            if (((i + 1) % 50) != 0)
            {
                if ((i + 51) <= 2499)
                {
                    if (boxes.get(i + 51).neighbors == 0)
                    {
                        if (!boxes.get(i + 51).clicked)
                        {
                            if (!boxes.get(i + 51).isMine)
                            {
                                leftClickChange(i + 51);
                            }
                        }
                    }
                    else
                    {
                        if (!boxes.get(i + 51).isMine)
                        {
                            if (!boxes.get(i + 51).clicked)
                            {
                                boxes.get(i + 51).color = colorBox;
                                boxes.get(i + 51).clicked = true;
                                numNotMines--;
                            }
                        }
                    }
                }
            }
        }
    }

    private void rightClickChange(int i)
    {
        if(boxes.get(i).flagged)
        {
            boxes.get(i).flagged = false;
            numFlags++;
        }
        else
        {
            boxes.get(i).flagged = true;
            numFlags--;
        }
    }

    private int getIndex(int mouseX, int mouseY)
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

    private static void drawTimer(Graphics g)
    {
        Font numFont = new Font("Arial", Font.BOLD, 13);

        g.setFont(numFont);
        g.setColor(Color.BLACK);
        g.drawString("Time: " + timeMin + " Minutes " + timeSec + " Seconds", 345, 785);
    }

    private static void drawGameOver(Graphics g)
    {
        if(gameOver)
        {
            Font numFont = new Font("Arial", Font.BOLD, 13);

            g.setFont(numFont);
            g.setColor(Color.BLACK);
            g.drawString("Game Over!", 145, 785);
        }
    }

    private static void drawMinesLeft(Graphics g)
    {
        Font numFont = new Font("Arial", Font.BOLD, 13);

        g.setFont(numFont);
        g.setColor(Color.BLACK);
        g.drawString("Flags Left: " + numFlags, 615, 785);
    }

    private static void drawGameWon(Graphics g)
    {
        if(gameWon)
        {
            Font numFont = new Font("Arial", Font.BOLD, 13);

            g.setFont(numFont);
            g.setColor(Color.BLACK);
            g.drawString("CONGRATULATIONS!", 145, 785);
        }
    }

    private static void drawEdges(Graphics g)
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
        Color flag = new Color(235, 56, 42);
        Color flagPole = new Color(8, 8, 8);

        if(gameOver || gameWon)
        {
            for (Box box : boxes)
            {
                box.flagged = false;
            }
        }

        for (Box box : boxes)
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
        Color sparkle = new Color(255, 255, 255);
        Color bomb = new Color(8, 8, 8);
        Color fuse = new Color(197, 167, 138);

        if(gameOver || gameWon)
        {
            for (Box box : boxes)
            {
                if (box.isMine)
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

    private void restartGame()
    {
        gameOver = false;
        gameWon = false;
        timeMin = 0;
        timeSec = 0;
        numFlags = 0;
        numNotMines = 2500;
        timer.restart();

        for (Box box: boxes)
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

            if (randMine <= 15)
            {
                boxes.get(i).isMine = true;
                numFlags++;
                numNotMines--;
            }
        }

        for (int i = 0; i <= 2499; i++)
        {
            if (i == 0)
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
            else if (i == 49)
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
            else if (i == 2450)
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
            else if (i == 2499)
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
            else if (i < 49)
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
            else if ((i % 50) == 0)
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
            else if (((i + 1) % 50) == 0)
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
            else if (i > 2450)
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
            else
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

        panel.repaint();
    }
}
