package com.fileblocker.flow.extensions.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="custom_extension", uniqueConstraints = @UniqueConstraint(name="uk_custom_name", columnNames="name"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CustomExtension {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=20)
    private String name;    // 소문자, '.' 없음
}
