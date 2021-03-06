/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.sql.implementation;

import retrofit2.Retrofit;
import com.google.common.reflect.TypeToken;
import com.microsoft.azure.AzureServiceResponseBuilder;
import com.microsoft.azure.CloudException;
import com.microsoft.rest.ServiceCall;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceResponse;
import java.io.IOException;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.Response;
import rx.functions.Func1;
import rx.Observable;

/**
 * An instance of this class provides access to all the operations defined
 * in RecommendedElasticPools.
 */
public final class RecommendedElasticPoolsInner {
    /** The Retrofit service to perform REST calls. */
    private RecommendedElasticPoolsService service;
    /** The service client containing this operation class. */
    private SqlManagementClientImpl client;

    /**
     * Initializes an instance of RecommendedElasticPoolsInner.
     *
     * @param retrofit the Retrofit instance built from a Retrofit Builder.
     * @param client the instance of the service client containing this operation class.
     */
    public RecommendedElasticPoolsInner(Retrofit retrofit, SqlManagementClientImpl client) {
        this.service = retrofit.create(RecommendedElasticPoolsService.class);
        this.client = client;
    }

    /**
     * The interface defining all the services for RecommendedElasticPools to be
     * used by Retrofit to perform actually REST calls.
     */
    interface RecommendedElasticPoolsService {
        @Headers("Content-Type: application/json; charset=utf-8")
        @GET("subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Sql/servers/{serverName}/recommendedElasticPools/{recommendedElasticPoolName}")
        Observable<Response<ResponseBody>> get(@Path("subscriptionId") String subscriptionId, @Path("resourceGroupName") String resourceGroupName, @Path("serverName") String serverName, @Path("recommendedElasticPoolName") String recommendedElasticPoolName, @Query("api-version") String apiVersion, @Header("accept-language") String acceptLanguage, @Header("User-Agent") String userAgent);

        @Headers("Content-Type: application/json; charset=utf-8")
        @GET("subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Sql/servers/{serverName}/recommendedElasticPools/{recommendedElasticPoolName}/databases/{databaseName}")
        Observable<Response<ResponseBody>> getDatabases(@Path("subscriptionId") String subscriptionId, @Path("resourceGroupName") String resourceGroupName, @Path("serverName") String serverName, @Path("recommendedElasticPoolName") String recommendedElasticPoolName, @Path("databaseName") String databaseName, @Query("api-version") String apiVersion, @Header("accept-language") String acceptLanguage, @Header("User-Agent") String userAgent);

        @Headers("Content-Type: application/json; charset=utf-8")
        @GET("subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Sql/servers/{serverName}/recommendedElasticPools")
        Observable<Response<ResponseBody>> list(@Path("subscriptionId") String subscriptionId, @Path("resourceGroupName") String resourceGroupName, @Path("serverName") String serverName, @Query("api-version") String apiVersion, @Header("accept-language") String acceptLanguage, @Header("User-Agent") String userAgent);

        @Headers("Content-Type: application/json; charset=utf-8")
        @GET("subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Sql/servers/{serverName}/recommendedElasticPools/{recommendedElasticPoolName}/databases")
        Observable<Response<ResponseBody>> listDatabases(@Path("subscriptionId") String subscriptionId, @Path("resourceGroupName") String resourceGroupName, @Path("serverName") String serverName, @Path("recommendedElasticPoolName") String recommendedElasticPoolName, @Query("api-version") String apiVersion, @Header("accept-language") String acceptLanguage, @Header("User-Agent") String userAgent);

        @Headers("Content-Type: application/json; charset=utf-8")
        @GET("subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Sql/servers/{serverName}/recommendedElasticPools/{recommendedElasticPoolName}/metrics")
        Observable<Response<ResponseBody>> listMetrics(@Path("subscriptionId") String subscriptionId, @Path("resourceGroupName") String resourceGroupName, @Path("serverName") String serverName, @Path("recommendedElasticPoolName") String recommendedElasticPoolName, @Query("api-version") String apiVersion, @Header("accept-language") String acceptLanguage, @Header("User-Agent") String userAgent);

    }

    /**
     * Gets information about an Azure SQL Recommended Elastic Pool.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @param recommendedElasticPoolName The name of the Azure SQL Recommended Elastic Pool to be retrieved.
     * @return the RecommendedElasticPoolInner object if successful.
     */
    public RecommendedElasticPoolInner get(String resourceGroupName, String serverName, String recommendedElasticPoolName) {
        return getWithServiceResponseAsync(resourceGroupName, serverName, recommendedElasticPoolName).toBlocking().single().getBody();
    }

    /**
     * Gets information about an Azure SQL Recommended Elastic Pool.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @param recommendedElasticPoolName The name of the Azure SQL Recommended Elastic Pool to be retrieved.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @return the {@link ServiceCall} object
     */
    public ServiceCall<RecommendedElasticPoolInner> getAsync(String resourceGroupName, String serverName, String recommendedElasticPoolName, final ServiceCallback<RecommendedElasticPoolInner> serviceCallback) {
        return ServiceCall.create(getWithServiceResponseAsync(resourceGroupName, serverName, recommendedElasticPoolName), serviceCallback);
    }

    /**
     * Gets information about an Azure SQL Recommended Elastic Pool.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @param recommendedElasticPoolName The name of the Azure SQL Recommended Elastic Pool to be retrieved.
     * @return the observable to the RecommendedElasticPoolInner object
     */
    public Observable<RecommendedElasticPoolInner> getAsync(String resourceGroupName, String serverName, String recommendedElasticPoolName) {
        return getWithServiceResponseAsync(resourceGroupName, serverName, recommendedElasticPoolName).map(new Func1<ServiceResponse<RecommendedElasticPoolInner>, RecommendedElasticPoolInner>() {
            @Override
            public RecommendedElasticPoolInner call(ServiceResponse<RecommendedElasticPoolInner> response) {
                return response.getBody();
            }
        });
    }

    /**
     * Gets information about an Azure SQL Recommended Elastic Pool.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @param recommendedElasticPoolName The name of the Azure SQL Recommended Elastic Pool to be retrieved.
     * @return the observable to the RecommendedElasticPoolInner object
     */
    public Observable<ServiceResponse<RecommendedElasticPoolInner>> getWithServiceResponseAsync(String resourceGroupName, String serverName, String recommendedElasticPoolName) {
        if (this.client.subscriptionId() == null) {
            throw new IllegalArgumentException("Parameter this.client.subscriptionId() is required and cannot be null.");
        }
        if (resourceGroupName == null) {
            throw new IllegalArgumentException("Parameter resourceGroupName is required and cannot be null.");
        }
        if (serverName == null) {
            throw new IllegalArgumentException("Parameter serverName is required and cannot be null.");
        }
        if (recommendedElasticPoolName == null) {
            throw new IllegalArgumentException("Parameter recommendedElasticPoolName is required and cannot be null.");
        }
        final String apiVersion = "2014-04-01";
        return service.get(this.client.subscriptionId(), resourceGroupName, serverName, recommendedElasticPoolName, apiVersion, this.client.acceptLanguage(), this.client.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<RecommendedElasticPoolInner>>>() {
                @Override
                public Observable<ServiceResponse<RecommendedElasticPoolInner>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<RecommendedElasticPoolInner> clientResponse = getDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<RecommendedElasticPoolInner> getDelegate(Response<ResponseBody> response) throws CloudException, IOException, IllegalArgumentException {
        return new AzureServiceResponseBuilder<RecommendedElasticPoolInner, CloudException>(this.client.mapperAdapter())
                .register(200, new TypeToken<RecommendedElasticPoolInner>() { }.getType())
                .registerError(CloudException.class)
                .build(response);
    }

    /**
     * Gets information about an Azure SQL Database inside of an Azure SQL Recommended Elastic Pool.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @param recommendedElasticPoolName The name of the Azure SQL Elastic Pool to be retrieved.
     * @param databaseName The name of the Azure SQL Database to be retrieved.
     * @return the DatabaseInner object if successful.
     */
    public DatabaseInner getDatabases(String resourceGroupName, String serverName, String recommendedElasticPoolName, String databaseName) {
        return getDatabasesWithServiceResponseAsync(resourceGroupName, serverName, recommendedElasticPoolName, databaseName).toBlocking().single().getBody();
    }

    /**
     * Gets information about an Azure SQL Database inside of an Azure SQL Recommended Elastic Pool.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @param recommendedElasticPoolName The name of the Azure SQL Elastic Pool to be retrieved.
     * @param databaseName The name of the Azure SQL Database to be retrieved.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @return the {@link ServiceCall} object
     */
    public ServiceCall<DatabaseInner> getDatabasesAsync(String resourceGroupName, String serverName, String recommendedElasticPoolName, String databaseName, final ServiceCallback<DatabaseInner> serviceCallback) {
        return ServiceCall.create(getDatabasesWithServiceResponseAsync(resourceGroupName, serverName, recommendedElasticPoolName, databaseName), serviceCallback);
    }

    /**
     * Gets information about an Azure SQL Database inside of an Azure SQL Recommended Elastic Pool.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @param recommendedElasticPoolName The name of the Azure SQL Elastic Pool to be retrieved.
     * @param databaseName The name of the Azure SQL Database to be retrieved.
     * @return the observable to the DatabaseInner object
     */
    public Observable<DatabaseInner> getDatabasesAsync(String resourceGroupName, String serverName, String recommendedElasticPoolName, String databaseName) {
        return getDatabasesWithServiceResponseAsync(resourceGroupName, serverName, recommendedElasticPoolName, databaseName).map(new Func1<ServiceResponse<DatabaseInner>, DatabaseInner>() {
            @Override
            public DatabaseInner call(ServiceResponse<DatabaseInner> response) {
                return response.getBody();
            }
        });
    }

    /**
     * Gets information about an Azure SQL Database inside of an Azure SQL Recommended Elastic Pool.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @param recommendedElasticPoolName The name of the Azure SQL Elastic Pool to be retrieved.
     * @param databaseName The name of the Azure SQL Database to be retrieved.
     * @return the observable to the DatabaseInner object
     */
    public Observable<ServiceResponse<DatabaseInner>> getDatabasesWithServiceResponseAsync(String resourceGroupName, String serverName, String recommendedElasticPoolName, String databaseName) {
        if (this.client.subscriptionId() == null) {
            throw new IllegalArgumentException("Parameter this.client.subscriptionId() is required and cannot be null.");
        }
        if (resourceGroupName == null) {
            throw new IllegalArgumentException("Parameter resourceGroupName is required and cannot be null.");
        }
        if (serverName == null) {
            throw new IllegalArgumentException("Parameter serverName is required and cannot be null.");
        }
        if (recommendedElasticPoolName == null) {
            throw new IllegalArgumentException("Parameter recommendedElasticPoolName is required and cannot be null.");
        }
        if (databaseName == null) {
            throw new IllegalArgumentException("Parameter databaseName is required and cannot be null.");
        }
        final String apiVersion = "2014-04-01";
        return service.getDatabases(this.client.subscriptionId(), resourceGroupName, serverName, recommendedElasticPoolName, databaseName, apiVersion, this.client.acceptLanguage(), this.client.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<DatabaseInner>>>() {
                @Override
                public Observable<ServiceResponse<DatabaseInner>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<DatabaseInner> clientResponse = getDatabasesDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<DatabaseInner> getDatabasesDelegate(Response<ResponseBody> response) throws CloudException, IOException, IllegalArgumentException {
        return new AzureServiceResponseBuilder<DatabaseInner, CloudException>(this.client.mapperAdapter())
                .register(200, new TypeToken<DatabaseInner>() { }.getType())
                .registerError(CloudException.class)
                .build(response);
    }

    /**
     * Returns information about Azure SQL Recommended Elastic Pools.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @return the List&lt;RecommendedElasticPoolInner&gt; object if successful.
     */
    public List<RecommendedElasticPoolInner> list(String resourceGroupName, String serverName) {
        return listWithServiceResponseAsync(resourceGroupName, serverName).toBlocking().single().getBody();
    }

    /**
     * Returns information about Azure SQL Recommended Elastic Pools.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @return the {@link ServiceCall} object
     */
    public ServiceCall<List<RecommendedElasticPoolInner>> listAsync(String resourceGroupName, String serverName, final ServiceCallback<List<RecommendedElasticPoolInner>> serviceCallback) {
        return ServiceCall.create(listWithServiceResponseAsync(resourceGroupName, serverName), serviceCallback);
    }

    /**
     * Returns information about Azure SQL Recommended Elastic Pools.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @return the observable to the List&lt;RecommendedElasticPoolInner&gt; object
     */
    public Observable<List<RecommendedElasticPoolInner>> listAsync(String resourceGroupName, String serverName) {
        return listWithServiceResponseAsync(resourceGroupName, serverName).map(new Func1<ServiceResponse<List<RecommendedElasticPoolInner>>, List<RecommendedElasticPoolInner>>() {
            @Override
            public List<RecommendedElasticPoolInner> call(ServiceResponse<List<RecommendedElasticPoolInner>> response) {
                return response.getBody();
            }
        });
    }

    /**
     * Returns information about Azure SQL Recommended Elastic Pools.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @return the observable to the List&lt;RecommendedElasticPoolInner&gt; object
     */
    public Observable<ServiceResponse<List<RecommendedElasticPoolInner>>> listWithServiceResponseAsync(String resourceGroupName, String serverName) {
        if (this.client.subscriptionId() == null) {
            throw new IllegalArgumentException("Parameter this.client.subscriptionId() is required and cannot be null.");
        }
        if (resourceGroupName == null) {
            throw new IllegalArgumentException("Parameter resourceGroupName is required and cannot be null.");
        }
        if (serverName == null) {
            throw new IllegalArgumentException("Parameter serverName is required and cannot be null.");
        }
        final String apiVersion = "2014-04-01";
        return service.list(this.client.subscriptionId(), resourceGroupName, serverName, apiVersion, this.client.acceptLanguage(), this.client.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<List<RecommendedElasticPoolInner>>>>() {
                @Override
                public Observable<ServiceResponse<List<RecommendedElasticPoolInner>>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<PageImpl<RecommendedElasticPoolInner>> result = listDelegate(response);
                        ServiceResponse<List<RecommendedElasticPoolInner>> clientResponse = new ServiceResponse<List<RecommendedElasticPoolInner>>(result.getBody().getItems(), result.getResponse());
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<PageImpl<RecommendedElasticPoolInner>> listDelegate(Response<ResponseBody> response) throws CloudException, IOException, IllegalArgumentException {
        return new AzureServiceResponseBuilder<PageImpl<RecommendedElasticPoolInner>, CloudException>(this.client.mapperAdapter())
                .register(200, new TypeToken<PageImpl<RecommendedElasticPoolInner>>() { }.getType())
                .registerError(CloudException.class)
                .build(response);
    }

    /**
     * Returns information about an Azure SQL Database inside of an Azure SQL Recommended Elastic Pool.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @param recommendedElasticPoolName The name of the Azure SQL Recommended Elastic Pool to be retrieved.
     * @return the List&lt;DatabaseInner&gt; object if successful.
     */
    public List<DatabaseInner> listDatabases(String resourceGroupName, String serverName, String recommendedElasticPoolName) {
        return listDatabasesWithServiceResponseAsync(resourceGroupName, serverName, recommendedElasticPoolName).toBlocking().single().getBody();
    }

    /**
     * Returns information about an Azure SQL Database inside of an Azure SQL Recommended Elastic Pool.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @param recommendedElasticPoolName The name of the Azure SQL Recommended Elastic Pool to be retrieved.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @return the {@link ServiceCall} object
     */
    public ServiceCall<List<DatabaseInner>> listDatabasesAsync(String resourceGroupName, String serverName, String recommendedElasticPoolName, final ServiceCallback<List<DatabaseInner>> serviceCallback) {
        return ServiceCall.create(listDatabasesWithServiceResponseAsync(resourceGroupName, serverName, recommendedElasticPoolName), serviceCallback);
    }

    /**
     * Returns information about an Azure SQL Database inside of an Azure SQL Recommended Elastic Pool.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @param recommendedElasticPoolName The name of the Azure SQL Recommended Elastic Pool to be retrieved.
     * @return the observable to the List&lt;DatabaseInner&gt; object
     */
    public Observable<List<DatabaseInner>> listDatabasesAsync(String resourceGroupName, String serverName, String recommendedElasticPoolName) {
        return listDatabasesWithServiceResponseAsync(resourceGroupName, serverName, recommendedElasticPoolName).map(new Func1<ServiceResponse<List<DatabaseInner>>, List<DatabaseInner>>() {
            @Override
            public List<DatabaseInner> call(ServiceResponse<List<DatabaseInner>> response) {
                return response.getBody();
            }
        });
    }

    /**
     * Returns information about an Azure SQL Database inside of an Azure SQL Recommended Elastic Pool.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @param recommendedElasticPoolName The name of the Azure SQL Recommended Elastic Pool to be retrieved.
     * @return the observable to the List&lt;DatabaseInner&gt; object
     */
    public Observable<ServiceResponse<List<DatabaseInner>>> listDatabasesWithServiceResponseAsync(String resourceGroupName, String serverName, String recommendedElasticPoolName) {
        if (this.client.subscriptionId() == null) {
            throw new IllegalArgumentException("Parameter this.client.subscriptionId() is required and cannot be null.");
        }
        if (resourceGroupName == null) {
            throw new IllegalArgumentException("Parameter resourceGroupName is required and cannot be null.");
        }
        if (serverName == null) {
            throw new IllegalArgumentException("Parameter serverName is required and cannot be null.");
        }
        if (recommendedElasticPoolName == null) {
            throw new IllegalArgumentException("Parameter recommendedElasticPoolName is required and cannot be null.");
        }
        final String apiVersion = "2014-04-01";
        return service.listDatabases(this.client.subscriptionId(), resourceGroupName, serverName, recommendedElasticPoolName, apiVersion, this.client.acceptLanguage(), this.client.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<List<DatabaseInner>>>>() {
                @Override
                public Observable<ServiceResponse<List<DatabaseInner>>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<PageImpl<DatabaseInner>> result = listDatabasesDelegate(response);
                        ServiceResponse<List<DatabaseInner>> clientResponse = new ServiceResponse<List<DatabaseInner>>(result.getBody().getItems(), result.getResponse());
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<PageImpl<DatabaseInner>> listDatabasesDelegate(Response<ResponseBody> response) throws CloudException, IOException, IllegalArgumentException {
        return new AzureServiceResponseBuilder<PageImpl<DatabaseInner>, CloudException>(this.client.mapperAdapter())
                .register(200, new TypeToken<PageImpl<DatabaseInner>>() { }.getType())
                .registerError(CloudException.class)
                .build(response);
    }

    /**
     * Returns information about an recommended elastic pool metrics.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @param recommendedElasticPoolName The name of the Azure SQL Recommended Elastic Pool to be retrieved.
     * @return the List&lt;RecommendedElasticPoolMetricInner&gt; object if successful.
     */
    public List<RecommendedElasticPoolMetricInner> listMetrics(String resourceGroupName, String serverName, String recommendedElasticPoolName) {
        return listMetricsWithServiceResponseAsync(resourceGroupName, serverName, recommendedElasticPoolName).toBlocking().single().getBody();
    }

    /**
     * Returns information about an recommended elastic pool metrics.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @param recommendedElasticPoolName The name of the Azure SQL Recommended Elastic Pool to be retrieved.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @return the {@link ServiceCall} object
     */
    public ServiceCall<List<RecommendedElasticPoolMetricInner>> listMetricsAsync(String resourceGroupName, String serverName, String recommendedElasticPoolName, final ServiceCallback<List<RecommendedElasticPoolMetricInner>> serviceCallback) {
        return ServiceCall.create(listMetricsWithServiceResponseAsync(resourceGroupName, serverName, recommendedElasticPoolName), serviceCallback);
    }

    /**
     * Returns information about an recommended elastic pool metrics.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @param recommendedElasticPoolName The name of the Azure SQL Recommended Elastic Pool to be retrieved.
     * @return the observable to the List&lt;RecommendedElasticPoolMetricInner&gt; object
     */
    public Observable<List<RecommendedElasticPoolMetricInner>> listMetricsAsync(String resourceGroupName, String serverName, String recommendedElasticPoolName) {
        return listMetricsWithServiceResponseAsync(resourceGroupName, serverName, recommendedElasticPoolName).map(new Func1<ServiceResponse<List<RecommendedElasticPoolMetricInner>>, List<RecommendedElasticPoolMetricInner>>() {
            @Override
            public List<RecommendedElasticPoolMetricInner> call(ServiceResponse<List<RecommendedElasticPoolMetricInner>> response) {
                return response.getBody();
            }
        });
    }

    /**
     * Returns information about an recommended elastic pool metrics.
     *
     * @param resourceGroupName The name of the Resource Group to which the resource belongs.
     * @param serverName The name of the Azure SQL Server
     * @param recommendedElasticPoolName The name of the Azure SQL Recommended Elastic Pool to be retrieved.
     * @return the observable to the List&lt;RecommendedElasticPoolMetricInner&gt; object
     */
    public Observable<ServiceResponse<List<RecommendedElasticPoolMetricInner>>> listMetricsWithServiceResponseAsync(String resourceGroupName, String serverName, String recommendedElasticPoolName) {
        if (this.client.subscriptionId() == null) {
            throw new IllegalArgumentException("Parameter this.client.subscriptionId() is required and cannot be null.");
        }
        if (resourceGroupName == null) {
            throw new IllegalArgumentException("Parameter resourceGroupName is required and cannot be null.");
        }
        if (serverName == null) {
            throw new IllegalArgumentException("Parameter serverName is required and cannot be null.");
        }
        if (recommendedElasticPoolName == null) {
            throw new IllegalArgumentException("Parameter recommendedElasticPoolName is required and cannot be null.");
        }
        final String apiVersion = "2014-04-01";
        return service.listMetrics(this.client.subscriptionId(), resourceGroupName, serverName, recommendedElasticPoolName, apiVersion, this.client.acceptLanguage(), this.client.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<List<RecommendedElasticPoolMetricInner>>>>() {
                @Override
                public Observable<ServiceResponse<List<RecommendedElasticPoolMetricInner>>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<PageImpl<RecommendedElasticPoolMetricInner>> result = listMetricsDelegate(response);
                        ServiceResponse<List<RecommendedElasticPoolMetricInner>> clientResponse = new ServiceResponse<List<RecommendedElasticPoolMetricInner>>(result.getBody().getItems(), result.getResponse());
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<PageImpl<RecommendedElasticPoolMetricInner>> listMetricsDelegate(Response<ResponseBody> response) throws CloudException, IOException, IllegalArgumentException {
        return new AzureServiceResponseBuilder<PageImpl<RecommendedElasticPoolMetricInner>, CloudException>(this.client.mapperAdapter())
                .register(200, new TypeToken<PageImpl<RecommendedElasticPoolMetricInner>>() { }.getType())
                .registerError(CloudException.class)
                .build(response);
    }

}
