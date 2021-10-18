package com.epam.jwd.web.dao;

import com.epam.jwd.web.db.ConnectionPool;
import com.epam.jwd.web.model.Bike;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public final class MethodBikeDao extends CommonDao<Bike> implements BikeDao {

    public static final String USER_ID = "user_id";
    private static final Logger LOG = LogManager.getLogger(MethodBikeDao.class);

    private static final String BIKE_TABLE_NAME = "u_bike";
    private static final String ID_FIELD_NAME = "id";
    private static final String MODEL_FIELD_NAME = "model";
    private static final String PRICE_FIELD_NAME = "price";
    public static final String SELECT_USER_ID_FROM_BIKE = "select user_id from u_bike b where b.id = ?";

    private MethodBikeDao(ConnectionPool pool) {
        super(pool, LOG);
    }

    @Override
    protected String getTableName() {
        return BIKE_TABLE_NAME;
    }

    @Override
    protected Bike extractResult(ResultSet rs) throws SQLException {
        return new Bike(
                rs.getLong(ID_FIELD_NAME),
                rs.getString(MODEL_FIELD_NAME),
                rs.getBigDecimal(PRICE_FIELD_NAME)
        );
    }

    @Override
    public List<Bike> findByModel(String model) {
        return null;
    }

    @Override
    public Optional<Long> findUserIdByBikeId(Long id) {
        try {
            return executePreparedForGenericEntity(SELECT_USER_ID_FROM_BIKE,
                    this::extractUserId, st -> st.setLong(1, id));
        } catch (InterruptedException e) {
            LOG.info("takeConnection interrupted", e);
            Thread.currentThread().interrupt();
            return Optional.empty();
        }
    }

    private long extractUserId(ResultSet rs) throws EntityExtractionFailedException {
        try {
            return rs.getLong(USER_ID);
        } catch (SQLException e) {
            LOG.error("sql exception occurred extracting entity from ResultSet", e);
            throw new EntityExtractionFailedException("could not extract entity", e);
        }
    }

    static BikeDao getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final BikeDao INSTANCE = new MethodBikeDao(ConnectionPool.locking());
    }
}
