package com.barisd.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserProfileUpdateRequestDto {
    String token;

    String email;
    String photo;
    String phone;
    String website;
}
