import javax.swing.Timer;

public class Furfeux {

    Terrain terrain;
    Joueur joueur;

    public Furfeux(String f) {
        this.terrain = new Terrain(f);
        this.joueur = terrain.getJoueur();
    }

//KeyListener
    public void tour() {
        this.joueur.getCase().entre(this.joueur);

        for (int i = 1; i < this.terrain.getHauteur() - 1; i++) {
            for (int j = 1; j < this.terrain.getLargeur() - 1; j++) {
                this.terrain.getCase(i, j).flamePropagation(this.terrain.getVoisinesTraversables(i, j));
            }
        }
    }

    public boolean partieFinie() {
        if (this.joueur.getCase() instanceof Sortie || this.joueur.getResistance() <= 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        int tempo = 100;

        FenetreJeu graphic = new FenetreJeu();


            Furfeux jeu = new Furfeux(graphic.getManoirs().get(graphic.getChoice()));
            graphic.setTerrain(jeu.terrain);

            Timer timer = new Timer(tempo, e -> {
                jeu.tour();
                graphic.repaint();
                if (jeu.partieFinie()) {
                    graphic.ecranFinal(Math.max(0, jeu.joueur.getResistance()));
                    ((Timer) e.getSource()).stop();
                }
            });
            timer.start();


    }
}