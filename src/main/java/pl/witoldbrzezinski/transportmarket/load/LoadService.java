package pl.witoldbrzezinski.transportmarket.load;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.witoldbrzezinski.transportmarket.security.UserService;

@Service("loadService")
public class LoadService {
	
	@Autowired
	LoadRepository loadRepository;
	
	@Autowired
    UserService userService;
	
    public List<LoadEntity> getAllLoads(){
    	
        List<LoadEntity> loadList = loadRepository.findAll();
         
        if(loadList.size() > 0) {
            return loadList;
        } else {
            return new ArrayList<LoadEntity>();
        }
    }
    
    public void saveOrUpdateLoad(LoadEntity load) {
    	load.setUser(userService.getCurrentUser());
    	loadRepository.save(load);
    	
    }

	public LoadEntity getLoad(long loadId) {
		// TODO Auto-generated method stub
		return loadRepository.getById(loadId);
	}
	
	public void deleteLoad(LoadEntity load) {
		loadRepository.delete(load);
	}
	// thanks to https://www.baeldung.com/spring-thymeleaf-pagination
    public Page<LoadEntity> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<LoadEntity> list;
        List<LoadEntity> loadsList = loadRepository.findAll();
        loadsList.sort(Comparator.comparing(LoadEntity::getLoadId));
		if (loadsList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, loadsList.size());
            list = loadsList.subList(startItem, toIndex);
        }

        Page<LoadEntity> loadPage
          = new PageImpl<LoadEntity>(list, PageRequest.of(currentPage, pageSize), loadsList.size());
        return loadPage;
    }


}
