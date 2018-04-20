package com.nesa.nbsupit;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import org.htmlcleaner.CleanerProperties;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadHtmlDom {

    private String content;

    public ReadHtmlDom(String content) {
        this.content = content;
    }

    public NodeList XpathsReader(String xpaths) {
        NodeList nodes = null;
        try {
            TagNode tagNode = new HtmlCleaner().clean(content);
            org.w3c.dom.Document doc = new org.htmlcleaner.DomSerializer(new CleanerProperties()).createDOM(tagNode);
            XPath xpath = XPathFactory.newInstance().newXPath();
            nodes = (NodeList) xpath.evaluate(xpaths, doc, XPathConstants.NODESET);
        } catch (ParserConfigurationException | XPathExpressionException e) {
            e.printStackTrace();
        }
        return nodes;
    }

    public Node XpathReader(String xpaths) {
        Node node = null;
        try {
            TagNode tagNode = new HtmlCleaner().clean(content);
            org.w3c.dom.Document doc = new org.htmlcleaner.DomSerializer(new CleanerProperties()).createDOM(tagNode);
            XPath xpath = XPathFactory.newInstance().newXPath();
            node = (Node) xpath.evaluate(xpaths, doc, XPathConstants.NODE);
        } catch (ParserConfigurationException | XPathExpressionException e) {
            e.printStackTrace();
        }
        return node;
    }
}
