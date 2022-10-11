
// Import libraries
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * ViewCustomers - Read an XML input file
 * @author Kenneth Otero
 * Date: December 17, 2021
 *
 */
public class ViewCustomers {

	/**
	 * main - read an XML input file
	 * @param args - not used
	 */
	public static void main(String[] args) {
		try {
			// View the customers.xml file in the console
			System.out.println("\n" + "Here are the customers: ");
			System.out.println("-----------------------------------------------------------");
			PrintXMLFile();
			System.out.println("-----------------------------------------------------------");
		} catch(Exception e) {
			// Print error message
			e.printStackTrace();
		}

	}
	
	/**
	 * PrintXMLFile - prints the contents of customers.xml to the console
	 */
	public static void PrintXMLFile() {
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
	        
	        // Print formatted customers
	        System.out.println("");
	        PrintCustomers(doc);
		} catch(SAXException | ParserConfigurationException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * PrintCustomers - prints the contents from customers.xml
	 * @param doc - document
	 */
	public static void PrintCustomers(Document doc) {
		try {
			NodeList customers = doc.getElementsByTagName("Customer");
	        Element cst = null;
	        
	        // Display root element
	        Element root = doc.getDocumentElement();
	        System.out.println("Root element is : " + root.getNodeName() + "\n");
	        
	        // Loop for each customer
		    for(int i=0; i < customers.getLength(); i++){
		    	// Get XML info
		    	cst = (Element) customers.item(i);
		        String id = cst.getAttribute("id");
		        Node name = cst.getElementsByTagName("name").item(0).getFirstChild();
		        Node type = cst.getElementsByTagName("type").item(0).getFirstChild();
		        Node address = cst.getElementsByTagName("address").item(0).getFirstChild();
		        Node city = cst.getElementsByTagName("city").item(0).getFirstChild();
		        Node state = cst.getElementsByTagName("state").item(0).getFirstChild();
		        Node zipcode = cst.getElementsByTagName("zipcode").item(0).getFirstChild();
		        
		        // Print customer info
		        System.out.println("Customer ID " + id);
		        System.out.println("         Name:    " + name.getNodeValue());
		        System.out.println("         Type:    " + type.getNodeValue());
		        System.out.println("         Address: " + address.getNodeValue());
		        System.out.println("                  " + city.getNodeValue() + ", "
		        										+ state.getNodeValue() + " "
		        										+ zipcode.getNodeValue());
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
