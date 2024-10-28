package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUsers(String userId, String userName, String fname, String lname, String email, String password, String ph) {
//		  if (userId == null || userId.isEmpty()) {
//		        System.out.println("Invalid userId, skipping test for this data set.");
//		        return; // Skip this test iteration if userId is invalid
//		    }
//		
		User userPayload = new User();
		
		int validUserId = userId.isEmpty() ? 999 : Integer.parseInt(userId);  // Use default 999 if userId is empty
		userPayload.setId(validUserId);
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(ph);
		
		Response res =UserEndPoints.createUser(userPayload);
		Assert.assertEquals(res.getStatusCode(), 200);
		
		
	}
		
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteByUserName(String userName) {
		Response res =UserEndPoints.deleteUser(userName);
		Assert.assertEquals(res.getStatusCode(), 200);
	}

}
