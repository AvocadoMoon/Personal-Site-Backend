# GeoCacheApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getSubmission**](GeoCacheApi.md#getSubmission) | **GET** /api/v1/geoCache/{pageNumber} |  |
| [**getSubmissionWithHttpInfo**](GeoCacheApi.md#getSubmissionWithHttpInfo) | **GET** /api/v1/geoCache/{pageNumber} |  |
| [**sendSubmission**](GeoCacheApi.md#sendSubmission) | **POST** /api/v1/geoCache |  |
| [**sendSubmissionWithHttpInfo**](GeoCacheApi.md#sendSubmissionWithHttpInfo) | **POST** /api/v1/geoCache |  |



## getSubmission

> List<GeoCacheSubmission> getSubmission(pageNumber)



Retrieve geo cache submission.

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
        Integer pageNumber = 56; // Integer | 
        try {
            List<GeoCacheSubmission> result = apiInstance.getSubmission(pageNumber);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling GeoCacheApi#getSubmission");
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
| **pageNumber** | **Integer**|  | |

### Return type

[**List&lt;GeoCacheSubmission&gt;**](GeoCacheSubmission.md)


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

## getSubmissionWithHttpInfo

> ApiResponse<List<GeoCacheSubmission>> getSubmission getSubmissionWithHttpInfo(pageNumber)



Retrieve geo cache submission.

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
        Integer pageNumber = 56; // Integer | 
        try {
            ApiResponse<List<GeoCacheSubmission>> response = apiInstance.getSubmissionWithHttpInfo(pageNumber);
            System.out.println("Status code: " + response.getStatusCode());
            System.out.println("Response headers: " + response.getHeaders());
            System.out.println("Response body: " + response.getData());
        } catch (ApiException e) {
            System.err.println("Exception when calling GeoCacheApi#getSubmission");
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
| **pageNumber** | **Integer**|  | |

### Return type

ApiResponse<[**List&lt;GeoCacheSubmission&gt;**](GeoCacheSubmission.md)>


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |


## sendSubmission

> void sendSubmission(geoCacheSubmission)



Send a geo cache submission to the endpoint.

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
            apiInstance.sendSubmission(geoCacheSubmission);
        } catch (ApiException e) {
            System.err.println("Exception when calling GeoCacheApi#sendSubmission");
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
| **geoCacheSubmission** | [**GeoCacheSubmission**](GeoCacheSubmission.md)|  | |

### Return type


null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

## sendSubmissionWithHttpInfo

> ApiResponse<Void> sendSubmission sendSubmissionWithHttpInfo(geoCacheSubmission)



Send a geo cache submission to the endpoint.

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
            ApiResponse<Void> response = apiInstance.sendSubmissionWithHttpInfo(geoCacheSubmission);
            System.out.println("Status code: " + response.getStatusCode());
            System.out.println("Response headers: " + response.getHeaders());
        } catch (ApiException e) {
            System.err.println("Exception when calling GeoCacheApi#sendSubmission");
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
| **geoCacheSubmission** | [**GeoCacheSubmission**](GeoCacheSubmission.md)|  | |

### Return type


ApiResponse<Void>

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

