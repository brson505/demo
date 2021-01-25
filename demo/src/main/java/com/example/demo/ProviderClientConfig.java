package com.example.demo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"vpcgClientConfig"})
@EqualsAndHashCode(exclude = {"vpcgClientConfig"})
@Data
@Entity
public class ProviderClientConfig implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private VpcgClientConfig vpcgClientConfig;


    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Singular("config")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "config", joinColumns = {
            @JoinColumn(name = "vpcgClientConfig_id", referencedColumnName = "vpcgClientConfig_id"),
            @JoinColumn(name = "provider", referencedColumnName = "provider")})
    private Map<String, String> config;
}