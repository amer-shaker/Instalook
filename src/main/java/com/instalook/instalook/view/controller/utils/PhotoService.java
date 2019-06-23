package com.instalook.instalook.view.controller.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.instalook.instalook.model.dal.entity.ImageFile;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Anas
 */
public class PhotoService {

    private Cloudinary getCloudinaryClient() {
        return new Cloudinary(ObjectUtils.asMap("cloud_name", "hc35dpor7",
                "api_key", "376617877336132",
                "api_secret", "VBJmbSHI_u87j7GYsxDs_4Jg1S4",
                "secure", true));
    }

    public Map<String, Object> uploadToCloudinary(ImageFile imageSent) {

        Cloudinary cloudinary = getCloudinaryClient();
        Map<String, Object> cloudinaryUrl = null;
        Map params = ObjectUtils.asMap("public_id", "images/" + imageSent.getImageName());
        File convFile;
        try {
            // convert from multipart file to File 
            convFile = multipartToFile(imageSent.getFile());
            @SuppressWarnings("unchecked")

            Map<String, Object> result = (Map<String, Object>) cloudinary.uploader().upload(convFile, params);
            System.out.println(result.get("secure_url"));
            cloudinaryUrl = result;

        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {

            System.out.println("Could not upload file to Cloundinary from MultipartFile " + imageSent.getImageName());
            ex.printStackTrace();

        }

        return cloudinaryUrl;
    }

    private File multipartToFile(MultipartFile multipartFile) throws IllegalStateException, IOException {
        CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) multipartFile;
        FileItem fileItem = commonsMultipartFile.getFileItem();
        DiskFileItem diskFileItem = (DiskFileItem) fileItem;
        String absPath = diskFileItem.getStoreLocation().getAbsolutePath();
        File file = new File(absPath);
        if (!file.exists()) {
            file.createNewFile();
            multipartFile.transferTo(file);
        }

        return file;
    }
}
