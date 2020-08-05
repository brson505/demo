package com.wizvera.demo1

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI


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

	@Test
	fun test(){
		val scope = "email profile"
		val redirectUri= "http://localhost/google/auth/callback"
		val clientId = "873828645421-bpn9tu20qhccgb19t7foqkc84ube0im4.apps.googleusercontent.com"
		val clientSecret = "ACWcLvufXUnlPeNrMYzBvIns"
		val apiKey = "AIzaSyAbqTWoUsUYd05m0I48nRDFeyf4oAa0K5E"
		val URL_token = "https://oauth2.googleapis.com/token"
		val code = "4/2wEvNoFOorbCAZumpSV_CIubWuh04MdHOPNFa6R2zvuhbzv9ppHLn0YsfsGcXobcuk0qpLxjuQAix-2lgdVFifg"

		val req = LinkedMultiValueMap<String, String>()
		req["code"] = code
		req["client_id"] = clientId
		req["client_secret"] = clientSecret
		req["redirect_uri"] = redirectUri
		req["grant_type"] = "authorization_code"

		val restTemplate = RestTemplate()
		val httpHeaders = HttpHeaders()
		httpHeaders.contentType = MediaType.APPLICATION_FORM_URLENCODED
		val httpEntity = HttpEntity(req, httpHeaders)
		val res = restTemplate.postForObject(URL_token, httpEntity, String.javaClass)
		println("res:[$res]")
	}
}
