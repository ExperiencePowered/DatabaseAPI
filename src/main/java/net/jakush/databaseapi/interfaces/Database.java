package net.jakush.databaseapi.interfaces;

import com.zaxxer.hikari.HikariDataSource;
import net.jakush.databaseapi.enums.TableFlags;

import java.util.List;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Interface for database management and for database connection work
 */
public abstract class Database {

    protected final HikariDataSource hikari;

    public Database() {
        this.hikari = new HikariDataSource();
    }

    /**
     * Provides state of if is hikari connected or not
     * @return If hikari isn't connected, will return false; else true wll be returned
     */
    public boolean isConnected() {
        return !hikari.isClosed();
    }

    /**
     * Closes hikari connection if method {@link #isConnected()} returns true
     */
    public void close() {
        if (isConnected()) {
            hikari.close();
        }
    }

    public abstract boolean createTable(String table, List<TableFlags> flags, List<DatabaseProperty> properties);
}
