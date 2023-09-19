package com.example.operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OperationService {
    @Autowired
    private OperationRepository repo;
    public List<Operation> listAll(String keyword) {
        if (keyword!=null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }
    public void save(Operation operation) {
        repo.save(operation);
    }
    public Operation get(Long id) {
        return repo.findById(id).get();
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
