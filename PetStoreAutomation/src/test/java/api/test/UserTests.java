package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setUpData() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority = 1)
	public void PostUder() {
		Response res =UserEndPoints.createUser(userPayload);
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test(priority = 2)
	public void getUser() {
		Response res =UserEndPoints.readUser(this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void updateUser() {
		//Update user details
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		Response res =UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
		res.then().log().all();
		//check it is updated or not
		Response checkResponse =UserEndPoints.readUser(this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(checkResponse.getStatusCode(), 200);
	}
	
	@Test(priority = 4)
	public void deleteUser() {
		Response res =UserEndPoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(res.getStatusCode(), 200);
	}

}
