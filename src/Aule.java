import java.util.Vector;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;



/**
*** Aule: classe che rappresenta le  aule di un determinato piano.
*** Viene implementata con un vettore di aule 
***@author: A. Carlone
***@version: 1.0
*/
public class Aule
{
    private Vector v_aule;
    private int piano;
  
    /**
    *** Costruttore: costruisce un vettore di aule del piano indicato
    *** @param piano piano da leggere 
    */
    public Aule(int piano)
     {
      System.out.println("----------------------");
      System.out.println("Entro in Aule()");
      v_aule = new Vector (1,1);
      System.out.println("size = " +v_aule.size());
      this.leggi(piano);
      System.out.println("size = " +v_aule.size());
      System.out.println("Esco da  Aule()");
      System.out.println("----------------------");
     }

    /**
    *** aggiunge un'aula nel vettore di aule 
    *** del piano indicato
    *** @param a aula
    */
    public void aggiungi(Aula a)
     {
         System.out.println("aggiungi() aula=" + a.getNome());
         v_aule.addElement(a);
     }
    
    /**
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
    
   /**
    *** Cancellazione di un elemento del vettore di aule v_aule
    *** verrà cancellato l'elemento in posizione i
    *** @param i posizione nel vettore dell'elemento da cancellare
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
    
    /**
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
   
   
public void leggi(int piano) {
 
 //   leggiDaFile(piano);
    leggiDaDB(piano);
}

/**  
*** Legge da file DatiAule.csv  
*** (presente sotto la cartella dati) i dati 
*** delle misure delle aule del piano e 
*** li carica su un vettore di oggetti di classe Aula
*** @param piano piano dell'aula da leggere
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

/**  
*** Legge da Database
*** i dati delle misure delle aule del piano e 
*** li carica su un vettore di oggetti di classe Aula
*** @param piano piano dell'aula da leggere
*/
public void leggiDaDB(int myPiano) {
        System.out.println("-------------------------");
        System.out.println("entro in leggiDaDB() piano="+myPiano);
        ResultSet rs ; 
        Connection con = null;
        int i = 0;
        
        String nomeaula="";
        int piano;
        int dB;
        int riverb; 
        int x1;
        int y1; 
        int x2;
        int y2; 
        int x3;
        int y3; 
        int x4;
        int y4; 
  
        /* mysql.itisavogadro.org/acarlone
        String dbName = "acarlone";
        String server = "mysql.itisavogadro.org";
        String driver = "jdbc:mysql://";
        String connStr = driver +  server + "/" + dbName;
        String userDB = "acarlone";
        String passDB = "Cambiami_2018";
      */
        //localhost
        String dbName = "mappa_avo";
        String server = "localhost";
        String driver = "jdbc:mysql://";
        String connStr = driver +  server + "/" + dbName;
        String userDB = "root";
        String passDB = "";
      
        try
        {
         Class.forName("com.mysql.jdbc.Driver");
         System.out.println("connessione a connStr=" + connStr);
         System.out.println("user=" + userDB);
         System.out.println("pass=" + passDB);
     
         con = DriverManager.getConnection(connStr, userDB, passDB); 
         System.out.println("connessione OK");
         Statement st = con.createStatement();

         rs = st.executeQuery("SELECT * FROM datiaule");
         do
          { 
          rs.next();
          nomeaula = rs.getString("nomeaula");
          piano= rs.getInt("piano");
          dB= rs.getInt("dB");
          riverb= rs.getInt("riverb");
          x1= rs.getInt("x1");
          y1= rs.getInt("y1");
          x2= rs.getInt("x2");
          y2= rs.getInt("y2");
          x3= rs.getInt("x3");
          y3= rs.getInt("y3");
          x4= rs.getInt("x4");
          y4= rs.getInt("y4");
         
          System.out.println("entro in leggiDaDB() nomeaula="+nomeaula);
          if  (myPiano == piano) 
           {
            Aula a1 = new Aula(nomeaula,
                               piano, 
                               dB, 
                               riverb, 
                               x1, y1, 
                               x2, y2, 
                               x3, y3, 
                               x4, y4 );
            this.aggiungi(a1);
            i++;                 
           }
      
         this.log();
         System.out.println("lette correttamente " + i + " aule ok");
        }
       while(!rs.isLast());
       con.close(); 
      }
      catch (Exception e)
       {
        e.printStackTrace();
       }
         
    System.out.println("esco da leggiDB() piano="+myPiano);
    System.out.println("-------------------------");
  }
}