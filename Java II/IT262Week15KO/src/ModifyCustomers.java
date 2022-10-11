
// Import libraries
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * ModifyCustomers - Modify an XML document and create an updated file
 * @author Kenneth Otero
 *
 */
public class ModifyCustomers {

	/**
	 * main - modify customers.xml to add new fields
	 * @param args - not used
	 */
	public static void main(String[] args) {
		try {
			UpdateXMLFile();
		} catch(Exception e) {
			// Print error message
			e.printStackTrace();
		}

	}
	
	/**
	 * ReadXMLFile - reads customer.xml
	 */
	public static void UpdateXMLFile() {
		try {
			// Define the input file
			String filePath = "customers.xml";
			File xmlFile = new File(filePath);
			
			// Create a new instance of the DocumentBuilderFactory
			DocumentBuilderFactory Factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder Builder;
	        
	        // Create a new DocumentBuilder
        	Builder = Factory.newDocumentBuilder();
        	
        	// Parse the XML and get the document elements
        	Document doc = Builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            // Add new fields: Customer Phone, Customer Contact Name,
            // Customer Email Address
            addPhone(doc);
            addContactName(doc);
            addEmail(doc);
            
            // Write the updated XML to a file
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            
            // Create the updated XML file
            StreamResult result = new StreamResult(new File("customers_modified.xml"));
            
            // Print line by line and have indenting
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                        
            // Write data
            transformer.transform(source, result);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * addPhone - add a phone element
	 * @param doc - document
	 */
	private static void addPhone(Document doc) {
        NodeList customers = doc.getElementsByTagName("Customer");
        Element cst = null;
        
        // Loop for each customer
        for(int i=0; i < customers.getLength(); i++){
        	cst = (Element) customers.item(i);
        	
        	// Create new element
            Element phoneElement = doc.createElement("CustomerPhone");
            
            // Depending on customer id, add phone number
            if (cst.getAttribute("id").matches("1")) {
            	phoneElement.appendChild(doc.createTextNode("513-111-1111"));
            } else if (cst.getAttribute("id").matches("2")) {
            	phoneElement.appendChild(doc.createTextNode("513-222-2222"));
            } else {
            	phoneElement.appendChild(doc.createTextNode("513-333-3333"));
            }
            
            cst.appendChild(phoneElement);
        }
    }
	
	/**
	 * addContactName - add a contact name element
	 * @param doc - document
	 */
	private static void addContactName(Document doc) {
        NodeList customers = doc.getElementsByTagName("Customer");
        Element cst = null;
        
        // Loop for each customer
        for(int i=0; i < customers.getLength(); i++){
        	cst = (Element) customers.item(i);
        	
        	// Create new element
            Element contactElement = doc.createElement("ContactName");
            
            // Depending on customer id, add contact name
            if (cst.getAttribute("id").matches("1")) {
            	contactElement.appendChild(doc.createTextNode("Tom Henderson"));
            } else if (cst.getAttribute("id").matches("2")) {
            	contactElement.appendChild(doc.createTextNode("Bobby Kotick"));
            } else {
            	contactElement.appendChild(doc.createTextNode("Jaqub Ajmal"));
            }
            
            // Add element
            cst.appendChild(contactElement);
        }
    }
	
	/**
	 * addEmail - add an email element
	 * @param doc - document
	 */
	private static void addEmail(Document doc) {
        NodeList customers = doc.getElementsByTagName("Customer");
        Element cst = null;
        
        // Loop for each customer
        for(int i=0; i < customers.getLength(); i++){
        	cst = (Element) customers.item(i);
        	
        	// Create new element
            Element emailElement = doc.createElement("EmailAddress");
            
            // Depending on customer id, add contact name
            if (cst.getAttribute("id").matches("1")) {
            	emailElement.appendChild(doc.createTextNode("acme@gmail.com"));
            } else if (cst.getAttribute("id").matches("2")) {
            	emailElement.appendChild(doc.createTextNode("ajnewton99@gmail.com"));
            } else {
            	emailElement.appendChild(doc.createTextNode("fosterburgers@gmail.com"));
            }
            
            // Add element
            cst.appendChild(emailElement);
        }
    }

}
