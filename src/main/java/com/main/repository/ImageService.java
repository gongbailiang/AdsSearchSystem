package com.main.repository;

/**
 * Created by gongbailiang on 9/1/16.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.main.models.AdImage;


@Service
public class ImageService {

    @Autowired
    private AmazonS3Client s3Client;

    private static final String S3_BUCKET_NAME = "adssearchsystem";


    /**
     * Save image to S3 and return CustomerImage containing key and public URL
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public AdImage saveFileToS3(MultipartFile multipartFile) throws Exception {

        try{
            File fileToUpload = convertFromMultiPart(multipartFile);
            String key = Instant.now().getEpochSecond() + "_" + fileToUpload.getName();

			/* save file */
            s3Client.putObject(new PutObjectRequest(S3_BUCKET_NAME, key, fileToUpload));

			/* get signed URL (valid for one year) */
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(S3_BUCKET_NAME, key);
            generatePresignedUrlRequest.setMethod(HttpMethod.GET);
            generatePresignedUrlRequest.setExpiration(DateTime.now().plusYears(1).toDate());

            URL signedUrl = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

            return new AdImage(key, signedUrl.toString());
        }
        catch(Exception ex){
            throw new Exception();
        }
    }

    /**
     * Delete image from S3 using specified key
     *
     * @param adImage
     */
    public void deleteImageFromS3(AdImage adImage){
        s3Client.deleteObject(new DeleteObjectRequest(S3_BUCKET_NAME, adImage.getkeyValue()));
    }

    /**
     * Convert MultiPartFile to ordinary File
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    private File convertFromMultiPart(MultipartFile multipartFile) throws IOException {

        File file = new File(multipartFile.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();

        return file;
    }
}