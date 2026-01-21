public class Hall extends CaseTraversable{
    private boolean key;
    private boolean potion;

    public Hall(int l, int c) {
        super(l,c,0);
        this.key = false;
        this.potion = false;

    }
    public Hall(int l, int c, int h) {

        super(l,c,h);
        this.key = false;
        this.potion = false;
    }

    public Hall(int l, int c, boolean k) {
        super(l,c,0);
        this.key = k;
        this.potion = false;
    }

    public Hall(int l, int c, boolean k, boolean p) {
        super(l,c,0);
        this.key = false;
        this.potion = true;
    }

    public boolean hasKey() {
        return this.key;
    }

    public boolean hasPotion(){return this.potion;}

    public void playerPicksKey(Joueur joueur) {
        if(this.hasKey()){
            this.key = false;
            joueur.pickKey();
        }
    }
    public void playerPicksPotion(Joueur joueur){
        if(this.hasPotion()){
            this.potion = false;
            joueur.PotionEffect();
        }
    }

    public void entre(Joueur joueur) {
        super.entre(joueur);
        this.playerPicksKey(joueur);
        this.playerPicksPotion(joueur);

    }

    public boolean estTraversable() {
        return true;
    }

}
