package com.barisd.mapper;

import com.barisd.dto.request.UserProfileSaveRequestDto;
import com.barisd.dto.response.UserProfileResponseDto;
import com.barisd.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {
    IUserProfileMapper INSTANCE= Mappers.getMapper(IUserProfileMapper.class);
    UserProfile dtoToUserProfile(UserProfileSaveRequestDto dto);

    UserProfileResponseDto fromUserProfile(UserProfile userProfile);
}
