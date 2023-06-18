package id.norio.salttest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import id.norio.salttest.databinding.ActivityMainBinding
import id.norio.salttest.response.NewsResponse

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDataNews()
    }

    private fun setDataNews() {
        viewModel.newsData.observe(this) { newsData ->
            if (newsData.articles != null) {
                val listNews = ArrayList<NewsResponse.Article>()
                for (news in newsData.articles) {
                    news?.let { listNews.add(it) }
                }
                val mAdapter = NewsAdapter(listNews)
                binding.rvNews.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = mAdapter
                }

                mAdapter.setOnNewsClickListener(object : NewsAdapter.OnNewsClickListener {
                    override fun onItemClicked(data: NewsResponse.Article) {
                        val intent = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.NEWS_DATA, data)
                        startActivity(intent)
                    }

                })
            }
        }
    }
}