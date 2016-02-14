package com.jiang.shanwe.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

public class BigIntToDateTypeHandler extends BaseTypeHandler<Date> {

    @Override
    public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return new Date(rs.getLong(columnName));
    }

    @Override
    public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return new Date(rs.getLong(columnIndex));
    }

    @Override
    public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new Date(cs.getLong(columnIndex));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType)
            throws SQLException {
    }

}
