package com.example.loginregisterfirebase.ui.gallery

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.loginregisterfirebase.R
import com.example.loginregisterfirebase.toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.nav_header_main.*
import java.io.ByteArrayOutputStream


class GalleryFragment : Fragment() {

    private val DEFAULT_IMAGE_URL = "https://picsum.photos/200"
    //val DEFAULT_DISPLAY_NAME= "Anonymous"
    private lateinit var imageUri: Uri
    private val REQUEST_IMAGE_CAPTURE = 100

    private val currentUser = FirebaseAuth.getInstance().currentUser

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_gallery, container, false)

        return root
    }
    private  val currentUser1 = FirebaseAuth.getInstance().currentUser



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        currentUser?.let{ user->
            Glide.with(this)
                .load(user.photoUrl)
                .into(image_view)
            //
            //Glide.with(this)
            //        .load(user.photoUrl)
            //        .into(headerimage)
            //headername.text=user.displayName
            //
            edit_text_name.setText(user.displayName)
            text_email.text=user.email
            text_my_bio.text=if(user.phoneNumber.isNullOrEmpty())"Enter your bio" else user.phoneNumber

            //headername.text = user.displayName
           // if (user.isEmailVerified) {
            //    text_not_verified.visibility = View.INVISIBLE
           // } else {
             //   text_not_verified.visibility = View.VISIBLE
            //}
        }
        image_view.setOnClickListener {
            takePictureIntent()
        }



        button_save.setOnClickListener {

            val photo = when {
                ::imageUri.isInitialized -> imageUri
                currentUser?.photoUrl == null -> Uri.parse(DEFAULT_IMAGE_URL)
                else -> currentUser.photoUrl
            }

            val name = edit_text_name.text.toString().trim()

            if (name.isEmpty()) {
                edit_text_name.error = "name required"
                edit_text_name.requestFocus()
                return@setOnClickListener
            }

            val updates = UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .setPhotoUri(photo)
                .build()


            progressbar.visibility = View.VISIBLE


            currentUser?.updateProfile(updates)
                ?.addOnCompleteListener { task ->
                    progressbar.visibility = View.INVISIBLE
                    if (task.isSuccessful) {
                        //error posibility
                        //headername.text=name
                        //
                        context?.toast("Profile Updated")
                    } else {
                        context?.toast(task.exception?.message!!)
                    }
                }

        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun takePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { pictureIntent ->
            pictureIntent.resolveActivity(activity?.packageManager!!)?.also {
                startActivityForResult(pictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            uploadImageAndSaveUri(imageBitmap)
        }
    }

    private fun uploadImageAndSaveUri(bitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        val storageRef = FirebaseStorage.getInstance()
            .reference
            .child("pics/${FirebaseAuth.getInstance().currentUser?.uid}")
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val image = baos.toByteArray()

        val upload = storageRef.putBytes(image)

        progressbar_pic.visibility = View.VISIBLE
        upload.addOnCompleteListener { uploadTask ->
            progressbar_pic.visibility = View.INVISIBLE

            if (uploadTask.isSuccessful) {
                storageRef.downloadUrl.addOnCompleteListener { urlTask ->
                    urlTask.result?.let {
                        imageUri = it
                        activity?.toast(imageUri.toString())
                        image_view.setImageBitmap(bitmap)
                        //possibility of error
                        //headerimage.setImageBitmap(bitmap)
                    }
                }
            } else {
                uploadTask.exception?.let {
                    activity?.toast(it.message!!)
                }
            }
        }
    }
}