package pl.witoldbrzezinski.transportmarket.security;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

  private final ModelMapper modelMapper;

  UserDTORegisterResponse registerRequestToDTO(UserEntity userEntity) {
    return modelMapper.map(userEntity, UserDTORegisterResponse.class);
  }
}
