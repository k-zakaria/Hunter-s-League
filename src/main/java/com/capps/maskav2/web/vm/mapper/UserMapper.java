package com.capps.maskav2.web.vm.mapper;

import com.capps.maskav2.domain.User;
import com.capps.maskav2.service.dto.UserDTO;
import com.capps.maskav2.web.vm.UserDeleteVM;
import com.capps.maskav2.web.vm.UserLoginVM;
import com.capps.maskav2.web.vm.UserRegisterVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserLoginVM userVM);
    User toEntity(UserRegisterVM userVM);
    UserDTO toDTO(User user);
    User toEntity(UserDeleteVM userDeleteVM);

}
