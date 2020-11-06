package com.yih.auth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class AppClientEntity {
    @Id
    private Long id;
}
