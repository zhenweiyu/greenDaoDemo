package com.example.zwy.greendaodemo;

import com.example.zwy.greendaodemo.entity.Books;
import com.example.zwy.greendaodemo.utils.BasePresenter;
import com.example.zwy.greendaodemo.utils.BaseView;

import java.util.List;

/**
 * Created by Zhen Weiyu on 2017/3/20.
 */

public interface BookShopContract {

    interface View extends BaseView<Presenter>{
       void refreshListView(List<Books> list);
    }


    interface Presenter extends BasePresenter{
        void addBook(Books books);

        void deleteBook(Books books);

        void updatePrice(Books books,int newPrice);

        void queryBook();

        void queryBook(String name);

    }


}
