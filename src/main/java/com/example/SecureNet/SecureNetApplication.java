// SecureNetApplication
package com.example.SecureNet;

import com.example.SecureNet.Model.Intrusion;
import com.example.SecureNet.Model.Vulnerability;
import com.example.SecureNet.Repository.IntrusionRepository;
import com.example.SecureNet.Repository.VulnerabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecureNetApplication {

	@Autowired
	private IntrusionRepository intrusionRepository;

	@Autowired
	private VulnerabilityRepository vulnerabilityRepository;

	public static void main(String[] args) {
		SpringApplication.run(SecureNetApplication.class, args);
	}


}
