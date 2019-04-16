package com.semanienterprise.softcom.ui.url;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.semanienterprise.softcom.R;
import com.semanienterprise.softcom.models.FormData;
import com.semanienterprise.softcom.retrofit.network.ApiClient;
import com.semanienterprise.softcom.retrofit.service.ApiService;
import com.semanienterprise.softcom.ui.display.DisplayPages;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.json_url)
    TextInputEditText jsonUrl;
    @BindView(R.id.json_url_InputLayout)
    TextInputLayout jsonUrlInputLayout;
    private ApiService service;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //init method
        init();
    }

    private void init() {
        service = ApiClient.getClient().create(ApiService.class);
    }

    @OnClick(R.id.fetchJson)
    public void onViewClicked() {
        final String JSONURL = jsonUrl.getText().toString();
        if (JSONURL.isEmpty()) {
            jsonUrlInputLayout.setError("URL cannot be empty");
        } else {
            jsonUrlInputLayout.setError(null);
            fetchData(JSONURL);
        }
    }

    private void fetchData(String JSONURL) {
        try {
            service.getJson(JSONURL)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<FormData>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposable = d;
                        }

                        @Override
                        public void onSuccess(FormData formData) {
                            Intent intent = new Intent(MainActivity.this, DisplayPages.class);
                            intent.putExtra("formData", formData);
                            startActivity(intent);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("Error", e.getMessage());
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
