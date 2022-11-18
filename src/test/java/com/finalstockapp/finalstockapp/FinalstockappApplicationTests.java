package com.finalstockapp.finalstockapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.finalstockapp.finalstockapp.Model.Company;
import com.finalstockapp.finalstockapp.Service.CompanyService;
import com.finalstockapp.finalstockapp.stockDetails.stocks;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DataJpaTest
class FinalstockappApplicationTests {

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	private static RestTemplate restTemplate;

	@Autowired
	private TestH2Repository h2Repository;

	@Autowired
	private CompanyService service;

	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void setUp() {
		baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/v1.0/market");
	}

	@Test
	public void testRegisterCompany() {
//		Company company=new Company(100,"Aachal","Aachal",100,"https://www.Aachal.com","BSE");
//		Company response=restTemplate.postForObject(baseUrl+"/register",company,Company.class);
		Company company = new Company(100, "Aachal", "Aachal", 100, "https://www.Aachal.com", "BSE");
		String tocken="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEyMyIsImV4cCI6MTY2NzMwMDAwOSwiaWF0IjoxNjY3MjgyMDA5fQ._5DyjhsKwGWyOsMobfsY5VIdYOQ_uOhewgxcYm0pJr9GC2tu2ZBrdAFpmsWkgQQA9g9Akpgb5yPwSb7oP-x3sw";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+tocken);
		HttpEntity<Company> request = new HttpEntity<>(company, headers);
		Company response = restTemplate.postForObject(baseUrl + "/register", request, Company.class);
		assertEquals(100, response.getCompanyCode());
		assertEquals(1, h2Repository.findAll().size());
	}

	@Test
	public void getCompanys() {
		Company company = new Company(100, "Aachal", "Aachal", 100, "https://www.Aachal.com", "BSE");
		String tocken="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEyMyIsImV4cCI6MTY2NzMwMDAwOSwiaWF0IjoxNjY3MjgyMDA5fQ._5DyjhsKwGWyOsMobfsY5VIdYOQ_uOhewgxcYm0pJr9GC2tu2ZBrdAFpmsWkgQQA9g9Akpgb5yPwSb7oP-x3sw";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+tocken);
		HttpEntity<Company> request = new HttpEntity<>(company, headers);
		Company response = restTemplate.postForObject(baseUrl + "/register", request, Company.class);
		List<Company> responsee = restTemplate.exchange(baseUrl + "/info", HttpMethod.GET, request, List.class).getBody();
		assertEquals(1, (responsee).size());
		;
		assertEquals(1, h2Repository.findAll().size());
	}

	@Test
	//@Sql(statements = "INSERT INTO apple_data (company_code, company_name, companyceo, company_turnover, company_website, stock_exchange) VALUES (100, 'aaa', 'Aachal', 12345678, 'www.google.com', 'bse')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	//@Sql(statements = "DELETE FROM apple_data WHERE company_code=100", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void getCompanyByCode() {
		Company company = new Company(100, "Aachal", "Aachal", 100, "https://www.Aachal.com", "BSE");
		String tocken="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEyMyIsImV4cCI6MTY2NzMwMDAwOSwiaWF0IjoxNjY3MjgyMDA5fQ._5DyjhsKwGWyOsMobfsY5VIdYOQ_uOhewgxcYm0pJr9GC2tu2ZBrdAFpmsWkgQQA9g9Akpgb5yPwSb7oP-x3sw";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+tocken);
		HttpEntity<Company> request = new HttpEntity<>(company, headers);
		Company comp = restTemplate.postForObject(baseUrl + "/register", request,Company.class);
		Company response = restTemplate.exchange(baseUrl + "/Company/{companyCode}", HttpMethod.GET, request, Company.class, 100).getBody();
		assertAll(
				() -> assertNotNull(response),
				() -> assertEquals(100, response.getCompanyCode()),
				() -> assertEquals("Aachal", response.getCompanyName()),
				() -> assertEquals("Aachal", response.getCompanyCEO()),
				() -> assertEquals(100, response.getCompanyTurnover()),
				() -> assertEquals("https://www.Aachal.com", response.getCompanyWebsite()),
				() -> assertEquals("BSE", response.getStockExchange())


		);

	}

//	@Test
//	public void getStocksByCode() {
//		Company company = new Company(100, "Facebook", "Aachal", 1000, "https://www.Aachal.com", "BSE");
//		Company response = restTemplate.postForObject(baseUrl + "/register",company, Company.class);
//		stocks stock = new stocks(1, 123F, "Facebook", 100, LocalDateTime.now());
//		Company stock1 = restTemplate.getForObject(baseUrl + "/Stocks/{companyCode}", Company.class, 100);
//		assertAll(
//				() -> assertNotNull(stock1),
//				() -> assertEquals("Facebook", stock1.getCompanyName()),
//				() -> assertEquals(100, stock1.getCompanyCode())
//
//
//
//		);
//
//	}







//	@Test
//	public void getCompanysByCode() throws JsonProcessingException {
//		Company company = new Company(100, "Aachal", "Aachal", 100, "https://www.Aachal.com", "BSE");
//		Company response = restTemplate.postForObject(baseUrl + "/register", company, Company.class);
//		MultiValueMap<String, String> headers = null;
//		HttpEntity<String> entity = new HttpEntity<>(null, headers);
//		ResponseEntity<Company> responsee = restTemplate.exchange(
//				(createURLWithPort() + "/12"), HttpMethod.GET, entity, Company.class);
//		Company st = responsee.getBody();
//		String expected = "{\"id\":20,\"buyer\":\"sam\",\"price\":50.0,\"qty\":4}";
//		assertEquals(responsee.getStatusCodeValue(), 200);
////        assertEquals(expected, objectMapper.writeValueAsString(st));
//		assert st != null;
//		assertEquals(st, service.getCompanyByCompanyCode(12));
////        assertEquals(st.getCompanyName(), service.getBuyer());
////        assertEquals(st, orderRepository.findById(20L).orElse(null));
//	}
//
//	private String createURLWithPort() {
//		return "http://localhost:" + port + "/api/v1.0/market/Company";
//	}

////	@Test
//	void contextLoads() {

//	}

	@Test
//	@Sql(statements = "INSERT INTO apple_data (company_code, company_name, companyceo, company_turnover, company_website, stock_exchange) VALUES (100, 'aaa', 'Aachal', 12345678, 'www.google.com', 'bse')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "DELETE FROM apple_data WHERE company_code=100", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateProduct() {
		Company company = new Company(100, "bbb", "ccc", 112233, "https://www.shubham.com", "nse");
		String tocken="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEyMyIsImV4cCI6MTY2NzMwMDAwOSwiaWF0IjoxNjY3MjgyMDA5fQ._5DyjhsKwGWyOsMobfsY5VIdYOQ_uOhewgxcYm0pJr9GC2tu2ZBrdAFpmsWkgQQA9g9Akpgb5yPwSb7oP-x3sw";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+tocken);
		HttpEntity<Company> request = new HttpEntity<>(company, headers);
		restTemplate.put(baseUrl + "/updateUsers/{companyCode}", request, 100);
		Company companyFromDB = h2Repository.findByCompanyCode(100).get();
		assertAll(
				() -> assertNotNull(companyFromDB),
				() -> assertEquals("bbb", companyFromDB.getCompanyName()),
				() -> assertEquals("ccc", companyFromDB.getCompanyCEO()),
				() -> assertEquals(112233, companyFromDB.getCompanyTurnover()),
				() -> assertEquals("https://www.shubham.com", companyFromDB.getCompanyWebsite()),
				() -> assertEquals("nse", companyFromDB.getStockExchange())

				);



	}

//	@Test
//	@Sql(statements = "INSERT INTO apple_data (company_code, company_name, companyceo, company_turnover, company_website, stock_exchange) VALUES (100, 'aaa', 'Aachal', 12345678, 'www.google.com', 'bse')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//	public void testDeleteCompanyCode(){
//		int recordCount=h2Repository.findAll().size();
//		assertEquals(1, recordCount);
//		restTemplate.delete(baseUrl+"/deletee/{companyCode}", 100);
//		assertEquals(0, h2Repository.findAll().size());
//
//	}
}
