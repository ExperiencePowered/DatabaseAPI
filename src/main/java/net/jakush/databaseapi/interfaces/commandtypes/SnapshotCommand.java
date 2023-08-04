package net.jakush.databaseapi.interfaces.commandtypes;

import org.jetbrains.annotations.NotNull;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Interface for all possible commands
 */
public interface SnapshotCommand {
    /**
     * Gets command in String (returns String ready to be used in statement)
     * @return string
     */
    String getCommand();

    /**
     * Gets table which was added while building command
     * @return table name
     */
    String getTable();

    /**
     * Interface for providing build command
     */
    interface Builder {
        /**
         * Sets table
         * @param table table name
         * @return builder (to make value-chain possible)
         */
        SnapshotCommand.Builder setTable(final @NotNull String table);

        /**
         * Builds command from Builder
         * @return Command
         */
        SnapshotCommand build();
    }
}
