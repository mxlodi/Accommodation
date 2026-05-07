package models;

public interface Displayable {
    String getSummary();

    default void display() {
        System.out.println(getSummary());
    }
}
