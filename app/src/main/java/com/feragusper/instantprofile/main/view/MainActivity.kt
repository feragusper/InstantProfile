package com.feragusper.instantprofile.main.view

import android.os.Bundle
import com.feragusper.instantprofile.R
import com.feragusper.instantprofile.commons.featureflag.FeatureFlag
import com.feragusper.instantprofile.commons.featureflag.RuntimeBehavior
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile)

        val bottomNavigationMenu = bottom_navigation.menu
        if (!RuntimeBehavior.isFeatureEnabled(FeatureFlag.PROJECTS)) {
            bottomNavigationMenu.removeItem(R.id.projects)
        }
        if (!RuntimeBehavior.isFeatureEnabled(FeatureFlag.RESUME)) {
            bottomNavigationMenu.removeItem(R.id.resume)
        }
        if (!RuntimeBehavior.isFeatureEnabled(FeatureFlag.MUSIC)) {
            bottomNavigationMenu.removeItem(R.id.music)
        }

//        val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
//        remoteConfig.fetchAndActivate()
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    val bottomNavigationMenu = bottom_navigation.menu
//                    if (!RuntimeBehavior.isFeatureEnabled(FeatureFlag.PROJECTS)) {
//                        bottomNavigationMenu.removeItem(R.id.projects)
//                    }
//                    if (!RuntimeBehavior.isFeatureEnabled(FeatureFlag.RESUME)) {
//                        bottomNavigationMenu.removeItem(R.id.resume)
//                    }
//                    if (!RuntimeBehavior.isFeatureEnabled(FeatureFlag.MUSIC)) {
//                        bottomNavigationMenu.removeItem(R.id.music)
//                    }
//                } else {
//                    Timber.e(firebase)
//                }
//            }
    }
}