package net.jakush.databaseapi.interfaces.query;

import net.jakush.databaseapi.databasetype.mysql.CommandType;

import java.util.function.Consumer;

import net.jakush.databaseapi.interfaces.Database;
import org.jetbrains.annotations.Nullable;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Interface for getting values, looping through values obtained from {@link Database#createStatement(CommandType, Consumer)}
 */
public interface Query {

    /**
     * Checks if there is next query <br>
     * Should be used before interacting with getters
     * @return if there is next query
     */
    boolean next();

    /**
     * Gets current row
     * @return row count
     */
    int getRowCount();

    /**
     * Moves cursor to -1<br>
     * If a query will be used more than once, this should be used before next query loop
     */
    void beforeFirst();

    /**
     * Closes a query (if someone tried to get an object then, it wouldn't do anything)<br>
     * Recommended when using a query is done
     */
    void close();


    /**
     * Gets string at
     * @param columnIndex index
     * @return string
     */
    String getString(int columnIndex);

    /**
     * Gets boolean at
     * @param columnIndex index
     * @return boolean
     */
    boolean getBoolean(int columnIndex);

    /**
     * Gets byte at
     * @param columnIndex index
     * @return byte
     */
    byte getByte(int columnIndex);

    /**
     * Gets short at
     * @param columnIndex index
     * @return short
     */
    short getShort(int columnIndex);

    /**
     * Gets int at
     * @param columnIndex index
     * @return int
     */
    int getInt(int columnIndex);

    /**
     * Gets long at
     * @param columnIndex index
     * @return long
     */
    long getLong(int columnIndex);

    /**
     * Gets float at
     * @param columnIndex index
     * @return float
     */
    float getFloat(int columnIndex);

    /**
     * Gets double at
     * @param columnIndex index
     * @return double
     */
    double getDouble(int columnIndex);

    /**
     * Gets {@link java.sql.Date} at
     * @param columnIndex index
     * @return {@link java.sql.Date}
     */
    java.sql.Date getDate(int columnIndex);

    /**
     * Gets {@link java.sql.Time} at
     * @param columnIndex index
     * @return {@link java.sql.Time}
     */
    java.sql.Time getTime(int columnIndex);

    /**
     * Gets {@link java.sql.Timestamp} at
     * @param columnIndex index
     * @return {@link java.sql.Timestamp}
     */
    java.sql.Timestamp getTimestamp(int columnIndex);

    /**
     * Gets string at
     * @param columnLabel label
     * @return string
     */
    String getString(String columnLabel);

    /**
     * Gets boolean at
     * @param columnLabel label
     * @return boolean
     */
    boolean getBoolean(String columnLabel);

    /**
     * Gets byte at
     * @param columnLabel label
     * @return byte
     */
    byte getByte(String columnLabel);

    /**
     * Gets short at
     * @param columnLabel label
     * @return short
     */
    short getShort(String columnLabel);

    /**
     * Gets int at
     * @param columnLabel label
     * @return int
     */
    int getInt(String columnLabel);

    /**
     * Gets long at
     * @param columnLabel label
     * @return long
     */
    long getLong(String columnLabel);

    /**
     * Gets float at
     * @param columnLabel label
     * @return float
     */
    float getFloat(String columnLabel);

    /**
     * Gets double at
     * @param columnLabel label
     * @return double
     */
    double getDouble(String columnLabel);

    /**
     * Gets {@link java.sql.Date} at
     * @param columnLabel label
     * @return {@link java.sql.Date}
     */
    java.sql.Date getDate(String columnLabel);

    /**
     * Gets {@link java.sql.Time} at
     * @param columnLabel label
     * @return {@link java.sql.Time}
     */
    java.sql.Time getTime(String columnLabel);

    /**
     * Gets {@link java.sql.Timestamp} at
     * @param columnLabel label
     * @return {@link java.sql.Timestamp}
     */
    java.sql.Timestamp getTimestamp(String columnLabel);

    /**
     * Gets an object at
     * @param columnIndex index
     * @return an object
     */
    Object getObject(int columnIndex);

    /**
     * Gets an object at
     * @param columnLabel label
     * @return an object
     */
    Object getObject(String columnLabel);

    /**
     * Tries to find column in which is column is named by label
     * @param columnLabel label
     * @return if found, returns column cursor, otherwise -1 will be returned
     */
    int findColumn(String columnLabel);

    /**
     * Tries to find label in which is label with cursor by index
     * @param columnIndex index
     * @return if found, returns column name, otherwise null will be returned
     */
    @Nullable String findColumn(int columnIndex);

    /**
     * Creates new metadata instance of query class
     * @return {@link QueryMetaData}
     */
    QueryMetaData getMetaData();

}
