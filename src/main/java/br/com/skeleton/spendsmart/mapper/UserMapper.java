package br.com.skeleton.spendsmart.mapper;

import br.com.skeleton.spendsmart.entity.User;
import br.com.skeleton.spendsmart.resource.request.UserRequest;
import br.com.skeleton.spendsmart.resource.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toEntity(UserRequest source);

    UserResponse toResponse(User source);

}
