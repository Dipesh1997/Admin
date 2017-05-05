package dev.edmt.firebasedemo;
public class FareData
{
    private String bus;
    private String from;
    private String destination;
    private String nop;
    private String service;
    private String seat;
    private String fare;
    private String uid;
    public FareData(){}
    //public FareData(String uid,String bus,String from,String destination,String nop,String service,String seat,String fare)
    public FareData(String bus,String from,String fare)
    {
        //this.uid=uid;
        this.bus =bus ;

        this.from =from ;
        /*
        this.destination =destination ;
        this.nop =nop ;
        this.service =service ;
        this.seat =seat ;
        */
        this.fare=fare;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getBus() {
        return bus;
    }
    public void setBus(String bus) {
        this.bus = bus;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getNop() {
        return nop;
    }
    public void setNop(String nop) {
        this.nop = nop;
    }
    public String getService() {
        return service;
    }
    public void setService(String service) {
        this.service = service;
    }
    public String getSeat() {
        return seat;
    }
    public void setSeat(String seat) {
        this.seat = seat;
    }
    public String getFare() {
        return fare;
    }
    public void setFare(String fare) {
        this.fare = fare;
    }
}
