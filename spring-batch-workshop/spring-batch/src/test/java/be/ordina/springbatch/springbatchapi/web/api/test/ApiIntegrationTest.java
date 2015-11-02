package be.ordina.springbatch.springbatchapi.web.api.test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.net.URI;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { TestApplication.class })
@WebAppConfiguration
@IntegrationTest({"server.port:0"})
@DirtiesContext
public class ApiIntegrationTest {
	
	@Value("${local.server.port}")
	private int port;
	
	@Before
	public void setup() {
		RestAssured.port = port;
	}
	
	@Test
	public void jobListIsOk() throws Exception {
		given()
			.basePath("/api/batch")
		.when()
			.get("/jobs")
		.then()
			.statusCode(200)
			.content("[0].name", is("sampleJob"));
	}
	
	@Test
	public void oneJobOk() throws Exception {
		RestAssured.post(new URI("/api/batch/jobs/sampleJob"));
		given()
			.basePath("/api/batch")
		.when()
			.get("/jobs/sampleJob")
		.then()
			.statusCode(200)
			.content("[0].executions[0].status", is("COMPLETED"));
	}
	
	@Test
	public void startOneJobOk() throws Exception {
		given()
			.basePath("/api/batch")
		.when()
			.post("/jobs/sampleJob")
		.then()
			.statusCode(200)
			.header("Location", notNullValue());
	}
	
}
