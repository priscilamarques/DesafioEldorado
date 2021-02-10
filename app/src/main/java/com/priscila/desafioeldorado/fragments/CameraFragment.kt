package com.priscila.desafioeldorado.fragments


import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.priscila.desafioeldorado.R


class CameraFragment : Fragment() {

    private lateinit var cameraBtn: Button
    private lateinit var camImage: ImageView
    private val cameraRequestId = 1222

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewF: View = inflater.inflate(R.layout.fragment_camera, container, false)
        return viewF
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        openCamera()
    }

    private fun initView(view: View) {
        cameraBtn = view.findViewById(R.id.camBtn)
        camImage = view.findViewById(R.id.camImage)
    }//;

    private fun openCamera() {
        if (context?.let { ContextCompat.checkSelfPermission(
                it, android.Manifest.permission.CAMERA
            ) } == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
                activity!!, arrayOf(android.Manifest.permission.CAMERA), cameraRequestId
            )
        }
        cameraBtn.setOnClickListener {
            val cameraInt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            Log.d("CAMERABUTTON", "$cameraInt")
            startActivityForResult(cameraInt, cameraRequestId)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequestId) {
            val images: Bitmap = data?.extras?.get("data") as Bitmap
            camImage.setImageBitmap(images)
        }
    }
}