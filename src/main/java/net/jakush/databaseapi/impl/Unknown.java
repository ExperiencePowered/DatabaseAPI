package net.jakush.databaseapi.impl;

import net.jakush.databaseapi.enums.DatabaseType;
import net.jakush.databaseapi.enums.TableFlags;
import net.jakush.databaseapi.interfaces.Database;
import net.jakush.databaseapi.interfaces.DatabaseProperty;

import java.util.List;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Impl for {@link Database} abstract class<br>
 * If you want to get database instance of MySQL, use {@link DatabaseType#NON_SUPPORTED}
 * This impl is made for debugging use from now. This means this impl may be removed at any time without deprecation
 */
public final class Unknown extends Database {

    public Unknown() {
        throw new UnsupportedOperationException("This database type is not implemented.");
    }

    @Override
    public boolean isConnected() {
        throw new UnsupportedOperationException("This database type is not implemented.");
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("This database type is not implemented.");
    }

    @Override
    public boolean createTable(String table, List<TableFlags> flags, List<DatabaseProperty> properties) {
        return isConnected();
    }
}
