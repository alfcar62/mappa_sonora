import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class MainFrame extends JFrame implements ActionListener
{
   private int piano = 0;
   private JPanel pan;
   private AulePanel aulep;
   private JComboBox cbPiani;
   private JButton btnCambiaPiano;
   
   public MainFrame(int piano)
    {
     super("scegli Piano");
     pan = new JPanel();
     aulep = new AulePanel(piano);
     cbPiani = new JComboBox();
     btnCambiaPiano = new JButton("Scegli");
     
     this.setPiano(piano);
     System.out.println("entro in MainFrame()");
     addWindowListener(new GestionePiani());
     initCombo();
     aulep.setBackground(Color.WHITE);
     pan.add(new Label("Piano: "));
     pan.add(cbPiani);
     pan.add(btnCambiaPiano);
     add(pan,"North");
     add(aulep,"Center");
     btnCambiaPiano.addActionListener(this);
     System.out.println("esco da MainFrame()");
    }      

public int getPiano()
  {  
   return (piano);   
  }

public void setPiano(int p)
  {  
   this.piano= p; 
   System.out.println("setto piano="+ Integer.valueOf(p));
  }

private void initCombo()
 {
   System.out.println("Entro in initCombo() ");
   cbPiani.addItem("piano terra");
   cbPiani.addItem("primo piano");
   cbPiani.addItem("secondo piano");
   cbPiani.addItem("terzo piano");
   cbPiani.addItem("quarto piano");
   
   cbPiani.setSelectedIndex(this.getPiano());
   System.out.println("Esco da initCombo() ");
 }
 
/*
*** gestione della scelta del piano da visualizzare
*/
   public void actionPerformed(ActionEvent e)
 {
    System.out.println("Entro in actionPerformed() ");
    String pulsante = e.getActionCommand();
    if (pulsante.equals("Scegli"))
      {
        int piano= cbPiani.getSelectedIndex();
        setPiano(piano);
        System.out.println("Scelto nuovo piano:"+piano);
        aulep.ripristina(piano);
        aulep.revalidate();
        aulep.repaint();
  //      this.revalidate();
   //     this.repaint();
       }
    System.out.println("esco da MainFrame()");
    //
 }
   
}