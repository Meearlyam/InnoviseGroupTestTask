package main.java.by.meearlyam.userseditor.model;

public enum RoleEnum {
    USER(1), CUSTOMER(1), ADMIN(2), PROVIDER(2), SUPER_ADMIN(3);

    private int layer;

    RoleEnum(int layer) {
        this.layer = layer;
    }

    public int getLayer() {
        return this.layer;
    }
}
