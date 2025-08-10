package api.steps;

import api.requests.UserApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

public class ApiSteps {

    private UserApi userApi = new UserApi();
    private Response response;

    @Given("I send a request to get user by id {string}")
    public void iSendRequestToGetUserById(String userId) {
        System.out.println("[STEP] Sending GET request for user ID: " + userId);
        response = userApi.getUserById(userId);

        System.out.println("[TRACK] Endpoint: /user/" + userId);
        System.out.println("[TRACK] Response Status: " + response.getStatusCode());
        System.out.println("[TRACK] Response Body: " + response.getBody().asString());
    }

    @Given("I create a new user with firstName {string}, lastName {string}, and email {string}")
    public void iCreateANewUser(String firstName, String lastName, String email) {
        System.out.println("[STEP] Creating a new user...");
        System.out.println("[TRACK] Input -> First Name: " + firstName + ", Last Name: " + lastName + ", Email: " + email);

        if (email == null || email.isEmpty() || email.equalsIgnoreCase("unique")) {
            email = firstName.toLowerCase() + "." + lastName.toLowerCase() + System.currentTimeMillis() + "@example.com";
            System.out.println("[TRACK] Generated unique email: " + email);
        }

        response = userApi.createUser(firstName, lastName, email);

        System.out.println("[TRACK] Endpoint: /user/create");
        System.out.println("[TRACK] Response Status: " + response.getStatusCode());
        System.out.println("[TRACK] Response Body: " + response.getBody().asString());
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        System.out.println("[STEP] Verifying response status code...");
        int actualStatusCode = response.getStatusCode();

        System.out.println("[TRACK] Expected Status: " + expectedStatusCode);
        System.out.println("[TRACK] Actual Status: " + actualStatusCode);

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        System.out.println("[RESULT] ✅ Status code validation PASSED");
    }
}
