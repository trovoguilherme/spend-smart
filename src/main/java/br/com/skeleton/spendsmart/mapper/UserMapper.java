package br.com.skeleton.spendsmart.mapper;

import br.com.skeleton.spendsmart.entity.User;
import br.com.skeleton.spendsmart.resource.request.UserRequest;
import br.com.skeleton.spendsmart.resource.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "password", source = "password", qualifiedByName = "mapBCryptPasswordEncoder")
    User toEntity(UserRequest source);

    UserResponse toResponse(User source);

    @Named("mapBCryptPasswordEncoder")
    default String mapBCryptPasswordEncoder(String source) {
        return new BCryptPasswordEncoder().encode(source);
    }

}
