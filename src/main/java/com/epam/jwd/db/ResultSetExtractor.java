package com.epam.jwd.db;

import com.epam.jwd.model.DBEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface ResultSetExtractor<T extends DBEntity> {

    T extract(ResultSet resultSet) throws EntityExtractionFailedException;

    default List<T> extractAll(ResultSet resultSet) throws SQLException, EntityExtractionFailedException {
        List<T> entities = new ArrayList<>();
        while (resultSet.next()) {
            final T entity = this.extract(resultSet);
            entities.add(entity);
        }
        return entities;
    }

}
