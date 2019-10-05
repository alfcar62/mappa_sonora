import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.io.*;
import java.awt.geom.AffineTransform;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*
*** PianoPanel: Pannello che contiene la mappa delle aule di un determinato piano
*/
public class PianoPanel extends JPanel
        implements  MouseListener, MouseMotionListener
{
 private Aule myAule; 
 private int piano; 
 private ZoomAndPanListener zoomAndPanListener; 
 private boolean init = true;
 private BufferedImage img;

 
 public PianoPanel(int piano) 
 {
   System.out.println("Entro in PianoPanel() ");
   myAule = new Aule(piano);
   setPiano(piano);
   System.out.println("size="+ myAule.getSize());

   this.zoomAndPanListener = new ZoomAndPanListener(this);
   this.addMouseListener(zoomAndPanListener);
   this.addMouseMotionListener(zoomAndPanListener);
   this.addMouseWheelListener(zoomAndPanListener);
   
   this.addMouseListener(this); // in ascolto del mouse
   this.addMouseMotionListener(this);
   System.out.println("Esco da PianoPanel() ");
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
        System.out.println("Entro in PianoPanel.ripristina()");
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
        System.out.println("Esco da PianoPanel.ripristina()");
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
  
  System.out.println("--------------------------");
  System.out.println("PianoPanel: Entro in paintCompoment() ");
  // inizio gestione panning e zooming
  Graphics2D g1 = (Graphics2D) g;
 
  if (init) {
            // Initialize the viewport by moving the origin to the center of the window,
            // and inverting the y-axis to point upwards.
            init = false;
 //           Dimension dim = getSize();
 //           int xc = dim.width / 2;
 //           int yc = dim.height / 2;
  //          g1.translate(xc, yc);

   //         g1.scale(1, -1);
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
  

 
  
  System.out.println("size="+ myAule.getSize());
 
  strPiano =  "Piano: " + this.piano;
  System.out.println("strPiano="+ strPiano);
 
  /* 
  *** disegna l'immagine di background con la planimetria del piano scelto
  */
  String strFile = "plan/";
   switch(this.piano)
    { 
        case 0:
        strFile = strFile + "plan_p0.jpg";
         break;
       case 1: 
         strFile = strFile + "plan_p1.jpg";
         break;
       case 2: 
        strFile = strFile + "plan_p2.jpg";
       break;
        case 3:
            strFile = strFile + "plan_p3.jpg";
       break;
        case 4:
            strFile.concat("plan_p4.jpg");
       break;
    }
   
   // getClass().getClassLoader().getResource(strFile);
   // File fileBackground = new File (getClass().getClassLoader().getResource(strFile).getFile());
    
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    File fileBackground = new File (classloader.getResource(strFile).getFile());
    
  
 //    File fileBackground = new File (getClass().getClassLoader().getResource (strFile).getFile());
 //   File fileBackground = new File(strFile);
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
  g.drawString(strPiano,20,20);
   
  for (int i=0;i<myAule.getSize();i++)
   {
      a = (Aula) myAule.getAula(i);
      Polygon po = new Polygon();
      Polygon pc = new Polygon();
        
      X1=a.getX1() * peso;
      Y1=a.getY1() * peso;
      X2=a.getX2() * peso;
      Y2=a.getY2() * peso;
      X3=a.getX3() * peso;
      Y3=a.getY3() * peso;
      X4=a.getX4() * peso;
      Y4=a.getY4() * peso;
      
      po.addPoint(X1, Y1);
      po.addPoint(X2, Y2);
      po.addPoint(X3, Y3);
      po.addPoint(X4, Y4);
      
      // scrive il nome dell'aula
      g.setColor(Color.black);
      g.drawString("Aula:"+a.getNome(),X1,Y1-5);
      String sPiano = "piano:" + Integer.valueOf(a.getPiano());
      g.drawString(sPiano,X1+150,Y1-5);
      
// disegna il rettangolo esterno 

      /* 
      *** settaggio colore in base al tempo di riverbero
      */
      myColor = a.getColoreTr();
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
      myColor = a.getColoreDb();
      
      g.setColor(myColor);
      g.fillPolygon(pc);
      
     // disegna i bordi esterni dell'aula
      g.setColor(Color.black);
      g.drawPolygon(po);
 
      // disegna i bordi interni dell'aula
      
      g.setColor(Color.black);
      g.drawPolygon(pc);
      // scrive i dati sui decibel sonori al centro
      g.drawString("Decibel:" + a.getDb(),X1+40,Y1+40);
      
       // scrive i dati sul tempo di riverbero al bordo
      g.drawString("T.Riverbero:" + a.getTr(),X1+10,Y1+15); 
     }
     
    System.out.println("Esco da paintCompoment() "); 
    System.out.println("--------------------------");
   }

   public void mouseExited(MouseEvent e)
    {    
    }     
  
  public void mouseEntered(MouseEvent e)
    {    
    }
  
  public void mousePressed(MouseEvent e)
    {  
      if (e.getClickCount()>=2)        
       { 
        System.out.println("double click"); 
        Aula a;
       
        System.out.println("x rel="+e.getX()+"y rel="+ e.getY()); 
        System.out.println("x abs ="+e.getXOnScreen()+"y abs="+ e.getYOnScreen()); 
        for (int i=0;i<myAule.getSize();i++)
         {
            a = (Aula) myAule.getAula(i);
   //         a.SiPresenta();
            if (e.getX() > a.getX1()  && 
                e.getX() < a.getX2()  &&
                e.getY() > a.getY1()  && 
                e.getY() < a.getY3()) 
                 {
                  System.out.println("cliccato dentro aula "+ a.getNome());
                  this.disegna_aula(a);
                 }
          }
       }
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
        
         AulaFrame af = new AulaFrame(a); 
         
         af.setTitle("Mappa Sonora aula "+a.getNome());
         af.setSize(1000,1000);
         af.setVisible(true);
   }
 }

