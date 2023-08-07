package net.jakush.databaseapi.databasetype.mysql.command;

import net.jakush.databaseapi.databasetype.mysql.CommandType;
import net.jakush.databaseapi.interfaces.SnapshotCondition;
import net.jakush.databaseapi.interfaces.commandtypes.UpdateCommand;
import net.jakush.databaseapi.utils.DatabaseCommandBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Impl for {@link UpdateCommand}
 */
public class UpdateCommandImpl implements UpdateCommand {

    private String table;
    private SnapshotCondition updateSet;
    private SnapshotCondition condition;

    @Override
    public String getTable() {
        Objects.requireNonNull(table, "Table name was null when accessing it.");
        return table;
    }

    @Override
    public UpdateCommand setTable(final @NotNull String table) {
        this.table = table;
        return this;
    }

    @Override
    public UpdateCommand setUpdateSet(final @NotNull SnapshotCondition updateSet) {
        this.updateSet = updateSet;
        return this;
    }

    @Override
    public UpdateCommand setCondition(final @NotNull SnapshotCondition condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public String toString() {
        Objects.requireNonNull(table, "Table was not set!");

        final DatabaseCommandBuilder commandBuilder = DatabaseCommandBuilder.getInstance()
                .setBase(CommandType.UPDATE)
                .setTable(null, table, false)
                .setUpdate(updateSet)
                .setCondition(condition);

        return commandBuilder.toString();
    }
}
