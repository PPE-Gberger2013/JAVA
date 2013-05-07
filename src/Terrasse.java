import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
/**
 *
 * @author Julien
 */
public class Terrasse extends Emplacement {
    
    public Terrasse() {}
    
    public Terrasse(int x,int y){
        super(x,y);
    }

    @Override
    public void afficher(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(getX(), getY(), DIM, DIM);
        if(selected){
            g.setColor(Color.black);
            g.setLineWidth(2);
            g.drawOval(getX(), getY(), DIM, DIM);
        }
    }
 
}
