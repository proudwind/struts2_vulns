package cn.seaii.s2001.test;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.InputSource;

import javax.xml.parsers.SAXParserFactory;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

public class XxeTest {
    public static void main(String[] args) throws IOException, SAXException {
        String xml = readXml();

        //XMLReader xmlReader = XMLReaderFactory.createXMLReader();
//        xmlReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
//        xmlReader.setFeature("http://xml.org/sax/features/external-general-entities", false);
//        xmlReader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        //xmlReader.parse( new InputSource(new StringReader(xml)) ); //无回显

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(xml);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);  // parse xml

            // 遍历xml节点name和value
//            StringBuffer buf = new StringBuffer();
//            NodeList rootNodeList = document.getChildNodes();
//            for (int i = 0; i < rootNodeList.getLength(); i++) {
//                Node rootNode = rootNodeList.item(i);
//                NodeList child = rootNode.getChildNodes();
//                for (int j = 0; j < child.getLength(); j++) {
//                    Node node = child.item(j);
//                    buf.append( node.getNodeName() + ": " + node.getTextContent() + "\n" );
//                }
//            }
//            sr.close();
            System.out.println(document);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("error");
        }
    }

    public static String readXml() throws IOException {
        String filename = "/Users/yuhaichao/Downloads/test.xml";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

        StringBuilder fileContent = new StringBuilder();
        String line;
        while((line = bufferedReader.readLine()) != null) {
            fileContent.append(line);
        }
        return fileContent.toString();
    }
}
