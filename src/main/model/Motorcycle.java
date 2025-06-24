package main.model;

public class Motorcycle extends Vehicle {
    private boolean hasHelmetStorage;

    public Motorcycle(String licensePlate, String ownerName, boolean hasHelmetStorage) {
        super(licensePlate, ownerName);
        this.hasHelmetStorage = hasHelmetStorage;
    }

    public boolean hasHelmetStorage() {
        return hasHelmetStorage;
    }

    public void setHasHelmetStorage(boolean hasHelmetStorage) {
        this.hasHelmetStorage = hasHelmetStorage;
    }

    @Override
    public Vehicle clone() {
        return new Motorcycle(this.licensePlate, this.ownerName, this.hasHelmetStorage);
    }
}