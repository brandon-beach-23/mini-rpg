package com.brandonbeach.mini_rpg.shared.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseEntity {
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
}

