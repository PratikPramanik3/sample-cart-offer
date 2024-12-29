package com.springboot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.springboot.controller.*;
import com.springboot.utils.JsonUtils;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;


@SpringBootTest
public class CartOfferApplicationTests extends AbstractTestNGSpringContextTests {


	// We have 2 types of offers FLAT X and FLAT X %
	@Test()
	public void addOfferToRestaurantOnSegments(String offerType, int offerValue, String segment, int restaurantId) throws Exception {
		List<String> segments = new ArrayList<>();
		SegmentResponse segmentResponse = new SegmentResponse();
		segmentResponse.setSegment(segment);
		segments.add(segment);
		OfferRequest offerRequest = new OfferRequest(restaurantId,offerType,offerValue,segments);
		offerRequest.setRestaurant_id(restaurantId);
		offerRequest.setOffer_type(offerType);
		offerRequest.setOffer_value(offerValue);
		String applyOfferEndpoint = "http://localhost:9001/api/v1/offer";
		String result = getResponseForCartApplications(offerRequest, applyOfferEndpoint);
		//ApiResponse apiResponse = JsonUtils.jsonToDto(result, ApiResponse.class);
		//Assert.assertEquals(apiResponse.getResponse_msg(),true);
	}

	@Test(dataProvider = "cartOfferTestCases", dataProviderClass = CartOfferDataProvider.class)
	public void applyCartOffer(int cartValue, int userId, int restaurantId, String segment, String offerType, int offerValue, int expectedValue) throws Exception {
		addOfferToRestaurantOnSegments(offerType, offerValue, segment, restaurantId);
		String applyCartOfferEndpoint = "http://localhost:9001/api/v1/cart/apply_offer";
		ApplyCartOfferRequest applyCartOfferRequest = new ApplyCartOfferRequest(cartValue, restaurantId, userId);
		applyCartOfferRequest.setCart_value(cartValue);
		applyCartOfferRequest.setRestaurant_id(restaurantId);
		applyCartOfferRequest.setUser_id(userId);
		String result = getResponseForCartApplications(applyCartOfferRequest, applyCartOfferEndpoint);
		System.out.println(result);
		//ApplyCartOfferResponse apiResponse = JsonUtils.jsonToDto(result, ApplyCartOfferResponse.class);
		// followed by asserts
	}

	public <T> String getResponseForCartApplications(T offerRequest, String endPoint) throws Exception {
		String urlString = endPoint;
		URL url = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json");

		ObjectMapper mapper = new ObjectMapper();

		String POST_PARAMS = mapper.writeValueAsString(offerRequest);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// print result
			System.out.println(response.toString());
			return response.toString();
		} else {
			System.out.println("POST request did not work.");
			return "";
		}
	}
}

