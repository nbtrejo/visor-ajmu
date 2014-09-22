/*
 * Driver.java
 * 
 * Recibe las peticiones del formulario y ejecuta la transformacion del log XML 
 * Utiliza Desktop API de Java SE  para visualizar el doc html generado
 * Funciona con Java SE 6 or later.
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class AJMUHandler {
	public static void performStat(String queryFileName ) throws TransformerConfigurationException, 
											TransformerException, TransformerFactoryConfigurationError, IOException {
		//(TransformerFactory.newInstance().newTransformer(new StreamSource(new File("usabilityRecord.xsl")))).transform(
		//		new StreamSource(new File("usabilityrecord.xml")), new StreamResult(new File("usabilityRecord.html")));
		
		//tomar los datos del formulario y armar un xsl que importe el xsl de consulta
		
		(TransformerFactory.newInstance().newTransformer(new StreamSource(new File(queryFileName+".xsl")))).transform(
				new StreamSource(new File("usabilityrecord.xml")), new StreamResult(new File(queryFileName+".html")));
		 
	}
	
	
	public static List<String> parseXML(String fileName)  {
        List<String> taskList = new ArrayList<String>();
        String aTask = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        taskList.add("ALL");
            XMLStreamReader xmlStreamReader;
			try {
				xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(fileName));
			
            int event = xmlStreamReader.getEventType();
            while(true){
                switch(event) {
                case XMLStreamConstants.START_ELEMENT:
                    if(xmlStreamReader.getLocalName().equals("taskName")){
                        
                    	aTask = new String();
                        aTask =  xmlStreamReader.getAttributeValue(1);
                    /*}else if(xmlStreamReader.getLocalName().equals("name")){
                        bName=true;
                    }else if(xmlStreamReader.getLocalName().equals("age")){
                        bAge=true;
                    }else if(xmlStreamReader.getLocalName().equals("role")){
                        bRole=true;
                    }else if(xmlStreamReader.getLocalName().equals("gender")){
                        bGender=true;*/
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    /*if(bName){
                        emp.setName(xmlStreamReader.getText());
                        bName=false;
                    }else if(bAge){
                        emp.setAge(Integer.parseInt(xmlStreamReader.getText()));
                        bAge=false;
                    }else if(bGender){
                        emp.setGender(xmlStreamReader.getText());
                        bGender=false;
                    }else if(bRole){
                        emp.setRole(xmlStreamReader.getText());
                        bRole=false;
                    }*/
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if(xmlStreamReader.getLocalName().equals("taskName")){
                        taskList.add(aTask);
                    }
                    break;
                }
                if (!xmlStreamReader.hasNext())
                    break;
 
              event = xmlStreamReader.next();
            }
             
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println( e.toString());
			} catch (XMLStreamException e) {
				// TODO Auto-generated catch block
				System.out.println( e.toString());
			}
			
        return taskList;
	}
	
	public static List<String> getAppAttributes(String fileName)  {
        List<String> appAttrib = new ArrayList<String>();
        String aAttribute = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        
            XMLStreamReader xmlStreamReader;
			try {
				xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(fileName));
			
            int event = xmlStreamReader.getEventType();
            while(true){
                switch(event) {
                case XMLStreamConstants.START_ELEMENT:
                    if(xmlStreamReader.getLocalName().equals("usabilityRecord")){
                        
                    	aAttribute =  xmlStreamReader.getAttributeValue(0);
                    	appAttrib.add(aAttribute);
                    	appAttrib.add(xmlStreamReader.getAttributeValue(1));
                   
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if(xmlStreamReader.getLocalName().equals("usabilityRecord")){
                        break;
                    	//taskList.add(aTask);
                    }
                    break;
                }
                if (!xmlStreamReader.hasNext())
                    break;
 
              event = xmlStreamReader.next();
            }
             
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println( e.toString());
			} catch (XMLStreamException e) {
				// TODO Auto-generated catch block
				System.out.println( e.toString());
			}
			
        return appAttrib;
	}
}
