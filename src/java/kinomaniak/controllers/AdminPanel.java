/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kinomaniak.controllers;

import java.util.ArrayList;
import kinomaniak.beans.*;

/**
 *
 * @author Jakub
 */
public class AdminPanel {
    BeanManager beanManager;

    public BeanManager getBeanManager() {
        return beanManager;
    }

    public void setBeanManager(BeanManager beanManager) {
        this.beanManager = beanManager;
    }

    /**
     * Creates a new instance of AdminPanel
     */
    public AdminPanel() {
    }
    
    private String name;
    private float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public void addAttraction(){
        Attraction a = new Attraction();
        a.setName(name);
        a.setPrice(price);
        beanManager.getDb().save(a);
    }
    
    private String title;
    private String genre;
    private String rating;
    private String desc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public void addMovie(){
        Movie m = new Movie();
        m.setName(title);
        m.setGenre(genre);
        m.setRating(rating);
        m.setDesc(desc);
        beanManager.getDb().save(m);
    }
    
    private int type;
    private int count;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public void addProduct(){
        Product p = new Product();
        p.setCount(count);
        p.setType(type);
        p.setName(name);
        p.setPrice(price);
        beanManager.getDb().save(p);
    }
    
    private ArrayList<String> movieIds;
    private ArrayList<String> roomIds;

    public ArrayList<String> getMovieIds() {
        return movieIds;
    }

    public void setMovieIds(ArrayList<String> movieIds) {
        this.movieIds = movieIds;
    }

    public ArrayList<String> getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(ArrayList<String> roomIds) {
        this.roomIds = roomIds;
    }
    
    public void addShow(){
        Show s = new Show();
        for(Movie m: beanManager.movies)
            if(m.getId() == Integer.valueOf(getMovieIds().get(0))){ 
                s.setMov(m);
                break;
            }
        for(CRoom cr: beanManager.rooms)
            if(cr.getId() == Integer.valueOf(getRoomIds().get(0))){
                s.setRoom(cr);
                break;
            }
    }
    
    private String user;
    private String password;
    private int utype;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUtype() {
        return utype;
    }

    public void setUtype(int utype) {
        this.utype = utype;
    }
    
    public void addUser(){
        User u = new User();
        u.setName(user);
        u.setPassword(password);
        u.setUtype(utype);
        beanManager.getDb().save(u);
    }
    
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void deleteUser(){
        beanManager.getDb().delete("User", id);
    }
    
    public void deleteProduct(){
        beanManager.getDb().delete("Product", id);        
    }
    
    public void deleteMovie(){
        beanManager.getDb().delete("Movie", id);        
    }
    
    public void deleteShow(){
        beanManager.getDb().delete("Show", id);        
    }
    
    public void deleteAttration(){
        beanManager.getDb().delete("Attraction", id);        
    }
    
    
}
