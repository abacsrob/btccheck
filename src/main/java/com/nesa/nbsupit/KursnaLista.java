/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nesa.nbsupit;

import java.math.BigDecimal;

/**
 *
 * @author NEsa
 */
public class KursnaLista {

    private String valuta;
    private BigDecimal kupovniKurs;
    private BigDecimal prodajniKurs;
    private BigDecimal srednjiKurs;
    private BigDecimal vaziZa;

    public KursnaLista(String valuta, BigDecimal kupovniKurs, BigDecimal prodajniKurs, BigDecimal srednjiKurs, BigDecimal vaziZa) {
        this.valuta = valuta;
        this.kupovniKurs = kupovniKurs;
        this.prodajniKurs = prodajniKurs;
        this.srednjiKurs = srednjiKurs;
        this.vaziZa = vaziZa;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public BigDecimal getKupovniKurs() {
        return kupovniKurs;
    }

    public void setKupovniKurs(BigDecimal kupovniKurs) {
        this.kupovniKurs = kupovniKurs;
    }

    public BigDecimal getProdajniKurs() {
        return prodajniKurs;
    }

    public void setProdajniKurs(BigDecimal prodajniKurs) {
        this.prodajniKurs = prodajniKurs;
    }

    public BigDecimal getSrednjiKurs() {
        return srednjiKurs;
    }

    public void setSrednjiKurs(BigDecimal srednjiKurs) {
        this.srednjiKurs = srednjiKurs;
    }

    public BigDecimal getVaziZa() {
        return vaziZa;
    }

    public void setVaziZa(BigDecimal vaziZa) {
        this.vaziZa = vaziZa;
    }

}
