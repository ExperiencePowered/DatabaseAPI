package net.jakush.databaseapi.databasetype.mysql.command;

import net.jakush.databaseapi.enums.CommandType;
import net.jakush.databaseapi.interfaces.SnapshotCondition;
import net.jakush.databaseapi.interfaces.commandtypes.DeleteCommand;
import net.jakush.databaseapi.interfaces.commandtypes.SnapshotCommand;
import net.jakush.databaseapi.utils.DatabaseCommandBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Impl for {@link DeleteCommand.Builder}
 */
public class DeleteCommandImpl implements DeleteCommand.Builder {

    private String table;
    private SnapshotCondition where;

    @Override
    public DeleteCommand.Builder setTable(final @NotNull String table) {
        this.table = table;
        return this;
    }

    @Override
    public DeleteCommand.Builder setCondition(final @NotNull SnapshotCondition condition) {
        this.where = condition;
        return this;
    }

    @Override
    public SnapshotCommand build() {
        Objects.requireNonNull(table, "Table was not set!");

        final DatabaseCommandBuilder commandBuilder = DatabaseCommandBuilder.getInstance()
                .setBase(CommandType.DELETE_FROM)
                .setTable("", table, false, false)
                .setCondition(where);

        return new DeleteCommand() {
            @Override
            public String getCommand() {
                return commandBuilder.toString();
            }

            @Override
            public String getTable() {
                return table;
            }
        };
    }
}
