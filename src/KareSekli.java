
public class KareSekli extends Sekil{

    public KareSekli(){
    	super("KARE");
    	
    	pozisyonlar = new Pozisyon[4];
    	
    	int ortaPozisyon = (int) Math.ceil((Constants.SUTUN/2))-1;
    	/*
    	 	Kare Sekline baslangic pozisyonlari ataniyor.
    	 	
			[P0][P1]
			[P2][P3]
    	 */
    	pozisyonlar[0]= new Pozisyon(ortaPozisyon, -1);
    	pozisyonlar[1]= new Pozisyon(ortaPozisyon+1, -1);
    	pozisyonlar[2]= new Pozisyon(ortaPozisyon, 0);
    	pozisyonlar[3]= new Pozisyon(ortaPozisyon+1, 0);
    }

	@Override
	public void dondur() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pozisyon[] sonrakiDonusPozisyonlari() {
		return pozisyonlar.clone();
	}
}