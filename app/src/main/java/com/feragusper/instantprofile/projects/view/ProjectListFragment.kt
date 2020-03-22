package com.feragusper.instantprofile.projects.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.feragusper.instantprofile.R
import com.feragusper.instantprofile.commons.view.BaseFragment
import com.feragusper.instantprofile.projects.viewmodel.ProjectListViewModel

class ProjectListFragment : BaseFragment<ProjectListViewModel, ProjectListViewModel.State>() {

    override fun getLayoutId() = R.layout.fragment_project_list
    override fun onStateChanged(state: ProjectListViewModel.State) {
        when (state) {
            is ProjectListViewModel.State.Success -> Toast.makeText(context, state.projectList.toString(), Toast.LENGTH_SHORT).show()
            is ProjectListViewModel.State.Error -> Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchAllProjects()
    }
}