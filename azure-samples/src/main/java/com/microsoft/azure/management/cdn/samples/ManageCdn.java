/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.cdn.samples;

import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.cdn.CdnEndpoint;
import com.microsoft.azure.management.cdn.CdnProfile;
import com.microsoft.azure.management.cdn.CustomDomainValidationResult;
import com.microsoft.azure.management.cdn.GeoFilterActions;
import com.microsoft.azure.management.cdn.QueryStringCachingBehavior;
import com.microsoft.azure.management.resources.fluentcore.arm.CountryISOCode;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import com.microsoft.azure.management.samples.Utils;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.File;

/**
 * Created by hovsepm on 10/26/2016.
 */
public final class ManageCdn {

    /**
     * Main entry point.
     * @param args the parameters
     */
    public static void main(String[] args) {

        final String cdnStandardProfileName = Utils.createRandomName("cdnStandardProfile");
        final String cdnPremiumProfileName = Utils.createRandomName("cdnPremiumProfile");
        final String cdnEndpointName = "endpoint-f3757d2a3e10";
        final String cdnPremiumEndpointName = "premiumVerizonEndpointFluentTest";
        final String rgName = Utils.createRandomName("rgRCCDN");

        try {

            final File credFile = new File("D:/my.azureauth");

            Azure azure = Azure
                    .configure()
                    .withLogLevel(HttpLoggingInterceptor.Level.BASIC)
                    //.withProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8888)))
                    .authenticate(credFile)
                    .withDefaultSubscription();

            // Print selected subscription
            System.out.println("Selected subscription: " + azure.subscriptionId());

            try {
                // ============================================================
                // Create a redis cache

                System.out.println("Creating a CDN Profile");

                CdnProfile standardProfile = azure.cdnProfiles().define(cdnStandardProfileName)
                        .withRegion(Region.US_CENTRAL)
                        .withNewResourceGroup(rgName)

                        .withStandardAkamaiSku()
                        .withNewEndpoint("supername.cloudapp.net")
                        .defineNewEndpoint("akamiEndpointWithoutMuchProperties")
                            .withOrigin("originSuperName", "storageforazdevextest.blob.core.windows.net")
                            .attach()
                        .defineNewEndpoint(cdnEndpointName, "mylinuxapp.azurewebsites.net")
                            .withContentTypeToCompress("powershell/pain")
                            .withGeoFilter("/path/videos", GeoFilterActions.BLOCK, CountryISOCode.ARGENTINA)
                            .withGeoFilter("/path/images", GeoFilterActions.BLOCK, CountryISOCode.BELGIUM)
                            .withContentTypeToCompress("text/plain")
                            .withCompressionEnabled(true)
                            .withQueryStringCachingBehavior(QueryStringCachingBehavior.BYPASS_CACHING)
                            .withHttpsAllowed(true)
                            .withHttpsPort(444)
                            .withHttpAllowed(true)
                            .withHttpPort(85)
                            .withCustomDomain("sdk-1-f3757d2a3e10.azureedge-test.net")
                            .withCustomDomain("sdk-2-f3757d2a3e10.azureedge-test.net")
                            .attach()
                        .create();

                CdnProfile premiumProfile = azure.cdnProfiles().define(cdnPremiumProfileName)
                        .withRegion(Region.US_CENTRAL)
                        .withNewResourceGroup(rgName)
                        .withPremiumVerizonSku()
                        .withNewPremiumEndpoint("someweirdname.blob.core.windows.net")
                        .defineNewPremiumEndpoint("supermuperep1")
                            .withPremiumOrigin("originPremium", "xplattestvmss1sto0575014.blob.core.windows.net")
                            .attach()
                        .defineNewPremiumEndpoint(cdnPremiumEndpointName)
                            .withPremiumOrigin("supername.cloudapp.net")
                            .withHttpAllowed(true)
                            .withHttpsAllowed(true)
                            .withHttpsPort(12)
                            .withHttpPort(123)
                            .attach()
                        .create();

                CdnProfile profileRead = standardProfile.refresh();

                for (CdnEndpoint endpoint : profileRead.endpoints().values()) {
                    System.out.println("CDN Endpoint: " + endpoint.name());
                    System.out.println("EP Hostname: " + endpoint.hostName());
                    System.out.println("EP Origin hostname: " + endpoint.originHostName());
                    System.out.println("EP optimization type: " + endpoint.optimizationType());
                    System.out.println("EP Origin host header: " + endpoint.originHostHeader());
                    System.out.println("EP Origin path: " + endpoint.originPath());
                    for (String customDomain : endpoint.customDomains()) {
                        System.out.println("EP custom domain: " + customDomain);
                    }
                }

                if (!standardProfile.isPremiumVerizon()) {
                    standardProfile.update()
                            .withTag("provider", "Akamai")
                            .withNewEndpoint("www.somewebsite.com")
                            .defineNewEndpoint("somenewnamefortheendpoint")
                                .withOrigin("www.someotherwebsite.com")
                                .withGeoFilter("/path/music", GeoFilterActions.BLOCK, CountryISOCode.ESTONIA)
                                .attach()
                            .updateEndpoint(cdnEndpointName)
                                .withoutGeoFilters()
                                .withHttpAllowed(true)
                                .withHttpPort(1111)
                                .withoutCustomDomain("sdk-2-f3757d2a3e10.azureedge-test.net")
                                .parent()
                    .apply();
                }

                premiumProfile.update()
                        .withTag("provider", "Verizon")
                        .withNewPremiumEndpoint("xplattestvmss1sto0575014.blob.core.windows.net")
                        .defineNewPremiumEndpoint("supermuperep3")
                            .withPremiumOrigin("xplattestvmss1sto0575014.blob.core.windows.net")
                        .attach()
                        .updatePremiumEndpoint(cdnPremiumEndpointName)
                            .withHttpsAllowed(true)
                            .withHttpsPort(1111)
                        .parent()
                        .withoutEndpoint("supermuperep1")
                .apply();

                String ssoUri = premiumProfile.generateSsoUri();

                System.out.println("Standard Akamai Endpoints: " + standardProfile.endpoints().size());
                CdnEndpoint standardEp = standardProfile.endpoints().get(cdnEndpointName);
                CustomDomainValidationResult validationResult = standardEp.validateCustomDomain("sdk-2-f3757d2a3e10.azureedge-test.net");
                standardProfile.stopEndpoint(standardEp.name());
                standardEp.start();

            } catch (Exception f) {
                System.out.println(f.getMessage());
                f.printStackTrace();
            } finally {
                if (azure.resourceGroups().getByName(rgName) != null) {
                    System.out.println("Deleting Resource Group: " + rgName);
                    azure.resourceGroups().deleteByName(rgName);
                    System.out.println("Deleted Resource Group: " + rgName);
                } else {
                    System.out.println("Did not create any resources in Azure. No clean up is necessary");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private ManageCdn() {
    }
}
