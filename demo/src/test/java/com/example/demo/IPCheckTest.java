package com.example.demo;

import org.springframework.security.web.util.matcher.IpAddressMatcher;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IPCheckTest {
    @Test
    public void test(){
        assertTrue(matches("192.168.2.1", "192.168.2.1")); // true
        assertFalse(matches("192.168.2.1", "192.168.2.0/32")); // false
        assertTrue(matches("192.168.2.5", "192.168.2.0/24")); // true
        assertFalse(matches("92.168.2.1", "fe80:0:0:0:0:0:c0a8:1/120")); // false
        assertTrue(matches("fe80:0:0:0:0:0:c0a8:11", "fe80:0:0:0:0:0:c0a8:1/120")); // true
        assertFalse(matches("fe80:0:0:0:0:0:c0a8:11", "fe80:0:0:0:0:0:c0a8:1/128")); // false
        assertFalse(matches("fe80:0:0:0:0:0:c0a8:11", "192.168.2.0/32")); // false

        assertTrue(matches("192.168.2.5", "0.0.0.0/0")); // true
    }

    private boolean matches(String ip, String subnet) {
        IpAddressMatcher ipAddressMatcher = new IpAddressMatcher(subnet);
        return ipAddressMatcher.matches(ip);
    }

    @Test
    public void a(){
        List<AllowIp> allowIps = new ArrayList<>();
        allowIps.add(AllowIp.builder().maskedIp("127.0.0.1").memo("localhost").build());
        allowIps.add(AllowIp.builder().maskedIp("127.0.0.2").memo("localhost").build());

        assertFalse(allowIps.stream().anyMatch(e -> e.matches("127.0.0.3")));
        assertTrue(allowIps.stream().anyMatch(e -> e.matches("127.0.0.1")));
    }
}
