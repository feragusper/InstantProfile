package com.feragusper.instantprofile.profile.api

import com.feragusper.instantprofile.commons.extensions.getChildStringValue
import com.feragusper.instantprofile.profile.api.FirebaseDatabaseProfileApi.ProfileEntity.Companion.KEY_AVATAR_IMAGE_FIREBASE_STORAGE_URL
import com.feragusper.instantprofile.profile.api.FirebaseDatabaseProfileApi.ProfileEntity.Companion.KEY_NAME
import com.feragusper.instantprofile.profile.api.FirebaseDatabaseProfileApi.ProfileEntity.Companion.KEY_RESUME
import com.feragusper.instantprofile.profile.api.FirebaseDatabaseProfileApi.ProfileEntity.Companion.KEY_ROLE
import com.feragusper.instantprofile.profile.api.FirebaseDatabaseProfileApi.ProfileEntity.Companion.PATH
import com.feragusper.instantprofile.profile.domain.model.Profile
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseDatabaseProfileApi @Inject constructor() : ProfileApi {

    override suspend fun fetchProfile() = suspendCoroutine<Profile> { cont ->
        val databaseReference = FirebaseDatabase.getInstance().reference

        databaseReference.child(PATH).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                cont.resume(dataSnapshot.toProfile())
                databaseReference.removeEventListener(this)
            }

            override fun onCancelled(error: DatabaseError) {
                cont.resumeWithException(RuntimeException(error.message))
                databaseReference.removeEventListener(this)
            }
        })
    }

    private fun DataSnapshot.toProfile(): Profile {
        return Profile(
            name = getChildStringValue(KEY_NAME),
            role = getChildStringValue(KEY_ROLE),
            resume = getChildStringValue(KEY_RESUME),
            avatarImageFirebaseStorageUrl = getChildStringValue(KEY_AVATAR_IMAGE_FIREBASE_STORAGE_URL)
        )
    }

    private class ProfileEntity {
        companion object {
            const val PATH = "profile"
            const val KEY_NAME = "name"
            const val KEY_ROLE = "role"
            const val KEY_RESUME = "resume"
            const val KEY_AVATAR_IMAGE_FIREBASE_STORAGE_URL = "avatar_image_firebase_storage_url"
        }
    }
}