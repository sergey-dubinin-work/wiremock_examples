package dubinin.sergey.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.util.Objects.isNull;

public class PrimitiveUserMockServer {

    private WireMockServer wireMockServer;

    public void start(){

        wireMockServer = new WireMockServer(8080);

        wireMockServer.start();

        stubFor(
                get(urlEqualTo("/api/users/1"))
                .willReturn(
                        aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\"name\":\"Sergey\",\"age\":31}")
                ));

    }

    public void stop(){
        if (!isNull(wireMockServer) && wireMockServer.isRunning()){
            wireMockServer.stop();
        }
    }

}
