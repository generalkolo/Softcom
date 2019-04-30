package com.semanienterprise.softcom.ui.url

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.semanienterprise.softcom.R
import com.semanienterprise.softcom.models.FormData
import com.semanienterprise.softcom.retrofit.service.ApiService
import com.semanienterprise.softcom.ui.display.DisplayPages
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
        val DATA_KEY: String = "DATA_KEY"
    }

    //initialize global variables
    private lateinit var service: ApiService
    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init method
        init()
    }

    private fun init() {
        service = ApiService.create()

        fetchJson.setOnClickListener {
            val JSONURL = etJSON_URL!!.text!!.toString()

            //give feedback error if URL is empty or load entered url
            if (JSONURL.isEmpty()) {
                JSON_URL_InputLayout.error = "URL cannot be empty"
            } else {
                JSON_URL_InputLayout.error = null
                fetchData(JSONURL)
            }
        }
    }

    private fun fetchData(JSON_URL: String) {
        try {
            service.getJson(JSON_URL)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : SingleObserver<FormData> {
                        override fun onSubscribe(d: Disposable) {
                            disposable = d
                        }

                        override fun onSuccess(formData: FormData) {
                            val intent = Intent(this@MainActivity, DisplayPages::class.java)
                            intent.putExtra(DATA_KEY, formData)
                            startActivity(intent)
                        }

                        override fun onError(e: Throwable) {
                            Log.d(TAG, e.message)
                        }
                    })
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
