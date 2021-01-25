package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;
//
////@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Person {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String firstName;
//    private String lastName;
//    //@ManyToMany(cascade = CascadeType.ALL)
//    @ElementCollection
//    @JoinTable(name="ATTRIBUTE_VALUE_RANGE", joinColumns=@JoinColumn(name="ID"))
//    @MapKeyColumn (name="RANGE_ID")
//    @Column(name="VALUE")
//    private Map<String, String> items;
//}