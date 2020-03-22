package com.feragusper.instantprofile.profile.repository

import com.feragusper.instantprofile.profile.domain.model.Profile
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ProfileRepositoryImpl @Inject constructor() : ProfileRepository {
    override suspend fun fetchProfile() = suspendCoroutine<Profile> { cont ->
        val databaseReference = FirebaseDatabase.getInstance().reference

        databaseReference.child("profile").addValueEventListener(object : ValueEventListener {
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

}

private fun DataSnapshot.toProfile(): Profile {
    return Profile(
        name = this.child("name").value.toString(),
        role = this.child("role").value.toString(),
        resume = this.child("resume").value.toString(),
        avatarImageUrl = this.child("avatar_image_url").value.toString()
    )
}
