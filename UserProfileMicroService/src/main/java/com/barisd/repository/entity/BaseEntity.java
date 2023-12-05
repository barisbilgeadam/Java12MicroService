package com.barisd.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;



@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseEntity {
    Long createAt;
    Long updateAt;
    Boolean state;
}
