package dev.edmt.firebasedemo;

/**
 * Created by DIPESH on 4/5/2017.
 */
public class UserData
{
    private String uid;
    private String name;
    private String contact;
    private String date;
    private String time;
    private String bus;
    private String from;
    private String destination;
    private String nop;
    private String service;
    private String seat;
    private String status;
    private String email;
    private String token;


    private String fare;

    public UserData() {}


    public UserData(String uid, String name, String contact,String date,String time,String bus,String from,String destination,String nop,String service,String seat,String status, String email,String fare,String token)
    {
        this.uid = uid;   // Primary key and key
        this.name = name;
        this.contact =contact ;
        this.date =date ;
        this.time =time ;
        this.bus =bus ;
        this.from =from ;
        this.destination =destination ;
        this.nop =nop ;
        this.service =service ;
        this.seat =seat ;
        this.status =status ;
        this.email = email;
        this.fare=fare;
        this.token=token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
