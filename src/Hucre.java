
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Hucre extends JPanel{

	private static final long serialVersionUID = -1016630202924755910L;

		public Hucre(int Size, Color arkaRenk) {
            setPreferredSize(new Dimension(Size, Size));
            setBorder(LineBorder.createBlackLineBorder());
            setBackground(arkaRenk);
        }
}
