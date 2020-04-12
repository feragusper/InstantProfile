package com.feragusper.instantprofile.main.view

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.feragusper.instantprofile.R
import com.feragusper.instantprofile.commons.featureflag.FeatureFlag
import com.feragusper.instantprofile.commons.featureflag.FeatureFlagManager
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var featureFlagManager: FeatureFlagManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val bottomNavigatioMmenu = bottomNavigationView.menu
        if (!featureFlagManager.isFeatureEnabled(FeatureFlag.PROJECTS)) {
            bottomNavigatioMmenu.removeItem(R.id.projectListFragment)
        }
        if (!featureFlagManager.isFeatureEnabled(FeatureFlag.RESUME)) {
            bottomNavigatioMmenu.removeItem(R.id.resumeFragment)
        }
        if (!featureFlagManager.isFeatureEnabled(FeatureFlag.MUSIC)) {
            bottomNavigatioMmenu.removeItem(R.id.musicFragment)
        }

        NavigationUI.setupWithNavController(bottomNavigationView, (supportFragmentManager.findFragmentById(R.id.fragNavHost) as NavHostFragment).navController)
    }
}