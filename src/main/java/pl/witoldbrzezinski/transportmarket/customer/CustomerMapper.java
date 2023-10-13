package pl.witoldbrzezinski.transportmarket.customer;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final ModelMapper modelMapper;

    CustomerDTOResponse toDTO(CustomerEntity customerEntity){
        return modelMapper.map(customerEntity,CustomerDTOResponse.class);
    }

    CustomerEntity toEntity(CustomerDTORequest customerDTORequest){
        return modelMapper.map(customerDTORequest,CustomerEntity.class);
    }


}
