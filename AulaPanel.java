



import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.awt.geom.AffineTransform;
import java.awt.event.*;
import javax.sound.sampled.*;

/*
*** AulePanel: Pannello che contiene la mappa delle aule di un determinato piano
*/
public class AulaPanel extends JPanel
{
 private Aula myAula; 
 private BufferedImage imgImg;
 private BufferedImage imgPlan;
 private BufferedImage imgDati;
 
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
  int d;        // distanza del bordo interno al rettangolo
  int dx; // distanza dal bordo asse x
  int dy; // distanza dal bordo asse y
  Color myColor;
  String strPiano;
  
  super.paintComponent(g);
 
  System.out.println("--------------------------");
  System.out.println("AulaPanel: Entro in paintCompoment() ");
 
  /* 
  *** disegna l'immagine di background con la planimetria dell'aula scelta
  */
  String strFilePlan = "plan/" + this.myAula.getNome()+ ".jpg";
  System.out.println("leggo il file "+ strFilePlan);
 
   //ClassLoader classloader = Thread.currentThread().getContextClassLoader();
     ClassLoader classloader = Thread.currentThread().getContextClassLoader();  
 
    File filePlan = new File (classloader.getResource(strFilePlan).getFile()); 
  
 
 //  File filePlan = new File (getClass().getClassLoader().getResource (strFilePlan).getFile()); 
 
 // File filePlan = new File(strFilePlan);
  
  System.out.println("leggo il file della planimetria "+ strFilePlan);
  
  
  /* 
  *** disegna l'immagine di background con la immagine dell'aula scelta
  */
  String strFileImg = "img/" + this.myAula.getNome()+ ".jpg";
  System.out.println("leggo il file "+ strFileImg);
  

 // File fileImg = new File (getClass().getClassLoader().getResource (strFileImg).getFile());
  File fileImg = new File (classloader.getResource(strFileImg).getFile()); 
  
 // File fileImg = new File(strFileImg);
   
  System.out.println("leggo il file della immagine "+ strFileImg);
  
  /* 
  *** disegna l'immagine di background con la immagine dell'aula scelta
  */
  String strFileDati = "dati/" + this.myAula.getNome()+ ".jpg";
  System.out.println("leggo il file "+ strFileDati);
  
  File fileDati = new File (classloader.getResource(strFileDati).getFile()); 
  
 // File fileDati = new File (getClass().getClassLoader().getResource (strFileDati).getFile());
 
 // File fileDati = new File(strFileDati);
   
  System.out.println("leggo il file dati "+ strFileDati);
      
  try
  { 
   imgPlan=ImageIO.read(filePlan); 
   imgImg=ImageIO.read(fileImg);
   imgDati=ImageIO.read(fileDati);
   
   int imgWPlan = imgPlan.getWidth(this);
   int imgHPlan = imgPlan .getHeight(this);
   
   int imgWImg = imgImg.getWidth(this);
   int imgHImg = imgImg.getHeight(this);
   
   int imgWDati = imgDati.getWidth(this);
   int imgHDati = imgDati.getHeight(this);
   
   System.out.println("image Plan Width= "+ imgWPlan + " Heigth="+imgHPlan);
   System.out.println("image Img Width= "+ imgWImg + " Heigth="+imgHImg);
   System.out.println("image Dati Width= "+ imgWDati + " Heigth="+imgHDati);
  
   this.setLayout(new BorderLayout());
 
   // g.drawImage(img, x, y, width, heigth, this); 
   g.drawImage(imgPlan, 100, 0, 300, 300, this); 
   g.drawImage(imgImg,  500, 0, 300, 300, this); 
   g.drawImage(imgDati, 500, 400, 300, 300, this); 
   System.out.println("dopo draw image");
  }
  catch(IOException e)
   {
         imgPlan = null;
         imgImg  = null;
         System.out.println("exception image");
   }

// disegna e colora l'aula indicata con i dati sonori
  
  g.setColor(Color.black); 
  
  Polygon po = new Polygon();
  Polygon pc = new Polygon();
   
  dx = 200;
  dy = 200;
  int x1 = 100;
  int y1 = 400;
  
  X1 = x1;
  Y1 = y1;
  X2 = x1 + dx;
  Y2 = Y1;
  X3 = X2;
  Y3 = Y1+dy;
  X4 = X1;
  Y4 = Y3;
      
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
 
   /*
   *** ascoltaAula(): Riproduce il suono dell'aula indicata
   */
  
  public void ascoltaAula()
   {   
        try
         {
          String strFileAudio = "audio/" + this.myAula.getNome()+ ".wav";
          System.out.println("leggo il file "+ strFileAudio);
          
           File audioFile = new File (getClass().getClassLoader().getResource (strFileAudio).getFile()); 
 //         File audioFile = new File(strFileAudio);
          AudioInputStream audioIn = AudioSystem.getAudioInputStream(audioFile);
          Clip suono = null;
          suono = AudioSystem.getClip();
          suono.open(audioIn);
          suono.start();
         }
        catch (Exception ex)
         {
           System.out.println("il suono non può essere caricato");
         }
    }
 
  
  /*
   *** salvaAula(): Salva la pianta dell'aula
   */
  
  public void salvaAula()
   {          
          JFileChooser fileChooser = new JFileChooser();
          String strFilePlan = "out/" + this.myAula.getNome()+ "_plan"+ ".png";
          System.out.println("scrivo su file "+ strFilePlan);
          File fp = new File(strFilePlan);
          String strFileDati = "out/" + this.myAula.getNome()+ "_dati"+ ".png";
          File fd = new File(strFileDati);
          fileChooser.setSelectedFile(fd);
           try
            { 
             ImageIO.write(this.getPlan(), "png", fp);
             ImageIO.write(this.getDati(), "png", fd);
            }
            catch (Exception ex)
            {
             System.out.println("errore in Salvataggio  aula su file");
            }
        
           JOptionPane.showMessageDialog(this, "file  salvati correttamente sotto cartella /out","Info",  JOptionPane.INFORMATION_MESSAGE);
    }// end salvaPiantaAula
  
  /*
  *** getDisegno(): ritorna l'immagine con la planimetria dell'aula
  */
  public BufferedImage getPlan()
         {  
             return imgPlan;
         }
  
  /*
  *** getDati(): ritorna l'immagine con i dati dell'aula
  */
  public BufferedImage getDati()
         {  
             return imgDati;
         }
}
