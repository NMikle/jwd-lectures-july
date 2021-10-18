package com.epam.jwd.web.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface StatementPreparator {

    void accept(PreparedStatement statement) throws SQLException;

}
