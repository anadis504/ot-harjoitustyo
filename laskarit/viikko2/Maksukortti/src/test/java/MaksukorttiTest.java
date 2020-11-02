
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }

    @Test
    public void syoEdullisestiVahentaaSaldoaOikein() {
        kortti.syoEdullisesti();
        assertEquals("Kortilla on rahaa 7.5 euroa", kortti.toString());
    }

    @Test
    public void syoMaukkaastiVahentaaSaldoaOikein() {
        kortti.syoMaukkaasti();
        assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
    }

    @Test
    public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        kortti.syoEdullisesti();
        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }

    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(25);
        assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
    }

    @Test
    public void kortinSaldoEiYlitaMaksimiarvoa() {
        kortti.lataaRahaa(200);
        assertEquals("Kortilla on rahaa 150.0 euroa", kortti.toString());
    }

//    harjoitellaan assertThat
    @Test
    public void syoMaukkaanEiVieSaldoNegatiiviseksi() {
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        assertThat(kortti.toString(), is("Kortilla on rahaa 2.0 euroa"));
    }

    @Test
    public void negatiivisenSummanLataaminenEiMuutaSaldoa() {
        kortti.lataaRahaa(-6);
        assertThat(kortti.toString(), is("Kortilla on rahaa 10.0 euroa"));
    }

    @Test
    public void pystyyOstaEdullisenKunRahaaTasan() {
        Maksukortti kortti2 = new Maksukortti(2.5);
        kortti2.syoEdullisesti();
        assertThat(kortti2.toString(), is("Kortilla on rahaa 0.0 euroa"));
    }
    
    @Test
    public void pystyyOstaMaukkaanKunRahaaTasan() {
        Maksukortti kortti2 = new Maksukortti(4);
        kortti2.syoMaukkaasti();
        assertThat(kortti2.toString(), is("Kortilla on rahaa 0.0 euroa"));
    }
}
