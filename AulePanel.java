import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.awt.geom.AffineTransform;

/*
*** AulePanel: Pannello che contiene la mappa delle aule di un determinato piano
*/
public class AulePanel extends JPanel
{
 private Aule myAule; 
 private int piano; 
 private ZoomAndPanListener zoomAndPanListener; 
 private boolean init = true;

 
 public AulePanel(int piano)
 {
   System.out.println("Entro in AulePanel() ");
   myAule = new Aule(piano);
   setPiano(piano);
   System.out.println("size="+ myAule.getSize());
   System.out.println("Esco da AulePanel() ");
   
   this.zoomAndPanListener = new ZoomAndPanListener(this);
   this.addMouseListener(zoomAndPanListener);
   this.addMouseMotionListener(zoomAndPanListener);
   this.addMouseWheelListener(zoomAndPanListener);
 }
 
public void setPiano(int p)
 {
           this.piano = p;
 } 

 /*
 *** ripristina la lettura da file delle aule
 */ 
  public void ripristina(int piano)
      {
        System.out.println("-------------------------");
        System.out.println("Entro in AulePanel.ripristina()");
        int size = myAule.getSize();
        System.out.println("size="+size);
        for (int i=0;i< size;i++)
         { 
            System.out.println("da cancellare aula [ " + i+"]="+myAule.getAula(i));
         }
        for (int i=size-1;i>=0 ;i--)
         { 
            System.out.println("cancellazione aula [ " + i+"]="+myAule.getAula(i));
            myAule.cancella(i);
         }
        myAule.leggiDaFile(piano);
        System.out.println("Esco da AulePanel.ripristina()");
        System.out.println("-------------------------");
      
      } 
  
  

 
 
/*
*** paintComponent(): Metodo invocato automaticamente 
*** alla creazione del pannello AulePanel, che visualizza la mappa sul Pannello
*/
  public void paintComponent(Graphics g)
{
 int X1,Y1,X2,Y2,X3,Y3,X4,Y4;

  Aula a;
  int peso=1; // fattore di moltiplicazione per la dimensione delle aule
  int d = 20;  // distanza dal bordo
  Color myColor;
  String strPiano;
  
  super.paintComponent(g);
  
  // inizio gestione panning e zooming
  Graphics2D g1 = (Graphics2D) g;
 
  if (init) {
            // Initialize the viewport by moving the origin to the center of the window,
            // and inverting the y-axis to point upwards.
            init = false;
            Dimension dim = getSize();
            int xc = dim.width / 2;
            int yc = dim.height / 2;
  //          g1.translate(xc, yc);
  //          g1.scale(1, -1);
            // Save the viewport to be updated by the ZoomAndPanListener
            zoomAndPanListener.setCoordTransform(g1.getTransform());
        } else {
            // Restore the viewport after it was updated by the ZoomAndPanListener
            g1.setTransform(zoomAndPanListener.getCoordTransform());
        }
      Font font = g1.getFont();
      AffineTransform affineTransform = new AffineTransform();
//      affineTransform.scale(1, -1);
      g1.setFont(font.deriveFont(affineTransform));
// fine  gestione panning e zooming
  
  
  System.out.println("--------------------------");
  System.out.println("Entro in paintCompoment() ");
  
  System.out.println("size="+ myAule.getSize());
 
  strPiano =  "Piano: " + this.piano;
  System.out.println("strPiano="+ strPiano);
 
  /* 
  *** disegna l'immagine di background con la planimetria del piano scelto
  */
  String strFile = "";
   switch(this.piano)
    { 
        case 0:
        strFile = "plan_p0.jpg";
         break;
       case 1: 
         strFile = "plan_p1.jpg";
         break;
       case 2: 
       //  strFile = new String("Plan_2piano.jpg");
       break;
        case 3:
        case 4:
      //   strFile = new String("Plan_3-4piano.jpg");
       break;
    }
   File fileBackground = new File(strFile);
   Image img;
  
  try
  {      
   img=ImageIO.read(fileBackground); 
   int imgW = img.getWidth(this);
   int imgH = img.getHeight(this);
   System.out.println("image Width= "+ imgW + " Heigth="+imgH);
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
      g.drawString("Aula:"+a.getNome(),X1,Y1-5);
      String sPiano = "piano:" + Integer.valueOf(a.getPiano());
      g.drawString(sPiano,X1+150,Y1-5);
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
      g.drawString("Decibel:" + a.getDb(),X1+40,Y1+40);
      
       // scrive i dati sul tempo di riverbero al bordo
      g.drawString("T.Riverbero:" + a.getTr(),X1+10,Y1+15); 
     }
     
    System.out.println("Esco da paintCompoment() "); 
    System.out.println("--------------------------");
   }


}
