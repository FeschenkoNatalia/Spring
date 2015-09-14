package com.company;

import javax.persistence.*;

@Entity
@Table(name="Addresses")
public class Address {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch=FetchType.LAZY, mappedBy="address")
    private Client client;

    private String country;
    private String city;
    private int house;

    public Address() {}

    public Address(String country, String city, int house) {
        this.country = country;
        this.city = city;
        this.house = house;
    }

    @Override
    public String toString() {
        return "[" + country + ", " + city + ", " + house + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }
}
