package eapli.base.ordersmanagement.warehousemanagement.domain;


import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Route implements ValueObject, Comparable<Route> {

    private String route;

    protected Route() {
    }

    @Override
    public String toString() {
        return route;
    }

    @Override
    public int hashCode() {
        return this.route.hashCode();
    }

    @Override
    public int compareTo(Route o) {
        return route.compareTo(o.route);
    }
}