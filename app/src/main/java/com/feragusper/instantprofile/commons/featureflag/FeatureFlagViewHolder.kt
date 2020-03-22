package com.feragusper.instantprofile.commons.featureflag

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_featureflag.view.*

class FeatureFlagViewHolder<T : Feature>(
    view: View,
    private val provider: FeatureFlagProvider,
    private val checkedListener: Function2<Feature, Boolean, Unit>
) : RecyclerView.ViewHolder(view) {

    fun bind(feature: T) {
        itemView.textview_featureflag_title.text = feature.title
        itemView.textview_featureflag_description.text = feature.explanation
        itemView.switch_featureflag.isChecked = provider.isFeatureEnabled(feature)
        itemView.switch_featureflag.setOnCheckedChangeListener { switch, isChecked ->
            if (switch.isPressed) checkedListener.invoke(feature, isChecked)
        }
    }
}