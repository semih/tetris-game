import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class TahtaSingleton extends JFrame{

	private static final long serialVersionUID = 639174715954823393L;
	
	private static TahtaSingleton ornek = null;

    private TahtaSingleton()
    {
        super("Tetris -Semih KIRDİNLİ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        TetrisJPanel tetrisPanel;
        tetrisPanel = new TetrisJPanel(Color.PINK, this);
        
        // pack();
        setResizable(false);
        setDefaultLookAndFeelDecorated(true);
        
        setLayout(new BorderLayout());
        add(tetrisPanel, BorderLayout.CENTER);
        addKeyListener(tetrisPanel);
        setVisible(true);
        tetrisPanel.repaint();
        tetrisPanel.oyunuZamanla(tetrisPanel, this);
    }

    public static TahtaSingleton createOne () {
        if (ornek == null) {
            synchronized (TahtaSingleton.class) {
                if (ornek == null) {
                    ornek = new TahtaSingleton();
                }
            }
        }
        return ornek;
    }
}
