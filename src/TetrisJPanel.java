
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class TetrisJPanel extends JPanel implements KeyListener {

    private static final long serialVersionUID = -938611169731485159L;
    public Sekil mevcutSekil;
    public List<Sekil> oynananSekiller = new ArrayList<Sekil>();
    public FactorySekil sekilOlustur;
    public Hucre hucre[][];
    public int sayac = 0;
    private int pozisyon = 0;
    private TahtaSingleton tahtaSingletonRef;

    private final Timer timer = new Timer();
    
    public TetrisJPanel(Color arkaRenk, TahtaSingleton tahtaSingleton) {
        super(new GridLayout(Constants.SATIR, Constants.SUTUN));
        tahtaSingletonRef = tahtaSingleton;
        setPreferredSize(new Dimension(Constants.BOYUT * Constants.SUTUN,
                Constants.BOYUT * Constants.SATIR));

        sekilOlustur = new FactorySekil();

        tabloHucreleriniOlustur();
    }

    private boolean hucreDoluMu(int gelecekPozisyonY, int gelecekPozisyonX) {
        if (gelecekPozisyonY < 0 || gelecekPozisyonY >= Constants.SATIR
                || gelecekPozisyonX >= Constants.SUTUN || gelecekPozisyonX < 0) {
            return true;
        }

        if (!mevcutSekil.doluMu(gelecekPozisyonX, gelecekPozisyonY)) {
            // Hucre Dolu mu
            if (hucre[gelecekPozisyonY][gelecekPozisyonX].getBackground().equals(Constants.SEKIL_RENK)) {
                return true;
            }
        }
        return false;
    }

    private void tabloHucreleriniOlustur() {
        hucre = new Hucre[Constants.SATIR][Constants.SUTUN];

        for (int i = 0; i < Constants.SATIR; i++) {
            for (int j = 0; j < Constants.SUTUN; j++) {
                hucre[i][j] = new Hucre(Constants.BOYUT, Constants.ARKA_RENK);
                add(hucre[i][j]);
            }
        }

        mevcutSekil = sekilOlustur.yeniSekil();

        oyunAlaniniCiz();
    }

    public void oyunuZamanla(final TetrisJPanel tetrisJPanel, final TahtaSingleton tahtaSingletonRef2) {
        int period = 1000; // repeat every sec.
        int delay = 1000;
        
        final TimerTask sekilOlusturucu = new TimerTask() {

            public void run() {

            	ilerlet(timer);
                
            }
        };
        timer.scheduleAtFixedRate(sekilOlusturucu, delay, period);
    }

    private boolean ilerlet(Timer timer){
    	boolean ilerleyebilir = ilerlemeyiDenetle();

        if (ilerleyebilir) {
        	sekliSifirla(mevcutSekil.getPozisyonlar());
            mevcutSekil.asagiIlerle();
            oyunAlaniniCiz();
        } else if (oyunBittiMi()) {
        	timer.cancel();
            timer.purge();
            System.exit(0);
        } else {
            oyunAlaniniCiz();
            doluSatirlariKontrolEt();
            
            oynananSekiller.add(mevcutSekil);
            mevcutSekil = sekilOlustur.yeniSekil();
            
            oyunAlaniniCiz();
        }
        return true;
    }
    
    /* ------------------------------------------------------------------------------------------ */
    /* HAREKET DENETLEMELERI */
    /* ------------------------------------------------------------------------------------------ */
    // TODO: Uygulamasini yaz
    private boolean oyunBittiMi() {
        return false;
    }

    /*
     * mevcut sekil bir alta ilerleyebilirmi kontrolu yapilir
     *
     * ilerleyebilirse: true doner
     * ilerleyemezse: false doner
     *
     * Ornegin sekil tabana ulasmissa veya baska bir dolu hucreye denk gelmisse "false" doner
     */
    protected boolean ilerlemeyiDenetle() {
        return birSonrakiAdimiKontrolEt(mevcutSekil.getPozisyonlar(), 0, 1);
    }

    private boolean sagaKaydirmayiDenetle() {
        return birSonrakiAdimiKontrolEt(mevcutSekil.getPozisyonlar(),1, 0);
    }

    private boolean solaKaydirmayiDenetle() {
        return birSonrakiAdimiKontrolEt(mevcutSekil.getPozisyonlar(),-1, 0);
    }

    // TODO: Uygulamasini yaz
    private boolean dondurmeyiDenetle() {
    	
    	if(mevcutSekil.getSekilAdi().equals("L")) {
    		Pozisyon[] sonrakiDonusPozisyonlari = mevcutSekil.sonrakiDonusPozisyonlari();
    		return birSonrakiAdimiKontrolEt(sonrakiDonusPozisyonlari, 0, 0);
    	} else {
    		return true;
    	}
    	
    }

    // TODO: Uygulamasini yaz
    private void doluSatirlariKontrolEt() {

        boolean tumSatirDolu = true;
         for (int i = 0; i < Constants.SATIR; i++) {
            
            // Tum satiri kontrol et
            for (int j = 0; j < Constants.SUTUN; j++) {
                if( !hucre[i][j].getBackground().equals(Constants.SEKIL_RENK) ) {
                    tumSatirDolu=false;
                    break;
                }
            }

            // Tum satiri temizle
            if(tumSatirDolu){

            	int silinenSatirIndeksi = i;
            	//dolu olan tüm satýrý sil.
                for (int k = 0; k < Constants.SUTUN; k++) {
                	
                	for (int m=silinenSatirIndeksi; m>0; m--) {
                		hucre[m][k].setBackground( hucre[m-1][k].getBackground() );
					}
                    
                }
            }
            tumSatirDolu=true;
        }
    }

    /* ------------------------------------------------------------------------------------------ */
    /* CIZIM METHODLARI */
    /* ------------------------------------------------------------------------------------------ */
    protected void oyunAlaniniCiz() {

        System.out.println("START oyunAlaniniCiz() >>>>>>>>>>>>>>>>>>>>");

        // Hucreleri sifirla
        /*
        for (int i = 0; i < Constants.SATIR; i++) {
            for (int j = 0; j < Constants.SUTUN; j++) {
                hucre[i][j].setBackground(Constants.ARKA_RENK);
            }
        }
        */

        // Mevcut Sekli Ciz
        sekliCiz(mevcutSekil.getPozisyonlar());

        // Oynanan Sekilleri Ciz
        /*
        for (Sekil oynanan : oynananSekiller) {
            sekliCiz(oynanan.getPozisyonlar());
        }
        */

        System.out.println("END oyunAlaniniCiz() <<<<<<<<<<<<<<<<<<<<");
    }

    private void sekliCiz(Pozisyon[] pozisyonlar) {
        for (Pozisyon pozisyon : pozisyonlar) {
            if (pozisyon.getY() < 0) {
                continue;
            }
            hucre[pozisyon.getY()][pozisyon.getX()].setBackground(Constants.SEKIL_RENK);
        }
    }
    
    private void sekliSifirla(Pozisyon[] pozisyonlar) {
        for (Pozisyon pozisyon : pozisyonlar) {
            if (pozisyon.getY() < 0) {
                continue;
            }

            hucre[pozisyon.getY()][pozisyon.getX()].setBackground(Constants.ARKA_RENK);
        }
    }

    /* ------------------------------------------------------------------------------------------ */
    /* TUS KONTROLLERI */
    /* ------------------------------------------------------------------------------------------ */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (solaKaydirmayiDenetle()) {
                	sekliSifirla(mevcutSekil.getPozisyonlar());
                    mevcutSekil.solaKaydir();
                }
                oyunAlaniniCiz();
                break;
            case KeyEvent.VK_RIGHT:
                if (sagaKaydirmayiDenetle()) {
                	sekliSifirla(mevcutSekil.getPozisyonlar());
                    mevcutSekil.sagaKaydir();
                }
                oyunAlaniniCiz();
                break;
            case KeyEvent.VK_UP:
                if (dondurmeyiDenetle()) {
                	sekliSifirla(mevcutSekil.getPozisyonlar());
                    mevcutSekil.dondur();
                }
                oyunAlaniniCiz();
                break;

            case KeyEvent.VK_DOWN:
                if (ilerlemeyiDenetle()) {
                	ilerlet(timer);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private boolean birSonrakiAdimiKontrolEt(Pozisyon [] pozisyonlar, int x_ilerlemesi, int y_ilerlemesi) {
        System.out.println("START birSonrakiAdimiKontrolEt() >>>>>>>>>>>>>>>>>>>>");

        // Mevcut seklin bir adim sonraki pozisyonlari icin kontrol et
        for (Pozisyon p : pozisyonlar) {

            int pozisyonX = p.getX();
            int pozisyonY = p.getY();

            // oyun alaninda gorunmeyen pozisyonlari kontrol etme
            if (pozisyonY + y_ilerlemesi < 0) {
                continue;
            }

            boolean dolu = hucreDoluMu(pozisyonY + y_ilerlemesi, pozisyonX + x_ilerlemesi);

            if (dolu) {
                return false;
            }
        }

        System.out.println("END birSonrakiAdimiKontrolEt() <<<<<<<<<<<<<<<<<<<<");

        return true;
    }
}
