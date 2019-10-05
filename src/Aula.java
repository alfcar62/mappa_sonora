import java.awt.Color;


/*
*** Aula: classe che rappresenta un'aula con i dati sonori e le 4 coordinate 
*/
public class Aula  {
	 private String nome;	// nome aula
	 private int piano;	// piano
	 private float db;	// decibel
	 private float tr;	// tempo di riverbero
         
         private int x1;
         private int y1;
         
         private int x2;
         private int y2;
         
         private int x3;
         private int y3;
         
         private int x4;
         private int y4;
        
/*
*** Costruttore della classe Aula
*/
public Aula(String nome, int piano,float db, float tr,int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4) 
{
 setNome(nome);
 setPiano(piano);
 setDb(db);
 setTr(tr);
 
 setX1(x1);
 setY1(y1);
 
 setX2(x2);
 setY2(y2);
 
 setX3(x3);
 setY3(y3);
 
 setX4(x4);
 setY4(y4);
} 

/*
*** Costruttore di copia della classe Aula
*/
public Aula(Aula a) 
{
 setNome(a.getNome());
 setPiano(a.getPiano());
 setDb(a.getDb());
 setTr(a.getTr());
 
 setX1(a.getX1());
 setY1(a.getY1());
 
 setX2(a.getX2());
 setY2(a.getY2());
 
 setX3(a.getX3());
 setY3(a.getY3());
 
 setX4(a.getX4());
 setY4(a.getY4());
} 
/*
*** metodi getter e setter
*/
public void setNome(String n)
{
 this.nome = n;
}

public String getNome()
{
 return this.nome;
}

public void setPiano(int p)
{
 this.piano = p;
}

public int getPiano()
{
 return this.piano;
}

public void setDb(float db)
{
 this.db = db;
}

public float getDb()
{
 return this.db;
}


public void setTr(float tr)
{
 this.tr = tr;
}

public float getTr()
{
 return this.tr;
}

public void setX1(int x1)
{
  this.x1 = x1;
}
public int getX1()
{
  return(this.x1);
}

public void setY1(int y1)
{
  this.y1 = y1;
}

public int getY1()
{
  return(this.y1);
}
public int getX2()
{
  return(this.x2);
}
public void setX2(int x2)
{
  this.x2 = x2;
}
public int getY2()
{
  return(this.y2);
}
public void setY2(int y2)
{
  this.y2 = y2;
}
public int getX3()
{
  return(this.x3);
}
public void setX3(int x3)
{
  this.x3 = x3;
}
public int getY3()
{
  return(this.y3);
}
public void setY3(int y3)
{
  this.y3 = y3;
}
public int getX4()
{
  return(this.x4);
}
public void setX4(int x4)
{
  this.x4 = x4;
}

public int getY4()
{
  return(this.y4);
}
public void setY4(int y4)
{
  this.y4 = y4;
}


/*
*** SiPresenta()
*/
public void SiPresenta()
 {
  System.out.println("---------------------------------------");
  System.out.println(this.toString());

  System.out.println("---------------------------------------");
 
 }

/*
*** toString()
*/
 public String toString()
 {
  String myStr;
  myStr = "|Aula:"+ getNome() + " |piano:" + getPiano() + " |decibel:" + getDb() + " |t.riverbero:" + getTr();
  myStr = myStr + "|coordinate: ";
  myStr = myStr + "x1:" + getX1() + " y1:" + getY1();
  myStr = myStr + "|x2:" + getX2() + " y2:" + getY2();
  myStr = myStr + "|x3:" + getX3() + " y3:" + getY3();
  myStr = myStr + "|x4:" + getX4() + " y4:" + getY4();
  return myStr;
 }
 
 /*
*** ritorna il colore associato al valore in decibel
*** db < 20-> verde
*** db > 25-> rosso
*** altrimenti giallo
*/
Color getColoreDb()    
    { 
        Color myColor;
    
      if (this.db < 20)
          myColor = Color.green;
      else if (this.db > 25) 
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
Color getColoreTr()     
    { 
        Color myColor;
   
        if (this.tr < 4)
          myColor = Color.green;
        else if (this.tr > 5) 
          myColor = Color.red;
        else myColor = Color.yellow;
        return myColor;
   } 
// end Aula
}