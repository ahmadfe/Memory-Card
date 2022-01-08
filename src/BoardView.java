import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BoardView implements Runnable {

    static Player p1;
    static Player p2;
    static Card[] cards;
    static int counter = 0;
    static JPanel p1Panel;
    static JLabel p1Score;
    static JPanel p2Panel;
    static JLabel p2Score;

    // Funcation check how man clicks for the player
    public void check() {

        if(counter == 2) {

            counter = 0;
            int i1 = -1;
            int i2 = -1;
            for (int j = 0; j < cards.length; j++) {
                if((cards[j].getResolved() == 0) && (cards[j].getStatus())) {
                    if(i1 == -1) i1 = j;
                    else i2 = j;
                }
            }

            if(cards[i1].getImageSorce() == cards[i2].getImageSorce()) {

                if (p1.isMyTurn()) {

                    cards[i1].setResolved(1);
                    cards[i2].setResolved(1);
                    p1Score.setText("Score: " + p1.addScore());
                    // if we eant to hide the button card
                    /*
                    cards[i1].button.setVisible(false);
                    cards[i2].button.setVisible(false);
                    */


                } else {

                    cards[i1].setResolved(2);
                    cards[i2].setResolved(2);
                    p2Score.setText("Score: " + p2.addScore());
                }

            } else {

                cards[i1].hideImage();
                cards[i2].hideImage();

                if(p1.isMyTurn()) {

                    p1.setMyTurn(false);
                    p2.setMyTurn(true);
                    p1Panel.setBackground(Color.blue);
                    p2Panel.setBackground(Color.yellow);
                } else {

                    p2.setMyTurn(false);
                    p1.setMyTurn(true);
                    p2Panel.setBackground(Color.blue);
                    p1Panel.setBackground(Color.yellow);
                }
            }

        }

    }

    public int getCardIndex(JButton btn) {
        int index = 0;
        for(int i=0; i<16; i++){
            if (cards[i].button == btn) {
                index = i; 
            }
        }
        return index; 
    }

    public void run() {

        JFrame frame = new JFrame("Memory-game");

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4, 4, 10, 10));
        gridPanel.setBounds(150, 0, 530, 400); //x,y,width,heigth
        gridPanel.setBackground(Color.blue);

        p1 = new Player("Kevenn", true);
        p2 = new Player("Dinaaa", false);        

        JLabel spacer = new JLabel("  ");

        p1Panel = new JPanel();
        p1Panel.setBounds(0, 0, 150, 200);
        p1Panel.setBackground(Color.blue);
        JLabel p1Name = new JLabel(p1.getName());
        p1Score = new JLabel("Score: " + p1.getScore());
        p1Name.setFont(new Font("Verdana", 1, 20));
        p1Score.setFont(new Font("Verdana", 1, 15));
        p1Panel.setBorder(new LineBorder(Color.BLACK));
        p1Panel.add(p1Name);
        p1Panel.add(spacer);
        p1Panel.add(p1Score);
        //Creat panel for player 2 
        p2Panel = new JPanel();
        p2Panel.setBounds(0, 200, 150, 200);
        p2Panel.setBackground(Color.yellow);
        JLabel p2Name = new JLabel(p2.getName());
        p2Score = new JLabel("Score: " + p2.getScore());
        p2Name.setFont(new Font("Verdana", 1, 20));
        p2Score.setFont(new Font("Verdana", 1, 15));
        p2Panel.setBorder(new LineBorder(Color.BLACK));
        p2Panel.add(p2Name);
        p2Panel.add(spacer);
        p2Panel.add(p2Score);

        JPanel panel2 = new JPanel();
        panel2.setBounds(0, 400, 700, 60); //x,y,width,heigth
        panel2.setBackground(Color.blue);

        JButton new_game = new JButton("New");
        JButton end_game = new JButton("End");
        new_game.setBackground(Color.green);
        end_game.setBackground(Color.red);
        new_game.setForeground(Color.BLACK);
        end_game.setForeground(Color.BLACK);
        new_game.setPreferredSize(new Dimension(75, 25));
        end_game.setPreferredSize(new Dimension(75, 25));
        new_game.setBorder(new LineBorder(Color.BLACK));
        end_game.setBorder(new LineBorder(Color.BLACK));
        
        cards = new Card[16];

        //Exit game button
        ActionListener exit = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        end_game.addActionListener(exit);

        ActionListener restart = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int j = 0; j < cards.length; j++) {
                    cards[j].hideImage();
                    cards[j].setResolved(0);
                }

                p1.setMyTurn(true);
                p2.setMyTurn(false);
                p1Panel.setBackground(Color.yellow);
                p2Panel.setBackground(Color.blue);
                p1Score.setText("Score: " + p1.setScore(0));
                p2Score.setText("Score: " + p2.setScore(0));
            }
        };
        new_game.addActionListener(restart);

        String[] images = {
            "src/img/fruit01.png", "src/img/fruit02.png", "src/img/fruit03.png", "src/img/fruit04.png",
            "src/img/fruit05.png", "src/img/fruit06.png", "src/img/fruit07.png", "src/img/fruit08.png",
            "src/img/fruit01.png", "src/img/fruit02.png", "src/img/fruit03.png", "src/img/fruit04.png",
            "src/img/fruit05.png", "src/img/fruit06.png", "src/img/fruit07.png", "src/img/fruit08.png"
        };

        for (int j = 0; j < cards.length; j++) {
            cards[j] = new Card(new JButton(), images[j]);
        }

        //Changing the array into a list to shuffle. 
        List<Card> cardsList = Arrays.asList(cards);
		//Shuffle it. 
        Collections.shuffle(cardsList);
		//Changing the list into the array back again. 
        cardsList.toArray(cards);

        for (int i = 0; i < cards.length; i++) {

            cards[i].setIndex(i);

            cards[i].button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    int idx = getCardIndex((JButton) e.getSource());
                    if(!(cards[idx].getStatus())) {
                        cards[idx].showImage();
                        counter++;
                        check();
                    }
                }
            });

            gridPanel.add(cards[i].button);
        }

        panel2.add(new_game);
        panel2.add(end_game);
        frame.add(gridPanel);
        frame.add(panel2);
        frame.add(p1Panel);
        frame.add(p2Panel);
        frame.setSize(700, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}