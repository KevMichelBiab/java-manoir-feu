import java.util.Random;
import java.util.ArrayList;
public abstract class CaseTraversable extends Case{

    private int heat;
    private Joueur joueur;

    public CaseTraversable(int l, int c, int h){
        super(l,c);
        this.heat = h;
        this.joueur = null;
    }




    /*getter*/

    public int getHeat(){
        return this.heat;
    }


    public boolean hasPlayer(){
        return this.joueur != null;
    }

    /**/

    public void entre(Joueur j){
        if(this.estTraversable()) {
            this.joueur = j;
        }

    }

    public void removePLayerFromCase(){
        this.joueur = null;
    }

    //fire thing

    public void flamePropagation(ArrayList<CaseTraversable> v){

        int fireSpread = 0;

        for(CaseTraversable c: v){
            fireSpread += c.heat;
        }
        Random rnd = new Random();
        int rand = rnd.nextInt(199);

        if (rand > 190) {
            if(this.heat > 0){
                this.heat--;
            }
        } else if (rand < fireSpread) {
            if(this.heat < 5){
                this.heat++;
            }
        }
    }

    //just use voisin arraylist as input
    //since we already have a method that generates it
    //in terrain
    /*public  void flamePropagation(Terrain t, Case c){
        if(c.getLig() >= 0 && c.getLig() < t.getCarte().length){
            if (c instanceof CaseTraversable){
                CaseTraversable newC = (CaseTraversable) c;
                int sommeHeat = 0;
                for(int i = newC.getLig() - 1; i <=3; i++){
                    for(int j = newC.getCol() - 1; j <= 3; j++){
                        if(t.getCarte()[i][j] instanceof CaseTraversable){
                            CaseTraversable ctd = (CaseTraversable) t.getCarte()[i][j];
                            sommeHeat += ctd.getHeat();
                        }

                    }
                }
                Random random = new Random();
                int randomNum = random.nextInt(199);
                if(newC.heat < 10 && newC.heat >0){
                    if(randomNum < sommeHeat){
                        newC.heat++;
                    }else{
                        newC.heat++;
                    }
                }

            }

        }

    }*/


}
