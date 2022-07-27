package org.k8s.sample.operator;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.quarkus.runtime.StartupEvent;
import org.k8s.sample.cr.CoffeeResource;
import org.k8s.sample.cr.CoffeeResourceDoneable;
import org.k8s.sample.cr.CoffeeResourceList;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.List;

public class Controller {

    @Inject
    KubernetesClient defaultclient;
    @Inject
    private MixedOperation<CoffeeResource, CoffeeResourceList, CoffeeResourceDoneable, Resource<CoffeeResource, CoffeeResourceDoneable>> crClient;

    void onStartup(@Observes StartupEvent ev) {

        List<CoffeeResource> list = crClient.list().getItems();
        for (CoffeeResource resource : list) {
            System.out.println("Found resource " + resource);
        }

        crClient.watch(new Watcher<CoffeeResource>() {
            @Override
            public void eventReceived(Action action, CoffeeResource resource) {
                System.out.println("Received " + action + " event for resource " + resource);
            }

            @Override
            public void onClose(KubernetesClientException e) {
                if (e != null) {
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        });
    }
}
