import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//This class aims to provide users with a GUI to select restart or exit

public class OverFrame extends JFrame {

    private Game game;
    private Button exit;
    private Button restart;
    private JPanel jp;
    public JFrame f;
    public OverFrame(Game game)  {
        this.game = game;

        //Button and label initialize
        Mylistener listen = new Mylistener();

        exit = new Button("Exit Game");
        exit.addActionListener(listen);
        restart = new Button("Restart");
        restart.addActionListener(listen);

        //frame initialize
        f= new JFrame("Legends Of Valor Game");
        jp = new JPanel();
        jp.setLayout(new FlowLayout());
        BufferedImage img = null;
        try {

            img = ImageIO.read(new File("src/ConfigFiles/over.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JLabel picLabel = new JLabel(new ImageIcon(img));
        //JLabel l = new JLabel("Please select the type of game you want to play:");


        //set frame layout and location
        picLabel.setLayout(null);



        exit.setBounds(335,550,110,30);
        restart.setBounds(335,500,110,30);

        //picLabel.add(l);
        picLabel.add(exit);
        picLabel.add(restart);
        jp.add(picLabel);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(jp,BorderLayout.CENTER);
        f.setSize(860,660);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        pack();
    }
    //button handler
    private class Mylistener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if(obj == exit){
                System.exit(1);
            }else if(obj == restart){
                f.setVisible(false);
                game = new LegendsOfValor();
                game.begin();
            }

        }
    }
}
