package eapli.base.warehousemanagement.domain;


import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Model implements ValueObject, Comparable<Model> {

    private String model;

    protected Model() {
    }

    @Override
    public String toString(){
        return model;
    }

    @Override
    public int hashCode() {
        return this.model.hashCode();
    }

    @Override
    public int compareTo(Model o) {
        return model.compareTo(o.model);
    }
}
