package com.feragusper.instantprofile.projects.repository

import com.feragusper.instantprofile.projects.domain.model.Project
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ProjectRepositoryImpl @Inject constructor() : ProjectRepository {
    override suspend fun fetchProjects() = suspendCoroutine<List<Project>> { cont ->
        val databaseReference = FirebaseDatabase.getInstance().reference

        databaseReference.child("projects").addValueEventListener(object : ValueEventListener {
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

}

private fun DataSnapshot.toProjectList(): List<Project> {
    return this.children.map { projectDataSnapshot -> projectDataSnapshot.toProject() }
}

private fun DataSnapshot.toProject(): Project {
    return Project(name = this.child("name").value.toString())
}
