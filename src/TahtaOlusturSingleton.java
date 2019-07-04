
public class TahtaOlusturSingleton {
    public TahtaSingleton cerceve;

    public TahtaOlusturSingleton() {
        cerceve = TahtaSingleton.createOne();   //singleton tasarım deseni
        cerceve.setBounds(450, 150, 300, 500);
        cerceve.setVisible(true);
    }

}
