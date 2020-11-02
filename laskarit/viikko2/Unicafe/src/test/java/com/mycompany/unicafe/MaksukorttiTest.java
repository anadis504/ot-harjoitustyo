package com.mycompany.unicafe;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
//    Harjoitellaan assertThat
    @Test
    public void alussaSaldoOikein() {
        assertThat(kortti.saldo(), is(equalTo(10)));
    }
    
    @Test
    public void rahanLataaminenKasvattaSaldoaOikein() {
        kortti.lataaRahaa(27);
        assertThat(kortti.toString(), is(equalTo("saldo: 0.37")));
    }
    
    @Test
    public void otosToimiiKunRahaTarpeeksi() {
        boolean ok = kortti.otaRahaa(8);
        assertThat(kortti.toString(), is("saldo: 0.2"));
        assertThat(ok, is(true));
    }
    
    @Test
    public void eiOtaKunRahaEiTarpeeksi() {
        boolean ok = kortti.otaRahaa(20);
        assertThat(kortti.saldo(), is(10));
        assertThat(ok, is(not(true)));
    }
    
}
