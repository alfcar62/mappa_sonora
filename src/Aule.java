import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
/*
*** Aule: classe che rappresenta le  aule di un determinato piano
***       viene implementata con un vettore di aule 
*/
public class Aule
{
    private Vector v_aule;
    private int piano;
  
    /*
    *** Costruttore: costruisce un vettore di aule del piano indicato
    */
    public Aule(int piano)
     {
      System.out.println("----------------------");
      System.out.println("Entro in Aule()");
      v_aule = new Vector (1,1);
      System.out.println("size = " +v_aule.size());
      this.leggiDaFile(piano);
      System.out.println("size = " +v_aule.size());
      System.out.println("Esco da  Aule()");
      System.out.println("----------------------");
     }

    public void aggiungi(Aula a)
     {
         System.out.println("aggiungi() aula=" + a.getNome());
         v_aule.addElement(a);
     }
    
    /*
    *** Cancellazione di tutto il vettore di aule
    *** 
    */
    public void cancella()
     { 
       int i; 
       System.out.println("----------------------");
       System.out.println("entro in cancella()");
       System.out.println("size() ="+ this.getSize());
        for (i=0;i< this.v_aule.size();i++)
             this.log();
        for (i=0;i< this.v_aule.size();i++)
             this.cancella(i);
        System.out.println("cancellati "+ i + "elementi");
        System.out.println("esco da cancella()");
        System.out.println("----------------------");
     }
    
   /*
    *** Cancellazione di un elemento del vettore di aule v_aule
    *** verrà cancellato l'elemento in posizione i
    */
    
    public void cancella(int i)
    {
     try
      {
        v_aule.removeElementAt(i);
      }
     catch(Exception e)
      {
	System.out.println("Impossibile eliminare");
        return;
      }
     System.out.println("Eliminazione indice= "+i+"  effettuata OK");
    }

    public int getSize()
       {
         return(v_aule.size());       
       }
      
    
    public int getPiano(int i)
       {
         Aula a = (Aula) v_aule.elementAt(i);
         return(a.getPiano());       
       }
     
    public void setPiano(int p)
       {
         this.piano =p;       
       }
    
     public Aula getAula(int i)
       {
         Aula a = (Aula) v_aule.elementAt(i);
         return(a);       
       }
    
    /*
    *** Legge le aule presenti nel vettore v_aule
    */
   public void log()
    {
	  Aula a;
          System.out.println("-------------------------");
          System.out.println("Entro in log() ");
          System.out.println("size="+ v_aule.size());

	  System.out.println("Aule: ");
 	  for (int i=0;i<v_aule.size();i++)
	   {
	     System.out.println("posizione "+i+" -> ");
             a = (Aula) v_aule.elementAt(i);
	     a.SiPresenta();
	   }
         System.out.println("Esco da log() ");
         System.out.println("-------------------------");
    }


/*
*** leggiDaFile: Legge l'elenco delle aule con i dati presenti su file csv e li carica su un vettore di oggetti di classe Aula
*/
public void leggiDaFile(int piano) {
        System.out.println("-------------------------");
        System.out.println("entro in leggiDaFile() piano="+piano);
//        String csvFile = "D:\\PC_NEW\\Scuola\\generale\\LinguaggiProgrammazione\\java\\Java_CSV\\vector2\\DatiAule.csv";
        String csvFile = "dati/DatiAule.csv";
        String line = "";
        String cvsSplitBy = ",";
        
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();   
        InputStream inputStream = classloader.getResourceAsStream(csvFile);
        
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        try (BufferedReader br = new BufferedReader(streamReader)) {
        line = br.readLine();   // skip della prima riga con le intestazioni
      
    //    Aule myAule = new Aule();

        int i=0;
        System.out.println("Elenco aule lette dal file "+ csvFile);
        System.out.println("");
        System.out.println("---------------------------------------");
        while ((line = br.readLine()) != null) 
           {
                String[] dati = line.split(cvsSplitBy); // use comma as separator
		int k=0;
 		String myNome   = dati[k++];    // nome
                String mySPiano  = dati[k++];    // piano
                int myPiano = Integer.parseInt(mySPiano);
                
                String mySDb     = dati[k++];    // decidel
                float myDb = Float.valueOf(mySDb);
                
                String mySTr	= dati[k++];    // tempo riverbero
                float myTr = Float.valueOf(mySTr);
                
                String mySX1 = dati[k++];       
 
                int myX1 = Integer.parseInt(mySX1);
         
                String mySY1 = dati[k++];
                int  myY1 = Integer.parseInt(mySY1);
                
                String mySX2 = dati[k++];
   
                int myX2 = Integer.parseInt(mySX2);
                String mySY2 = dati[k++];    
    
                int myY2 = Integer.parseInt(mySY2);
                
                String mySX3 = dati[k++]; 
     
                int myX3 = Integer.parseInt(mySX3);             
                String mySY3 = dati[k++]; 
  
                int myY3 = Integer.parseInt(mySY3);
                
                String mySX4 = dati[k++]; 
       
                int myX4 = Integer.parseInt(mySX4);
                String mySY4 = dati[k++]; 
        
                int myY4 = Integer.parseInt(mySY4);
              
              if  (myPiano == piano) 
               {
                    Aula a1 = new Aula(myNome, myPiano, myDb, myTr, myX1, myY1, myX2, myY2, myX3, myY3, myX4, myY4 );
                    this.aggiungi(a1);
                    i++;                 
               }
            }
           this.log();
           System.out.println("lette correttamente " + i + " aule ok");
            
           
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    System.out.println("esco da leggiDaFile() piano="+piano);
    System.out.println("-------------------------");
  }
}