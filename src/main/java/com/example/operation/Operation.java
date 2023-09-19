package com.example.operation;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="operation")
@Data
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String creationDate;
    private String operationType;
    private String senderWarehouse;
    private String receiverWarehouse;
    private String agent;
    private String user;


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getSenderWarehouse() {
        return senderWarehouse;
    }

    public void setReceiverWarehouse(String receiverWarehouse) {this.receiverWarehouse = receiverWarehouse;}

    public String getAgent() {return agent;}

    public void setAgent(String agent) {this.agent = agent;}

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Operation [op_id=" + id + ", op_name=" + name + ", op_creation_date=" + creationDate + ", op_operation_type=" + operationType + ", op_sender_warehouse=" + senderWarehouse + ", op_receiver_warehouse=" + receiverWarehouse + ", op_agent=" + agent + ", op_user=" + user + "]";
    }
}

