/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.appservice.implementation;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.microsoft.azure.management.appservice.DeploymentSlot;
import com.microsoft.azure.management.appservice.HostNameBinding;
import com.microsoft.azure.management.appservice.PublishingCredentials;
import com.microsoft.azure.management.appservice.WebApp;
import com.microsoft.azure.management.appservice.WebAppSourceControl;
import rx.Observable;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The implementation for {@link DeploymentSlot}.
 */
class DeploymentSlotImpl
        extends WebAppBaseImpl<DeploymentSlot, DeploymentSlotImpl>
        implements
            DeploymentSlot,
            DeploymentSlot.Definition,
            DeploymentSlot.Update {
    private final WebAppImpl parent;
    private final String name;

    DeploymentSlotImpl(String name, SiteInner innerObject, SiteConfigInner configObject, final WebAppImpl parent, final WebAppsInner client, AppServiceManager manager, WebSiteManagementClientImpl serviceClient) {
        super(name.replaceAll(".*/", ""), innerObject, configObject, client, manager, serviceClient);
        this.name = name.replaceAll(".*/", "");
        this.parent = parent;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public DeploymentSlotImpl refresh() {
        SiteInner inner = client.getSlot(resourceGroupName(), parent.name(), name());
        inner.withSiteConfig(client.getConfigurationSlot(resourceGroupName(), parent.name(), name()));
        setInner(inner);
        return this;
    }

    @Override
    public Map<String, HostNameBinding> getHostNameBindings() {
        List<HostNameBindingInner> collectionInner = client.listHostNameBindingsSlot(resourceGroupName(), parent.name(), name());
        List<HostNameBinding> hostNameBindings = new ArrayList<>();
        for (HostNameBindingInner inner : collectionInner) {
            hostNameBindings.add(new HostNameBindingImpl<>(inner, this, client));
        }
        return Maps.uniqueIndex(hostNameBindings, new Function<HostNameBinding, String>() {
            @Override
            public String apply(HostNameBinding input) {
                return input.name().replace(name() + "/", "");
            }
        });
    }

    @Override
    public void start() {
        client.startSlot(resourceGroupName(), parent.name(), name());
    }

    @Override
    public void stop() {
        client.stopSlot(resourceGroupName(), parent.name(), name());
    }

    @Override
    public void restart() {
        client.restartSlot(resourceGroupName(), parent.name(), name());
    }

    @Override
    public DeploymentSlotImpl withBrandNewConfiguration() {
        SiteConfigInner configInner = new SiteConfigInner();
        configInner.withLocation(regionName());
        inner().withSiteConfig(configInner);
        return this;
    }

    @Override
    public DeploymentSlotImpl withConfigurationFromParent() {
        return withConfigurationFromWebApp(parent);
    }

    @Override
    public DeploymentSlotImpl withConfigurationFromWebApp(WebApp webApp) {
        inner().withSiteConfig(webApp.inner().siteConfig());
        return this;
    }

    @Override
    public DeploymentSlotImpl withConfigurationFromDeploymentSlot(DeploymentSlot deploymentSlot) {
        inner().withSiteConfig(deploymentSlot.inner().siteConfig());
        return this;
    }

    @Override
    public WebAppImpl parent() {
        return parent;
    }

    @Override
    Observable<SiteInner> createOrUpdateInner(SiteInner site) {
        return client.createOrUpdateSlotAsync(resourceGroupName(), parent.name(), name(), site);
    }

    @Override
    Observable<SiteInner> getInner() {
        return client.getSlotAsync(resourceGroupName(), parent.name(), name());
    }

    @Override
    Observable<SiteConfigInner> createOrUpdateSiteConfig(SiteConfigInner siteConfig) {
        return client.createOrUpdateConfigurationSlotAsync(resourceGroupName(), parent.name(), name(), siteConfig);
    }

    @Override
    Observable<Void> deleteHostNameBinding(String hostname) {
        return client.deleteHostNameBindingSlotAsync(resourceGroupName(), parent().name(), name(), hostname);
    }

    @Override
    Observable<StringDictionaryInner> listAppSettings() {
        return client.listApplicationSettingsSlotAsync(resourceGroupName(), parent().name(), name());
    }

    @Override
    Observable<StringDictionaryInner> updateAppSettings(StringDictionaryInner inner) {
        return client.updateApplicationSettingsSlotAsync(resourceGroupName(), parent().name(), name(), inner);
    }

    @Override
    Observable<ConnectionStringDictionaryInner> listConnectionStrings() {
        return client.listConnectionStringsSlotAsync(resourceGroupName(), parent().name(), name());
    }

    @Override
    Observable<ConnectionStringDictionaryInner> updateConnectionStrings(ConnectionStringDictionaryInner inner) {
        return client.updateConnectionStringsSlotAsync(resourceGroupName(), parent().name(), name(), inner);
    }

    @Override
    Observable<SlotConfigNamesResourceInner> listSlotConfigurations() {
        return client.listSlotConfigurationNamesAsync(resourceGroupName(), parent().name());
    }

    @Override
    Observable<SlotConfigNamesResourceInner> updateSlotConfigurations(SlotConfigNamesResourceInner inner) {
        return client.updateSlotConfigurationNamesAsync(resourceGroupName(), parent().name(), inner);
    }

    @Override
    Observable<SiteSourceControlInner> createOrUpdateSourceControl(SiteSourceControlInner inner) {
        return client.createOrUpdateSourceControlSlotAsync(resourceGroupName(), parent().name(), name(), inner);
    }

    @Override
    public void swap(String slotName) {
        client.swapSlotSlot(resourceGroupName(), parent().name(), name(), new CsmSlotEntityInner().withTargetSlot(slotName));
    }

    @Override
    public void applySlotConfigurations(String slotName) {
        client.applySlotConfigurationSlot(resourceGroupName(), parent().name(), name(), new CsmSlotEntityInner().withTargetSlot(slotName));
    }

    @Override
    public void resetSlotConfigurations() {
        client.resetSlotConfigurationSlot(resourceGroupName(), parent().name(), name());
    }

    @Override
    Observable<Void> deleteSourceControl() {
        return client.deleteSourceControlSlotAsync(resourceGroupName(), parent().name(), name()).map(new Func1<Object, Void>() {
            @Override
            public Void call(Object o) {
                return null;
            }
        });
    }

    @Override
    public PublishingCredentials getPublishingCredentials() {
        UserInner inner = client.listPublishingCredentialsSlot(resourceGroupName(), parent().name(), name());
        return new PublishingCredentialsImpl(inner);
    }

    @Override
    public WebAppSourceControl getSourceControl() {
        SiteSourceControlInner siteSourceControlInner = client.getSourceControlSlot(resourceGroupName(), parent().name(), name());
        return new WebAppSourceControlImpl<>(siteSourceControlInner, this, serviceClient);
    }

    @Override
    public void verifyDomainOwnership(String certificateOrderName, String domainVerificationToken) {
        verifyDomainOwnershipAsync(certificateOrderName, domainVerificationToken).toBlocking().subscribe();
    }

    @Override
    public Observable<Void> verifyDomainOwnershipAsync(String certificateOrderName, String domainVerificationToken) {
        IdentifierInner identifierInner = new IdentifierInner().withIdentifierId(domainVerificationToken);
        identifierInner.withLocation("global");
        return client.createOrUpdateDomainOwnershipIdentifierSlotAsync(resourceGroupName(), parent().name(), name(), certificateOrderName, identifierInner)
                .map(new Func1<IdentifierInner, Void>() {
                    @Override
                    public Void call(IdentifierInner identifierInner) {
                        return null;
                    }
                });
    }
}