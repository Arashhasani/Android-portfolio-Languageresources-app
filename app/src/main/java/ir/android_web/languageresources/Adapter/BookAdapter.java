package ir.android_web.languageresources.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import ir.android_web.languageresources.BookItem;
import ir.android_web.languageresources.R;

/**
 * Created by Arash on 12/25/2018.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    ArrayList<BookItem> bookItems;
    Context context;

    public BookAdapter(ArrayList<BookItem> bookItems, Context context) {
        this.bookItems = bookItems;
        this.context = context;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booklayout,null);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BookViewHolder holder, int position) {
        final BookItem bookItem = bookItems.get(position);

        holder.bookname.setText(bookItem.getName());

        Glide.with(context).load(bookItem.getPic()).error(R.drawable.ic_signal_wifi_off_black_24dp).into(holder.bookimg);
//
        holder.bookcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(String .valueOf(bookItem.getLink())));
                context.startActivity(intent);

//                Toast.makeText(context,""+bookItem.getLink(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookItems.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{


        TextView bookname;
        CircleImageView bookimg;
        CardView bookcard;

        public BookViewHolder(View itemView) {
            super(itemView);

            bookname=(TextView)itemView.findViewById(R.id.bookname);
            bookimg=(CircleImageView) itemView.findViewById(R.id.bookimg);
            bookcard=(CardView)itemView.findViewById(R.id.bookcard);
        }
    }
}
