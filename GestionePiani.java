


/*
*** GestionePiani: classe che implementa l'interfaccia WindowListener
*/

import java.awt.event.*;

public  class GestionePiani implements WindowListener
   {
    public void windowIconified(WindowEvent e)  {} 
    public void windowDeiconified(WindowEvent e)  {} 
    public void windowActivated(WindowEvent e)  {} 
    public void windowDeactivated(WindowEvent e)  {}
    public void windowOpened(WindowEvent e)  {}
    public void windowClosed(WindowEvent e)  {}
    public void windowClosing(WindowEvent e)  
    {
        System.out.println("Programma terminato");
        System.exit(0);
    } 
  }