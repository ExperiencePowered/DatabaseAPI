package net.jakush.databaseapi.interfaces.query;

import org.jetbrains.annotations.NotNull;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Interface for providing metadata about a query
 */
public interface QueryMetaData {

    /**
     * Adds column to metadata
     * @param columnName name of column
     */
    void addColumn(final @NotNull String columnName);

    /**
     * Gets column size
     * @return total column amount
     */
    int getColumnCount();

    /**
     * Gets columnName by finding columnIndex
     * @param columnIndex index
     * @return columnName
     */
    String getColumnName(final int columnIndex);

}
