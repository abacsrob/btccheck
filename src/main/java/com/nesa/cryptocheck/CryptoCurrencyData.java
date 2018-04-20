package com.nesa.cryptocheck;

import java.math.BigDecimal;

public class CryptoCurrencyData {

	private String currencyCode;
	private String currencyName;
	private BigDecimal marketcap;
	private BigDecimal usdPrice;
	private BigDecimal volume24h;
	private BigDecimal circulatingSupply;
	private BigDecimal pricePercentChange24h;

	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public BigDecimal getMarketcap() {
		return marketcap;
	}
	public void setMarketcap(BigDecimal marketcap) {
		this.marketcap = marketcap;
	}
	public BigDecimal getUsdPrice() {
		return usdPrice;
	}
	public void setUsdPrice(BigDecimal usdPrice) {
		this.usdPrice = usdPrice;
	}
	public BigDecimal getVolume24h() {
		return volume24h;
	}
	public void setVolume24h(BigDecimal volume24h) {
		this.volume24h = volume24h;
	}
	public BigDecimal getCirculatingSupply() {
		return circulatingSupply;
	}
	public void setCirculatingSupply(BigDecimal circulatingSupply) {
		this.circulatingSupply = circulatingSupply;
	}
	public BigDecimal getPricePercentChange24h() {
		return pricePercentChange24h;
	}
	public void setPricePercentChange24h(BigDecimal pricePercentChange24h) {
		this.pricePercentChange24h = pricePercentChange24h;
	}
	@Override
	public String toString() {
		return "CryptoCurrencyData [currencyCode=" + currencyCode + ", currencyName=" + currencyName + ", marketcap="
				+ marketcap + ", usdPrice=" + usdPrice + ", volume24h=" + volume24h + ", circulatingSupply="
				+ circulatingSupply + ", pricePercentChange24h=" + pricePercentChange24h + "]";
	}

}
