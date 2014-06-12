/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kinomaniak.beans;

import java.io.Serializable;
import org.jdom2.Element;

/**
 * Klasa reprezentująca czas rozpoczęscia seansu
 * @author qbass
 */
public class Time implements Serializable{

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.hour;
        hash = 79 * hash + this.minute;
        hash = 79 * hash + this.day;
        hash = 79 * hash + this.month;
        hash = 79 * hash + this.year;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Time other = (Time) obj;
        if (this.hour != other.hour) {
            return false;
        }
        if (this.minute != other.minute) {
            return false;
        }
        if (this.day != other.day) {
            return false;
        }
        if (this.month != other.month) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        return true;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Time() {
    }
    private static final long serialVersionUID = 2L;
    private int id;
    private int hour;
    private int minute;
    private int day;
    private int month;
    private int year;
    
    public int getId(){
        return this.id;
    }
        
    public Element toXML(){
        Element res = new Element("Time");
        res.addContent(new Element("hour").setText(String.valueOf(this.hour)));
        res.addContent(new Element("minute").setText(String.valueOf(this.minute)));
        res.addContent(new Element("day").setText(String.valueOf(this.day)));
        res.addContent(new Element("month").setText(String.valueOf(this.month)));
        res.addContent(new Element("year").setText(String.valueOf(this.year)));
        return res;
    }
    
    public Time(Element node){
        if(!node.getName().equals("Time")){
//            throw new RuntimeException("Wrong element type");            
            System.out.println("Wrong element type: Time, got: "+node.getName());
        }
        
        this.hour = Integer.valueOf(node.getChildText("hour"));
        this.minute = Integer.valueOf(node.getChildText("minute"));
        this.day = Integer.valueOf(node.getChildText("day"));
        this.month = Integer.valueOf(node.getChildText("month"));
        this.year = Integer.valueOf(node.getChildText("year"));
    }
        
    /**
     * Konstruktor klasy czasu dla ustawienia godziny
     * @param hour dokładna godzina
     * @param minute dokładna minuta
     */
    public Time(int hour,int minute){
            this.hour = hour;
            this.minute = minute;
        }        
    public Time(int id, int hour, int minute){
        this.id = id;
        this.hour = hour;
        this.minute = minute;
    }
    /**
     * Konstruktor klasy czasu dla ustawienia daty i godziny
     * @param hour godzina
     * @param minute minuta
     * @param day dzień
     * @param month miesiąc
     * @param year rok
     */
    public Time(int hour,int minute,int day,int month,int year){
        this.hour = hour;
        this.minute  =minute;
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    public Time(int id, int hour,int minute,int day,int month,int year){
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.day = day;
        this.month = month;
        this.year = year;
    }
    /**
     * Pobiera godzinę z klasy {@link #Time}.
     * @return hour godzina rozpoczęcia
     */
    public int getHour(){
            return this.hour;
        }
    /**
     * Pobiera minutę z klasy {@link #Time}.
     * @return minute minuta rozpoczęcia
     */
    public int getMinute(){
            return this.minute;
        }
    /**
     * Pobiera dzień z klasy  {@link #Time}.
     * @return day
     */
    public int getDay(){
        return this.day;
    }
    /**
     * Pobiera miesiąc z klasy {@link #Time}.
     * @return month
     */
    public int getMonth(){
        return this.month;
    }
    /**
     * Pobiera rok z klasy  {@link #Time}.
     * @return year
     */
    public int getYear(){
        return this.year;
    }
    }
