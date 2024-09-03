package com.shopmanagement.storage;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class StorageServiceImpl implements StorageServices{

    private final Path rootLocation;
    private final Path userProfileImage;

    public StorageServiceImpl(StoragePropertiesMaster properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.userProfileImage = Paths.get(properties.getUserProfileImage());
    }


    @Override
    public void init() {

        try {
            Files.createDirectories(rootLocation);
            Files.createDirectories(userProfileImage);

        } catch (IOException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public void storeBaseImage(String baseImage, String updatedName, String fileType) throws IOException {
        byte[] byteArray = Base64.decodeBase64(baseImage);
        FileOutputStream fos = null;
        System.out.println("baseImage-"+baseImage);
        System.out.println("updatedName-"+updatedName);
        System.out.println("fileType-"+fileType);
        try {
            fos = switch (fileType) {
                case Constants.USER_PROFILE -> new FileOutputStream(userProfileImage + "/" + updatedName);
                default -> fos;
            };
            assert fos != null;
            fos.write(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fos != null;
        fos.close();
    }
}
