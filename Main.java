import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
// w  w  w  . j  ava2  s  .com
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

  public static void main(String[] args) {
    Color TRUE_COLOR = new Color(180, 200, 255);
    Color FALSE_COLOR = new Color(255, 100, 100);

    final MyBean panel = new MyBean();
    panel.setTitle(true);
    panel.setBackground(TRUE_COLOR);
    panel.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        panel.setTitle(!panel.getTitle());
      }
    });
    panel.addPropertyChangeListener(new PropertyChangeListener() {

      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(MyBean.TITLE_PROP_NAME)) {
          panel.setBackground(panel.getTitle() ? TRUE_COLOR : FALSE_COLOR);
        }
      }
    });
    JFrame frame = new JFrame();
    frame.getContentPane().add(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Size of the window 
    frame.setSize(400, 300);

    frame.setVisible(true);

  }

}

class MyBean extends JPanel {

  public final static String TITLE_PROP_NAME = "title";
  static final long serialVersionUID = 1L;
  boolean title;
  PropertyChangeSupport pcs = new PropertyChangeSupport(this);

  public boolean getTitle() {
    return this.title;
  }

  public void setTitle(boolean title) {
    boolean old = this.title;
    this.title = title;
    this.pcs.firePropertyChange(TITLE_PROP_NAME, old, title);
  }

  @Override
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    this.pcs.addPropertyChangeListener(listener);
  }

  @Override
  public void removePropertyChangeListener(PropertyChangeListener listener) {
    this.pcs.removePropertyChangeListener(listener);
  }
}