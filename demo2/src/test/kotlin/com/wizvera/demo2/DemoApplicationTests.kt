package com.wizvera.demo2

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.util.UriComponentsBuilder


@SpringBootTest
class DemoApplicationTests {

	@Test
	fun contextLoads() {
	}

	@Test
	fun testMultiValueMap(){
		val queryParams: MultiValueMap<String, String> = LinkedMultiValueMap()
		queryParams.add("name1", "value1")
		queryParams.add("name1", "value1 2")
		queryParams.add("name2", "value2")
		queryParams.add("name3", "value3")

		assertThat(queryParams.toString()).isEqualTo("{name1=[value1, value1 2], name2=[value2], name3=[value3]}")
	}

	@Test
	fun testUriComponentsBuilder(){
		var uri: String

		uri = UriComponentsBuilder.fromUriString("http://wizvera.com/q")
				.queryParam("a", "a")
				.queryParam("b", "b b")
				.build()
				.toUriString()
		assertThat(uri.toString()).isEqualTo("http://wizvera.com/q?a=a&b=b b")

		uri = UriComponentsBuilder.fromUriString("http://wizvera.com/q")
				.queryParam("a", "a")
				.queryParam("b", "b b")
				.build()
				.encode()
				.toUriString()
		assertThat(uri.toString()).isEqualTo("http://wizvera.com/q?a=a&b=b%20b")
	}

}
