/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nesa.nbsupit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author NEsa
 */
public class TestNbs {

    public static List<KursnaLista> kursnaListaNBS() {
        String responseString = "";
        String outputString = "";
        List<KursnaLista> kursnaLista = new ArrayList<>();
        String wsURL = "http://www.nbs.rs/kursnaListaModul/zaDevize.faces?listtype=html&lang=lat";
        try {
            URL url = new URL(wsURL);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) connection;
            InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            while ((responseString = in.readLine()) != null) {
                outputString = outputString + responseString + "\n";
            }
            ReadHtmlDom r = new ReadHtmlDom(outputString);
            NodeList lista = r.XpathsReader("//tbody[@id='index:spisakDeviz:tbody_element']//tr");
            for (int i = 1; i <= lista.getLength(); i++) {
                Node nodeValuta = r.XpathReader("//form[@id='index']//tbody[@id='index:spisakDeviz:tbody_element']//tr[" + i + "]//td[3]");
                Node nodeVaziZa = r.XpathReader("//form[@id='index']//tbody[@id='index:spisakDeviz:tbody_element']//tr[" + i + "]//td[4]");
                Node nodeKupovniKurs = r.XpathReader("//form[@id='index']//tbody[@id='index:spisakDeviz:tbody_element']//tr[" + i + "]//td[5]");
                Node nodeProdajniKurs = r.XpathReader("//form[@id='index']//tbody[@id='index:spisakDeviz:tbody_element']//tr[" + i + "]//td[6]");
                String valuta = nodeValuta.getTextContent();
                BigDecimal bdVaziZa = new BigDecimal(nodeVaziZa.getTextContent());
                BigDecimal bdKupovniKurs = new BigDecimal(nodeKupovniKurs.getTextContent());
                BigDecimal bdProdajniKurs = new BigDecimal(nodeProdajniKurs.getTextContent());
                BigDecimal bdSrednjiKurs = bdKupovniKurs.add(bdProdajniKurs).divide(new BigDecimal("2"), 2, RoundingMode.HALF_UP);

                kursnaLista.add(new KursnaLista(valuta, bdKupovniKurs, bdProdajniKurs, bdSrednjiKurs, bdVaziZa));
            }
        } catch (IOException ex) {
            Logger.getLogger(TestNbs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kursnaLista;
    }

    public static void main(String[] args) {
        for (KursnaLista kursnaLista : kursnaListaNBS()) {
            System.out.println("Valuta: " + kursnaLista.getValuta()
                    + " Srednji kurs: " + kursnaLista.getSrednjiKurs());
        }
    }
}
