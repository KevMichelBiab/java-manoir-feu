import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

public class FenetreJeu extends JPanel implements ActionListener, KeyListener {
    private Terrain terrain;
    private int tailleCase = 72;
    private int hauteur, largeur;
    private JFrame frame;
    private Joueur player;
    private int choice;
    ArrayList<String> manoirs = new ArrayList<>(Arrays.asList("src/manoir1.txt", "src/manoir2.txt", "src/manoir3.txt"));

    JButton manoir1 = new JButton("niveau 1");
    JButton manoir2 = new JButton("niveau 2");
    JButton manoir3 = new JButton("niveau 3");

    public FenetreJeu() {
        this.hauteur = 0;
        this.largeur = 0;
        this.player = null;
        this.terrain = null;
        this.choice = -1;

        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(9 * tailleCase, 9 * tailleCase));

        JFrame frame = new JFrame("Furfeux");
        this.frame = frame;


        manoir1.setBounds((int)(9*tailleCase*0.07), (int)(9*tailleCase*0.25), 150, 100);
        manoir2.setBounds((int)(9*tailleCase*0.37), (int)(9*tailleCase*0.25), 150, 100);
        manoir3.setBounds((int)(9*tailleCase*0.67), (int)(9*tailleCase*0.25), 150, 100);

        manoir1.setFocusable(false);
        manoir2.setFocusable(false);
        manoir3.setFocusable(false);
        //actionListeners
        manoir1.addActionListener(this);
        manoir2.addActionListener(this);
        manoir3.addActionListener(this);


        this.frame.add(manoir1);
        this.frame.add(manoir2);
        this.frame.add(manoir3);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.addKeyListener(this);
        frame.setVisible(true);



        while (this.choice == -1) {
            frame.setVisible(true);
            //dance with the stars, feel the rhythmic tingle in your limbs and relish as your lungs burn one last time
            //before numbness consumes, and you lose your sense of self
            //relish in the pain, as it is proportional to your brightness
            //quench your thirst with a decade worth of memories
            //do wonder whether you're to join them or not
            //do wonder if you'd rather belong with the husks down, down, below

        }

    }

    public void setTerrain(Terrain t) {
        this.terrain = t;
        this.hauteur = t.getHauteur();
        this.largeur = t.getLargeur();
        this.player = t.getJoueur();
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public int getChoice() {
        return choice;
    }

    public ArrayList<String> getManoirs() {
        return manoirs;
    }

    //button properties and actions
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == manoir1){
            this.frame.remove(manoir1);
            this.frame.remove(manoir2);
            this.frame.remove(manoir3);
            frame.repaint();
            this.choice = 0;
        }
        if (e.getSource() == manoir2){
            this.frame.remove(manoir1);
            this.frame.remove(manoir2);
            this.frame.remove(manoir3);
            frame.repaint();
            this.choice = 1;
        }
        if (e.getSource() == manoir3){
            this.frame.remove(manoir1);
            this.frame.remove(manoir2);
            this.frame.remove(manoir3);
            frame.repaint();
            this.choice = 2;;
        }
    }




    //

//Can you explain to me the TypeCaseColour method especially the part with hall

    /*this method is just to make my life easier and instead of having it directly
    in paint component i just made a method based on the type of case
    it'll return the proper colour
    and i just use it in paint component to make it easier to read
    sorry if i'm not coherent i still haven't had my daily irreversibly heart damaging amount of caffeine and taurine -Milo
     */

    public Color typeCaseColour(Case c) {
        /*Return the appropriate colour based on the type of case
         * else just returns null cuz intelliJ told me to lol */

        if (c instanceof Mur) {
            return Color.BLACK;
        } else if (c instanceof Porte) {
            if (!c.estTraversable()) {
                return Color.GREEN;
            } else {
                return Color.WHITE;
            }
        } else if (c instanceof Hall) {
            return new Color(255, 0, 0, ((Hall) c).getHeat() * 25);
        } else if (c instanceof Sortie) {
            return Color.BLUE;
        } else {

            return null;
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        for (int lig = 0; lig < this.hauteur; lig++) {
            for (int col = 0; col < this.largeur; col++) {


                if ((lig - this.player.getCase().getLig()) * (lig - this.player.getCase().getLig()) + (col - this.player.getCase().getCol()) * (col - this.player.getCase().getCol()) <= 10) {


                    Case c = this.terrain.getCase(lig, col);

                    int visibleLig = (4 + lig - this.player.getCase().getLig());
                    int visibleCol = (4 + col - this.player.getCase().getCol());

                    g.setColor(Color.white);
                    g.fillRect(visibleCol * this.tailleCase, visibleLig * this.tailleCase, this.tailleCase, this.tailleCase);

                    g.setColor(typeCaseColour(c));
                    g.fillRect(visibleCol * this.tailleCase, visibleLig * this.tailleCase, this.tailleCase, this.tailleCase);


                    g.fillRect(visibleCol * this.tailleCase, visibleLig * this.tailleCase, this.tailleCase, this.tailleCase);
                    if (c instanceof Porte) {
                        g.setColor(Color.black);
                        g.drawRect(visibleCol * this.tailleCase, visibleLig * this.tailleCase, this.tailleCase, this.tailleCase);
                    }

                    if (c instanceof Hall) {
                        if (((Hall) c).hasKey()) {
                            Image imgKey = new ImageIcon("src/key.png").getImage();
                            g.drawImage(imgKey, visibleCol * this.tailleCase, visibleLig * this.tailleCase , this);

                        }

                        if (((Hall) c).hasPotion()) {
                            Image imgPotion = new ImageIcon("src/potion-blue.png").getImage();
                            g.drawImage(imgPotion, visibleCol * this.tailleCase, visibleLig * this.tailleCase, this);
                        }

                    }

                    if (c instanceof CaseTraversable) {
                        if (((CaseTraversable) c).hasPlayer()) {
                            Image imgPlayer = new ImageIcon("src/silly_abomination.png").getImage();
                            g.drawImage(imgPlayer, visibleCol * this.tailleCase, visibleLig * this.tailleCase, this);

                        }
                    }

                }

                g.setColor(Color.black);
                String HpIndicator = "HP: ";
                g.setFont(new Font("Verdana", 1, 30));
                g.drawString(HpIndicator, 50,50);

                g.setColor(Color.red);
                g.fillRect(118,28,(int)(0.40*800)+4, 24);
                g.setColor(Color.green);
                g.fillRect(120,30,(int)(0.40*this.player.getResistance()), 20);

                g.setColor(Color.BLACK);

                String keyIndicator = "Key: ";
                g.drawString(keyIndicator,50,90);

                String Keyvalue = String.valueOf( this.player.getCles());
                g.drawString(Keyvalue,150,90);




            }
        }

        frame.repaint();

    }

    //key input part
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {

            case 37:
                this.player.bouge(this.terrain.directionCase(this.player.getCase(), Direction.ouest));
                break;
            case 38:
                this.player.bouge(this.terrain.directionCase(this.player.getCase(), Direction.nord));
                break;
            case 39:
                this.player.bouge(this.terrain.directionCase(this.player.getCase(), Direction.est));
                break;
            case 40:
                this.player.bouge(this.terrain.directionCase(this.player.getCase(), Direction.sud));
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    public void ecranFinal(int n) {
        frame.remove(this);
        JLabel label = new JLabel("Score " + n);
        label.setFont(new Font("Verdana", 1, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        frame.getContentPane().add(label);
        frame.repaint();
    }
}



