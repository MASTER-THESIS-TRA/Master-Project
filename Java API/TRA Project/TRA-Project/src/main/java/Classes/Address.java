package Classes;

public class Address extends Location {

    public String
            country,
            region,
            postalCode,
            city,
            street,
            number;

    public Address(String country, String region, String postalCode, String city, String street, String number) {
        this.country = country;
        this.region = region;
        this.postalCode = postalCode;
        this.city = city;
        this.street = street;
        this.number = number;
    }
}
