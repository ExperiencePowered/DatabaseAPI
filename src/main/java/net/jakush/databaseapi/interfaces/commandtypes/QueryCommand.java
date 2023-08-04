package net.jakush.databaseapi.interfaces.commandtypes;

import net.jakush.databaseapi.interfaces.query.Query;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Interface for query type commands
 */
public interface QueryCommand extends SnapshotCommand {
    /**
     * Gets query
     * @return query
     */
    Query getQuery();

}
