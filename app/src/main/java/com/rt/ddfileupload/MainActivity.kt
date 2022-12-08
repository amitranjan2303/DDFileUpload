package com.rt.ddfileupload

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rt.ddfileupload.adapter.FileListAdapter
import com.rt.ddfileupload.databinding.ActivityMainBinding
import com.rt.ddfileupload.model.FileUploadModel
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Log.d("Amit", "Request Permission on App")
        //use to request permission for filAe read
        requestPermission()
    }

    private val filePermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.READ_EXTERNAL_STORAGE, false) -> {
                getFiles(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))
            }
        }
    }

    private fun requestPermission() {
        filePermissionLauncher.launch(
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        )
    }

//    val fileDirectory: File? =
//        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

    private fun getFiles(fileDirectory: File?){

        val fileList: ArrayList<FileUploadModel> = ArrayList<FileUploadModel>()
        Log.d("Amit ", "fileDirectory $fileDirectory")
        Log.d("Amit ", "listFiles ${fileDirectory?.listFiles() != null}")

        if (fileDirectory?.listFiles() != null) {
            Log.d("Amit ", "inside if ")
            Log.d("Amit ", "listFiles size ${fileDirectory.listFiles()?.size}")
            Log.d("Amit ", "list size ${fileDirectory.list()?.size}")

            for (file in fileDirectory.listFiles()) {
                Log.d("Amit ", "file $file")

                if (file.isDirectory()) {
//                    fileList.add(file)
                    getFiles(file)
                } else {
                    if (file.name.endsWith(".png")
                        || file.name.endsWith(".jpg")
                        || file.name.endsWith(".jpeg")
                        || file.name.endsWith(".gif")
                    ) {
                        val fileUploadModel=FileUploadModel(0,false,file)
                        fileList.add(fileUploadModel)
                        Log.d("Amit ", "Path " + file.path)
                        Log.d("Amit ", "Extension " + file.extension)
                        Log.d("Amit ", "Name " + file.name)
                    }
                }
            }
        }

        setUpSourceFiles(fileList)
    }

    private fun setUpSourceFiles(fileList:ArrayList<FileUploadModel>) {
        val fileListAdapterSource = FileListAdapter()
        fileListAdapterSource.updateList(fileList)
//        binding.rvFiles.layoutManager = GridLayoutManager(this, 3)
        binding.rvFiles.layoutManager = LinearLayoutManager(this)
        binding.rvFiles.adapter=fileListAdapterSource
    }
}