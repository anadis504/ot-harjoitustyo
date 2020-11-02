/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anadis
 */
public class KassapaateTest {

    Kassapaate kassapaate;

    public KassapaateTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void kassassaAlussaOikeinRahamaara() {
        assertThat(kassapaate.kassassaRahaa(), is(100000));
        assertThat(kassapaate.edullisiaLounaitaMyyty(), is(0));
        assertThat(kassapaate.maukkaitaLounaitaMyyty(), is(0));
    }

    @Test
    public void kateinenMaksuSyoEdullisestiToimiiOikeinKunRahaaLiika() {
        int palautus = kassapaate.syoEdullisesti(500);
        assertThat(palautus, is(260));
        assertThat(kassapaate.edullisiaLounaitaMyyty(), is(1));
        assertThat(kassapaate.kassassaRahaa(), is(100000 + 240));
    }

    @Test
    public void kateinenSyoMaukkaastiToimiKunRahaaLiika() {
        assertThat(kassapaate.syoMaukkaasti(500), is(100));
        assertThat(kassapaate.maukkaitaLounaitaMyyty(), is(1));
        assertThat(kassapaate.kassassaRahaa(), is(100000 + 400));
    }

    @Test
    public void kaiteinenSyoToimiKunRahaTasanEdullinen() {
        assertThat(kassapaate.syoEdullisesti(240), is(0));
        assertThat(kassapaate.edullisiaLounaitaMyyty(), is(1));
    }

    @Test
    public void kaiteinenSyoToimiKunRahaTasanMaukas() {
        assertThat(kassapaate.syoMaukkaasti(400), is(0));
        assertThat(kassapaate.maukkaitaLounaitaMyyty(), is(1));
    }

    @Test
    public void kateinenSyoEdullinenToimiiKunRahaaEiRiita() {
        assertThat(kassapaate.syoEdullisesti(30), is(30));
        assertThat(kassapaate.edullisiaLounaitaMyyty(), is(0));
        assertThat(kassapaate.kassassaRahaa(), is(100000));
    }

    @Test
    public void kateinenSyoMaukkaastiToimiiKunRahaaEiRiita() {
        assertThat(kassapaate.syoMaukkaasti(70), is(70));
        assertThat(kassapaate.maukkaitaLounaitaMyyty(), is(0));
        assertThat(kassapaate.kassassaRahaa(), is(100000));
    }

    @Test
    public void kortillaEdullinenMaksuToimiKunKortillaOnRahaaTarpeenksi() {
        Maksukortti kortti = new Maksukortti(240);
        assertThat(kassapaate.syoEdullisesti(kortti), is(true));
        assertThat(kassapaate.kassassaRahaa(), is(100000));
    }

    @Test
    public void kortillaMaukasMaksuToimiKunKortillaOnRahaaTarpeenksi() {
        Maksukortti kortti = new Maksukortti(400);
        assertThat(kassapaate.syoMaukkaasti(kortti), is(true));
        assertThat(kassapaate.kassassaRahaa(), is(100000));
    }

    @Test
    public void kortillaEdullinenMaksuToimiOikeinKunRahaaEiRiita() {
        Maksukortti kortti = new Maksukortti(20);
        assertThat(kassapaate.syoEdullisesti(kortti), is(false));
        assertThat(kassapaate.kassassaRahaa(), is(100000));
    }

    @Test
    public void kortillaMaukasMaksuToimiiOikeinKunRahaaEiRiita() {
        Maksukortti kortti = new Maksukortti(40);
        assertThat(kassapaate.syoMaukkaasti(kortti), is(false));
        assertThat(kassapaate.kassassaRahaa(), is(100000));
        assertThat(kassapaate.kassassaRahaa(), is(100000));
    }

    @Test
    public void kortinLataaminenOnnistuuOikein() {
        Maksukortti kortti = new Maksukortti(40);
        kassapaate.lataaRahaaKortille(kortti, 100);
        assertThat(kortti.saldo(), is(140));
        assertThat(kassapaate.kassassaRahaa(), is(100000 + 100));
    }

    @Test
    public void kortinLataaminenOikeinKunSummaNegatiivinen() {
        kassapaate.lataaRahaaKortille(new Maksukortti(20), -60);
        assertThat(kassapaate.kassassaRahaa(), is(100000));
    }
}
