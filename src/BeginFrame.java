import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//This class aims to provide users with a GUI to start game

public class BeginFrame extends JFrame {

    private Game game;
    private Button start;
    private JPanel jp;
    public JFrame f;
    public BeginFrame(Game game)  {
        this.game = game;

        //Button and label initialize
        Mylistener listen = new Mylistener();

        start = new Button("Start Game");
        start.addActionListener(listen);

        //frame initialize
        f= new JFrame("Legends Of Valor Game");
        jp = new JPanel();
        jp.setLayout(new FlowLayout());
        BufferedImage img = null;
        try {

            img = ImageIO.read(new File("src/ConfigFiles/start.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JLabel picLabel = new JLabel(new ImageIcon(img));
        //JLabel l = new JLabel("Please select the type of game you want to play:");


        //set frame layout and location
        picLabel.setLayout(null);



        start.setBounds(400,438,110,30);


        //picLabel.add(l);
        picLabel.add(start);
        jp.add(picLabel);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(jp,BorderLayout.CENTER);
        f.setSize(965,540);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        pack();
    }
    //button handler
    private class Mylistener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if(obj == start){
                f.setVisible(false);
                game.playGame();
            }

        }
    }
}
