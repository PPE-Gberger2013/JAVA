import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
/**
 *
 * @author Julien
 */
public abstract class Emplacement extends Rectangle {
    protected static final int DIM=15*Plan.ECHELLE;

    protected boolean selected=false;
    
    public Emplacement() {
        super(0,0,10,10);
    }
    
    public Emplacement(int x,int y) {
        super(x,y,DIM,DIM);
    }

    public abstract void afficher(Graphics g);
    
    public void selectionne(){ selected = true;}
    public void deselectionne(){ selected = false;}

}
