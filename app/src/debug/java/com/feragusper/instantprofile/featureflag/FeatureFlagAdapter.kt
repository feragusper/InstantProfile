package com.feragusper.instantprofile.featureflag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.feragusper.instantprofile.R
import com.feragusper.instantprofile.commons.featureflag.Feature
import com.feragusper.instantprofile.commons.featureflag.configurations.FeatureFlagConfigurations

private class FeatureFlagAdapter<T : Feature>(
    val items: Array<T>,
    val configurations: FeatureFlagConfigurations,
    val checkedListener: Function2<Feature, Boolean, Unit>
) : RecyclerView.Adapter<FeatureFlagViewHolder<T>>() {

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FeatureFlagViewHolder<T>, position: Int) =
        holder.bind(items[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureFlagViewHolder<T> {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_featureflag, parent, false)
        return FeatureFlagViewHolder(itemView, configurations, checkedListener)
    }
}