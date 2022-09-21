package com.marchand.githubrepoviewer.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marchand.githubrepoviewer.R
import com.marchand.githubrepoviewer.daos.RepoDao
import com.marchand.githubrepoviewer.db.RepoDatabase
import com.marchand.githubrepoviewer.entities.Repo
import com.marchand.githubrepoviewer.model.ReposDataItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ReposAdapter: RecyclerView.Adapter<ReposAdapter.MyViewHolder>() {

    private var reposList: List<ReposDataItem>? = null
    private lateinit var repoDao: RepoDao

    fun setReposList(reposList: List<ReposDataItem>?) {
        this.reposList = reposList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repos_item, parent, false)

        repoDao = RepoDatabase.getInstance(parent.context).repoDao
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvRepoName.text = reposList?.get(position)?.full_name
        holder.tvDescription.text = reposList?.get(position)?.description
        holder.tvStars.text = reposList?.get(position)?.stargazers_count.toString()
        holder.tvLanguage.text = reposList?.get(position)?.language
        CoroutineScope(Job() + Dispatchers.Main).launch {
            Glide.with(holder.imgAvatar.context)
                .load(reposList?.get(position)?.owner?.avatar_url)
                .into(holder.imgAvatar);
        }
        holder.cardView.setOnClickListener {
            startActivity(
                holder.cardView.context,
                Intent(Intent.ACTION_VIEW).setData(Uri.parse(reposList?.get(position)?.html_url)),
                null
            )
        }
        holder.btnFavoritar.setOnClickListener{
            CoroutineScope(Job() + Dispatchers.IO).launch {
                val repo = reposList?.get(position)!!
                repoDao.insertRepo(Repo(
                    repo.id,
                    repo.full_name,
                    repo.description?: "",
                    repo.stargazers_count,
                    repo.owner.avatar_url,
                    repo.language?: "",
                    repo.html_url
                ))
            }
            holder.btnFavoritar.isEnabled = false
            holder.btnFavoritar.setAlpha(0.35f)
        }
    }

    override fun getItemCount(): Int {
        if (reposList == null)
            return 0
        return reposList!!.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRepoName: TextView = itemView.findViewById(R.id.tv_repo_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        val imgAvatar: ImageView = itemView.findViewById(R.id.imgview_avatar)
        val tvLanguage: TextView = itemView.findViewById(R.id.tv_language)
        val tvStars: TextView = itemView.findViewById(R.id.tv_stars)
        val cardView: CardView = itemView.findViewById(R.id.card_view)
        val btnFavoritar: Button = itemView.findViewById(R.id.btn_favoritar)
    }
}