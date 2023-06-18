package id.norio.salttest.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.norio.salttest.R
import id.norio.salttest.databinding.ActivityDetailBinding
import id.norio.salttest.response.NewsResponse

/**
 * Created by Norio on 6/18/2023.
 */
class DetailActivity : AppCompatActivity() {

    private var data: NewsResponse.Article? = null
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val NEWS_DATA = "news_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
    }

    @Suppress("DEPRECATION")
    private fun getData() {
        data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) intent.getParcelableExtra(
            NEWS_DATA, NewsResponse.Article::class.java
        )
        else intent.getParcelableExtra(NEWS_DATA)
        initView()
    }

    private fun initView() {
        binding.toolbar.title = "Detail"
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener { finish() }

        Glide.with(this).load(data?.urlToImage).into(binding.ivDetail)
        binding.tvTitleDetail.text = data?.title
        binding.tvNameDetail.text = data?.source?.name
        binding.tvDescDetail.text = data?.description

        binding.tvNameDetail.setOnClickListener { openBrowser() }
    }

    private fun openBrowser() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(data?.url)
        )
        startActivity(intent)
    }
}