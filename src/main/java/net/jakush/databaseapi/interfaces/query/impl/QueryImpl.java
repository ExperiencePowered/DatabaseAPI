package net.jakush.databaseapi.interfaces.query.impl;

import com.google.errorprone.annotations.RestrictedApi;
import net.jakush.databaseapi.interfaces.query.Query;
import net.jakush.databaseapi.interfaces.query.QueryMetaData;
import org.jetbrains.annotations.Nullable;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Impl for {@link Query}
 */
public class QueryImpl implements Query {

    private final List<Map<String, Object>> rows;
    private int currentRow;

    public QueryImpl() {
        rows = new ArrayList<>();
        currentRow = -1;
    }

    /**
     * Add one line as Map to query
     * @param row a map of data, first is always column label, second is value
     */
    @RestrictedApi(explanation = "This API is only for creating Query and should NOT be used outside.", link = "")
    public void addRow(final Map<String, Object> row) {
        rows.add(row);
    }

    @Override
    public boolean next() {
        currentRow++;
        return currentRow < rows.size();
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public void beforeFirst() {
        currentRow = -1;
    }

    @Override
    public void close() {
        rows.clear();
    }

    @Override
    public String getString(final int columnIndex) {
        return (String) getObject(columnIndex);
    }

    @Override
    public boolean getBoolean(final int columnIndex) {
        return getBoolean(findColumn(columnIndex));
    }

    @Override
    public byte getByte(final int columnIndex) {
        return (byte) getObject(columnIndex);
    }

    @Override
    public short getShort(final int columnIndex) {
        return (short) getObject(columnIndex);
    }

    @Override
    public int getInt(final int columnIndex) {
        return (int) getObject(columnIndex);
    }

    @Override
    public long getLong(final int columnIndex) {
        return (long) getObject(columnIndex);
    }

    @Override
    public float getFloat(final int columnIndex) {
        return (float) getObject(columnIndex);
    }

    @Override
    public double getDouble(final int columnIndex) {
        return (double) getObject(columnIndex);
    }

    @Override
    public Date getDate(final int columnIndex) {
        return Date.valueOf(getString(columnIndex));
    }

    @Override
    public Time getTime(final int columnIndex) {
        return Time.valueOf(getString(columnIndex));
    }

    @Override
    public Timestamp getTimestamp(final int columnIndex) {
        return Timestamp.valueOf(getString(columnIndex));
    }

    @Override
    public String getString(final String columnLabel) {
        return (String) getObject(columnLabel);
    }

    @Override
    public boolean getBoolean(final String columnLabel) {
        int data = getInt(columnLabel);
        if (data != 1 && data != 0) {
            throw new ClassCastException("Cannot convert " + data + " to boolean type.");
        }
        return data != 0;
    }

    @Override
    public byte getByte(final String columnLabel) {
        return (byte) getObject(columnLabel);
    }

    @Override
    public short getShort(final String columnLabel) {
        return (short) getObject(columnLabel);
    }

    @Override
    public int getInt(final String columnLabel) {
        return (int) getObject(columnLabel);
    }

    @Override
    public long getLong(final String columnLabel) {
        return (long) getObject(columnLabel);
    }

    @Override
    public float getFloat(final String columnLabel) {
        return (float) getObject(columnLabel);
    }

    @Override
    public double getDouble(final String columnLabel) {
        return (double) getObject(columnLabel);
    }

    @Override
    public Date getDate(final String columnLabel) {
        return Date.valueOf(getString(columnLabel));
    }

    @Override
    public Time getTime(final String columnLabel) {
        return Time.valueOf(getString(columnLabel));
    }

    @Override
    public Timestamp getTimestamp(final String columnLabel) {
        return Timestamp.valueOf(getString(columnLabel));
    }

    @Override
    public Object getObject(final int columnIndex) {
        return getObject(findColumn(columnIndex));
    }

    @Override
    public Object getObject(String columnLabel) {
        if (currentRow < 0) throw new UnsupportedOperationException("While getting object, you must firstly check if there is an object.");
        Map<String, Object> row = rows.get(currentRow);
        return row.get(columnLabel);
    }

    @Override
    public int findColumn(String columnLabel) {
        Map<String, Object> firstRow = rows.get(0);
        int columnIndex = 0;
        for (String key : firstRow.keySet()) {
            if (key.equalsIgnoreCase(columnLabel)) {
                return columnIndex;
            }
            columnIndex++;
        }
        return -1;
    }

    @Override
    public @Nullable String findColumn(final int columnIndex) {
        if (currentRow < 0) throw new UnsupportedOperationException("While getting object, you must firstly check if there is an object.");

        Map<String, Object> firstRow = rows.get(0);
        int currentIndex = 0;
        for (String column : firstRow.keySet()) {
            if (currentIndex == columnIndex) {
                return column;
            }
            currentIndex++;
        }

        return null;
    }

    @Override
    public QueryMetaData getMetaData() {
        QueryMetaData metaData = new QueryMetaDataImpl();
        if (!rows.isEmpty()) {
            Map<String, Object> firstRow = rows.get(0);
            for (String column : firstRow.keySet()) {
                metaData.addColumn(column);
            }
        }
        return metaData;
    }
}
