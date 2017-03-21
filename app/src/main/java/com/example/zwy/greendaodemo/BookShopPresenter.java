package com.example.zwy.greendaodemo;

import android.content.Context;

import com.example.zwy.greendaodemo.entity.Books;
import com.example.zwy.greendaodemo.entity.BooksDao;
import com.example.zwy.greendaodemo.entity.DaoMaster;
import com.example.zwy.greendaodemo.entity.DaoSession;

import java.util.List;


/**
 * Created by Zhen Weiyu on 2017/3/20.
 */

public class BookShopPresenter implements BookShopContract.Presenter {

    private BookShopContract.View view;

    private Context mContext;

    private BooksDao booksDao;

    public BookShopPresenter(Context context, BookShopContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        this.mContext = context;
    }


    @Override
    public void addBook(Books books) {
        this.booksDao.insert(books);
        queryBook();
    }

    @Override
    public void deleteBook(Books books) {
        List<Books> list = this.booksDao.queryBuilder().where(BooksDao.Properties.BookName.eq(books.getBookName())).build().list();
        for (Books book : list) {
            this.booksDao.delete(book);
        }
        queryBook();
    }

    @Override
    public void updatePrice(Books books, int newPrice) {
        Books temp = this.booksDao.queryBuilder().where(BooksDao.Properties.BookName.eq(books.getBookName()), BooksDao.Properties.Author.
                eq(books.getAuthor())).build().unique();
        temp.setPrice(newPrice);
        this.booksDao.update(temp);
        queryBook();
    }

    @Override
    public void queryBook() {
        List<Books> list = this.booksDao.loadAll();
        view.refreshListView(list);
    }

    @Override
    public void queryBook(String name) {
        List<Books>list = this.booksDao.queryBuilder().where(BooksDao.Properties.BookName.eq(name)).build().list();
        view.refreshListView(list);
    }

    @Override
    public void start() {
        initDB();
    }

    @Override
    public void release() {
        this.booksDao.getSession().getDatabase().close();
    }

    private void initDB() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(mContext.getApplicationContext(), "LIBRARY.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        this.booksDao = daoSession.getBooksDao();

    }


}
