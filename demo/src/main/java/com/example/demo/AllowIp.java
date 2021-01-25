package com.example.demo;

import lombok.*;
import org.springframework.security.web.util.matcher.IpAddressMatcher;

import javax.persistence.*;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"vpcgClientConfig"})
@EqualsAndHashCode(exclude = {"vpcgClientConfig"})
@Data
@Entity
public class AllowIp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private VpcgClientConfig vpcgClientConfig;

    /**
    Takes a specific IP address or a range specified using the IP/Netmask (e.g. 192.168.1.0/24 or 202.24.0.0/14).
     */
    private String maskedIp;

    private String memo;

    public boolean matches(String ip) {
        IpAddressMatcher ipAddressMatcher = new IpAddressMatcher(this.maskedIp);
        return ipAddressMatcher.matches(ip);
    }
}
