package io.stream.com.mappers;

import io.stream.com.models.User;
import io.stream.com.models.dtos.ProfileDto;
import io.stream.com.models.dtos.SignUpDto;
import io.stream.com.utils.TimeUtil;

public class UserMapper {

    public static User mapSignUp(SignUpDto signUpDto){
        return User.builder()
                .username(signUpDto.getUsername())
                .email(signUpDto.getEmail())
                .avatarId(0)
                .accountNonExpired(true)
                .accountNotLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .password(signUpDto.getPassword())
                .build();
    }

    public static ProfileDto mapProfile(User user){
        return ProfileDto.builder()
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
