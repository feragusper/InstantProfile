package com.feragusper.instantprofile.projects.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.feragusper.instantprofile.R
import com.feragusper.instantprofile.commons.view.BaseFragment
import com.feragusper.instantprofile.projects.domain.model.Project
import com.feragusper.instantprofile.projects.view.adapter.ProjectListAdapter
import com.feragusper.instantprofile.projects.viewmodel.ProjectListViewModel
import goToUrl
import kotlinx.android.synthetic.main.fragment_project_list.*

class ProjectListFragment : BaseFragment<ProjectListViewModel, ProjectListViewModel.State>() {

    private lateinit var contactsListSkeleton: Skeleton
    private lateinit var projectListAdapter: ProjectListAdapter

    override fun getLayoutId() = R.layout.fragment_project_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchAllProjects()
        projectListAdapter = ProjectListAdapter { project ->
            goToProjectDetail(project)
        }
        projectList.apply {
            adapter = projectListAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun goToProjectDetail(project: Project) {
        goToUrl(project.linkUrl)
    }

    override fun onStateChanged(state: ProjectListViewModel.State) {
        when (state) {
            is ProjectListViewModel.State.Loading -> {
                contactsListSkeleton = projectList.applySkeleton(R.layout.item_project, 5)
                contactsListSkeleton.showSkeleton()
//                contactsListSkeleton.maskCornerRadius = resources.getInteger(R.integer.recycler_views_image_circle_radius).toFloat()
            }
            is ProjectListViewModel.State.Success -> {
                contactsListSkeleton.showOriginal()
                projectListAdapter.setProjectList(state.projectList)
            }
            is ProjectListViewModel.State.Error -> Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
        }
    }
}