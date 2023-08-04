package net.jakush.databaseapi.exceptions;

import net.jakush.databaseapi.interfaces.Database;
import net.jakush.databaseapi.interfaces.commandtypes.SnapshotCommand;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Exception which should be only thrown, when query command is used in {@link Database#runStatement(SnapshotCommand)}
 */
public class QueryInitializationException extends IllegalStateException {
    public QueryInitializationException(final String s) {
        super(s);
    }
}
