package pl.witoldbrzezinski.transportmarket.load;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.witoldbrzezinski.transportmarket.customer.CustomerDTORequest;
import pl.witoldbrzezinski.transportmarket.customer.CustomerDTOResponse;

import java.util.List;

public interface LoadService {

    List<LoadDTOResponse> getAll();

    LoadDTOResponse getById(Long id);

    LoadDTOResponse save(LoadDTORequest loadDTORequest);

    LoadDTOResponse update(Long id, LoadDTORequest loadDTORequest);

    void delete(Long id);

    Page<LoadDTOResponse> findPaginated(Pageable pageable);


}
