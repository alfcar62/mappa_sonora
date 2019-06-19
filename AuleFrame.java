import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AuleFrame extends JFrame 
{
   private AulePanel aulep;
  
   public AuleFrame(int piano)
      {    
       System.out.println("Entro in AuleFrame() ");
       
       aulep = new AulePanel(piano);
       this.setBackground(Color.white);
       this.setForeground(Color.white);
       this.getContentPane().add(aulep);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       System.out.println("Esco da AuleFrame() ");         
      }

}
