package net.jakush.databaseapi.interfaces.query.impl;

import net.jakush.databaseapi.interfaces.query.QueryMetaData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Impl of {@link QueryMetaData}
 */

public class QueryMetaDataImpl implements QueryMetaData {

    private final List<String> columnNames;

    public QueryMetaDataImpl() {
        columnNames = new ArrayList<>();
    }

    @Override
    public void addColumn(@NotNull String columnName) {
        columnNames.add(columnName);
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames.get(columnIndex);
    }
}
