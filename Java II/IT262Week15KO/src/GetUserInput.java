
// Import libraries
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * GetUserInput - Interact with console user input
 * @author Kenneth Otero
 * Date: December 17, 2021
 *
 */
public class GetUserInput {

	/**
	 * main - Get user input
	 * @param args - not used
	 */
	public static void main(String[] args) {
		try {
			// Create input variables
			int intSelection = 0;
			
			// Get input
			intSelection = GetSelection();
			
			// View customers or modify XML file
			if (intSelection == 1) {
				// View the customers.xml file in the console
				ViewCustomers.main(args);
			} else if (intSelection == 2) {
				// Modify the customers.xml file into a new file
				ModifyCustomers.main(args);
				System.out.println("File 'customers_modified.xml' has been created.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Program ended.");
		}
 
	}

	/**
	 * GetSelection - Gather user input
	 * @return intSelection - 1 or 2
	 */
	public static int GetSelection() {
		// Create input variables
		int intSelection = 0;
		try {
			// Loop until good input
			do {
				// Ask to view customers or create modified customers file
				System.out.print("Enter '1' to view customers. Enter '2' to create a modified customers file: ");
				intSelection = ReadIntegerFromUser();
				
				// Validate input
				if (intSelection != 1 || intSelection != 2) {
					continue;
				}

			} while(intSelection == 0);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// Return selection
		return intSelection;
	}
	
	/**
	 * ReadIntegerFromUser - reads user input for integers.
	 * @return intValue - user int
	 */
	public static int ReadIntegerFromUser() {

		// Declare Variables
		int intValue = 0;

		try {
			String strBuffer = "";

			// Input stream
			BufferedReader burInput = new BufferedReader(new InputStreamReader(System.in));

			// Read a line from the user
			strBuffer = burInput.readLine();
			
			// Assign integer
			intValue = Integer.parseInt(strBuffer);

		} catch (Exception excError) {
			// If input is not a integer then display error
			System.out.println("Please enter '1' or '2'.");
			intValue = 0;
		}

		// Return integer 
		return intValue;
	}

}
