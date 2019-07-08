import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

/*
*** AulaFrame
*** classe che rappresenta il Frame con il dettaglio di una singola aula
*** Contiene: 
*** un pannello centrale con la planimetria e l'immagine di una aula
*** un pannello inferiore con i pulsanti
*/
public class AulaFrame extends JFrame 
        implements ActionListener
{
   private JPanel pan = new JPanel(); 
   private JPanel panBtn = new JPanel();
   
   private JButton btnAudio = new JButton("Audio");
 
   private AulaPanel aulap; //pannello con la pianta dell'aula
     
   public AulaFrame(Aula a)
    {
     System.out.println("--------------------------------");
     System.out.println("Entro in Aula Frame ");
    
     aulap = new AulaPanel(a);
     aulap.setBackground(Color.white);
   //  this.setBackground(Color.WHITE);
     pan.setBackground(Color.WHITE);
     
     pan.setLayout(new BorderLayout());
     panBtn.setLayout(new FlowLayout());
     
     pan.add(aulap, "Center");
     pan.add(panBtn, "North");
     
     panBtn.add(btnAudio);
 
     this.getContentPane().add(pan,BorderLayout.CENTER);
     this.pack();
     
     btnAudio.addActionListener(this);
     
     System.out.println("esco da AulaFrame()");
     System.out.println("--------------------------------");
    }      

        

/*
*** gestione degli eventi
*/
   public void actionPerformed(ActionEvent e)
 {
    System.out.println("--------------------------------");
    System.out.println("Entro in actionPerformed() ");
    String pulsante = e.getActionCommand();
 
    if (pulsante.equals("Immagine"))
      {      
        System.out.println("Scelta immagine aula");
         pan.repaint();
       }
    if (pulsante.equals("Pianta"))
      {      
        System.out.println("Scelta pianta aula");
         pan.repaint();
       }
    
    if (pulsante.equals("Audio"))
      {      
        System.out.println("Scelto Audio aula");
        
        pan.repaint();
       }  
 
    System.out.println("esco da ActionPerformed()");
    System.out.println("--------------------------------");
 }
   
}