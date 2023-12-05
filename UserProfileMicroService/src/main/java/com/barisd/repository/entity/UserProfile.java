package com.barisd.repository.entity;

import lombok.*;

import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Document //mongodb için.
public class UserProfile extends BaseEntity {
    @MongoId
    String id; //document nosql dblerde id uuid tarafından oluşturulur. Bu da stringe karşılık gelir.
    Long authid;
    String username;
    String email;
    String photo;
    String phone;
    String website;
}
