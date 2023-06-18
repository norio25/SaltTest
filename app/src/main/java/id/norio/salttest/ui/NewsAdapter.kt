package id.norio.salttest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.norio.salttest.databinding.RecyclerviewNewsBinding
import id.norio.salttest.response.NewsResponse

/**
 * Created by Norio on 6/18/2023.
 */
class NewsAdapter(private var listItem: ArrayList<NewsResponse.Article>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var onNewsClickListener: OnNewsClickListener? = null

    fun setOnNewsClickListener(onNewsClickListener: OnNewsClickListener) {
        this.onNewsClickListener = onNewsClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            RecyclerviewNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int = listItem.size

    inner class NewsViewHolder(private val binding: RecyclerviewNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NewsResponse.Article) {
            Glide.with(itemView.context).load(data.urlToImage).into(binding.ivNews)
            binding.tvName.text = data.source?.name
            binding.tvTitle.text = data.title

            itemView.setOnClickListener { onNewsClickListener?.onItemClicked(data) }
        }
    }

    interface OnNewsClickListener {
        fun onItemClicked(data: NewsResponse.Article)
    }
}