package net.jakush.databaseapi.enums;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Used for t
 */
public enum DataType {

    /**
      * <h2 style="color: #1e90ff;">String Data Types</h2>
      */
    CHAR(String.class, 255),
    VARCHAR(String.class, 65535),
    BINARY(Byte.class),
    TINY_BLOB(Blob.class, 255),
    TINY_TEXT(String.class),
    TEXT(String.class, 65535),
    BLOB(Blob.class, 65535),
    MEDIUM_TEXT(String.class, 16777215),
    MEDIUM_BLOB(Blob.class, 16777215),
    LONG_TEXT(String.class, 4294967295L),
    LONG_BLOB(Blob.class, 4294967295L),
    // ENUM, SET - NOT FOUND A WAY TO IMPLEMENT THIS YET

    /**
     * <h2 style="color: #c71585;">Numeric Data Types</h2>
     */
    // BIT - NOT FOUND A WAY TO IMPLEMENT THIS YET
    TINY_INT(Integer.class),
    BOOL(Boolean.class),
    BOOLEAN(Boolean.class),
    SMALL_INT(Integer.class),
    MEDIUM_INT(Integer.class),
    INT(Integer.class),
    INTEGER(Integer.class),
    BIG_INT(Integer.class),
    FLOAT(Float.class),
    DOUBLE(Double.class),
    // DOUBLE PRECISION, DECIMAL, DEC - NOT FOUND A WAY TO IMPLEMENT THIS YET

    /**
     * <h2 style="color: #00ff00;">Date and Time Data Types</h2>
     */
    DATE(Date.class),
    // DATETIME - NOT FOUND A WAY TO IMPLEMENT THIS YET
    TIMESTAMP(Timestamp.class),
    TIME(Time.class),
    @ApiStatus.Experimental // Could be broken
    YEAR(Integer.class);

    private final @NotNull Class<?> clazz;
    private long size = -1;

    DataType(@NotNull Class<?> clazz) {
        this.clazz = clazz;
    }

    DataType(@NotNull Class<?> clazz, long size) {
        this.clazz = clazz;
        this.size = size;
    }

    /**
     * Every enum has its class to convert a type to java <br>
     * Example: <br>
     * {@link DataType#INT} will return Integer class and value has to extend Integer as well.
     * @return class of that type
     */
    public @NotNull Class<?> getClazz() {
        return clazz;
    }

    public long getSize() {
        return size;
    }
}
