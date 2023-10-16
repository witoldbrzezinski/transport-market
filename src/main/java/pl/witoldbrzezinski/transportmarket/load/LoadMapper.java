package pl.witoldbrzezinski.transportmarket.load;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.witoldbrzezinski.transportmarket.customer.CustomerDTORequest;
import pl.witoldbrzezinski.transportmarket.customer.CustomerDTOResponse;
import pl.witoldbrzezinski.transportmarket.customer.CustomerEntity;

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
