package org.nutz.dao.impl.jdbc.oracle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import org.nutz.castor.Castors;
import org.nutz.dao.jdbc.ValueAdaptor;

public class OracleDateAdapter implements ValueAdaptor {

    public Object get(ResultSet rs, String colName) throws SQLException {
        return rs.getTimestamp(colName);
    }

    public void set(PreparedStatement stat, Object obj, int index) throws SQLException {
        if (obj == null) {
            stat.setNull(index, Types.DATE);
            return;
        }

        if (!(obj instanceof Timestamp)) {
            obj = Castors.me().castTo(obj, Timestamp.class);
        }
        stat.setTimestamp(index, (Timestamp) obj);
    }

}
