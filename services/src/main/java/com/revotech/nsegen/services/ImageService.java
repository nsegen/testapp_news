package com.revotech.nsegen.services;

/**
 * Created by Revotech on 13.07.16.
 */

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Created by Revotech on 07.07.16.
 */
public class ImageService {

    private static Logger log = Logger.getLogger(ImageService.class);

    private static String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "").replace(" ", "");
            }
        }
        return null;
    }

    public static boolean deleteImage(String imageUrl){

        final String imageServerUrl = ResourceBundle.getBundle("ImageServer").getString("url");

        StringBuilder imgUrl = new StringBuilder(imageUrl);
        int start = imageUrl.indexOf(imageServerUrl);
        imgUrl.delete(start, imageServerUrl.length()+1);
        File newsImage = new File(imgUrl.toString());
        return newsImage.delete();
    }

    public static String uploadImage(HttpServletRequest request, String title) throws IOException, ServletException {
        Part part = request.getPart("image");
        final String imageServerUrl = ResourceBundle.getBundle("ImageServer").getString("url");
        //log.info(MainController.class.getName());
        if(part.getSubmittedFileName().isEmpty()){
            return null;
        }
        final String fileName = getFileName(part);
        String path = "images";
        try(OutputStream out = new FileOutputStream(new File(path + File.separator + title + fileName));
            InputStream fileContent = part.getInputStream()) {

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

        } catch (FileNotFoundException fne) {
            log.info("Problems during file upload. Error: {0}" +
                    Arrays.toString(new Object[]{fne.getMessage()}));
        }

        return imageServerUrl + "/" + path + "/" + title + fileName;
    }
}