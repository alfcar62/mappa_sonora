/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Main
 */
class MainMappa {
    public static void main(String[] args) 
       {
         int piano = 0;  // si parte con il piano terra
         MainFrame f = new MainFrame(piano); 
         f.setPiano(piano); 
         f.setTitle("Mappa Sonora");
         f.setSize(1600,1600);
         f.setVisible(true);
       }
} 