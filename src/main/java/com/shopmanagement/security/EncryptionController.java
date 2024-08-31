package com.shopmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/encryptedKeyGenerator")
public class EncryptionController {

    @Autowired
    private UserAuth userAuth;

    @GetMapping("/generate-key")
    public String generateKey() {
        try {
            System.out.println("generateKey=="+EncryptionUtil.generateKey());
            return EncryptionUtil.generateKey();
        } catch (Exception e) {
            return "Error generating key: " + e.getMessage();
        }
    }

    @GetMapping("/encrypt")
    public String encrypt(@RequestParam String data, @RequestParam String key) {
        try {
            return EncryptionUtil.encrypt(data, key);
        } catch (Exception e) {
            return "Error encrypting data: " + e.getMessage();
        }
    }

    @GetMapping("/decrypt")
    public String decrypt(@RequestParam String encryptedData, @RequestParam String key) {
        try {
            return EncryptionUtil.decrypt(encryptedData, key);
        } catch (Exception e) {
            return "Error decrypting data: " + e.getMessage();
        }
    }

    @GetMapping("/getUserIdByToken/{token}")
    public Long getUserIdByToken(@PathVariable String token) {
        System.out.println("getUserId=="+userAuth.getUserId(token));
        return userAuth.getUserId(token);
    }
}
