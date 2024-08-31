package com.shopmanagement;

import com.shopmanagement.storage.StorageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PharmacyApplication implements CommandLineRunner {

	@Autowired
	StorageServices storageServices;


	public static void main(String[] args) {
		SpringApplication.run(PharmacyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		storageServices.init();
	}

}
