import java.beans.IntrospectionException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
/**
 *
 * @author Julien
 */
public class Plan extends BasicGameState implements ComponentListener, MouseListener{
    public static final int ID=1;
    public static int ECHELLE=1;
    
    private static final int MARGIN=5;
    private static String[] src={"images/ZoneA.png","images/ZoneB.png","images/ZoneC.png"};
    private char zone;
    private Image map;
    private static ArrayList<Emplacement> emplacements;
    
    private MouseOverArea sauver,addTerrasse,addEtalage;

    
    // drag&drop
    private static Emplacement selected=null;
    private boolean clicked=false;
    
    private Input input;
    
    @Override
    public int getID() {return ID;}

    @Override
    public void init(GameContainer container, StateBasedGame app) throws SlickException {
       emplacements = new ArrayList();
       map = new Image(src[0]);
       zone =  'A';
       input = container.getInput();
       sauver = new MouseOverArea(container,new Image("images/btn_sauve.png"),827,640,50,50,this);
       addTerrasse = new MouseOverArea(container,new Image("images/btn_add.png"),MARGIN + 10,630,32,32,this);
       addEtalage = new MouseOverArea(container,new Image("images/btn_add.png"),MARGIN + 200,630,32,32,this);
        
       try {emplacements = (ArrayList<Emplacement>) XMLTools.decodeFromFile("plan"+zone+".xml");} 
       catch (FileNotFoundException ex) {
            Logger.getLogger(Plan.class.getName()).log(Level.SEVERE, null, ex);
        } 
       catch (IOException ex) {
            Logger.getLogger(Plan.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void render(GameContainer container, StateBasedGame app, Graphics g) throws SlickException {
        int width = container.getWidth() - MARGIN;
        int height = width / map.getHeight() * map.getWidth();
        
        map.draw(MARGIN, MARGIN, width,height , 0, 0, map.getWidth(), map.getHeight());
        for(Emplacement obj:emplacements) {obj.afficher(g);}
        
        ToolBar(container,g,height);
        
        
    }

    @Override
    public void update(GameContainer container, StateBasedGame app, int delta) 
            throws SlickException {

        if(input.isKeyPressed(Keyboard.KEY_DELETE)){
            if(selected != null){
                try{
                    supprimerEmplacement(selected);
                    JOptionPane.showMessageDialog(null, "emplacement supprimé");
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }

    }
    
    
      public static void ajouterEmplacement(Emplacement obj) {emplacements.add(obj);}
    
    public static void supprimerEmplacement(Emplacement obj) {
        if( emplacements.contains(obj) )emplacements.remove(obj);
    }
    
    

    private void ToolBar(GameContainer container,Graphics g,int height) {
        g.setColor(Color.white);
        height+=10;
        g.fillRect(MARGIN, height, 400, 80 );
        height+=20;
        addEtalage.render(container, g);
        g.setColor(Color.red);
        g.drawString("Etalage", MARGIN + addEtalage.getX() + 32, height+5);
        addTerrasse.render(container, g);
        g.setColor(Color.green);
        g.drawString("Terrasse", MARGIN + addTerrasse.getX() + 32, height+5);
        
        sauver.render(container, g);

    }

    @Override
    public void componentActivated(AbstractComponent source) {
        
        if(source == sauver){
            try {XMLTools.encodeToFile(emplacements, "plan"+zone+".xml");
                JOptionPane.showMessageDialog(null, "sauvegarde effectuée");
            }
            catch (FileNotFoundException ex) {
                Logger.getLogger(Plan.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (IOException | IntrospectionException ex) {
                Logger.getLogger(Plan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(source == addTerrasse){
            ajouterEmplacement(new Terrasse(10,10));
        }
        if(source == addEtalage){
            ajouterEmplacement(new Etalage(10,10));
        }
    }
    

    @Override
    public void mouseClicked(int button,int x,int y,int clickCount){
        if(button == Input.MOUSE_LEFT_BUTTON){
            for(Emplacement obj:emplacements){
                if(obj.contains(x, y)){
                    selected = obj;
                    selected.selectionne();
                    break;
                }
                else{
                    if(selected!=null){
                        selected.deselectionne();
                        selected = null;
                    }
                }
            }
        }
        if((selected!=null)
        && (selected.contains(x, y))
        ){clicked=true;}
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        if(button == Input.MOUSE_LEFT_BUTTON
        && (selected!=null)
        && (selected.contains(x, y))
        ){clicked=true;}
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        if(button == Input.MOUSE_LEFT_BUTTON
        && (selected!=null)
        && (selected.contains(x, y))
        ){clicked=false;}
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        if(clicked
        && (selected!=null)
        && (selected.contains(oldx, oldy))
        ){
            if( (selected.getX()+(newx-oldx))>MARGIN && (selected.getX()+(newx-oldx))<(852+Emplacement.DIM) )
            selected.setX(selected.getX()+(newx-oldx));
            if( (selected.getY()+(newy-oldy))>MARGIN && (selected.getY()+(newy-oldy))<(570+Emplacement.DIM) )
            selected.setY(selected.getY()+(newy-oldy));
        }
        
    }


}
