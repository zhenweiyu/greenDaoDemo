package com.example.zwy.greendaodemo.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zwy.greendaodemo.R;
import com.example.zwy.greendaodemo.entity.Books;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhen Weiyu on 2017/3/20.
 */

public class BookListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Books> list;
    private Context context;
    private final int HEADER = 1;
    private final int COMMON = 2;


    public BookListAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setBookList(List<Books> list) {
        this.list.clear();
        this.list.addAll(list);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == HEADER){
            HeaderViewHolder headerViewHolder = new HeaderViewHolder(LayoutInflater.from(context).inflate(R.layout.book_item_header,parent,false));
            return headerViewHolder;
        }else if(viewType == COMMON) {
            BookViewHolder bookViewHolder = new BookViewHolder(LayoutInflater.from(context).inflate(R.layout.book_item_layout,parent,false));
            return bookViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == COMMON) {
            final int realPosition = position-1;
            BookViewHolder bookViewHolder = (BookViewHolder) holder;
            bookViewHolder.name.setText(list.get(realPosition).getBookName());
            bookViewHolder.author.setText(list.get(realPosition).getAuthor());
            bookViewHolder.price.setText(list.get(realPosition).getPrice() + "");
            bookViewHolder.btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemDel(list.get(realPosition));
                    }
                }
            });
            bookViewHolder.btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onItemUpdate(list.get(realPosition));
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return HEADER;
        else return COMMON;
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }


    public static class BookViewHolder extends RecyclerView.ViewHolder {

        public TextView name, author, price;
        public Button btnDel,btnUpdate;

        public BookViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView);
            author = (TextView) itemView.findViewById(R.id.textView2);
            price = (TextView) itemView.findViewById(R.id.textView3);
            btnDel = (Button) itemView.findViewById(R.id.button2);
            btnUpdate = (Button)itemView.findViewById(R.id.button3);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder{

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }


    private ItemOnClickListener listener;

    public interface ItemOnClickListener {
        void onItemDel(Books books);
        void onItemUpdate(Books books);
    }

    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
        listener = itemOnClickListener;
    }

}
