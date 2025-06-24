package main.model;

import java.time.LocalDateTime;

public abstract class Vehicle implements Cloneable {
    protected String licensePlate;
    protected String ownerName;
    protected LocalDateTime entryTime;
    protected LocalDateTime exitTime;

    public Vehicle(String licensePlate, String ownerName) {
        this.licensePlate = licensePlate;
        this.ownerName = ownerName;
    }

    public void setEntryTime(LocalDateTime time) {
        this.entryTime = time;
    }

    public void setExitTime(LocalDateTime time) {
        this.exitTime = time;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    // דפוס Prototype
    public abstract Vehicle clone();
}