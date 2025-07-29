package com.example.newsaggregator
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import android.view.View
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import android.widget.ImageView

class MainActivity : ComponentActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var button: Button
    private val apiKey = "6e65d79e98774313b8634605dd3be3ff"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.newsRecyclerView)
        button = findViewById(R.id.btnLoadNews)

        recyclerView.layoutManager = LinearLayoutManager(this)

        button.setOnClickListener {
            loadNews()
        }
    }


    private fun loadNews() {
        val interceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                .build()
            chain.proceed(request)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val api = retrofit.create(NewsApiService::class.java)

        val emptyImage = findViewById<ImageView>(R.id.emptyImage)

        api.getTopHeadlines(apiKey = apiKey).enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                Log.d("API_RESPONSE", "Código HTTP: ${response.code()}")
                Log.d("API_RESPONSE", "Body: ${response.body()}")

                if (response.isSuccessful) {
                    val articles = response.body()?.articles ?: emptyList()
                    Log.d("API_RESPONSE", "Número de artigos: ${articles.size}")

                    if (articles.isNotEmpty()) {
                        emptyImage.visibility = View.GONE
                        recyclerView.adapter = NewsAdapter(articles)
                    } else {
                        emptyImage.visibility = View.VISIBLE
                        recyclerView.adapter = null
                    }
                } else {
                    Log.e("API_RESPONSE", "Resposta não foi sucesso: ${response.errorBody()?.string()}")
                    emptyImage.visibility = View.VISIBLE
                    recyclerView.adapter = null
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("API_ERROR", "Erro na chamada da API", t)
                emptyImage.visibility = View.VISIBLE
                recyclerView.adapter = null
            }
        })
    }



}
