package com.taisiia.shop.domain.dao;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Audited
@EntityListeners(AuditingEntityListener.class)
public class Product implements IdentifiedDataSerializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    private Double price;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Producer producer;
    @ManyToMany
    @JoinTable(name = "product_category", inverseJoinColumns = @JoinColumn(name = "category_id"))
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<Category> categories;
    private String photoUrl;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String lastModifiedBy;
    private Integer quantity;


    @Override
    public int getFactoryId() {

        return 1;
    }

    @Override
    public int getClassId() {
        return 1;
    }

    @Override
    public void writeData(ObjectDataOutput objectDataOutput) throws IOException {
        objectDataOutput.writeLong(id);
        objectDataOutput.writeString(name);
        objectDataOutput.writeString(description);
        objectDataOutput.writeDouble(price);
        objectDataOutput.writeObject(producer);
       // objectDataOutput.writeInt(categories.size());

        //  objectDataOutput.writeObject(categories);
        objectDataOutput.writeString(photoUrl);
        objectDataOutput.writeObject(createdDate);
//        objectDataOutput.writeObject(lastModifiedDate);
//        objectDataOutput.writeObject(createdBy);
//        objectDataOutput.writeObject(lastModifiedDate);
        objectDataOutput.writeInt(quantity);


    }

    @Override
    public void readData(ObjectDataInput objectDataInput) throws IOException {
        id = objectDataInput.readLong();
        name = objectDataInput.readString();
        description = objectDataInput.readString();
        price = objectDataInput.readDouble();
        producer = objectDataInput.readObject();
        // categories = objectDataInput.readObject();
        photoUrl = objectDataInput.readString();
        createdDate = objectDataInput.readObject();
//        lastModifiedDate = objectDataInput.readObject();
//        createdBy = objectDataInput.readObject();
//        lastModifiedBy = objectDataInput.readObject();
        quantity = objectDataInput.readInt();


    }
}
