package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ClientDetailsRepository repository) {
		return (args) -> {
			VpcgClientConfig vpcgClientConfig = new VpcgClientConfig();
			vpcgClientConfig.setClientId("wizvera");

			vpcgClientConfig.addAllowIp(AllowIp.builder().maskedIp("127.0.0.1").memo("localhost").build());
			vpcgClientConfig.addAllowIp(AllowIp.builder().maskedIp("127.0.0.2").memo("localhost2").build());

			vpcgClientConfig.addProviderClientConfig(ProviderClientConfig.builder()
					.provider(Provider.toss)
					.config("a", "b")
					.config("b", "c")
					.build());

			repository.save(vpcgClientConfig);

//			Set<String> allowIps = new HashSet<>();
//			allowIps.add("127.0.0.1");
//			allowIps.add("192.168.0.133");

//			Map<Provider, ProviderClientConfig> providerClientConfigMap = new HashMap<>();
//			ProviderClientConfig providerClientConfig = ProviderClientConfig.builder()
//					.provider(Provider.kakao)
//					//.clientDetails(clientDetails)
//					.build();
//			providerClientConfigMap.put(providerClientConfig.getProvider(), providerClientConfig);
//
//			vpcgClientConfig.setProviderClientConfigMap(providerClientConfigMap);

			//repository.save(vpcgClientConfig);

			for (VpcgClientConfig vpcgClientConfig1 : repository.findAll()) {
				log.info(vpcgClientConfig1.toString());
			}
		};
	}

//
//	@Bean
//	public CommandLineRunner demo(PersonRepository repository) {
//		return (args) -> {
//			// save a few customers
//			Map<String, String> a = new HashMap<>();
//			a.put("key1", "value1");
//			a.put("key2", "value2");
//
//			repository.save(new Person(null, "Jack2", "Bauer2", a));
//			//repository.save(new Person(null, "Jack2", "Bauer2", a));
//
//			// fetch all customers
//			log.info("Customers found with findAll():");
//			log.info("-------------------------------");
//			for (Person customer : repository.findAll()) {
//				log.info(customer.toString());
//			}
//			log.info("");
//
//			// fetch an individual customer by ID
//			Optional<Person> person = repository.findById(1L);
//			log.info("Customer found with findById(1L):");
//			log.info("--------------------------------");
//			log.info(person.get().toString());
//			log.info("");
//
//			// fetch customers by last name
//			log.info("Customer found with findByLastName('Bauer'):");
//			log.info("--------------------------------------------");
//			repository.findByLastName("Bauer").forEach(bauer -> {
//				log.info(bauer.toString());
//			});
//			// for (Customer bauer : repository.findByLastName("Bauer")) {
//			//  log.info(bauer.toString());
//			// }
//			log.info("");
//		};
//	}
}
