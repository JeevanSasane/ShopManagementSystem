package com.shopmanagement.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
