public class Porte extends CaseTraversable{

    private boolean open;

    public Porte(int l, int c, boolean s) {
        super(l,c,0);
        this.open = s;

    }

   /*removed is open cuz it's the same as estTraversable broski*/
    public boolean estTraversable() {
        return this.open;
    }

    public void entre(Joueur j){
        super.entre(j);

    }

    public void openDoor(){
        this.open = true;
    }

}