import java.util.Vector;
import java.awt.*;
import javax.swing.*;

public class Aule extends JPanel
{
    private Vector v_aule;

    public Aule()
     {
      v_aule = new Vector (1,1);
     }

    public void aggiungi(Aula a)
     {
      v_aule.addElement(a);
     }

    public void cancella(int indice)
    {
     try
      {
	v_aule.removeElementAt(indice);
      }
     catch(Exception e)
      {
	System.out.println("Impossibile eliminare");
        return;
      }
     System.out.println("Eliminazione effettuata OK");
    }

   public void leggi()
    {
	  Aula a;

          System.out.println("Entro in leggi() ");
          System.out.println("size="+ v_aule.size());

	  System.out.println("Aule: ");
 	  for (int i=0;i<v_aule.size();i++)
	   {
	     System.out.println("posizione "+i+" -> ");
             a = (Aula) v_aule.elementAt(i);
	     a.SiPresenta();
	   }
    }

 /*
 *** Disegna il Frame con la disposizione dell aule
 */
  public void Disegna()
  {
 //   System.out.println("Entro in Disegna() ");
 //   System.out.println("size="+ v_aule.size());
    AuleFrame f = new AuleFrame();
    f.add(this);
    f.setTitle("Aule IIS Avogadro");
    f.setSize(800,800);
    f.setForeground(Color.white);
    f.setBackground(Color.white);
    f.setVisible(true);
 } // Disegna()


public void paintComponent(Graphics g)
{
 int X1,Y1,X2,Y2,X3,Y3,X4,Y4;

  Aula a;
  int peso=20; // fattore di moltiplicazione per la dimensione delle aule
  int d = 20;  // distanza dal bordo
  Color myColor;
  
  super.paintComponent(g);

  System.out.println("Entro in paintCompoment() ");
  System.out.println("size="+ v_aule.size());
  for (int i=0;i<v_aule.size();i++)
   {
      a = (Aula) v_aule.elementAt(i);

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
 
      // disegna il rettangolo esterno 
      int width= X2-X1;
      int heigth = Y3-Y2;
    
      /* 
      *** settaggio colore in base al tempo di riverbero
      */
      myColor = this.getColoreTr(a.getTr());
      g.setColor(myColor);
      g.fillRect(X1, Y1, width, heigth);
      
     // disegna il rettangolo centrale 
      width= (X2-d) - (X1+d);
      heigth = (Y3-d)-(Y2+d);
      
      /* 
      *** settaggio colore in base ai decibel
      */  
      myColor = this.getColoreDb(a.getDb());
  
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
   }

/*
*** ritorna il colore associato al valore in decibel
*** db < 20-> verde
*** db > 25-> rosso
*** altrimenti giallo
*/
Color getColoreDb(float  db)    
    { 
        Color myColor;
    
      if (db < 20)
          myColor = Color.green;
      else if (db > 25) 
          myColor = Color.red;
      else myColor = Color.yellow;
        return myColor;
    }
 
/*
*** ritorna il colore associato al valore del tempo di riverbero
*** tr < 4-> verde
*** tr > 5-> rosso
*** altrimenti giallo
*/
Color getColoreTr(float  tr)     
    { 
        Color myColor;
   
        if (tr < 4)
          myColor = Color.green;
        else if (tr > 5) 
          myColor = Color.red;
        else myColor = Color.yellow;
        return myColor;
   }  
}