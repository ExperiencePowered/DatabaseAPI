package net.jakush.databaseapi.interfaces.commandtypes;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import net.jakush.databaseapi.enums.CommandType;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Interface for {@link CommandType#INSERT_INTO}
 */
public interface InsertCommand extends SnapshotCommand {
    interface Builder extends SnapshotCommand.Builder {
        /**
         * Sets values <br>
         * Example of this is: <br>
         * <br>
         * Already put string without this method will look like <br>
         * <b>INSERT INTO table</b> <br>
         * And now you need to put there also values - (..., ...) <br>
         * Which obviously does this method
         *
         * @param table table name
         * @return builder (to make value-chain possible)
         */
        Builder setValues(final @NotNull List<String> valueList);
    }
}
