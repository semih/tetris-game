
public abstract class Sekil {

    private String sekilAdi;
    protected Pozisyon[] pozisyonlar;

    public Sekil(String sekilAdi) {
        this.sekilAdi = sekilAdi;
    }

    public boolean doluMu(Pozisyon poz) {
        return doluMu(poz.getX(), poz.getY());
    }

    public boolean doluMu(int pozisyonX, int pozisyonY) {
        for (Pozisyon pozisyon : pozisyonlar) {
            if (pozisyonX == pozisyon.getX() && pozisyonY == pozisyon.getY()) {
                return true;
            }
        }
        return false;
    }

    public void solaKaydir() {

        for (Pozisyon pozisyon : pozisyonlar) {
            pozisyon.setX(pozisyon.getX() - 1);
        }

    }

    public void sagaKaydir() {

        for (Pozisyon pozisyon : pozisyonlar) {
            pozisyon.setX(pozisyon.getX() + 1);
        }

    }

    public boolean asagiIlerle() {

        for (Pozisyon pozisyon : pozisyonlar) {
            pozisyon.setY(pozisyon.getY() + 1);
        }

        return true;
    }

    public abstract void dondur();
    
    public abstract Pozisyon[] sonrakiDonusPozisyonlari();

    @Override
    public String toString() {
        String mesaj = sekilAdi + "( ";
        for (int i = 0; i < pozisyonlar.length; i++) {
            mesaj += "[" + pozisyonlar[i].x + "," + pozisyonlar[i].y + "]" + " ";
        }
        mesaj += ")";
        return mesaj;
    }

    public Pozisyon[] getPozisyonlar() {
        return pozisyonlar;
    }

    public void setPozisyonlar(Pozisyon[] pozisyonlar) {
        this.pozisyonlar = pozisyonlar;
    }

	public String getSekilAdi() {
		return sekilAdi;
	}
}
