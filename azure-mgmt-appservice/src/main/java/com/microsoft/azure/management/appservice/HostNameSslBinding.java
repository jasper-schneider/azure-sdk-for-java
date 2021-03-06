/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.management.appservice;

import com.microsoft.azure.management.apigeneration.Fluent;
import com.microsoft.azure.management.keyvault.Vault;
import com.microsoft.azure.management.resources.fluentcore.arm.models.ChildResource;
import com.microsoft.azure.management.resources.fluentcore.model.Attachable;
import com.microsoft.azure.management.resources.fluentcore.model.Wrapper;

import java.io.File;

/**
 * A Host name - SSL certificate binding definition.
 */
@Fluent
public interface HostNameSslBinding extends
    Wrapper<HostNameSslState>,
    ChildResource<WebAppBase<?>> {
    /**
     * @return the SSL type
     */
    SslState sslState();

    /**
     * @return the virtual IP address assigned to the host name if IP based SSL is enabled
     */
    String virtualIP();

    /**
     * @return the SSL cert thumbprint.
     */
    String thumbprint();

    /**
     * The entirety of a hostname SSL binding definition.
     * @param <ParentT> the return type of the final {@link Attachable#attach()}
     */
    interface Definition<ParentT> extends
            DefinitionStages.Blank<ParentT>,
            DefinitionStages.WithHostname<ParentT>,
            DefinitionStages.WithCertificate<ParentT>,
            DefinitionStages.WithKeyVault<ParentT>,
            DefinitionStages.WithSslType<ParentT>,
            DefinitionStages.WithAttach<ParentT> {
    }

    /**
     * Grouping of hostname SSL binding definition stages applicable as part of a web app creation.
     */
    interface DefinitionStages {
        /**
         * The first stage of a hostname SSL binding definition.
         * @param <ParentT> the return type of the final {@link WithAttach#attach()}
         */
        interface Blank<ParentT> extends WithHostname<ParentT> {
        }

        /**
         * The stage of a hostname SSL binding definition allowing hostname to be specified.
         * @param <ParentT> the return type of the final {@link WithAttach#attach()}
         */
        interface WithHostname<ParentT> {
            /**
             * Specifies the hostname to bind SSL certificate to.
             * @param hostname the naked hostname, excluding "www". But use *. prefix for wild card typed certificate order.
             * @return the next stage of the hostname SSL binding definition
             */
            WithCertificate<ParentT> forHostname(String hostname);
        }

        /**
         * The stage of a hostname SSL binding definition allowing certificate information to be specified.
         * @param <ParentT> the return type of the final {@link WithAttach#attach()}
         */
        interface WithCertificate<ParentT> {
            /**
             * Uploads a PFX certificate.
             * @param pfxFile the PFX certificate file to upload
             * @param password the password to the certificate
             * @return the next stage of the hostname SSL binding definition
             */
            WithSslType<ParentT> withPfxCertificateToUpload(File pfxFile, String password);

            /**
             * Places a new App Service certificate order to use for the hostname.
             * @param certificateOrderName the name of the certificate order
             * @return the next stage of the hostname SSL binding definition
             */
            WithKeyVault<ParentT> withNewStandardSslCertificateOrder(String certificateOrderName);

            /**
             * Specifies a ready-to-use certificate order to use. This is usually useful for reusing wildcard certificates.
             * @param certificateOrder the ready-to-use certificate order
             * @return the next stage of the hostname SSL binding definition
             */
            WithSslType<ParentT> withExistingAppServiceCertificateOrder(AppServiceCertificateOrder certificateOrder);
        }

        /**
         * The stage of a hostname SSL binding definition allowing key vault for certificate store to be specified.
         * @param <ParentT> the return type of the final {@link WithAttach#attach()}
         */
        interface WithKeyVault<ParentT> {
            /**
             * Stores the certificate in an existing vault.
             * @param vault the existing vault to use
             * @return the next stage of the hostname SSL binding definition
             */
            WithSslType<ParentT> withExistingKeyVault(Vault vault);

            /**
             * Creates a new key vault to store the certificate.
             * @param vaultName the name of the key vault to create
             * @return the next stage of the hostname SSL binding definition
             */
            WithSslType<ParentT> withNewKeyVault(String vaultName);
        }

        /**
         * The stage of a hostname SSL binding definition allowing SSL type to be specified.
         * @param <ParentT> the return type of the final {@link WithAttach#attach()}
         */
        interface WithSslType<ParentT> {
            /**
             * Uses Server Name Indication (SNI) based SSL.
             * @return the next stage of the hostname SSL binding definition
             */
            WithAttach<ParentT> withSniBasedSsl();

            /**
             * Uses IP based SSL. Only one hostname can be bound to IP based SSL.
             * @return the next stage of the hostname SSL binding definition
             */
            WithAttach<ParentT> withIpBasedSsl();
        }

        /**
         * The final stage of the hostname SSL binding definition.
         * <p>
         * At this stage, any remaining optional settings can be specified, or the hostname SSL binding definition
         * can be attached to the parent web app definition using {@link WithAttach#attach()}.
         * @param <ParentT> the return type of {@link WithAttach#attach()}
         */
        interface WithAttach<ParentT> extends
                Attachable.InDefinition<ParentT> {
        }
    }

    /**
     * The entirety of a hostname SSL binding definition as part of a web app update.
     * @param <ParentT> the return type of the final {@link UpdateDefinitionStages.WithAttach#attach()}
     */
    interface UpdateDefinition<ParentT> extends
            UpdateDefinitionStages.Blank<ParentT>,
            UpdateDefinitionStages.WithHostname<ParentT>,
            UpdateDefinitionStages.WithCertificate<ParentT>,
            UpdateDefinitionStages.WithKeyVault<ParentT>,
            UpdateDefinitionStages.WithSslType<ParentT>,
            UpdateDefinitionStages.WithAttach<ParentT> {
    }

    /**
     * Grouping of hostname SSL binding definition stages applicable as part of a web app update.
     */
    interface UpdateDefinitionStages {
        /**
         * The first stage of a hostname SSL binding definition.
         * @param <ParentT> the return type of the final {@link WithAttach#attach()}
         */
        interface Blank<ParentT> extends WithHostname<ParentT> {
        }

        /**
         * The stage of a hostname SSL binding definition allowing hostname to be specified.
         * @param <ParentT> the return type of the final {@link WithAttach#attach()}
         */
        interface WithHostname<ParentT> {
            /**
             * Specifies the hostname to bind SSL certificate to.
             * @param hostname the naked hostname, excluding "www". But use *. prefix for wild card typed certificate order.
             * @return the next stage of the hostname SSL binding definition
             */
            WithCertificate<ParentT> forHostname(String hostname);
        }

        /**
         * The stage of a hostname SSL binding definition allowing certificate information to be specified.
         * @param <ParentT> the return type of the final {@link WithAttach#attach()}
         */
        interface WithCertificate<ParentT> {
            /**
             * Uploads a PFX certificate.
             * @param pfxFile the PFX certificate file to upload
             * @param password the password to the certificate
             * @return the next stage of the hostname SSL binding definition
             */
            WithSslType<ParentT> withPfxCertificateToUpload(File pfxFile, String password);

            /**
             * Places a new App Service certificate order to use for the hostname.
             * @param certificateOrderName the name of the certificate order
             * @return the next stage of the hostname SSL binding definition
             */
            WithKeyVault<ParentT> withNewStandardSslCertificateOrder(String certificateOrderName);

            /**
             * Specifies a ready-to-use certificate order to use. This is usually useful for reusing wildcard certificates.
             * @param certificateOrder the ready-to-use certificate order
             * @return the next stage of the hostname SSL binding definition
             */
            WithSslType<ParentT> withExistingAppServiceCertificateOrder(AppServiceCertificateOrder certificateOrder);
        }

        /**
         * The stage of a hostname SSL binding definition allowing key vault for certificate store to be specified.
         * @param <ParentT> the return type of the final {@link WithAttach#attach()}
         */
        interface WithKeyVault<ParentT> {
            /**
             * Stores the certificate in an existing vault.
             * @param vault the existing vault to use
             * @return the next stage of the hostname SSL binding definition
             */
            WithSslType<ParentT> withExistingKeyVault(Vault vault);

            /**
             * Creates a new key vault to store the certificate.
             * @param vaultName the name of the key vault to create
             * @return the next stage of the hostname SSL binding definition
             */
            WithSslType<ParentT> withNewKeyVault(String vaultName);
        }

        /**
         * The stage of a hostname SSL binding definition allowing SSL type to be specified.
         * @param <ParentT> the return type of the final {@link WithAttach#attach()}
         */
        interface WithSslType<ParentT> {
            /**
             * Uses Server Name Indication (SNI) based SSL.
             * @return the next stage of the hostname SSL binding definition
             */
            WithAttach<ParentT> withSniBasedSsl();

            /**
             * Uses IP based SSL. Only one hostname can be bound to IP based SSL.
             * @return the next stage of the hostname SSL binding definition
             */
            WithAttach<ParentT> withIpBasedSsl();
        }

        /**
         * The final stage of the hostname SSL binding definition.
         * <p>
         * At this stage, any remaining optional settings can be specified, or the hostname SSL binding definition
         * can be attached to the parent web app update using {@link WithAttach#attach()}.
         * @param <ParentT> the return type of {@link WithAttach#attach()}
         */
        interface WithAttach<ParentT> extends
                Attachable.InUpdate<ParentT> {
        }
    }
 }
