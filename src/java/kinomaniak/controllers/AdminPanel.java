/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kinomaniak.controllers;

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
    
    public void addMovie(String title, String genre, String rating, String desc){
        Movie m = new Movie();
        m.setName(title);
        m.setGenre(genre);
        m.setRating(rating);
        m.setDesc(desc);
        beanManager.getDb().save(m);
    }
    
    public void addProduct(String name, int type, float price, int count){
        Product p = new Product();
        p.setCount(count);
        p.setType(type);
        p.setName(name);
        p.setPrice(price);
        beanManager.getDb().save(p);
    }
    
    public void addShow(int movid, int roomid){
        Show s = new Show();
        for(Movie m: beanManager.movies)
            if(m.getId() == movid){ 
                s.setMov(m);
                break;
            }
        for(CRoom cr: beanManager.rooms)
            if(cr.getId() == roomid){
                s.setRoom(cr);
                break;
            }
    }
    
    public void addUser(String user, String password, int utype){
        User u = new User();
        u.setName(user);
        u.setPassword(password);
        u.setUtype(utype);
        beanManager.getDb().save(u);
    }
    
}
