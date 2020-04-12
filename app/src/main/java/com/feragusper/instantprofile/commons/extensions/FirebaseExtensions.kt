package com.feragusper.instantprofile.commons.extensions

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.feragusper.instantprofile.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.storage.FirebaseStorage

fun DataSnapshot.getChildStringValue(keyName: String): String {
    return getChildValue(keyName).value.toString()
}

fun DataSnapshot.getChildValue(keyName: String): DataSnapshot {
    return this.child(keyName)
}

fun DataSnapshot.getChildrenValue(keyName: String): Iterable<DataSnapshot> {
    return getChildValue(keyName).children
}

fun ImageView.loadFirebaseStorageUrl(firebaseStorageUrl: String, requestOptions: RequestOptions? = null, origin: String = "unknown") {
    try {
        context?.let { contextNotNull ->
            val requestBuilder = Glide.with(contextNotNull)
                .load(
                    FirebaseStorage
                        .getInstance()
                        .getReferenceFromUrl(firebaseStorageUrl)
                )
            requestOptions?.let { requestOption ->
                requestBuilder.apply(requestOption)
            }
            requestBuilder.placeholder(R.drawable.ic_f)
                .into(this)
        }
    } catch (e: Exception) {
        Log.e("FirebaseExtensions", "An error has occurred while trying to load image view using glide and firebase storage. URL is $firebaseStorageUrl - Origin is $origin")
    }
}