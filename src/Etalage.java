import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
/**
 *
 * @author Julien
 */
public class Etalage extends Emplacement{
    
    public Etalage() {}

    public Etalage(int x,int y){
        super(x,y);
    }

    @Override
    public void afficher(Graphics g) {
        g.setColor(Color.red);
        g.fill(this);
        if(selected){
            g.setColor(Color.black);
            g.setLineWidth(2);
            g.draw(this);
            
        }
    }


}
