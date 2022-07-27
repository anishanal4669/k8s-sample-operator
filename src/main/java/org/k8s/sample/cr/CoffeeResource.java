package org.k8s.sample.cr;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.client.CustomResource;
import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonDeserialize
@RegisterForReflection
public class CoffeeResource extends CustomResource {

    private CoffeeResourceSpec spec;
    private CoffeeResourceStatus status;

    public CoffeeResourceSpec getSpec() {
        return spec;
    }

    public void setSpec(CoffeeResourceSpec spec) {
        this.spec = spec;
    }

    @Override
    public String toString(){
        return "name=" + getMetadata().getName() + ", version=" + getMetadata().getResourceVersion() + ", spec=" + spec;
    }
}
