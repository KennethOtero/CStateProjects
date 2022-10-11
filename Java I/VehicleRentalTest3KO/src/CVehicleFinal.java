
// Import libraries
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

/**
 * CVehicleFinal - Vehicle rental program.
 * @author Kenneth Otero
 * Date: April 26, 2021
 *
 */
public class CVehicleFinal {
	
	// Define the Connection
	private static Connection m_conAdministrator;

	/**
	 * main method - Vehicle rental program.
	 * @param args - not used.
	 */
	public static void main(String[] args) {
		// Declare variables
		String strName = "";
		String strPhoneNumber = "";
		String strEmail = "";
		String strQuit = "";
		String astrVehicles[] = new String[3];
		int intDays = 0;
		int intVehicles = 0;
		int intIndex = 0;
		
		try {
			// Formatting
			System.out.println("Vehicle Rental.");
			System.out.println("Type 'QUIT' at any time to exit!");
			System.out.println("--------------------------------");
			
			// Can we connect to the database?
			if ( OpenDatabaseConnectionSQLServer( ) == true ) {
				// Display pickup locations
				LoadListFromDatabase( "TLocations", "intLocationID" , "strName", "strAddress", "strCity", "strZip");
				System.out.println("");
			} else {
				// No, warn the user ...
				System.out.println("Error loading the table");
			}
			
			// Ask for name
			do {
				System.out.print("Enter your name: ");
				strQuit = ReadInputFromUser();
				if (strQuit.toUpperCase().matches("QUIT")) {
					// Exit the method
					return;
				} else {
					// Assign name
					strName = strQuit;
					break;
				}
			} while (strName == "");
			
			// Ask for phone number
			do {
				System.out.print("Enter phone number in either format: '##########' or '###-###-####': ");
				strQuit = ReadInputFromUser();
				
				// Check for quit
				if (strQuit.toUpperCase().matches("QUIT")) {
					// Exit the method
					return;
				}
				
				// Assign phone number
				strPhoneNumber = strQuit;
			} while (IsValidPhoneNumber(strQuit) == false || strPhoneNumber == "");
			
			// Ask for email address
			do {
				System.out.print("Enter email address: ");
				strQuit = ReadInputFromUser();
				
				// Check for quit
				if (strQuit.toUpperCase().matches("QUIT")) {
					// Exit the method
					return;
				}
				
				// Assign email
				strEmail = strQuit;
			} while (IsValidEmailAddress(strQuit) == false || strEmail == "");
			
			// Ask for number of rental days
			do {
				System.out.print("Enter number of rental days: ");
				strQuit = ReadInputFromUser();
				
				// Check for quit
				if (strQuit.toUpperCase().matches("QUIT")) {
					// Exit the method
					return;
				} else {
					intDays = 0;
				}
				
				// Assign rental days
				try {
					intDays = Integer.parseInt(strQuit);
				} catch (Exception e) {
					intDays = 0;
				}
				
			} while (IsInteger(strQuit) == false || intDays <= 0); 
			
			// Ask for number of vehicles to rent
			do {
				System.out.print("Enter number of vehicles to rent: ");
				strQuit = ReadInputFromUser();
				
				// Check for quit
				if (strQuit.toUpperCase().matches("QUIT")) {
					// Exit the method
					return;
				}
				
				// Assign number of vehicles
				try {
					intVehicles = Integer.parseInt(strQuit);
				} catch (Exception e) {
					intVehicles = 0;
				}
				
			} while (IsInteger(strQuit) == false || IsValidVehicleNumber(intVehicles) == false);
			
			// Ask for the type of vehicle
			System.out.print("Enter the type of vehicle: 'Car', 'Motorbike', or 'Trailer'.");
			System.out.println("");
			
			// Loop through all vehicles
			for (intIndex = 0; intIndex < intVehicles; intIndex ++) {
				System.out.print("Vehicle #" + (intIndex + 1) + ": ");
				strQuit = ReadInputFromUser();
				if (strQuit.toUpperCase().matches("QUIT")) {
					return;
				} else if (strQuit.toUpperCase().matches("CAR")
						   || strQuit.toUpperCase().matches("MOTORBIKE") 
						   || strQuit.toUpperCase().matches("TRAILER")) {
					// Assign vehicle
					astrVehicles[intIndex] = strQuit; 
				} else {
					intIndex -= 1;
				}
			}
			
			// Padding
			System.out.println("");
			
			// Display vehicle information
			VehicleInfo(astrVehicles);
			
			// Calculate total rental price for the vehicle(s)
			VehiclePrice(astrVehicles, intDays);
			
		} catch (Exception excError) {
			System.out.println(excError);
		} finally {
			// Close database connection
			CloseDatabaseConnection();
			
			// Formatting
			System.out.println("");
			System.out.println("Program Ended.");
			System.out.println("--------------------------------");
		}
	}
	
	/**
	 * VehiclePrice - calculates rent price
	 * @param astrValues - vehicle types
	 * @param intDays - # of rental days
	 * @return sngTotalRent - total rent
	 */
	public static float VehiclePrice(String astrValues[], int intDays) {
		// Declare variables
		float asngTotal[] = new float[3];
		int intIndex = 0;
		float sngTotalRent = 0;
		
		// Create Car
		CCar clsCar = new CCar(); 
		clsCar.SetWheels(4);
		clsCar.SetMPG(40);
		
		// Create Motorbike
		CMotorbike clsBike = new CMotorbike();
		clsBike.SetWheels(2);
		clsBike.SetMPG(20);
		
		// Create Trailer
		CTrailer clsTrailer = new CTrailer();
		clsTrailer.SetWheels(4);
		clsTrailer.SetMPG(0);
		
		// Padding
		//System.out.println("");
		
		// Loop through vehicles
		for (intIndex = 0; intIndex < astrValues.length; intIndex ++) {
			if (astrValues[intIndex].toUpperCase().matches("CAR")) {
				asngTotal[intIndex] = clsCar.GetPrice() * intDays; 
				System.out.printf("Car rent price: %.2f \n", asngTotal[intIndex]);
			} else if (astrValues[intIndex].toUpperCase().matches("MOTORBIKE")) {
				asngTotal[intIndex] = clsBike.GetPrice() * intDays;
				System.out.printf("Motorbike rent price: %.2f \n", asngTotal[intIndex]);
			} else if (astrValues[intIndex].toUpperCase().matches("TRAILER")) {
				asngTotal[intIndex] = clsTrailer.GetPrice() * intDays;
				System.out.printf("Trailer rent price: %.2f \n", asngTotal[intIndex]);
			}
		}
		
		// Calculate total rent
		for (intIndex = 0; intIndex < asngTotal.length; intIndex ++) {
			sngTotalRent += asngTotal[intIndex];
		}
		
		// Display total rent price
		System.out.printf("Total for renting all vehicles: %.2f \n", sngTotalRent);
		
		// Return total price for renting all vehicles
		return sngTotalRent;
		
	}
	
	/**
	 * VehicleInfo - displays vehicle information
	 * @param astrValues - vehicle array
	 */
	public static void VehicleInfo(String astrValues[]) {
		// Declare variables
		int intIndex = 0;
		
		// Create Car
		CCar clsCar = new CCar(); 
		clsCar.SetWheels(4);
		clsCar.SetMPG(40);
		
		// Create Motorbike
		CMotorbike clsBike = new CMotorbike();
		clsBike.SetWheels(2);
		clsBike.SetMPG(20);
		
		// Create Trailer
		CTrailer clsTrailer = new CTrailer();
		clsTrailer.SetWheels(4);
		clsTrailer.SetMPG(0);
		
		// Loop through array to display vehicle info
		for (intIndex = 0; intIndex < astrValues.length; intIndex ++) {
			if (astrValues[intIndex].toUpperCase().matches("CAR")) {
				System.out.println("How the car drives: ");
				clsCar.GetHowToDrive();
				System.out.println("Car's # of wheels: " + clsCar.GetWheels());
				System.out.println("Car's MPG: " + clsCar.GetMPG());
				System.out.println("Daily rent price: " + clsCar.GetPrice());
				System.out.println("");
			} else if (astrValues[intIndex].toUpperCase().matches("MOTORBIKE")) {
				System.out.println("How the motorbike drives: ");
				clsBike.GetHowToDrive();
				System.out.println("Bike's # of wheels: " + clsBike.GetWheels());
				System.out.println("Bike's MPG: " + clsBike.GetMPG());
				System.out.println("Daily rent price: " + clsBike.GetPrice());
				System.out.println("");
			} else if (astrValues[intIndex].toUpperCase().matches("TRAILER")) {
				System.out.println("How the trailer drives: ");
				clsTrailer.GetHowToDrive();
				System.out.println("Trailer's # of wheels: " + clsTrailer.GetWheels());
				System.out.println("Trailer's MPG: " + clsTrailer.GetMPG());
				System.out.println("Daily rent price: " + clsTrailer.GetPrice());
				System.out.println("");
			}
		}
	}
	
	/**
	 * IsValidVehicleNumber - Check if vehicle # is within 1-3
	 * @param intCount - Vehicle count
	 * @return blnResult - boolean result
	 */
	public static boolean IsValidVehicleNumber(int intCount) {
		// Declare variables
		boolean blnResult = false;
		
		// Boundary check
		if (intCount <= 0 || intCount > 3) {
			blnResult = false;
		} else {
			blnResult = true;
		}
		
		// Return result
		return blnResult;
	}
	
	/**
	 * Method: IsInteger- checks if string is an integer
	 * @param strResponse - User input
	 * String to check
	 * @return blnNumeric - boolean result
	 */
	public static boolean IsInteger(String strResponse) {
		boolean blnNumeric = true;
		
		try
		{
			Integer.parseInt(strResponse);
		}
		catch( NumberFormatException e )
		{
			blnNumeric = false;
		}
		
		return blnNumeric;
	}
	
	/**
	 * Method: IsValidPhoneNumber - Check if phone number entered is in correct format
	 * @param strPhoneNumber - Phone number
	 * Phone number entered by user
	 * @return blnIsValidPhoneNumber - boolean result
	 */
	public static boolean IsValidPhoneNumber(String strPhoneNumber) {
		boolean blnIsValidPhoneNumber = false;
		
		try
		{
			// Declare variables
			String strStart = "^";
			String strStop = "$";
			String strDash = "\\-";
			String strPattern1 = "";
			String strPattern2 = "";
			
			// String patterns
			// ###-###-####
			strPattern1 = strStart + "\\d{3}" + strDash + "\\d{3}" + strDash + "\\d{4}" + strStop;
			// ##########
			strPattern2 = strStart + "\\d{10}" + strStop;
			
			// Does it match any of the formats?
			if( strPhoneNumber.matches( strPattern1 ) == true || 
				strPhoneNumber.matches( strPattern2 ) == true )
			{
				// Yes
				blnIsValidPhoneNumber = true;
			}
		}
		catch( Exception excError )
		{
			// Display Error Message
			System.out.println( excError );
		}
		// Return result
		return blnIsValidPhoneNumber;
	}
	
	/**
	 * Method: IsValidEmailAddress - Check if email entered is valid
	 * @param strEmailAddress - Email entered by user
	 * @return blnIsValidEmailAddress - boolean result
	 */
	public static boolean IsValidEmailAddress(String strEmailAddress) {
		boolean blnIsValidEmailAddress = false;
		
		try
		{
			// Declare variables
			String strStart = "^";
			String strStop = "$";
			String strPattern = "";
			
			// Set string pattern
			strPattern = strStart + "[a-zA-Z][a-zA-Z0-9\\.\\-]*" + "@" + "[a-zA-Z][a-zA-Z0-9\\.\\-]*\\.[a-zA-Z]{2,6}" + strStop;
			
			// Does it match?
			if( strEmailAddress.matches( strPattern ) == true )
			{
				// Yes
				blnIsValidEmailAddress = true;
			}
			
		}
		catch( Exception excError )
		{
			// Display Error Message
			System.out.println( excError );
		}
		
		return blnIsValidEmailAddress;
	}
	
	/**
	 * ReadIntegerFromUser - reads user input for integers.
	 * @return intValue - integer value
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

			// Convert from string to integer
			intValue = Integer.parseInt(strBuffer);

		} catch (Exception excError) {
			// If input is not an integer then display error
			System.out.println("Please enter numbers only.");
			intValue = -1;
			//System.exit(1);
		}

		// Return integer age value
		return intValue;
	}
	
	/**
	 * ReadInputFromUser - reads string input from the user
	 * @return strBuffer - string value
	 */
	public static String ReadInputFromUser() {
		// Declare variables
		String strBuffer = "";

		try {

			// Input stream
			BufferedReader burInput = new BufferedReader(new InputStreamReader(System.in));

			// Read a line from the user
			strBuffer = burInput.readLine();

		} catch (Exception excError) {
			System.out.println(excError.toString());
		}

		// Return string
		return strBuffer;
	}
	
	/**
	 * OpenDatabaseConnectionSQLServer - get SQL db connection
	 * @return blnResult - boolean result
	 */
	public static boolean OpenDatabaseConnectionSQLServer() {
		boolean blnResult = false;

		try {
			SQLServerDataSource sdsTeamsAndPlayers = new SQLServerDataSource();
			sdsTeamsAndPlayers.setServerName("localhost"); // localhost or IP or server name
			// sdsTeamsAndPlayers.setServerName( "localhost\\SQLExpress" ); // SQL Express
			// version
			sdsTeamsAndPlayers.setPortNumber(1433);
			sdsTeamsAndPlayers.setDatabaseName("dbVehicleRental");

			// Login Type:
			// SQL Server
			sdsTeamsAndPlayers.setUser("sa");
			sdsTeamsAndPlayers.setPassword("password"); // Empty string "" for blank password

			// Open a connection to the database
			m_conAdministrator = sdsTeamsAndPlayers.getConnection();

			// Success
			blnResult = true;
		} catch (Exception excError) {
			// Display Error Message
			System.out.println("Cannot connect - error: " + excError);

			// Warn about SQL Server JDBC Drivers
			System.out.println("Make sure download MS SQL Server JDBC Drivers");
		}

		return blnResult;
	}
	
	/**
	 * LoadListFromDatabase - Load from dbVehicleRental
	 * @param strTable - TLocations
	 * @param strPrimaryKeyColumn - intLocationID
	 * @param strNameColumn - strName
	 * @param strAddress - strAddress
	 * @param strCity - strCity
	 * @param strZip - strZip
	 * @return blnResult - boolean result
	 */
	public static boolean LoadListFromDatabase(String strTable, String strPrimaryKeyColumn, String strNameColumn, 
											   String strAddress, String strCity, String strZip) {

		// set flag to false
		boolean blnResult = false;

		try {
			String strSelect = "";
			Statement sqlCommand = null;
			ResultSet rstTSource = null;
			int intID = 0;
			String strName = "";
			String strAddressColumn = "";
			String strCityColumn = "";
			String strZipColumn = "";

			// Build the SQL string
			strSelect = "SELECT " + strPrimaryKeyColumn + ", " 
						+ strNameColumn + ", " 
						+ strAddress + ", "
						+ strCity + ", "
						+ strZip
						+ " FROM " + strTable 
						+ " ORDER BY "
						+ strPrimaryKeyColumn;

			// Retrieve the all the records
			sqlCommand = m_conAdministrator.createStatement();
			rstTSource = sqlCommand.executeQuery(strSelect);
			// Loop through all the records
			while (rstTSource.next() == true) {
				// Get ID and Name from current row
				intID = rstTSource.getInt(1);
				strName = rstTSource.getString(2);
				strAddressColumn = rstTSource.getString(3);
				strCityColumn = rstTSource.getString(4);
				strZipColumn = rstTSource.getString(5);
				// Print the list
				System.out.printf("ID: %-5s Name: %-13s Address: %-13s City: %-10s Zip: %-13s \n", 
								  intID, strName, strAddressColumn, strCityColumn, strZipColumn);
			}
			// Clean up
			rstTSource.close();
			sqlCommand.close();
			// Success
			blnResult = true;
		} catch (Exception e) {
			System.out.println("Error loading table");
			System.out.println("Error is " + e);
		}

		return blnResult;
	}

	/**
	 * Name: CloseDatabaseConnection Abstract: Close the connection to the database
	 * @return blnResult - boolean result
	 */
	public static boolean CloseDatabaseConnection() {
		boolean blnResult = false;

		try {
			// Is there a connection object?
			if (m_conAdministrator != null) {
				// Yes, close the connection if not closed already
				if (m_conAdministrator.isClosed() == false) {
					m_conAdministrator.close();

					// Prevent JVM from crashing
					m_conAdministrator = null;
				}
			}
			// Success
			blnResult = true;
		} catch (Exception excError) {
			// Display Error Message
			System.out.println(excError);
		}

		return blnResult;
	}

}
