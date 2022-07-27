package org.k8s.sample.cr;

import io.fabric8.kubernetes.api.builder.Function;
import io.fabric8.kubernetes.client.CustomResourceDoneable;

public class CoffeeResourceDoneable extends CustomResourceDoneable<CoffeeResource> {

    public CoffeeResourceDoneable(CoffeeResource resource, Function<CoffeeResource, CoffeeResource> function) {
        super(resource, function);
    }
}