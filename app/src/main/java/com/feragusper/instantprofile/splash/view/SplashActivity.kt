package com.feragusper.instantprofile.splash.view

import android.content.Intent
import android.os.Bundle
import com.feragusper.instantprofile.R
import com.feragusper.instantprofile.commons.featureflag.FeatureFlagManager
import com.feragusper.instantprofile.main.view.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var featureFlagManager: FeatureFlagManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        GlobalScope.launch {
            featureFlagManager.refreshFeatureFlags()
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }

    }
}