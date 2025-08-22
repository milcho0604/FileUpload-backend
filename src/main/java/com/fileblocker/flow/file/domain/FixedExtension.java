//package com.fileblocker.flow.file.domain;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name = "fixed_extension", uniqueConstraints = {
//        @UniqueConstraint(name = "uk_fixed_extension_name", columnNames = "name")
//})
//@Getter @Setter
//@NoArgsConstructor @AllArgsConstructor @Builder
//public class FixedExtension {
//
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, length = 20)
//    private String name;       // 표준화된 소문자 확장자( . 없음 )
//
//    @Column(nullable = false)
//    private boolean blocked;   // 체크 여부(차단=true)
//}
