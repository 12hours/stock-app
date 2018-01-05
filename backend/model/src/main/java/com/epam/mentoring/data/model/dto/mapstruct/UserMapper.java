package com.epam.mentoring.data.model.dto.mapstruct;

import com.epam.mentoring.data.model.User;
import com.epam.mentoring.data.model.dto.view.UserView;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserView userToUserView(User user);

}
