package io.stream.com.mappers;

import java.util.Date;

import io.stream.com.models.User;
import io.stream.com.models.dtos.ProfileDto;
import io.stream.com.models.dtos.SignUpDto;
import io.stream.com.models.enums.Roles;
import io.stream.com.utils.TimeUtil;

public class UserMapper {

    public static User mapSignUp(SignUpDto signUpDto, String encryptedPassword){
        return User.builder()
                .username(signUpDto.getUsername())
                .email(signUpDto.getEmail())
                .avatarId(0)
                .accountNonExpired(true)
                .accountNotLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .password(encryptedPassword)
                .roles(Roles.ROLE_USER.name())
                .lastSeen(new Date())
                .build();
    }

    public static ProfileDto mapProfile(User user){
        return ProfileDto.builder()
            .userId(user.getUserId())
            .username(user.getUsername())
            .email(user.getEmail())
            .avatarId(user.getAvatarId())
            .enabled(user.isEnabled())
            .bio(user.getBio())
            .creationDate(TimeUtil.convertToSimpleFormat(user.getCreationDate()))
            .lastSeen(TimeUtil.convertToSimpleFormat(user.getLastSeen()))
            .fullName(user.getFullName())
            .build();
    }
}
