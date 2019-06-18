/*
* Author: A. Carlone
 * Legge un file csv contenente i dati delle aule
 * i dati sono in formato csv
 * nome aula,  piano, decidel, tempo di riverbero
 */
//package aule;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
*** leggiAuleCsv: Legge l'elenco delle aule con i dati presenti su file csv e li carica su un vettore di oggetti di classe Aula
*/
public class leggiAuleCsv {

    public static void main(String[] args) {
        String csvFile = "D:\\PC_NEW\\Scuola\\generale\\LinguaggiProgrammazione\\java\\Java_CSV\\vector2\\DatiAule.csv";
   
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        line = br.readLine();   // skip della prima riga con le intestazioni

        Aule myAule = new Aule();
      
        int i=0;
        System.out.println("Elenco aule lette dal file "+ csvFile);
        System.out.println("");
        System.out.println("---------------------------------------");
        while ((line = br.readLine()) != null) {
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
                          
                Aula a1 = new Aula(myNome, myPiano, myDb, myTr, myX1, myY1, myX2, myY2, myX3, myY3, myX4, myY4 );
  
                myAule.aggiungi(a1);
                i++;
            }
            System.out.println("lette correttamente " + i + " aule");
            
            myAule.leggi();
            myAule.Disegna();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

 }