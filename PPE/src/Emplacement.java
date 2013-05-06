import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
/**
 *
 * @author Julien
 */
public abstract class Emplacement extends Rectangle {
    private Color couleur;
    
    protected static final int DIM=15*Plan.ECHELLE;

    protected boolean selected=false;
    
    public Emplacement() {
        super(0,0,10,10);
    }
    
    public Emplacement(int x,int y,Color c) {
        super(x,y,DIM,DIM);
        couleur = c;
    }

    public void afficher(Graphics g) {
        g.setColor(couleur);
    }
    
    public void selectionne(){ selected = true;}
    public void deselectionne(){ selected = false;}
    
//    public int distance(Emplacement obj) {
//        int[][] tab = new int[4][4];
//        
//        
//        
//    }
    
}
