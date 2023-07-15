package com.sata.izonovel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.sata.izonovel.Model.ListFavoriteRequest;
import com.sata.izonovel.Model.ListFavoriteResponse;
import com.sata.izonovel.Model.ListNovelRequestModel;
import com.sata.izonovel.Model.ListNovelResponseModel;
import com.sata.izonovel.Model.SearchFavoritRequest;
import com.sata.izonovel.Retrofit.APIService;
import com.sata.izonovel.adpter.DaftarNovelAdapter;
import com.sata.izonovel.adpter.FavoriteNovelAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class favorite extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FavoriteNovelAdapter favoriteNovelAdapter;
    LinearLayoutManager linearLayoutManager;
    private ProgressDialog progressDialog;
    private String keyword;
    private TextInputEditText etCari;
    private LinearLayout btncari;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        setTitle(R.string.title_favorite);

        recyclerView = findViewById(R.id.reycle_favorit_novel);
        btncari = findViewById(R.id.btncari);
        etCari = findViewById(R.id.etCari);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);


        keyword =  "ironman";
        onLoadNovelData();

        btncari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            keyword = etCari.getText().toString();
                onLoadNovelData();
            }
        });
    }
        private void onLoadNovelData(){
            SearchFavoritRequest searchFavoritRequest=new SearchFavoritRequest();
            searchFavoritRequest.setCollection("novel");
            searchFavoritRequest.setDatabase("izonovel");
            searchFavoritRequest.setDataSource("Cluster0");
            searchFavoritRequest.setFilter("");

            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            APIService.endpoint().listfavorit(searchFavoritRequest).enqueue(new Callback<ListFavoriteResponse>() {
                @Override
                public void onResponse(Call<ListFavoriteResponse> call, Response<ListFavoriteResponse> response) {
                    List<ListFavoriteResponse.Documents> documents = response.body().getDocuments();

                    favoriteNovelAdapter = new FavoriteNovelAdapter(favorite.this, documents);
                    recyclerView.setAdapter(favoriteNovelAdapter);
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<ListFavoriteResponse> call, Throwable t) {
                    Log.d("TES GAGAL", t.toString());
                    String pesan =  t.toString() + "\n \nKlik OK untuk memuat ulang data...";
                    progressDialog.dismiss();

                    AlertDialog.Builder builder =  new AlertDialog.Builder(favorite.this);
                    builder.setTitle("Info");
                    builder.setMessage(pesan)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    onLoadNovelData();
                                }
                            });
                    builder.show();
                }
            });
        }
}


