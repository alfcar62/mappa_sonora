import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.awt.geom.AffineTransform;
import java.awt.event.*;

/*
*** AulePanel: Pannello che contiene la mappa delle aule di un determinato piano
*/
public class AulaPanel extends JPanel
{
 private Aula myAula; 
 private BufferedImage img;
 
 public AulaPanel(Aula a) 
 {
   System.out.println("Entro in AulaPanel() "); 
   myAula = new Aula(a);
   System.out.println("Esco da AulaPanel() "); 
 }
 

/*
*** paintComponent(): Metodo invocato automaticamente 
*** alla creazione del pannello AulePanel, che visualizza la mappa sul Pannello
*/
  public void paintComponent(Graphics g)
{
 int X1,Y1,X2,Y2,X3,Y3,X4,Y4;

  int peso=1; // fattore di moltiplicazione per la dimensione delle aule
  int d = 20;  // distanza dal bordo
  Color myColor;
  String strPiano;
  
  super.paintComponent(g);
 
  System.out.println("--------------------------");
  System.out.println("AulaPanel: Entro in paintCompoment() ");
 
  /* 
  *** disegna l'immagine di background con la planimetria del piano scelto
  */
  String strFile = this.myAula.getNome()+ ".jpg";
   
  System.out.println("leggo il file "+ strFile);
  File fileBackground = new File(strFile);
  BufferedImage img;
      
  try
  {      
   img=ImageIO.read(fileBackground); 
   int imgW = img.getWidth(this);
   int imgH = img.getHeight(this);
   System.out.println("image Width= "+ imgW + " Heigth="+imgH);
   this.setLayout(new BorderLayout());
 
   g.drawImage(img, 0, 0, this); 
   System.out.println("draw image");
  }
  catch(IOException e)
    {
         img=null;
         System.out.println("exception image");
    }

// disegna le aule del piano
  
  g.setColor(Color.black);

   
  
      Polygon po = new Polygon();
      Polygon pc = new Polygon();
        
      X1=this.myAula.getX1() * peso;
      Y1=this.myAula.getY1() * peso;
      X2=this.myAula.getX2() * peso;
      Y2=this.myAula.getY2() * peso;
      X3=this.myAula.getX3() * peso;
      Y3=this.myAula.getY3() * peso;
      X4=this.myAula.getX4() * peso;
      Y4=this.myAula.getY4() * peso;
      
      po.addPoint(X1, Y1);
      po.addPoint(X2, Y2);
      po.addPoint(X3, Y3);
      po.addPoint(X4, Y4);
      
      // scrive il nome dell'aula
      g.setColor(Color.black);
      g.drawString("Aula:"+this.myAula.getNome(),X1,Y1-5);
      String sPiano = "piano:" + Integer.valueOf(this.myAula.getPiano());
      g.drawString(sPiano,X1+150,Y1-5);
      
// disegna il rettangolo esterno 

      /* 
      *** settaggio colore in base al tempo di riverbero
      */
      myColor = this.myAula.getColoreTr();
      g.setColor(myColor);
      g.fillPolygon(po);
      
     // disegna il rettangolo centrale 
     
      d= (X2-X1) * 20 /100;  // bordo interno del 20% 
      
      if (X1 == X4)  // aula dritta 
       {   
         pc.addPoint(X1+d, Y1+d);
         pc.addPoint(X2-d, Y2+d);
         pc.addPoint(X3-d, Y3-d);
         pc.addPoint(X4+d, Y4-d);
      }
      else // aula storta
      {   
         pc.addPoint(X1+d/4, Y1+d+d/3);
         pc.addPoint(X2-d, Y2+d/2);
         pc.addPoint(X3, Y3-d);
         pc.addPoint(X4+d, Y4-d/2);
      }
      
      /* 
      *** settaggio colore in base ai decibel
      */  
      myColor = this.myAula.getColoreDb();
      
      g.setColor(myColor);
      g.fillPolygon(pc);
      
     // disegna i bordi esterni dell'aula
      g.setColor(Color.black);
      g.drawPolygon(po);
 
      // disegna i bordi interni dell'aula
      
      g.setColor(Color.black);
      g.drawPolygon(pc);
      // scrive i dati sui decibel sonori al centro
      g.drawString("Decibel:" + this.myAula.getDb(),X1+40,Y1+40);
      
       // scrive i dati sul tempo di riverbero al bordo
      g.drawString("T.Riverbero:" + this.myAula.getTr(),X1+10,Y1+15); 
     
     
    System.out.println("Esco da paintCompoment() "); 
    System.out.println("--------------------------");
   }

   public void mouseExited(MouseEvent e)
    {    
    }     
  
  public void mouseEntered(MouseEvent e)
    {    
    }
  
  
  
  public void mouseReleased(MouseEvent e)
    {    
    }
  
  public void mouseMoved(MouseEvent e)
    {    
    }
 
   
  
  public void mouseDragged(MouseEvent e)
    {    
        System.out.println("dragged"); 
    }
  
   public void mouseClicked(MouseEvent e)
   {  
      System.out.println("clicked");
   }
   
   public void disegna_aula (Aula a)
   {
        System.out.println("entro in disegna_aula()");
        String strFile = a.getNome() + ".jpg";
        File fileBackground = new File(strFile);
        BufferedImage img;  
   
        try
         {      
          img=ImageIO.read(fileBackground); 
          int imgW = img.getWidth(this);
          int imgH = img.getHeight(this);
          System.out.println("image Width= "+ imgW + " Heigth="+imgH);
          this.setLayout(new BorderLayout());
          
  //        g.drawImage(img, 0, 0, this); 
          System.out.println("draw image");
         }
        catch(IOException e)
         {
          img=null;
          System.out.println("exception image");
         }

   }
}
