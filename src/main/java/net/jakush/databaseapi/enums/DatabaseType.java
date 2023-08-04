package net.jakush.databaseapi.enums;

import net.jakush.databaseapi.interfaces.Database;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Provides supported database types (except {@link DatabaseType#NON_SUPPORTED})
 */
public enum DatabaseType {

    MySQL(net.jakush.databaseapi.databasetype.mysql.MySQL.class),
    NON_SUPPORTED(net.jakush.databaseapi.databasetype.unknown.Unknown.class),
    ;

    private final Class<? extends Database> clazz;

    DatabaseType(Class<? extends Database> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends Database> getClazz() {
        return clazz;
    }
}
