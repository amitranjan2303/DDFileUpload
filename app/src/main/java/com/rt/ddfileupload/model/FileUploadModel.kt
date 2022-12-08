package com.rt.ddfileupload.model

import java.io.File

data class FileUploadModel(
    var uploadProgress:Int?,
    var isProgressStart:Boolean?,
    var file: File?,
)