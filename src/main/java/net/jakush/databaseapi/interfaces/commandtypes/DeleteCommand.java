package net.jakush.databaseapi.interfaces.commandtypes;

import net.jakush.databaseapi.databasetype.mysql.CommandType;
import net.jakush.databaseapi.interfaces.SnapshotCondition;
import org.jetbrains.annotations.NotNull;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Interface for {@link CommandType#DELETE_FROM}
 */
public interface DeleteCommand extends SnapshotCommand {
    /**
     * In translate to MySQL commands, this means <br>
     * <b>WHERE something = '...'</b>
     * @param condition condition
     * @return builder (to make value-chain possible)
     */
    DeleteCommand setCondition(final @NotNull SnapshotCondition condition);

    /**
     * Sets table
     * @param tableName table name to set
     * @return builder (to make value-chain possible)
     */
    DeleteCommand setTable(final @NotNull String tableName);
}
