package com.taisiia.shop.domain.dao;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.IOException;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producer implements IdentifiedDataSerializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private String countryCode;

    @Override
    public int getFactoryId() {
        return 2;
    }

    @Override
    public int getClassId() {
        return 2;
    }

    @Override
    public void writeData(ObjectDataOutput objectDataOutput) throws IOException {
        objectDataOutput.writeLong(id);
        objectDataOutput.writeString(name);
        objectDataOutput.writeString(country);
        objectDataOutput.writeString(countryCode);

    }

    @Override
    public void readData(ObjectDataInput objectDataInput) throws IOException {
        id = objectDataInput.readLong();
        name = objectDataInput.readString();
        country = objectDataInput.readString();
        countryCode = objectDataInput.readString();
    }
}
