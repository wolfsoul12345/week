package com.dream.week.utils;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


public class UpLoadUtils {

    public static void upload(MultipartFile file, String fileName , String path) throws IOException {
        String realPath = path + "/" + fileName;
        File dest = new File(realPath);
        dest.getParentFile().mkdir();
        file.transferTo(dest);
    }

}
