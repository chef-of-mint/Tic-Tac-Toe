package com.example.loginregisterfirebase.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginregisterfirebase.R
import com.example.loginregisterfirebase.UsersAdapter
import com.example.loginregisterfirebase.UsersModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_users.*


class UsersFragment : Fragment() {

    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {

        val UsersList = ArrayList<UsersModel>()


        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.


                for (userSnapshot in dataSnapshot.children) {

                    val userCard = userSnapshot.getValue(UsersModel::class.java)

                    userCard?.id = userSnapshot.key
                    userCard?.let { UsersList.add(it) }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Toast.makeText(activity, "Not able to fetch data", Toast.LENGTH_SHORT).show()
            }
        })
        userList.layoutManager = LinearLayoutManager(activity)
        userList.adapter = UsersAdapter(UsersList)

        super.onActivityCreated(savedInstanceState)
    }



}