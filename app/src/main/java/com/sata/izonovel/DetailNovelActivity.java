package com.sata.izonovel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.sata.izonovel.Model.DetailNovelRequest;
import com.sata.izonovel.Model.DetailNovelResponse;
import com.sata.izonovel.Model.ListNovelRequestModel;
import com.sata.izonovel.Retrofit.APIService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailNovelActivity extends AppCompatActivity {

    TextView tvPengarang, tvPenerbit,  tvTanggal ,tvGenre,tvSinopsis;

    ImageView imgDetailNovel;
    private  ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_novel);

        tvPengarang = findViewById(R.id.det_pengarang);
        tvPenerbit = findViewById(R.id.det_nmpenenerbit);
        tvTanggal = findViewById(R.id.det_thnterbit);
        tvGenre = findViewById(R.id.det_genre);
        tvSinopsis = findViewById(R.id.det_sinopsis);
        imgDetailNovel = findViewById(R.id.imgdetail_novel);

        //digunakan untuk mengambil data kiriman dari activity intent, sebelum nya
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String judul = intent.getStringExtra("judul");
        setTitle("Detail Novel - " + judul);

        Log.d("INFO-id", id);
        Log.d("INFO-judul", judul);

        id = intent.getStringExtra("imdbID");

        detailnovel();
    }
        private void detailnovel() {
             DetailNovelRequest detailNovelRequest=new DetailNovelRequest();
             detailNovelRequest.setCollection("novel");
             detailNovelRequest.setDatabase("izonovel");
             detailNovelRequest.setDataSource("Cluster0");
             detailNovelRequest.setFilter("");

            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            APIService.endpoint().listDetailNovel(detailNovelRequest).enqueue(new Callback<DetailNovelResponse>() {
                @Override
                public void onResponse(Call<DetailNovelResponse> call, Response<DetailNovelResponse> response) {
                tvPengarang.setText("response.body().getPengarang()");
                tvPenerbit.setText("response.body().getPenerbit()");
                tvTanggal.setText("response.body().getTahunTerbit()");
                tvGenre.setText("response.body().getGenre()");
                tvSinopsis.setText("response.body().getSinopsis()");
                Picasso.get().load(response.body().getGambar()).into(imgDetailNovel);
                }

                @Override
                public void onFailure(Call<DetailNovelResponse> call, Throwable t) {
                    Log.d("TES GAGAL", t.toString());
                }
            });

    }
}