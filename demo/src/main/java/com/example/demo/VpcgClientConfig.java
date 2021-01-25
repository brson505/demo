package com.example.demo;

import lombok.*;
import org.hibernate.annotations.CollectionType;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VpcgClientConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String clientId;

    @OneToMany(mappedBy = "vpcgClientConfig", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AllowIp> allowIps = new ArrayList<>();;

    @OneToMany(mappedBy = "vpcgClientConfig", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapKey(name = "provider")
    private Map<Provider, ProviderClientConfig> providerClientConfigMap = new HashMap<>();

    public void addAllowIp(AllowIp allowIp){
        allowIp.setVpcgClientConfig(this);
        allowIps.add(allowIp);
    }

    public boolean isAllowIp(String ip){
        return allowIps.stream().anyMatch(e->e.matches(ip));
    }

    public void addProviderClientConfig(ProviderClientConfig providerClientConfig) {
        providerClientConfig.setVpcgClientConfig(this);
        providerClientConfigMap.put(providerClientConfig.getProvider(), providerClientConfig);
    }
}
