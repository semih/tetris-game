
public class FactorySekil {

//    Sekiller sekiller;
//    enum Sekiller
//    {
//        KARE,
//        LE
//    }

    Sekil sekil;
    
    public Sekil yeniSekil()
    {
        int gelenSekil = (int)(Math.random() * 2);

        switch(gelenSekil)
        {
            case 0:
                sekil=new KareSekli();
                break;
            case 1:
                sekil=new LSekli();
                break;
            default:
                sekil=new KareSekli();
                break;
        }

        return sekil;
    }
}
