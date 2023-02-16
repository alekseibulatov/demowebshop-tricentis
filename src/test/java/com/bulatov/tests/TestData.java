package com.bulatov.tests;

import static com.bulatov.specs.LoginSpecs.loginRequestSpec;
import static com.bulatov.specs.LoginSpecs.responseSpecification;
import static io.restassured.RestAssured.given;

public class TestData {

    static String cookieValue = "98ECA832765A5DF31011154F869207B2F50A02D7325CD91D25C4BDEDA8EB815245644633833A97767010BCE3A8" +
            "95F4080C6BCDDB1E85AFE3413B6A90635B3A12D69ABF56525C2DDE8D327C64352FD9F5F72015FEB81416C1690A5FA636BB4071" +
            "F04D639A675745F707C3498AB951E7120561E3546B2E399EE65E3D9785CB73D32A0437900BE92EBFA861188BCD2E2553A6BFF0" +
            "37A5D4C9DBBED722C040016452;";
    static String requestTokenHeader = "RJRbyVQMAPumMBcLgvvs19ooY8Qu7F5L6lKqmCBooJ-TUp6_UFbctGTYm18NF24PrFGbl7rJsdNI6UR2IaE" +
            "vTBG9wzBtG0UzA3Fzo2Bvq301;";
    static String requestTokenBody = "kYDVjOvNu8qidz24PzcHdSWOuYH7amIvN3HFUbXuA7D-czsMACh6qR3whJ2Iah0VmlRn-c5yl_XZlU5kj4MIx" +
            "GkrPIyTx9g0G9HnZWHTooEXIKARnChCBLv10RTKE6BY0";

    static String receiptCookie() {
        return given(loginRequestSpec)
                .contentType("application/x-www-form-urlencoded")
                .formParam("Email", "tonali3852@crtsec.com")
                .formParam("Password", "123456")
                .when()
                .post("/login")
                .then()
                .spec(responseSpecification(302))
                .extract().cookie("NOPCOMMERCE.AUTH");
    }
}
