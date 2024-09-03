package com.shopmanagement.storage;

import java.io.IOException;

public interface StorageServices {

    void init();

    void storeBaseImage(String baseImage, String updatedName, String fileType) throws IOException;
}
