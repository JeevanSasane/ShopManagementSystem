package com.shopmanagement.storage;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties("storage")
@Configuration
public class StoragePropertiesMaster {

    private String location = "upload-dir";
    private String userProfileImage = "./upload-dir/userProfileImage";

}
