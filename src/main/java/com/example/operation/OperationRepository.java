package com.example.operation;


import java.util.List;
import com.example.operation.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    @Query("select p from Operation p where concat(p.id, '', p.name, '', p.creationDate, '', p.operationType, '', p.senderWarehouse, '', p.receiverWarehouse, '', p.agent, '', p.user) like %?1%")
    List<Operation> search(String keyword);
}