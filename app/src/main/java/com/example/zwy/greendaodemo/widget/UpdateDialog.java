package com.example.zwy.greendaodemo.widget;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zwy.greendaodemo.R;
import com.example.zwy.greendaodemo.entity.Books;

/**
 * Created by Zhen Weiyu on 2017/3/21.
 */

public class UpdateDialog extends Dialog {

    private TextView editName,editAuthor;
    private EditText editPrice;
    private Button btnCommit,btnCancel;
    private Context context;
    private Books books;
    public UpdateDialog(Context context, final UpdateListener listener, final Books books) {
        super(context);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.book_item_update,null);
        setContentView(view,new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setTitle("修改书籍价格");
        editName = (TextView)view.findViewById(R.id.edit_book_name);
        editAuthor = (TextView)view.findViewById(R.id.edit_author_name);
        editPrice = (EditText)view.findViewById(R.id.edit_price);
        btnCommit = (Button)view.findViewById(R.id.btnCommit);
        btnCancel = (Button)view.findViewById(R.id.btnCancel);
        this.listener = listener;
        this.books = books;
        editName.setText(this.books.getBookName());
        editAuthor.setText(this.books.getAuthor());
        editPrice.setText(this.books.getPrice()+"");
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    if(!TextUtils.isEmpty(editPrice.getText().toString())){
                        if(Integer.parseInt(editPrice.getText().toString())!=books.getPrice()){
                            listener.updateCommit(books,Integer.parseInt(editPrice.getText().toString()));
                        }
                        dismiss();

                    }else{
                        Toast.makeText(getContext(),"请填写新价格",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private UpdateListener listener;

    public interface UpdateListener{
        void updateCommit(Books books,int newPrice);
    }



}
