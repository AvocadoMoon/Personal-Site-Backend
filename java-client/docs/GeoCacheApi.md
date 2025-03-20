# GeoCacheApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**receiveSubmission**](GeoCacheApi.md#receiveSubmission) | **POST** /api/v1/geoCache |  |
| [**receiveSubmissionWithHttpInfo**](GeoCacheApi.md#receiveSubmissionWithHttpInfo) | **POST** /api/v1/geoCache |  |



## receiveSubmission

> void receiveSubmission(geoCacheSubmission)



### Example

```java
// Import classes:
import com.ezequiel.ApiClient;
import com.ezequiel.ApiException;
import com.ezequiel.Configuration;
import com.ezequiel.models.*;
import com.ezequiel.api.GeoCacheApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080");

        GeoCacheApi apiInstance = new GeoCacheApi(defaultClient);
        GeoCacheSubmission geoCacheSubmission = new GeoCacheSubmission(); // GeoCacheSubmission | 
        try {
            apiInstance.receiveSubmission(geoCacheSubmission);
        } catch (ApiException e) {
            System.err.println("Exception when calling GeoCacheApi#receiveSubmission");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **geoCacheSubmission** | [**GeoCacheSubmission**](.md)|  | |

### Return type


null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

## receiveSubmissionWithHttpInfo

> ApiResponse<Void> receiveSubmission receiveSubmissionWithHttpInfo(geoCacheSubmission)



### Example

```java
// Import classes:
import com.ezequiel.ApiClient;
import com.ezequiel.ApiException;
import com.ezequiel.ApiResponse;
import com.ezequiel.Configuration;
import com.ezequiel.models.*;
import com.ezequiel.api.GeoCacheApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080");

        GeoCacheApi apiInstance = new GeoCacheApi(defaultClient);
        GeoCacheSubmission geoCacheSubmission = new GeoCacheSubmission(); // GeoCacheSubmission | 
        try {
            ApiResponse<Void> response = apiInstance.receiveSubmissionWithHttpInfo(geoCacheSubmission);
            System.out.println("Status code: " + response.getStatusCode());
            System.out.println("Response headers: " + response.getHeaders());
        } catch (ApiException e) {
            System.err.println("Exception when calling GeoCacheApi#receiveSubmission");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Response headers: " + e.getResponseHeaders());
            System.err.println("Reason: " + e.getResponseBody());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **geoCacheSubmission** | [**GeoCacheSubmission**](.md)|  | |

### Return type


ApiResponse<Void>

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

