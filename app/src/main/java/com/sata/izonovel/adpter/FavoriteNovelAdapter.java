package com.sata.izonovel.adpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sata.izonovel.DetailNovelActivity;
import com.sata.izonovel.Model.ListFavoriteResponse;
import com.sata.izonovel.R;
import com.sata.izonovel.favorite;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteNovelAdapter extends RecyclerView.Adapter<FavoriteNovelAdapter.AdapterHolder>{
    private Context context;

    private List<ListFavoriteResponse.Documents> documentsList;

    public FavoriteNovelAdapter(Context context, List<ListFavoriteResponse.Documents> documentsList){
        this.context = context;
        this.documentsList = documentsList;
    }
    @NonNull
    @Override
    public FavoriteNovelAdapter.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_favorit, parent, false);
        AdapterHolder holder = new AdapterHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteNovelAdapter.AdapterHolder holder, int position) {
    final ListFavoriteResponse.Documents documents1 = documentsList.get(position);

        String judulNovel = documents1.getJudul();
        String tahunDanPengarang = documents1.getTahunTerbit() +" | "+ documents1.getPengarang();
        String sinopsis = documents1.getSinopsis();
        String genre = documents1.getGenre();

        holder.JudulNovel.setText(judulNovel);
        holder.TahunDanPengarang.setText(tahunDanPengarang);
        holder.Sinopsis.setText(trimString(sinopsis));
        holder.Genre.setText(genre);

        String gambar = documents1.getGambar();
        Picasso.get().load(gambar).into(holder.imgPoster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailNovelActivity.class);
                intent.putExtra("id",documents1.get_id());
                intent.putExtra("judul", judulNovel);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return documentsList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView JudulNovel, TahunDanPengarang, Sinopsis, Genre;
        ImageView imgPoster;
        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            JudulNovel = itemView.findViewById(R.id.tvJudulNovel);
            TahunDanPengarang = itemView.findViewById(R.id.tvTahunDanPengarang);
            Sinopsis = itemView.findViewById(R.id.tvSinopsis);
            Genre = itemView.findViewById(R.id.tvGenre);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }
    }
    public String trimString(String item) {
        if (item.length() > 140){
            return  item.substring(0,140 )+"...";
        }
        return item;
    }
}
