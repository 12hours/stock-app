package com.epam.mentoring.data.model.dto.mapstruct;

import com.epam.mentoring.data.model.User;
import com.epam.mentoring.data.model.dto.view.UserView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface UserMapper {

    @Mappings({
//            @Mapping(target = "links", expression = ("java( new java.util.HashMap<String, Object>())"))
    })
    UserView userToUserView(User user);

}
