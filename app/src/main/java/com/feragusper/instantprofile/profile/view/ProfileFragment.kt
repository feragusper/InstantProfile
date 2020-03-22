package com.feragusper.instantprofile.profile.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.createSkeleton
import com.feragusper.instantprofile.R
import com.feragusper.instantprofile.commons.view.BaseFragment
import com.feragusper.instantprofile.profile.domain.model.Profile
import com.feragusper.instantprofile.profile.viewmodel.ProfileViewModel
import com.google.firebase.storage.FirebaseStorage
import goToScheduleACall
import goToSendMessage
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment<ProfileViewModel, ProfileViewModel.State>() {

    private var skeleton: Skeleton? = null

    override fun getLayoutId() = R.layout.fragment_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchProfile()
        skeleton = skeletonLayout.createSkeleton()
        skeleton?.showShimmer = true

        profileSendMeAMessage.setOnClickListener {
            this@ProfileFragment.goToSendMessage()
        }

        profileScheduleACall.setOnClickListener {
            this@ProfileFragment.goToScheduleACall()
        }
    }

    override fun onStateChanged(state: ProfileViewModel.State) {
        when (state) {
            is ProfileViewModel.State.Loading -> skeleton?.showSkeleton()
            is ProfileViewModel.State.Success -> renderProfile(state.profile)
            is ProfileViewModel.State.Error -> Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun renderProfile(profile: Profile) {
        context?.let { contextNotNull ->
            Glide.with(contextNotNull)
                .load(
                    FirebaseStorage
                        .getInstance()
                        .getReferenceFromUrl(profile.avatarImageUrl)
                )
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_f)
                .into(profileAvatar)
        }
        profileName.text = profile.name
        profileRole.text = profile.role
        profileResume.text = profile.resume
        skeleton?.showOriginal()
    }
}