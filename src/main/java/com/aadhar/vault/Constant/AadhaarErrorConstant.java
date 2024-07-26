package com.aadhar.vault.Constant;

import java.util.Map;

public class AadhaarErrorConstant {

	public static void populateErrorMessages(Map<String, String> errorMessages) {
		// TODO Auto-generated method stub
		  // ASA-Auth Errors
        errorMessages.put("V#01", "Invalid Vault Request XML");
        errorMessages.put("V#02", "Error in DB");
        errorMessages.put("V#03", "XSD Schema File Not Found");
        errorMessages.put("V#04", "Null response received from crypto service");
        errorMessages.put("V#05", "Crypto service issue");
        errorMessages.put("V#06", "Reference number not found");
        errorMessages.put("V#07", "UID not found");
        errorMessages.put("V#08", "Unable to Parse the XML");
        errorMessages.put("V#09", "UID not activated");
        errorMessages.put("V#10", "Mismatch of operation");
        errorMessages.put("V#11", "Invalid date");
        errorMessages.put("V#12", "Reference number or Aadhaar Number is null");
        errorMessages.put("V#13", "Problem in generation of hash");
        errorMessages.put("V#14", "Flag to generate type of reference id is not set in the DB");
        errorMessages.put("V#15", "No DB schema exist for access code assigned");
        errorMessages.put("V#16", "Invalid UID value as per Verhoff check");
        errorMessages.put("V#17", "Response parsing error for the response received from crypto");
        errorMessages.put("V#18", "Issue in vault service");
        errorMessages.put("V#19", "Unable to parse the data");
        errorMessages.put("V#20", "Aadhaar exist and not activated");
        errorMessages.put("V#21", "Aadhaar already stored");
        errorMessages.put("V#22", "Value of AC and SA is not matching");
        errorMessages.put("V#23", "Duplicate transaction ID");
        errorMessages.put("V#24", "Data integrity violation");
        
        // C# Errors
        errorMessages.put("C#01", "LK not found");
        errorMessages.put("C#02", "Error in DB");
        errorMessages.put("C#03", "Digital signing error");
        errorMessages.put("C#04", "Decryption error");
        errorMessages.put("C#05", "XSD Schema File Not Found");
        errorMessages.put("C#06", "Invalid request XML");
        errorMessages.put("C#07", "Encryption error");
        errorMessages.put("C#08", "Unable to Parse the XML");
        errorMessages.put("C#09", "LK expired");
        errorMessages.put("C#10", "Key expired");
        errorMessages.put("C#11", "Invalid LK");
        errorMessages.put("C#12", "Key Identifier not found");
        errorMessages.put("C#13", "Operation not available");
        errorMessages.put("C#14", "Issue in Crypto Service");
        errorMessages.put("C#15", "Mismatch of operation");
        errorMessages.put("C#16", "Empty data for encryption or decryption");
        errorMessages.put("C#17", "DB schema not found");
        errorMessages.put("C#18", "Bulk operation not allowed");
        errorMessages.put("C#19", "LK value is not provided");
        errorMessages.put("C#20", "Invalid date value of the LK");
        errorMessages.put("C#21", "Error in parsing the response to be sent");
        errorMessages.put("C#22", "Operation not allowed");
	}

}
