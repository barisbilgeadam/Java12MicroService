package com.barisd.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document //mongodb için.
public class UserProfile {
    @MongoId
    String id; //document nosql dblerde id uuid tarafından oluşturulur. Bu da stringe karşılık gelir.
    Long authId;
    String username;
    String email;
    String photo;
    String phone;
    String website;
}
