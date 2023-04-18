package com.example.demo.classes;

// @JsonRootName(value = "airline")
public class Airline {
    private String country;
    private String iata;
    private String name;
    private String callsign;
    private String icao;
    private int id;
    private String type;

    // public Airline(String country, String iata, String name, String callsign, String icao, int id, String type) {
    //     this.country = country;
    //     this.iata = iata;
    //     this.name = name;
    //     this.callsign = callsign;
    //     this.icao = icao;
    //     this.id = id;
    //     this.type = type;
    // }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getIata() {
        return iata;
    }
    public void setIata(String iata) {
        this.iata = iata;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCallsign() {
        return callsign;
    }
    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }
    public String getIcao() {
        return icao;
    }
    public void setIcao(String icao) {
        this.icao = icao;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return "Airline [country=" + country + ", iata=" + iata + ", name=" + name + ", callsign=" + callsign
                + ", icao=" + icao + ", id=" + id + ", type=" + type + "]";
    }

}
