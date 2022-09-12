package ru.netology.entity;

public class Location {

    private final String city;

    private final Country country;

    private final String street;

    private final int builing;

    public Location(String city, Country country, String street, int builing) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.builing = builing;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    public int getBuiling() {
        return builing;
    }

    @Override
    public int hashCode() {
        int result = getCity() == null ? 0 : getCity().hashCode();
        result = 31 * result + getCountry().hashCode();
        result = 31 * result + getStreet().hashCode();
        result = 31 * result + getBuiling();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Location s = (Location) o;

        if(getCity() == s.getCity() || (getCity()!=null && getCity().equals(s.getCity()))) {
            if(getCountry() == s.getCountry() || (getCountry()!=null && getCountry().equals(s.getCountry()))) {
                if(getStreet() == s.getStreet() || (getStreet()!=null && getStreet().equals(s.getStreet()))) {
                    if(getBuiling()==s.getBuiling()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
