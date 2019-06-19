import java.awt.*;
import javax.swing.*;

public class AulePanel extends JPanel
{
 private Aule myAule; 
 private int piano;   
 public AulePanel(int piano)
      {
        System.out.println("Entro in AulePanel() ");
        myAule = new Aule(piano);
        this.piano = piano;
        System.out.println("size="+ myAule.getSize());
        System.out.println("Esco da AulePanel() ");
      }


 /*
 *** ripristina la lettura da file delle aule
 */ 
  public void ripristina(int piano)
      {
        myAule.cancella();
        myAule.leggiDaFile(piano);
      } 
  
public void paintComponent(Graphics g)
{
 int X1,Y1,X2,Y2,X3,Y3,X4,Y4;

  Aula a;
  int peso=20; // fattore di moltiplicazione per la dimensione delle aule
  int d = 20;  // distanza dal bordo
  Color myColor;
  String strPiano;
  
  
  super.paintComponent(g);
  
  System.out.println("Entro in paintCompoment() ");
  
  System.out.println("size="+ myAule.getSize());
 
  strPiano =  "Piano: " + this.piano;
 
  g.setColor(Color.black);
  g.drawString(strPiano,20,20);
   
  for (int i=0;i<myAule.getSize();i++)
   {
      a = (Aula) myAule.getAula(i);

      X1=a.getX1() * peso;
      Y1=a.getY1() * peso;
      X2=a.getX2() * peso;
      Y2=a.getY2() * peso;
      X3=a.getX3() * peso;
      Y3=a.getY3() * peso;
      X4=a.getX4() * peso;
      Y4=a.getY4() * peso;
      
      // scrive il nome dell'aula
      g.setColor(Color.black);
      g.drawString(a.getNome(),X1,Y1-5);
      String sPiano = "piano" + Integer.valueOf(a.getPiano());
      g.drawString(sPiano,X1+100,Y1-5);
      // disegna il rettangolo esterno 
      int width= X2-X1;
      int heigth = Y3-Y2;
    
      /* 
      *** settaggio colore in base al tempo di riverbero
      */
      myColor = a.getColoreTr();
      g.setColor(myColor);
      g.fillRect(X1, Y1, width, heigth);
      
     // disegna il rettangolo centrale 
      width= (X2-d) - (X1+d);
      heigth = (Y3-d)-(Y2+d);
      
      /* 
      *** settaggio colore in base ai decibel
      */  
      myColor = a.getColoreDb();
  
      g.setColor(myColor);
      g.fillRect(X1+d, Y1+d, width, heigth);
     // disegna i bordi esterni dell'aula
      g.setColor(Color.black);
      g.drawLine(X1,Y1,X2,Y2);
      g.drawLine(X2,Y2,X3,Y3);
      g.drawLine(X3,Y3,X4,Y4);
      g.drawLine(X4,Y4,X1,Y1);
     
      // disegna i bordi interni dell'aula
      
      g.setColor(Color.black);
      g.drawLine(X1+d,Y1+d,X2-d,Y2+d);
      g.drawLine(X2-d,Y2+d,X3-d,Y3-d);
      g.drawLine(X3-d,Y3-d,X4+d,Y4-d);
      g.drawLine(X4+d,Y4-d,X1+d,Y1+d);
     
      // scrive i dati sui decibel sonori al centro
      g.drawString("decibel:" + a.getDb(),X1+40,Y1+40);
      
       // scrive i dati sul tempo di riverbero al bordo
      g.drawString("t.riverbero:" + a.getTr(),X1+10,Y1+15); 
     }
     
    System.out.println("Esco da paintCompoment() "); 
   }


}
