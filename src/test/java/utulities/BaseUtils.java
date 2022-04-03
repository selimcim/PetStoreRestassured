package utulities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;


public class BaseUtils {
    public static final int SUCCESS_STATUS_CODE = 200;
    public static final int BAD_REQUEST_STATUS_CODE = 400;

    public static final int POST = 1;
    public static final int GET = 2;
    public static final int DELETE = 3;
    public static final int PUT = 4;

    public static String getBaseUri(){
        return ConfigurationReader.get("baseUri");
    }

    public static String getBaseUri(String resourcePath){
        return ConfigurationReader.get("baseUri") + resourcePath;
    }

    public static JSONObject createJSONPayload(Object object) {
        return new JSONObject(object);
    }

    public static RequestSpecification createRequestSpecification() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.header("Content-Type", "application/json");
        requestSpecification.header("accept", "application/json");
        return requestSpecification;
    }

    public static Response sendRequest(int httpRequestType, String requestUri, String payLoad) {
        RequestSpecification requestSpecification= null;
        Response response = null;
        switch (httpRequestType) {
            case BaseUtils.POST:
                response = createRequestSpecification().body(payLoad).post(requestUri);
                break;
            case BaseUtils.GET:
                response = createRequestSpecification().get(requestUri);
                break;
            case BaseUtils.DELETE:
                response = createRequestSpecification().delete(requestUri);
                break;
            case BaseUtils.PUT:
                response = createRequestSpecification().body(payLoad).post(requestUri);
            default:
                response = requestSpecification.post(requestUri);
                break;
        }
        return response;
    }

}
