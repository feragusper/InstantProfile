package com.feragusper.instantprofile.projects.api

import com.feragusper.instantprofile.commons.extensions.getChildStringValue
import com.feragusper.instantprofile.commons.extensions.getChildrenValue
import com.feragusper.instantprofile.projects.api.FirebaseDatabaseProjectApi.ProjectEntity.Companion.KEY_DESCRIPTION
import com.feragusper.instantprofile.projects.api.FirebaseDatabaseProjectApi.ProjectEntity.Companion.KEY_LINK_URL
import com.feragusper.instantprofile.projects.api.FirebaseDatabaseProjectApi.ProjectEntity.Companion.KEY_LOGO_FIREBASE_STORAGE_URL
import com.feragusper.instantprofile.projects.api.FirebaseDatabaseProjectApi.ProjectEntity.Companion.KEY_NAME
import com.feragusper.instantprofile.projects.api.FirebaseDatabaseProjectApi.ProjectEntity.Companion.KEY_TECHNOLOGIES
import com.feragusper.instantprofile.projects.api.FirebaseDatabaseProjectApi.ProjectEntity.Companion.PATH
import com.feragusper.instantprofile.projects.domain.Technology
import com.feragusper.instantprofile.projects.domain.model.Project
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseDatabaseProjectApi @Inject constructor() : ProjectApi {

    override suspend fun fetchProjectList() = suspendCoroutine<List<Project>> { cont ->
        val databaseReference = FirebaseDatabase.getInstance().reference

        databaseReference.child(PATH).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                cont.resume(dataSnapshot.toProjectList())
                databaseReference.removeEventListener(this)
            }

            override fun onCancelled(error: DatabaseError) {
                cont.resumeWithException(RuntimeException(error.message))
                databaseReference.removeEventListener(this)
            }
        })
    }

    private fun DataSnapshot.toProjectList(): List<Project> {
        return this.children.map { projectDataSnapshot -> projectDataSnapshot.toProject() }
    }

    private fun DataSnapshot.toProject(): Project {
        return Project(
            name = getChildStringValue(KEY_NAME),
            logoFirebaseStorageUrl = getChildStringValue(KEY_LOGO_FIREBASE_STORAGE_URL),
            linkUrl = getChildStringValue(KEY_LINK_URL),
            description = getChildStringValue(KEY_DESCRIPTION),
            technologies = getChildrenValue(KEY_TECHNOLOGIES).map {
                it.toTechnology()
            })
    }

    private fun DataSnapshot.toTechnology(): Technology {
        return Technology(
            name = getChildStringValue(ProjectEntity.TechnologyEntity.KEY_NAME),
            logoFirebaseStorageUrl = getChildStringValue(ProjectEntity.TechnologyEntity.KEY_LOGO_FIREBASE_STORAGE_URL)
        )
    }

    private class ProjectEntity {
        companion object {
            const val PATH = "projects"
            const val KEY_NAME = "name"
            const val KEY_LOGO_FIREBASE_STORAGE_URL = "logo_firebase_storage_url"
            const val KEY_LINK_URL = "link_url"
            const val KEY_DESCRIPTION = "description"
            const val KEY_TECHNOLOGIES = "technologies"
        }

        class TechnologyEntity {
            companion object {
                const val KEY_NAME = "name"
                const val KEY_LOGO_FIREBASE_STORAGE_URL = "logo_firebase_storage_url"
            }
        }
    }
}

