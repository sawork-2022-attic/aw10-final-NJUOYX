package computerdatabase.cart;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.util.Random;

import io.gatling.core.body.StringBody;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class BasicCart extends Simulation {

    final String userTemplate = "{\"uid\":\"#{uid}\"}";

    final String userItem = "{\n" +
    "  \"uid\": \"#{uid}\",\n" +
    "  \"item\": {\n" +
    "    \"product\": {\n" +
    "      \"asin\": \"B01HI273ZW\",\n" +
    "      \"image_url\": \"https://images-na.ssl-images-amazon.com/images/I/41BUi2lHvzL._SS40_.jpg\",\n" +
    "      \"image_url_high_res\": \"https://images-na.ssl-images-amazon.com/images/I/41BUi2lHvzL.jpg\",\n" +
    "      \"main_cart\": \"Computers\",\n" +
    "      \"price\": 6.5,\n" +
    "      \"title\": \"Bumpers Triggers Buttons Dpad LB RB LT RT for Xbox One Elite Controller Red 3.5mm\"\n" +
    "    },\n" +
    "    \"quantity\": 1\n" +
    "  }\n" +
    "}";

    HttpProtocolBuilder httpProtocol =
    http.baseUrl("http://localhost:8001")
    .contentTypeHeader("application/json");

    ScenarioBuilder scn =
        scenario("Scenario")
                .exec(session ->{
                    String uid = "user"+new Random().nextInt(100000);
                    System.out.println(uid);
                    return session.set("uid", uid);
                })
                .exec(
                    http("show cart")
                    .post("/cart")
                    .body(StringBody(userTemplate))
                )
                .pause(1)
                .exec(
                    http("add product")
                    .post("/cart/add")
                    .body(StringBody(userItem))
                )
                .pause(1)
                .exec(
                    http("checkout")
                    .post("/cart/checkout")
                    .body(StringBody(userTemplate))
                )
                ;
    
    {
    setUp(scn.injectOpen(atOnceUsers(1000)).protocols(httpProtocol));
    }
}
