/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kinomaniak.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    
    private ArrayList<String> movieIds = new ArrayList<String>();
    private ArrayList<String> roomIds = new ArrayList<String>();
    private String roomId;
    private String movieId;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public ArrayList<String> getMovieIds() {
        if(movieIds.isEmpty()){
            for(Movie mov: beanManager.getMovies())
                movieIds.add(""+mov.getId()+"."+mov.getName());
        }
        return movieIds;
    }

    public void setMovieIds(ArrayList<String> movieIds) {
        System.out.println(movieIds);
        this.movieIds = movieIds;
    }

    public ArrayList<String> getRoomIds() {
        if(roomIds.isEmpty()){
            for(CRoom cr: beanManager.getRooms())
                roomIds.add(String.valueOf(cr.getId()));
        }
        return roomIds;
    }

    public void setRoomIds(ArrayList<String> roomIds) {
        this.roomIds = roomIds;
    }
    
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void addShow(){
        Show s = new Show();
        for(Movie m: beanManager.getMovies())
            if(m.getId() == Integer.valueOf(getMovieId())){ 
                s.setMov(m);
                break;
            }
        for(CRoom cr: beanManager.getRooms())
            if(cr.getId() == Integer.valueOf(getRoomId())){
                s.setRoom(cr);
                break;
            }
        Time tm = new Time(getDate().getHours(), getDate().getMinutes(), getDate().getDay(),
                getDate().getMonth(), getDate().getYear());
        beanManager.getDb().save(tm);
        int timeId;
        ArrayList<Object> obj = beanManager.getDb().parser.load(beanManager.getDb().getConnection(), "Time");
        for(Object o: obj){
            if(o instanceof Time){
                Time t = (Time)o;
                if(t.equals(tm)) s.setTime(t);
            }
        }
        beanManager.getDb().save(s);
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
    
    private ArrayList<String> users = new ArrayList<String>();
    private String userr;

    public String getUserr() {
        return userr;
    }

    public void setUserr(String userr) {
        this.userr = userr;
    }
    
    public ArrayList<String> getUsers() {
        if(users.isEmpty())
            for(User u: beanManager.getUsers())
                users.add(""+u.getId()+": "+u.getName());
            
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }    
    
    public void deleteUser(){
        int id = Integer.parseInt(this.getUserr().split(":")[0]);
        beanManager.getDb().delete("User", id);
    }
    
    
    
    private ArrayList<String> products = new ArrayList<String>();

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
    private String product;
    
    public ArrayList<String> getProducts() {
        if(products.isEmpty())
            for(Product p: beanManager.getProducts())
                products.add(""+p.getId()+": "+p.getName());
        return products;
    }

    public void setProducts(ArrayList<String> products) {
        this.products = products;
    }
        
    public void deleteProduct(){
        int id = Integer.parseInt(this.getProducts().get(0).split(":")[0]);
        beanManager.getDb().delete("Product", id);        
    }
    
    private ArrayList<String> movies = new ArrayList<String>();
    private String movie;

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }
    
    public ArrayList<String> getMovies() {
        if(movies.isEmpty())
            for(Movie m: beanManager.getMovies())
                movies.add(""+m.getId()+": "+m.getName());
        return movies;
    }

    public void setMovies(ArrayList<String> movies) {
        this.movies = movies;
    }
    
    
    public void deleteMovie(){
        System.out.println(Arrays.toString(this.getMovie().split(":")));
        int id = Integer.parseInt(this.getMovie().split(":")[0]);
        System.out.println("MOVIEID:"+id);
        beanManager.getDb().delete("Movie", id);        
    }
    
    private ArrayList<String> shows = new ArrayList<String>();
    private String show;

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public ArrayList<String> getShows() {
        if(shows.isEmpty())
            for(Show s: beanManager.getShows())
                shows.add(""+s.getID()+": "+s.getMovie().getName()+"("+s.getFormatted()+")");
        return shows;
    }

    public void setShows(ArrayList<String> shows) {
        this.shows = shows;
    }
    
    
    public void deleteShow(){        
        int id = Integer.parseInt(this.getShow().split(":")[0]);
        beanManager.getDb().delete("Show", id);        
    }
    
    private ArrayList<String> attractions = new ArrayList<String>();
    private String attraction;

    public String getAttraction() {
        return attraction;
    }

    public void setAttraction(String attraction) {
        this.attraction = attraction;
    }

    public ArrayList<String> getAttractions() {
        if(attractions.isEmpty())
            for(Attraction a: beanManager.getAttractions())
                attractions.add(""+a.getId()+": "+a.getName());
        return attractions;
    }

    public void setAttractions(ArrayList<String> attractions) {
        this.attractions = attractions;
    }
    
    public void deleteAttration(){
        int id = Integer.parseInt(this.getAttraction().split(":")[0]);
        System.out.println("ATTRID:"+id);
        beanManager.getDb().delete("Attraction", id);        
    }
    
    
}
