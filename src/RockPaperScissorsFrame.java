import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    JPanel mainPnl, btnPnl, statsPnl, showPnl;
    JButton paperBtn, rockBtn, scissorBtn, quitBtn;
    TextArea results;
    JScrollPane pane;
    JLabel playerWin, compWin, tie, gamesPlayed;

    int paperCount = 0, rockCount = 0, scissorCount = 0;
    int playerCount = 0, compCount = 0, gameTie = 0, gamesPlayedScore = 0;

    public RockPaperScissorsFrame() {

        setTitle("Rock Paper Scissors Game");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        add(mainPnl);

        createBtnPnl();
        mainPnl.add(btnPnl, BorderLayout.NORTH);

        createShowPnl();
        mainPnl.add(showPnl, BorderLayout.CENTER);

        createStatsPnl();
        mainPnl.add(statsPnl, BorderLayout.SOUTH);

        setVisible(true);
    }
    private Image fitimage(Image img , int w , int h)
    {
        BufferedImage resizedimage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedimage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0,w,h,null);
        g2.dispose();
        return resizedimage;
    }
    private void createBtnPnl() {
        btnPnl = new JPanel();
        btnPnl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnPnl.setLayout(new GridLayout(2, 4));
        paperBtn = new JButton("Paper");
        paperBtn.setIcon((new ImageIcon(new ImageIcon("src//paper.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT))));
        paperBtn.addActionListener((ActionEvent ae) ->
        {
            gamesPlayedScore++;
            results.append("You chose Paper\n");
            Game(1);
        });

        rockBtn = new JButton("Rock");
        rockBtn.setIcon((new ImageIcon(new ImageIcon("src//rock.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT))));
        rockBtn.addActionListener((ActionEvent ae) ->
        {
            gamesPlayedScore++;
            results.append("You chose Rock\n");
            Game(2);
        });

        scissorBtn = new JButton("Scissor");
        scissorBtn.setIcon((new ImageIcon(new ImageIcon("src//Scissors.jpeg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT))));
        scissorBtn.addActionListener((ActionEvent ae) ->
        {
            gamesPlayedScore++;
            results.append("You chose Scissors\n");
            Game(3);
        });


        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 15));
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));


        btnPnl.add(paperBtn);
        btnPnl.add(rockBtn);
        btnPnl.add(scissorBtn);
        btnPnl.add(quitBtn);
    }

   private void createShowPnl() {
        showPnl = new JPanel();
        results = new TextArea(15, 50);
        pane = new JScrollPane(results);
        showPnl.add(pane);

    }
    private void createStatsPnl()
    {
        statsPnl = new JPanel();
        statsPnl.setLayout(new GridLayout(2, 2));

        playerWin = new JLabel("Player Win: " + playerCount);
        compWin = new JLabel("Computer Win: " + compCount);
        tie = new JLabel("Tie: " + gameTie);
        gamesPlayed = new JLabel("Games Played: " + gamesPlayedScore);

        statsPnl.add(playerWin);
        statsPnl.add(compWin);
        statsPnl.add(tie);
        statsPnl.add(gamesPlayed);
    }
    private void Game(int playerMove) {
        Random rand = new Random();
        int compMoves = rand.nextInt(3) + 1;

        // comp move 1 = paper
        // comp move 2 = rock
        // comp move 3 = scissor
        if (compMoves == playerMove) {
            if (compMoves == 1) {
                results.append("computer chose Paper\n");
                results.append("It's a tie!\n\n");

            } else if (compMoves == 2) {
                results.append("computer chose Rock\n");
                results.append("It's a tie!\n\n");

            } else if (compMoves == 3) {
                results.append("computer chose Scissors\n");
                results.append("It's a tie!\n\n");
            }
            gameTie++;
        }
        else if (compMoves == 1 && playerMove == 2)
        {
            results.append("computer chose Paper\n");
            results.append("Paper covers Rock\n");
            results.append("computer wins\n\n");
            compCount++;
        }
        else if (compMoves == 1 && playerMove == 3)
        {
            results.append("computer chose Paper\n");
            results.append("Scissor cuts Paper\n");
            results.append("player wins\n\n");
            playerCount++;
        }
        else if (compMoves == 2 && playerMove == 1)
        {
            results.append("computer chose Rock\n");
            results.append("Paper covers Rock\n");
            results.append("player wins\n\n");
            playerCount++;
        }
        else if (compMoves == 2 && playerMove == 3)
        {
            results.append("computer chose Rock\n");
            results.append("Rock breaks Scissors\n");
            results.append("computer wins\n\n");
            compCount++;
        }
        else if (compMoves == 3 && playerMove == 1)
        {
            results.append("computer chose Scissor\n");
            results.append("Scissor cuts Paper\n");
            results.append("computer wins\n\n");
            compCount++;
        }
        else
        {
            results.append("computer chose Scissor\n");
            results.append("Rock breaks Scissors\n");
            results.append("player wins\n\n");
            playerCount++;
        }

        playerWin.setText("Player Win: " + playerCount);
        compWin.setText("Computer Win: " + compCount);
        tie.setText("Tie: " + gameTie);
        gamesPlayed.setText("Games Played: " + gamesPlayedScore);
    }

}