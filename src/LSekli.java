
public class LSekli extends Sekil {

	private int donmeDurumu = 0;
	
    public LSekli(){
        super("L");
        
        pozisyonlar = new Pozisyon[4];
        
    	/*
		 	L Sekline baslangic pozisyonlari ataniyor.
		 	
			[P0]
			[P1]
			[P2][P3]
		 */
        int ortaPozisyon = (int) Math.ceil((Constants.SUTUN/2))-1;
        pozisyonlar[0]= new Pozisyon(ortaPozisyon, -2);
    	pozisyonlar[1]= new Pozisyon(ortaPozisyon, -1);
    	pozisyonlar[2]= new Pozisyon(ortaPozisyon, 0);
    	pozisyonlar[3]= new Pozisyon(ortaPozisyon+1, 0);
    }

    
    public Pozisyon[] sonrakiDonusPozisyonlari(){
    	
    	Pozisyon[] kopya = pozisyonlariKopyala(pozisyonlar);
    	
    	switch (donmeDurumu) {
			case 0:
				kopya[0].setX(kopya[0].getX()-2);
				kopya[0].setY(kopya[0].getY()+2);
				kopya[1].setX(kopya[1].getX()-1);
				kopya[1].setY(kopya[1].getY()+1);
				kopya[3].setX(kopya[3].getX()-1);
				kopya[3].setY(kopya[3].getY()-1);
				break;
			case 1:
				kopya[0].setX(kopya[0].getX()+2);
				kopya[0].setY(kopya[0].getY()+2);
				kopya[1].setX(kopya[1].getX()+1);
				kopya[1].setY(kopya[1].getY()+1);
				kopya[3].setX(kopya[3].getX()-1);
				kopya[3].setY(kopya[3].getY()+1);
				break;
			case 2:
				kopya[0].setX(kopya[0].getX()+2);
				kopya[0].setY(kopya[0].getY()-2);
				kopya[1].setX(kopya[1].getX()+1);
				kopya[1].setY(kopya[1].getY()-1);
				kopya[3].setX(kopya[3].getX()+1);
				kopya[3].setY(kopya[3].getY()+1);
				break;
			case 3:
				kopya[0].setX(kopya[0].getX()-2);
				kopya[0].setY(kopya[0].getY()-2);
				kopya[1].setX(kopya[1].getX()-1);
				kopya[1].setY(kopya[1].getY()-1);
				kopya[3].setX(kopya[3].getX()+1);
				kopya[3].setY(kopya[3].getY()-1);
				
				break;
			default:
				break;
    	}
    	
    	return kopya;
    	
    }
    
	private Pozisyon[] pozisyonlariKopyala(Pozisyon[] pozisyonlar) {
		
		Pozisyon [] kopyaPozisyonlar = new Pozisyon[4];
		int i=0;
		for (Pozisyon pozisyon : pozisyonlar) {
			kopyaPozisyonlar[i] = new Pozisyon(pozisyon.getX(), pozisyon.getY());
			i++;
		}
		return kopyaPozisyonlar;
	}


	@Override
	public void dondur() {
		
		pozisyonlar = sonrakiDonusPozisyonlari();
		
		donmeDurumu=(donmeDurumu+1)%4;
	}
}
