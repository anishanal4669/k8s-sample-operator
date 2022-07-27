package org.k8s.sample.operator;

import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinition;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.internal.KubernetesDeserializer;
import org.k8s.sample.cr.CoffeeResource;
import org.k8s.sample.cr.CoffeeResourceDoneable;
import org.k8s.sample.cr.CoffeeResourceList;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

public class ClientGenerator {

    @Produces
    @Singleton
    KubernetesClient makeDefaultClient(){
        return new DefaultKubernetesClient().inNamespace("default");
    }

    @Produces
    @Singleton
    MixedOperation<CoffeeResource, CoffeeResourceList, CoffeeResourceDoneable, Resource<CoffeeResource, CoffeeResourceDoneable>>
    makeCustomResourceClient(KubernetesClient defaultClient) {
        KubernetesDeserializer.registerCustomKind("cache.anish.com/v1", "coffee", CoffeeResource.class);
        CustomResourceDefinition crd = defaultClient.customResourceDefinitions().list().getItems().stream()
                .findFirst().orElseThrow(RuntimeException::new);
        return defaultClient.customResources(crd, CoffeeResource.class, CoffeeResourceList.class, CoffeeResourceDoneable.class);
    }

}
