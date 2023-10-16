package pl.witoldbrzezinski.transportmarket.load;

import java.util.Collections;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.witoldbrzezinski.transportmarket.security.UserServiceImpl;

@Service
@RequiredArgsConstructor
public class LoadServiceImpl implements LoadService {

  private final LoadRepository loadRepository;
  private final LoadMapper loadMapper;
  private final UserServiceImpl userService;

  @Override
  public List<LoadDTOResponse> getAll() {
    return loadRepository.findAll().stream().map(loadMapper::toDTO).toList();
  }

  @Override
  public LoadDTOResponse getById(Long id) {
    LoadEntity loadEntity =
        loadRepository.findById(id).orElseThrow(() -> new LoadNotFoundException(id));
    return loadMapper.toDTO(loadEntity);
  }

  @Override
  public LoadDTOResponse save(LoadDTORequest loadDTORequest) {
    LoadEntity loadEntity = loadMapper.toEntity(loadDTORequest);
    loadEntity.setUser(userService.getCurrentUser());
    loadRepository.save(loadEntity);
    return loadMapper.toDTO(loadEntity);
  }

  @Override
  @Transactional
  public LoadDTOResponse update(Long id, LoadDTORequest loadDTORequest) {
    LoadEntity loadEntity =
        loadRepository.findById(id).orElseThrow(() -> new LoadNotFoundException(id));
    loadEntity.setName(loadDTORequest.getName());
    loadEntity.setLoadingDate(loadDTORequest.getLoadingDate());
    loadEntity.setLoadingCity(loadDTORequest.getLoadingCity());
    loadEntity.setLoadingPostcode(loadDTORequest.getLoadingPostcode());
    loadEntity.setUnloadingDate(loadDTORequest.getUnloadingDate());
    loadEntity.setUnloadingCity(loadDTORequest.getUnloadingCity());
    loadEntity.setUnloadingPostcode(loadDTORequest.getUnloadingPostcode());
    loadEntity.setWeight(loadDTORequest.getWeight());
    loadEntity.setLoadType(loadDTORequest.getLoadType());
    loadEntity.setPrice(loadDTORequest.getPrice());
    return loadMapper.toDTO(loadEntity);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    LoadEntity loadEntity =
        loadRepository.findById(id).orElseThrow(() -> new LoadNotFoundException(id));
    loadEntity.setDeleted(true);
  }

  public Page<LoadDTOResponse> findPaginated(Pageable pageable) {
    int pageSize = pageable.getPageSize();
    int currentPage = pageable.getPageNumber();
    int startItem = currentPage * pageSize;
    List<LoadDTOResponse> list;
    List<LoadDTOResponse> loads = loadRepository.findAll().stream().map(loadMapper::toDTO).toList();
    // TODO fix sorting
 //   loads.sort(Comparator.comparing(LoadDTOResponse::getId));
    if (loads.size() < startItem) {
      list = Collections.emptyList();
    } else {

      int toIndex = Math.min(startItem + pageSize, loads.size());
      list = loads.subList(startItem, toIndex);
    }
      return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), loads.size());
  }
}
