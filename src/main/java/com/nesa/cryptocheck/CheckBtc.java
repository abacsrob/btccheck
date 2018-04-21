package com.nesa.cryptocheck;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nesa.nbsupit.ReadHtmlDom;

public class CheckBtc {

	private static Map<String, CryptoCurrencyData> getCurrencyData() {
        Map<String, CryptoCurrencyData> r = new HashMap<>();
		try {
            URL url = new URL("https://coinmarketcap.com");
            URLConnection connection = url.openConnection();
            HttpsURLConnection httpConn = (HttpsURLConnection) connection;
            InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String responseString = null;
            while ((responseString = in.readLine()) != null) {
                sb.append(responseString);
            }
            ReadHtmlDom dom = new ReadHtmlDom(sb.toString());
            NodeList trNodes = dom.XpathsReader("//table[@id='currencies']//tr");
//            BigDecimal percentageAcc = BigDecimal.ONE.divide(new BigDecimal(trNodes.getLength() - 1), 10, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP);
//            BigDecimal percentageActual = BigDecimal.ZERO;
            for (int j = 0; j < trNodes.getLength() - 1; j++) {
                NodeList tdNodes = dom.XpathsReader("//table[@id='currencies']//tr[" + (j + 1) + "]//td");
                CryptoCurrencyData bdata = new CryptoCurrencyData();
                for (int i = 0; i < tdNodes.getLength(); i++) {
                	Node node = dom.XpathReader("//table[@id='currencies']//tr[" + (j + 1) + "]//td[" + (i + 1) + "]");
                	String content = node.getFirstChild().getTextContent();
                	try {
                	switch (i) {
                	case 0:
                		break;
                	case 1:
                		bdata.setCurrencyCode(node.getChildNodes().item(1).getChildNodes().item(0).getTextContent());
                		bdata.setCurrencyName(node.getChildNodes().item(2).getTextContent());
                		break;
                	case 2:
                		bdata.setMarketCap(new BigDecimal(content));
                		break;
                	case 3:
                		bdata.setUsdPrice(new BigDecimal(content.replaceAll("[^\\d.]", "")));
                		break;
                	case 4:
                		bdata.setVolume24h(new BigDecimal(content.substring(1).replaceAll(",", "")));
                		break;
                	case 5:
                		bdata.setCirculatingSupply(new BigDecimal(content.substring(0, content.length() - bdata.getCurrencyCode().length()).replaceAll(",", "")));
                		break;
                	case 6:
                		bdata.setPricePercentChange24h(new BigDecimal(content.substring(0, content.length() - 1)));
                	}
                	} catch (Exception x) {
                		bdata = null;
                	}
                }
                if (bdata != null) {
                	r.put(bdata.getCurrencyCode(), bdata);
//                	percentageActual = percentageActual.add(percentageAcc);
//            		System.out.println("Parse complete: " + percentageActual + "%");
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return r;
	}
	public static void main(String[] args) {
		Map <String, CryptoCurrencyData> cryptos = getCurrencyData();
		
		//check out BTC data
		CryptoCurrencyData btcData = cryptos.get("BTC");
		System.out.println(btcData);
	}

}
