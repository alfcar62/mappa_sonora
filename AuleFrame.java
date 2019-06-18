import java.awt.*;
import javax.swing.*;

public class AuleFrame extends JFrame
{
    private Aule aule = new Aule();

    public AuleFrame()
      {
       this.setBackground(Color.white);
       this.setForeground(Color.white);
       this.getContentPane().add(aule);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      }

}
