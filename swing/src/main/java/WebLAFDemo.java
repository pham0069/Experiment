import com.alee.extended.panel.GroupPanel;
import com.alee.extended.panel.GroupingType;
import com.alee.extended.window.TestFrame;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.toolbar.WhiteSpace;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.util.Vector;

public class WebLAFDemo {
    public static void main ( final String[] args ) {
        SwingUtilities.invokeLater ( new Runnable ()
        {
            public void run ()
            {
                // Initialize L&F here, before creating any UI
                WebLookAndFeel.install();

                final JTextArea textArea = new JTextArea ( "Simple text area" );
                final Vector<String> model = new Vector<String>();
                model.add("Fox");
                model.add("Wolf");
                model.add("Skunk");
                final JComboBox comboBox = new JComboBox(model);
                final JScrollPane scrollPane = new JScrollPane ( textArea );
                scrollPane.setPreferredSize ( new Dimension( 300, 150 ) );
                scrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
                scrollPane.setHorizontalScrollBarPolicy ( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );

                final JProgressBar progressBar = new JProgressBar ();
                progressBar.setIndeterminate ( true );

                final JButton ok = new JButton ( "Ok" );
                final JButton cancel = new JButton ( "Cancel" );

                TestFrame.show ( new GroupPanel( GroupingType.fillFirst, 5, false, comboBox, progressBar,
                        new GroupPanel ( GroupingType.fillFirst, 5, new WhiteSpace(), ok, cancel ) ), 5 );
            }
        } );
    }
}
