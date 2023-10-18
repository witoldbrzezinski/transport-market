package pl.witoldbrzezinski.transportmarket.load;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadMapper {

  private final ModelMapper modelMapper;

  LoadDTOResponse toDTO(LoadEntity loadEntity) {
    return modelMapper.map(loadEntity, LoadDTOResponse.class);
  }

  LoadEntity toEntity(LoadDTORequest loadDTORequest) {
    return modelMapper.map(loadDTORequest, LoadEntity.class);
  }
}
