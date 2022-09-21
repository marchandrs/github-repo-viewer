package com.marchand.githubrepoviewer.adapters

import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
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

class FavoriteReposAdapter: RecyclerView.Adapter<FavoriteReposAdapter.MyViewHolder>() {

    private var favReposList: List<Repo>? = null
    private lateinit var repoDao: RepoDao

    fun setFavReposList(favReposList: List<Repo>?) {
        this.favReposList = favReposList
    }

    fun getFavReposList(): List<Repo>? {
        return favReposList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fav_repos_item, parent, false)

        repoDao = RepoDatabase.getInstance(parent.context).repoDao
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvRepoName.text = favReposList?.get(position)?.name
        holder.tvDescription.text = favReposList?.get(position)?.description
        holder.tvStars.text = favReposList?.get(position)?.starCount.toString()
        holder.tvLanguage.text = favReposList?.get(position)?.language
        holder.tvLanguage.updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToBottom = R.id.tv_description
        }

        CoroutineScope(Job() + Dispatchers.Main).launch {
            Glide.with(holder.imgAvatar.context)
                .load(favReposList?.get(position)?.avatarUrl)
                .into(holder.imgAvatar);
        }
        holder.cardView.setOnClickListener {
            ContextCompat.startActivity(
                holder.cardView.context,
                Intent(Intent.ACTION_VIEW).setData(Uri.parse(favReposList?.get(position)?.htmlUrl)),
                null
            )
        }
    }

    override fun getItemCount(): Int {
        if (favReposList == null)
            return 0
        return favReposList!!.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRepoName: TextView = itemView.findViewById(R.id.tv_repo_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        val imgAvatar: ImageView = itemView.findViewById(R.id.imgview_avatar)
        val tvLanguage: TextView = itemView.findViewById(R.id.tv_language)
        val tvStars: TextView = itemView.findViewById(R.id.tv_stars)
        val cardView: CardView = itemView.findViewById(R.id.card_view)
    }
}