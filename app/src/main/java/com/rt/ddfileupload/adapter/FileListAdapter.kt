package com.rt.ddfileupload.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rt.ddfileupload.R
import com.rt.ddfileupload.databinding.ItemFileBinding
import com.rt.ddfileupload.model.FileUploadModel

//https://github.com/pooja-gaikwad15/recyclerview-list-drag-and-drop/tree/master/app/src/main
class FileListAdapter() : RecyclerView.Adapter<FileListAdapter.FileListViewHolder>() {

    var fileList: ArrayList<FileUploadModel> = ArrayList<FileUploadModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileListViewHolder {
        val binding = ItemFileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FileListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FileListViewHolder, position: Int) {
        holder.onBind(fileList[position])
    }

    override fun getItemCount(): Int {
        return fileList.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_file
    }

    fun updateList(list: ArrayList<FileUploadModel>) {
        this.fileList = list
    }

    inner class FileListViewHolder(val binding: ItemFileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: FileUploadModel) {
            binding.title.text = model.file?.name
            val bitmap = BitmapFactory.decodeFile(model.file?.absolutePath)
            binding.image.setImageBitmap(bitmap)
        }
    }
}