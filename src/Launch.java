import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
/**
 *
 * @author Julien
 */
public class Launch extends StateBasedGame {
    
    public Launch(){super("Gestion des Terrasses");}
    
    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new Launch());
            container.setDisplayMode(882, 700, false);
            container.setTargetFrameRate(1000);
            container.setMultiSample(4);
            container.setVSync(true);
            container.setShowFPS(false);
            container.start();
        } catch (SlickException ex) {
            Logger.getLogger(Launch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
      addState(new Plan());
    }
   
        
}