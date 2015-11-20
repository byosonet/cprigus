/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cprigus.services.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class TestWebService {
    public static void main (String[] args) throws MalformedURLException, IOException{       
        String xml = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.dgie.banxico.org.mx\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "      <ws:tiposDeCambioBanxico soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"/>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";

    URL url = new URL("http://www.banxico.org.mx/DgieWSWeb/DgieWS");
    URLConnection  conn =  url.openConnection();
    conn.setDoOutput(true);
    conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
    conn.setRequestProperty("SOAPAction", "http://www.banxico.org.mx/DgieWSWeb/DgieWS");

    // Send the request XML
    OutputStream outputStream = conn.getOutputStream();
    outputStream.write(xml.getBytes());
    outputStream.close();

    // Read the response XML
    InputStream inputStream = conn.getInputStream();
    Scanner sc = new Scanner(inputStream, "UTF-8");
    StringBuilder temp = new StringBuilder();
    sc.useDelimiter("\\A");
    if (sc.hasNext()) 
        temp.append(sc.next());
 
    String temp2 = temp.toString();
    temp2 = temp2.replace("&lt;", "<");
    temp2 = temp2.replace("&gt;", ">");
    temp2 = temp2.replace("&quot;","\"");
    temp2 = temp2.replace("&#xF3;", "ó");
    temp2 = temp2.replace("&#xE9;", "é");
    
    System.out.println(" -- Xml Banxico: "+temp2);
    sc.close();
    inputStream.close();
    }
}
