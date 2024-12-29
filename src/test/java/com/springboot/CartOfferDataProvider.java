package com.springboot;

import org.testng.annotations.DataProvider;

public class CartOfferDataProvider {

    @DataProvider(name = "cartOfferTestCases")
    public static Object[][] cartOfferTestCases() {
        return new Object[][] {


                // Scenario: Flat X% Amount Off
                {200, 1, 1, "p1", "FLATX%", 10, 180},

                // Scenario: Flat X Off
                {200, 1, 1, "p1", "FLATX", 10, 190}, // -> o/p is 180 picking according to findFirst

                // Scenario: No Matching Offers
                {200, 1, 2, "p2", "FLATX", 10, 200},

                // Scenario: Cart Value Zero
                {0, 1, 1, "p1", "FLATX", 10, 0},

                // Scenario: Negative Offer Value
                {200, 1, 1, "p1", "FLATX", -10, 200}, // -> o/p is 180 picking according to findFirst
                // Scenario: Multiple Matching Offers
                {200, 1, 1, "p1", "FLATX", 10, 190}, // -> o/p is 180 picking according to findFirst

                {100000, 1, 1, "p1", "FLATX", 10, 99990},

                {100000, 1, 1, "p1", "FLATX%", 10, 90000}, //


                 {200, 1, 1, "p1", "FLATX", 0, 200}, // -> o/p is 180 picking according to findFirst , But running fine post server restart due clearance of array

                // Scenario: Boundary Offer Values (Flat 0% Off)
                {200, 1, 1, "p1", "FLATX%", 0, 200}, // -> o/p is 180 picking according to findFirst , But running fine post server restart due clearance of array

                // Scenario: Boundary Offer Values (Flat 100% Off)
                {200, 1, 1, "p1", "FLATX%", 100, 0},// -> o/p is 180 picking according to findFirst

                 {200, 1, 1, "p1", "FLATX%", -10, 0}, // -> o/p is 180 picking according to findFirst , But running fine post server restart due clearance of array // o/p : 220

                // Scenario: Nonexistent User
               {200, 999, 1, "p1", "FLATX", 10, 200}, // java.io.FileNotFoundException: http://localhost:1080/api/v1/user_segment?user_id=999 SegmentResponse(segment=null) // o/p : 200

                // Scenario: Nonexistent Segment
                {200, 1, 1, "nonexistent", "FLATX", 10, 200} // -> o/p is 180 picking according to findFirst, But triggered alone post server restart ,  // o/p : 200
        };
    }
}
