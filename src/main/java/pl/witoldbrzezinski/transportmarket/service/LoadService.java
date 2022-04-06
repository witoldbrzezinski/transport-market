package pl.witoldbrzezinski.transportmarket.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import pl.witoldbrzezinski.transportmarket.entity.Load;
import pl.witoldbrzezinski.transportmarket.repository.LoadRepository;

@Service("loadService")
public class LoadService {
	
	@Autowired
	LoadRepository repository;
	
    public List<Load> getAllLoads(){
    	
        List<Load> loadList = repository.findAll();
         
        if(loadList.size() > 0) {
            return loadList;
        } else {
            return new ArrayList<Load>();
        }
    }
    
    public void saveOrUpdateLoad(Load load) {
    	repository.save(load);
    }

	public Load getLoad(long loadId) {
		// TODO Auto-generated method stub
		return repository.getById(loadId);
	}
	
	public void deleteLoad(Load load) {
		repository.delete(load);
	}
	// thanks to https://www.baeldung.com/spring-thymeleaf-pagination
    public Page<Load> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Load> list;
        List<Load> loadsList = repository.findAll();
		if (loadsList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, loadsList.size());
            list = loadsList.subList(startItem, toIndex);
            list.sort(Comparator.comparing(Load::getLoadId));
        }

        Page<Load> loadPage
          = new PageImpl<Load>(list, PageRequest.of(currentPage, pageSize), loadsList.size());
        return loadPage;
    }


}
