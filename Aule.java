import java.util.Vector;
import java.awt.*;
import java.awt.event.*;

public class Aule extends Canvas
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
    *** Disegna le aule
    */
  public void Disegna()
  {
    System.out.println("Entro in Disegna() ");
    System.out.println("size="+ v_aule.size());
    Frame f = new Frame("Mappa sonora Aule IIS Avogadro");
 
    System.out.println("Aule: ");
    
    f.add(this);  
    f.setSize(610,640);
    f.setLocation(500,100);
    f.setVisible(true);
 } // Disegna()


public void paint(Graphics g)
{
 int X1,Y1,X2,Y2,X3,Y3,X4,Y4;

  Aula a;
  System.out.println("Entro in paint() ");
  System.out.println("size="+ v_aule.size());
  for (int i=0;i<v_aule.size();i++)
   {
      a = (Aula) v_aule.elementAt(i);
	    
      X1=a.getX1() * 10;
      Y1=a.getY1() * 10;
      X2=a.getX2() * 10;
      Y2=a.getY2() * 10;
      X3=a.getX3() * 10;
      Y3=a.getY3() * 10;
      X4=a.getX4() * 10;
      Y4=a.getY4() * 10;

      g.setColor(Color.black);
    
      g.drawString(a.getNome(),X1,Y1);
     
      g.drawString("decibel:" + a.getDb(),X1,Y1+20);
      g.drawString("t.riverbero:" + a.getDb(),X1,Y1+30);
      g.setColor(Color.red);
      g.drawLine(X1,Y1,X2,Y2);
      g.drawLine(X2,Y2,X3,Y3);
      g.drawLine(X3,Y3,X4,Y4);
      g.drawLine(X4,Y4,X1,Y1);
     }
   }
}