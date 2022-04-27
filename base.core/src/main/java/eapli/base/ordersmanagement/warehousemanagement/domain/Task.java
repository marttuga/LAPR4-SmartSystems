package eapli.base.ordersmanagement.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Task implements ValueObject, Comparable<Task> {

    private String task;

    protected Task() {
    }

    @Override
    public String toString() {
        return task;
    }

    @Override
    public int hashCode() {
        return this.task.hashCode();
    }

    @Override
    public int compareTo(Task o) {
        return task.compareTo(o.task);
    }
}