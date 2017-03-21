package com.example.zwy.greendaodemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zwy.greendaodemo.entity.Books;
import com.example.zwy.greendaodemo.widget.BookListAdapter;
import com.example.zwy.greendaodemo.widget.DividerItemDecoration;
import com.example.zwy.greendaodemo.widget.UpdateDialog;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookShopFragment extends Fragment implements BookShopContract.View{

    private EditText inputName,inputAuthor,inputPrice,inputNameForSearch;
    private Button btnAdd,btnCheck,btnCheckByName;
    private RecyclerView recyclerView;
    private BookListAdapter bookListAdapter;
    private BookShopContract.Presenter presenter;
    public BookShopFragment() {
        // Required empty public constructor
    }

    public static BookShopFragment newInstance() {
        BookShopFragment fragment = new BookShopFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_green_dao_demo, container, false);
        inputName = (EditText)view.findViewById(R.id.editText);
        inputAuthor = (EditText)view.findViewById(R.id.editText2);
        inputPrice = (EditText)view.findViewById(R.id.editText3);
        btnAdd = (Button)view.findViewById(R.id.button);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        btnCheck = (Button)view.findViewById(R.id.btnCheck);
        inputNameForSearch = (EditText)view.findViewById(R.id.edit_search_by_name);
        btnCheckByName = (Button)view.findViewById(R.id.btn_search_by_name);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),linearLayoutManager.VERTICAL));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(presenter!=null&&!TextUtils.isEmpty(inputName.getText().toString())
                        &&!TextUtils.isEmpty(inputAuthor.getText().toString())
                        &&!TextUtils.isEmpty(inputPrice.getText().toString())){
                    Books books = new Books();
                    books.setBookName(inputName.getText().toString());
                    books.setAuthor(inputAuthor.getText().toString());
                    books.setPrice(Integer.parseInt(inputPrice.getText().toString()));
                    presenter.addBook(books);
                }else {
                    Toast.makeText(getContext(),"请填写完整资料",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(presenter!=null){
                    presenter.queryBook();
                }
            }
        });
        btnCheckByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(presenter!=null&& !TextUtils.isEmpty(inputNameForSearch.getText().toString())){
                    presenter.queryBook(inputNameForSearch.getText().toString());
                }
            }
        });
        bookListAdapter = new BookListAdapter(getContext());
        bookListAdapter.setItemOnClickListener(new BookListAdapter.ItemOnClickListener() {
            @Override
            public void onItemDel(Books books) {
                if(presenter!=null){
                    presenter.deleteBook(books);
                }
            }

            @Override
            public void onItemUpdate(Books books) {
                UpdateDialog dialog = new UpdateDialog(getContext(), new UpdateDialog.UpdateListener() {
                    @Override
                    public void updateCommit(Books books,int newPrice) {
                        if(presenter!=null){
                            presenter.updatePrice(books,newPrice);
                        }
                    }
                },books);
                dialog.show();
            }
        });
        recyclerView.setAdapter(bookListAdapter);

        return view;
    }

    @Override
    public void refreshListView(List<Books> list) {
        if(bookListAdapter!=null){
            bookListAdapter.setBookList(list);
            bookListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setPresenter(BookShopContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(presenter!=null){
            presenter.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.release();
        }
    }
}
