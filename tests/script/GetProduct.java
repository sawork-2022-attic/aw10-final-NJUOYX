package computerdatabase.product;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class GetProduct extends Simulation{
    HttpProtocolBuilder httpProtocol =
            http.baseUrl("http://localhost:8001");
    
    ScenarioBuilder scn =
            scenario("Scenario")
                    .exec(http("request_1").get("/product/0319261425"));

    {
        setUp(scn.injectOpen(atOnceUsers(1000)).protocols(httpProtocol));
    }
}
