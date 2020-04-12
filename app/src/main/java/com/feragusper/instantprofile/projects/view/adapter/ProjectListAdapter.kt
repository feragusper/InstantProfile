package com.feragusper.instantprofile.projects.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.feragusper.instantprofile.R
import com.feragusper.instantprofile.commons.extensions.loadFirebaseStorageUrl
import com.feragusper.instantprofile.projects.domain.Technology
import com.feragusper.instantprofile.projects.domain.model.Project
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_project.view.*

class ProjectListAdapter(private val itemClickListener: (Project) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val ITEM_VIEW_TYPE_HEADER: Int = 0
        private const val ITEM_VIEW_TYPE_CARD: Int = 1
    }

    private val items = mutableListOf<Project>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_project_list_header, parent, false))
            else -> ProjectViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false))
        }
    }

    override fun getItemCount(): Int = items.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProjectViewHolder) {
            // Position - 1 means the real position (ignoring the header offset)
            holder.bind(items[position - 1])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ITEM_VIEW_TYPE_HEADER
            else -> ITEM_VIEW_TYPE_CARD
        }
    }

    fun setProjectList(projects: List<Project>) {
        items.clear()
        items.addAll(projects)
        notifyDataSetChanged()
    }

    inner class HeaderViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer

    inner class ProjectViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(project: Project) {
            itemView.projectLogo.loadFirebaseStorageUrl(
                firebaseStorageUrl = project.logoFirebaseStorageUrl,
                origin = "projectLogo for project $project"
            )
            itemView.projectName.text = project.name
            itemView.projectDescription.text = project.description
            itemView.projectTechnologiesContainer.bind(project.technologies)
            itemView.setOnClickListener {
                itemClickListener(project)
            }
        }

        @SuppressLint("InflateParams")
        private fun LinearLayout.bind(technologies: List<Technology>) {
            technologies.forEach { technology ->
                val technologyView = LayoutInflater.from(context).inflate(R.layout.item_technology, null) as ImageView
                addView(
                    technologyView.apply {
                        loadFirebaseStorageUrl(
                            firebaseStorageUrl = technology.logoFirebaseStorageUrl,
                            origin = "technology $technology"
                        )
                    }
                )
            }
        }
    }

}
