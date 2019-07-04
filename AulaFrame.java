import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

/*
*** MainFrame
*** classe che rappresenta il Frame principale
*** Conterrà 
*** un pannello superiore per la scelta del piano
*** un pannello centrale con la mappa delle aule
*/
public class AulaFrame extends JFrame 
        implements ActionListener
{
   private AulaPanel aulap;
   
   public AulaFrame(Aula a)
    {
     System.out.println("--------------------------------");
     System.out.println("Entro in Aula Frame ");
 
     aulap = new AulaPanel(a);
   
     this.pack();
  
     aulap.setBackground(Color.WHITE);
 
     add(aulap,"Center");
  
     
     System.out.println("esco da AulaFrame()");
     System.out.println("--------------------------------");
    }      

 

        

/*
*** gestione della scelta del piano da visualizzare
*/
   public void actionPerformed(ActionEvent e)
 {
    System.out.println("--------------------------------");
    System.out.println("Entro in actionPerformed() ");
    String pulsante = e.getActionCommand();
   
    this.repaint();
 
    System.out.println("esco da ActionPerformed()");
    System.out.println("--------------------------------");
 }
   
}