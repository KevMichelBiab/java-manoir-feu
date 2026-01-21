import java.lang.Thread;
import java.lang.Math;
public class Joueur {

    private CaseTraversable c;
    private int resistance;
    private int cles;
    public Joueur(CaseTraversable c, int r, int k) {
        this.c = c;
        this.resistance = r;
        this.cles = k;

    }

    public void bouge(Case cible) {


        if (cible instanceof Porte && checkCounter() && !cible.estTraversable()) {
            this.useKey();
            Porte cp = (Porte) cible;
            cp.openDoor();
            this.c.removePLayerFromCase();
            cp.entre(this);
            this.c = cp;

        }if (cible instanceof Porte && cible.estTraversable()) {
            Porte cp = (Porte) cible;
            this.c.removePLayerFromCase();
            cp.entre(this);
            this.c = cp;

        }

        else if (cible instanceof Hall || cible instanceof Sortie) {
            this.resistanceRestante(((CaseTraversable) cible).getHeat());
            CaseTraversable ct = (CaseTraversable) cible;
            this.c.removePLayerFromCase();
            ct.entre(this);
            this.c = ct;

        }
    }
//pickKey = when the player picks keys
    public void pickKey(){
        ++this.cles;
    }
//when the player picks a potion
    public void PotionEffect() {
        this.resistance += 200;

        if (this.resistance >= 800) {
            this.resistance = 800;
        }
    }



    public boolean checkCounter(){
        return (this.cles > 0);
    }

    public void useKey(){
        this.cles--;
    }


    public void resistanceRestante(int dmg)  {
        this.resistance = this.resistance - (dmg*10);
    }

    /*getters*/

    public int getResistance(){
        return this.resistance;
    }

    public int getCles(){
        return this.cles;
    }

    public CaseTraversable getCase() {
        CaseTraversable ct = (CaseTraversable)c;
        return ct;
    }

}
