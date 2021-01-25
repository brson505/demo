package com.wizvera.demo1

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForEntity
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import java.net.URL


//doc: https://developers.google.com/identity/protocols/oauth2/web-server
@Controller
class GoogleController {

  val scope = "email profile"
  val redirectUri= "http://localhost/google/auth/callback"
  val clientId = "873828645421-bpn9tu20qhccgb19t7foqkc84ube0im4.apps.googleusercontent.com"
  val clientSecret = "ACWcLvufXUnlPeNrMYzBvIns"
  val apiKey = "AIzaSyAbqTWoUsUYd05m0I48nRDFeyf4oAa0K5E"
  val URL_token = "https://oauth2.googleapis.com/token"

  @GetMapping("/google/auth")
  fun login(): ModelAndView {
    val redirectUri = UriComponentsBuilder.fromHttpUrl("https://accounts.google.com/o/oauth2/v2/auth")
            .queryParam("scope", scope)
            .queryParam("redirect_uri", redirectUri)
            .queryParam("client_id", clientId)
            .queryParam("response_type", "code")
            .build()
            .encode()
            .toUriString()

    return ModelAndView("redirect:$redirectUri")
  }

  @GetMapping("/google/auth/callback")
  @ResponseBody
  fun google(@RequestParam params:Map<String,String>): String? {
    println("param:" + params.toString());


    /*
    POST /token HTTP/1.1
    Host: oauth2.googleapis.com
    Content-Type: application/x-www-form-urlencoded

    code=4/P7q7W91a-oMsCeLvIaQm6bTrgtp7&
    client_id=your_client_id&
    client_secret=your_client_secret&
    redirect_uri=https%3A//oauth2.example.com/code&
            grant_type=authorization_code
     */
    //data class TokenReq(val code: String,  val client_id: String, val client_secret: String, val redirect_uri: String, val grant_type: String)


    val requestParams = LinkedMultiValueMap<String, String>()

    requestParams["code"] = params.get("code")!!
    requestParams["client_id"] = clientId
    requestParams["client_secret"] = clientSecret
    requestParams["redirect_uri"] = redirectUri
    requestParams["grant_type"] = "authorization_code"

    val restTemplate = RestTemplate()
    val httpHeaders = HttpHeaders()
    httpHeaders.contentType = MediaType.APPLICATION_FORM_URLENCODED

    val httpEntity = HttpEntity(requestParams, httpHeaders)
    var uri = UriComponentsBuilder.fromHttpUrl(URL_token).build().toUri()
    val res : ResponseEntity<String> = restTemplate.postForEntity(uri, httpEntity)

    println("token res:" + res.body)
    println("token res:" + res.headers)

    return res.body
  }

  @GetMapping("/google2")
  @ResponseBody
  fun google2(@RequestParam params:Map<String,String>): String {
    return "hi";
  }



  @GetMapping("/index1")
  fun index1(model: Model): String {

    model["scope"] = scope
    model["redirectUri"] = redirectUri
    model["clientId"] = clientId;
    model["responseType"] = "code"
    model["apiKey"] = apiKey

    return "index1"
  }

  @GetMapping("/index2")
  fun index2(model: Model): String {
    model["scope"] = scope
    model["redirectUri"] = redirectUri
    model["clientId"] = clientId;
    model["responseType"] = "code"
    model["apiKey"] = apiKey

    return "index2"
  }

}