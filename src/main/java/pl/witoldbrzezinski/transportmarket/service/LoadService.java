package pl.witoldbrzezinski.transportmarket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.witoldbrzezinski.transportmarket.entity.Load;
import pl.witoldbrzezinski.transportmarket.repository.LoadRepository;

@Service
public class LoadService {
	
	@Autowired
	LoadRepository repository;
	
    public List<Load> getAllLoads()
    {
        List<Load> loadList = repository.findAll();
         
        if(loadList.size() > 0) {
            return loadList;
        } else {
            return new ArrayList<Load>();
        }
    }
    
    public void saveLoad(Load load) {
    	repository.save(load);
    }

	public Load getLoad(long loadId) {
		// TODO Auto-generated method stub
		return repository.getById(loadId);
	}


	public void updateLoadById(Long load_id) {
		
		Load load = repository.getById(load_id);
		repository.save(load);
	}

}
