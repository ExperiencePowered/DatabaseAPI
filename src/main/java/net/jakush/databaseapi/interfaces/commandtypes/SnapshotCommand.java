package net.jakush.databaseapi.interfaces.commandtypes;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Interface for all possible commands
 */
public interface SnapshotCommand {

    /**
     * Gets command in String (returns String ready to be used in a statement)
     * @return string
     */
    String toString();

    /**
     * Gets table name
     * @return table name
     */
    String getTable();
}
