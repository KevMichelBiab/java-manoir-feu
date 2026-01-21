public class Sortie extends CaseTraversable{

    public Sortie(int l, int c, int h) {
        super(l,c,h);
    }

    public void entre(Joueur joueur) {
        super.entre(joueur);
    }
    public boolean estTraversable(){
        return true;
    }


}
