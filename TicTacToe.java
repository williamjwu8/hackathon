/*
 * William Wu
 * 11/27/2020
 * TicTacToe.java
 * ultimate version of tic tac toe, 9x9
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.Random;

public class TicTacToe {
    private Board board;
    private Graphics g;
    private boolean restartboard;
    private int thinlinethickness = 4;
    private int thicklinethickness = 6;
    private int moves = 0; // if reach 81 or end, end game.
    private Color oColor = Color.BLUE, xColor = Color.RED;
    static final char BLANK = ' ', O = 'O', X = 'X';
    private int maxnum = 81;
    private char[][] position = {{BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK}, {BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK}, {BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK}, {BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK}, {BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK}, {BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK}, {BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK}, {BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK}, {BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK}};
    private int blanknum = 0;
    private char[][] megaboard = {{BLANK, BLANK, BLANK}, {BLANK, BLANK, BLANK}, {BLANK, BLANK, BLANK}};
    private int copywins = 0, copylosses = 0, copydraws = 0;
    private int wins = 0, losses = 0, draws = 0;
    private boolean won = false;
    // Start the game
    public static void main(String args[]) {
        new TicTacToe();
    }
    private JButton restart = new JButton("Restart");
    private JButton exit = new JButton("Exit");
    private int numfacts = 5;
    private String[] facts = {"The first print reference to a game called \"tick-tack-toe\" occurred in 1884, but was a total different game.", "In 1952, Tic-Tac-Toe was developed on an EDSAC computer in the University of Cambridge, becoming one of the first video games.", "Games played on three-in-a-row boards can be traced back to ancient Egypt.", "An early variation of tic-tac-toe was played in the Roman Empire, around the first century BC.", "In 1975, tic-tac-toe was also used by MIT students to demonstrate the computational power of Tinkertoy elements."};
    private JTextArea text = new JTextArea("hi");
    // Initialize
    public TicTacToe() {
        JFrame f = new JFrame();
        JFrame r = new JFrame();
        JPanel rulesPanel = new JPanel();
        JTextArea rules = new JTextArea("The Rules:\n\nEach turn, you mark one of the small squares.\n\n"+"When you get three in a row on a small board, you've won that board.\t\n\n"+ "To win the game, you need to win three small boards in a row.");
        f.setTitle("Ultimate Tic Tac Toe - William");
        JPanel topPanel = new JPanel();
        f.setTitle("Ultimate Tic Tac Toe - William");
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartboard = true;
            }
        });
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        topPanel.setLayout(new FlowLayout());
        f.add(topPanel);
        f.add(topPanel, BorderLayout.NORTH);
        f.add(board = new Board(), BorderLayout.CENTER);
        topPanel.add(restart, BorderLayout.EAST);
        f.add(exit, BorderLayout.EAST);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 800);
        f.setVisible(true);
        r.add(rulesPanel, BorderLayout.WEST);
        r.add(rules);
        r.setSize(500, 200);
        r.setVisible(true);
    }

    private class Board extends JPanel implements MouseListener {
        private Random random = new Random();
        private Random random1 = new Random();
        public Board() {
            addMouseListener(this);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int w = getWidth();
            int h = getHeight();
            Graphics2D g2d = (Graphics2D) g;

            g2d.setPaint(Color.WHITE);
            g2d.fill(new Rectangle2D.Double(0, 0, w, h)); // fills entire board with white
            g2d.setPaint(Color.BLACK);
            g2d.setStroke(new BasicStroke(thinlinethickness));
            // create the border lines
            for (int i = 1; i < 9; i++) {
                if (i % 3 == 0) {
                    g2d.setStroke(new BasicStroke(thicklinethickness));
                }
                g2d.draw(new Line2D.Double(w * i / 9, 0, w * i / 9, h));
                g2d.draw(new Line2D.Double(0, h * i / 9, w, h * i / 9));
                g2d.setStroke(new BasicStroke(thinlinethickness));
            }
            blanknum = 0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    double xpos = (i + 0.4 / 3.0) * w / 9.0;
                    double ypos = (j + 0.4 / 3.0) * h / 9.0;
                    double circlew = w / 8.0;
                    double circleh = h / 8.0;
                    double xr = w / 26.0;
                    double yr = h / 26.0;
                    if (position[i][j] == O) {
                        g2d.setColor(oColor);
                        g2d.draw(new Ellipse2D.Double(xpos, ypos, circlew * 2.0 / 3.0, circleh * 2.0 / 3.0));
                    } else if (position[i][j] == X) {
                        g2d.setColor(xColor);
                        g2d.draw(new Line2D.Double(xpos, ypos + yr * 2, xpos + xr * 2, ypos));
                        g2d.draw(new Line2D.Double(xpos, ypos, xpos + xr * 2, ypos + yr * 2));
                    } else {
                        blanknum++;
                    }
                }

            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    double xpos = (i + 0.4 / 1.0) * w / 3.0 - 6;
                    double ypos = (j + 0.4 / 1.0) * h / 3.0 - 6;
                    double circlew = w / 8.0;
                    double circleh = h / 8.0;
                    double xr = w / 26.0;
                    double yr = h / 26.0;
                    if (megaboard[i][j] == O) {
                        g2d.setColor(oColor);
                        g2d.draw(new Ellipse2D.Double(xpos - 60, ypos - 60, circlew * 2.0, circleh * 2.0));
                    } else if (megaboard[i][j] == X) {
                        g2d.setColor(xColor);
                        g2d.draw(new Line2D.Double(xpos - 66, ypos - yr * 3 + 21, xpos + xr * 3 + 39, ypos + 122));
                        g2d.draw(new Line2D.Double(xpos - 66, ypos + 122, xpos + xr * 3 + 39, ypos - yr * 3 + 21));
                    }
                }
            }
            if (restartboard == true) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        position[i][j] = BLANK;
                    }
                }
                for(int i = 0; i < 3; i++) {
                    for(int j = 0; j < 3; j++) {
                        megaboard[i][j] = BLANK;
                    }
                }
                g2d.setPaint(Color.WHITE);
                g2d.fill(new Rectangle2D.Double(0, 0, w, h)); // fills entire board with white
                g2d.setPaint(Color.BLACK);
                g2d.setStroke(new BasicStroke(thinlinethickness));
                // create the border lines
                for (int i = 1; i < 9; i++) {
                    if (i % 3 == 0) {
                        g2d.setStroke(new BasicStroke(thicklinethickness));
                    }
                    g2d.draw(new Line2D.Double(w * i / 9, 0, w * i / 9, h));
                    g2d.draw(new Line2D.Double(0, h * i / 9, w, h * i / 9));
                    g2d.setStroke(new BasicStroke(thinlinethickness));
                }
                restartboard = false;
                repaint();
            }
            repaint();
        }


        public void mouseClicked(MouseEvent e) {
            int xpos = e.getX() * 9 / getWidth();
            int ypos = e.getY() * 9 / getHeight();
            boolean change = true;
            if (position[xpos][ypos] != BLANK) {
                change = false;
                System.out.println("You may not place your O there. Please place it in an empty square.");
            }
            if (change == true) {
                if (xpos >= 0 && xpos < 9 && ypos >= 0 && ypos < 9 && position[xpos][ypos] == BLANK) {
                    repaint();
                    position[xpos][ypos] = O;
                    moves++;
                }
                findWinner();
                if(moves == 81) {
                    findWinner();
                }
                int x;
                do {
                    x = random.nextInt(maxnum);
                } while (!(position[x / 9][x % 9] == BLANK));
                moves++;
                position[x / 9][x % 9] = 'X';
                if(won != true) {
                    findWinner();
                }
            }

        }

        public void mousePressed(MouseEvent e) {
            // empty
        }

        public void mouseReleased(MouseEvent e) {
            // empty
        }

        public void mouseEntered(MouseEvent e) {
            // empty
        }

        public void mouseExited(MouseEvent e) {
            // empty
        }

        public void findWinner(char player, int xpos, int ypos) {

        }

        public void findWinner() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (position[i * 3][j * 3] == position[i * 3 + 1][j * 3] && position[i * 3 + 1][j * 3] == position[i * 3 + 2][j * 3] && position[i * 3][j * 3] != BLANK) {
                        if (position[i * 3][j * 3] == O) {
                            megaboard[i][j] = O;
                        } else {
                            megaboard[i][j] = X;
                        }
                    }
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (position[i * 3][j * 3 + 1] == position[i * 3 + 1][j * 3 + 1] && position[i * 3 + 1][j * 3 + 1] == position[i * 3 + 2][j * 3 + 1] && position[i * 3][j * 3 + 1] != BLANK) {
                        if (position[i * 3][j * 3 + 1] == O) {
                            megaboard[i][j] = O;
                        } else {
                            megaboard[i][j] = X;
                        }
                    }
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (position[i * 3][j * 3 + 2] == position[i * 3 + 1][j * 3 + 2] && position[i * 3 + 1][j * 3 + 2] == position[i * 3 + 2][j * 3 + 2] && position[i * 3][j * 3 + 2] != BLANK) {
                        if (position[i * 3][j * 3 + 2] == O) {
                            megaboard[i][j] = O;
                        } else {
                            megaboard[i][j] = X;
                        }
                    }
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (position[i * 3][j * 3] == position[i * 3][j * 3 + 1] && position[i * 3][j * 3 + 1] == position[i * 3][j * 3 + 2] && position[i * 3][j * 3] != BLANK) {
                        if (position[i * 3][j * 3] == O) {
                            megaboard[i][j] = O;
                        } else {
                            megaboard[i][j] = X;
                        }
                    }
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (position[i * 3 + 1][j * 3] == position[i * 3 + 1][j * 3 + 1] && position[i * 3 + 1][j * 3 + 1] == position[i * 3 + 1][j * 3 + 2] && position[i * 3 + 1][j * 3] != BLANK) {
                        if (position[i * 3 + 1][j * 3] == O) {
                            megaboard[i][j] = O;
                        } else {
                            megaboard[i][j] = X;
                        }
                    }
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (position[i * 3 + 2][j * 3] == position[i * 3 + 2][j * 3 + 1] && position[i * 3 + 2][j * 3 + 1] == position[i * 3 + 2][j * 3 + 2] && position[i * 3 + 2][j * 3] != BLANK) {
                        if (position[i * 3 + 2][j * 3] == O) {
                            megaboard[i][j] = O;
                        } else {
                            megaboard[i][j] = X;
                        }
                    }
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (position[i * 3][j * 3] == position[i * 3 + 1][j * 3 + 1] && position[i * 3 + 1][j * 3 + 1] == position[i * 3 + 2][j * 3 + 2] && position[i * 3 + 1][j * 3 + 1] != BLANK) {
                        if (position[i * 3 + 1][j * 3 + 1] == O) {
                            megaboard[i][j] = O;
                        } else {
                            megaboard[i][j] = X;
                        }
                    }
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (position[i * 3 + 2][j * 3] == position[i * 3 + 1][j * 3 + 1] && position[i * 3 + 1][j * 3 + 1] == position[i * 3][j * 3 + 2] && position[i * 3 + 1][j * 3 + 1] != BLANK) {
                        if (position[i * 3 + 1][j * 3 + 1] == O) {
                            megaboard[i][j] = O;
                        } else {
                            megaboard[i][j] = X;
                        }
                    }
                }
            }
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(megaboard[i][j] == O) {
                        for(int m = i*3; m < (i+1)*3; m++) {
                            for(int k = j*3; k < (j+1)*3; k++) {
                                if(position[m][k] == BLANK) {
                                    moves++;
                                    position[m][k] = O;
                                }
                            }
                        }
                    }
                    if(megaboard[i][j] == X) {
                        for(int m = i*3; m < (i+1)*3; m++) {
                            for(int k = j*3; k < (j+1)*3; k++) {
                                if(position[m][k] == BLANK) {
                                    moves++;
                                    position[m][k] = X;
                                }
                            }
                        }
                    }
                }
            }
            findbigwinner();
        }
        public void findbigwinner() {
            if(megaboard[0][0] == megaboard[0][1] && megaboard[0][1] == megaboard[0][2] && megaboard[0][0] != BLANK) {
                if(megaboard[0][0] == O) {
                    wins++;
                }
                else {
                    losses++;
                }
            }
            else if(megaboard[1][0] == megaboard[1][1] && megaboard[1][1] == megaboard[1][2] && megaboard[1][0] != BLANK) {
                if(megaboard[1][0] == O) {
                    wins++;
                }
                else {
                    losses++;
                }
            }
            else if(megaboard[2][0] == megaboard[2][1] && megaboard[2][1] == megaboard[2][2] && megaboard[2][0] != BLANK) {
                if(megaboard[2][0] == O) {
                    wins++;
                }
                else {
                    losses++;
                }
            }
            else if(megaboard[0][0] == megaboard[1][0] && megaboard[1][0] == megaboard[2][0] && megaboard[0][0] != BLANK) {
                if(megaboard[0][0] == O) {
                    wins++;
                }
                else {
                    losses++;
                }
            }
            else if(megaboard[0][1] == megaboard[1][1] && megaboard[1][1] == megaboard[2][1] && megaboard[0][1] != BLANK) {
                if(megaboard[0][1] == O) {
                    wins++;
                }
                else {
                    losses++;
                }
            }
            else if(megaboard[0][2] == megaboard[1][2] && megaboard[1][2] == megaboard[2][2] && megaboard[0][2] != BLANK) {
                if(megaboard[0][2] == O) {
                    wins++;
                }
                else {
                    losses++;
                }
            }
            else if(megaboard[0][0] == megaboard[1][1] && megaboard[1][1] == megaboard[2][2] && megaboard[0][0] != BLANK) {
                if(megaboard[0][0] == O) {
                    wins++;
                }
                else {
                    losses++;
                }
            }
            else if(megaboard[2][0] == megaboard[1][1] && megaboard[1][1] == megaboard[0][2] && megaboard[2][0] != BLANK) {
                if(megaboard[2][0] == O) {
                    wins++;
                }
                else {
                    losses++;
                }
            }
            if(moves == 81 && copywins == wins && copylosses == losses) {
                draws++;
            }
            if(copywins != wins || copylosses != losses || copydraws != draws) {
                if(copywins == wins && copylosses == losses && copydraws == draws) {
                    return;
                }
                int index = random1.nextInt(numfacts);
                if (JOptionPane.showConfirmDialog(null, "Fact about tic-tac-toe: " + facts[index] + "\n\nYou have "+wins+ " wins, "+losses+" losses, "+draws+" draws\n" + "Play again?", "Good game", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                won = true;
                moves = 0;
                copywins = wins;
                copylosses = losses;
                copydraws = draws;
                restart.doClick();
            }
        }
    }
}
