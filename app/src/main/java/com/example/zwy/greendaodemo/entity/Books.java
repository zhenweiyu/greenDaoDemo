package com.example.zwy.greendaodemo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Zhen Weiyu on 2017/3/20.
 */
@Entity(nameInDb = "books")

public class Books {

    @Id
    private Long id;

    @Property(nameInDb = "BOOK_NAME")
    @NotNull
    private String bookName;

    @Property(nameInDb = "AUTHOR")
    private String author;


    private int price;


    @Generated(hash = 1784410390)
    public Books(Long id, @NotNull String bookName, String author, int price) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
    }


    @Generated(hash = 2016280518)
    public Books() {
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getBookName() {
        return this.bookName;
    }


    public void setBookName(String bookName) {
        this.bookName = bookName;
    }


    public String getAuthor() {
        return this.author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }


    public int getPrice() {
        return this.price;
    }


    public void setPrice(int price) {
        this.price = price;
    }


}
