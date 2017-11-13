package Util;

import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class GerenteDeJanelas {

    private static JDesktopPane jDesktopPane;

    public GerenteDeJanelas(JDesktopPane jDesktopPane) {
        GerenteDeJanelas.jDesktopPane = jDesktopPane;
    }

    public void abrirJanelas(JInternalFrame jInternalFrame) {
        if (jInternalFrame.isVisible()) {
            jInternalFrame.toFront();
            jInternalFrame.requestFocus();
            
        } else {
            jDesktopPane.add(jInternalFrame);
            jInternalFrame.setVisible(true);
            //Inicia JInternalFrame Centralizado
            Dimension desktopSize = jDesktopPane.getSize();
            Dimension jInternalFrameSize = jInternalFrame.getSize();
            jInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
        }
    }

}
