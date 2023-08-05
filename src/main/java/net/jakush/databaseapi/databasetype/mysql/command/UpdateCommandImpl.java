package net.jakush.databaseapi.databasetype.mysql.command;

import net.jakush.databaseapi.enums.CommandType;
import net.jakush.databaseapi.interfaces.SnapshotCondition;
import net.jakush.databaseapi.interfaces.commandtypes.SnapshotCommand;
import net.jakush.databaseapi.interfaces.commandtypes.UpdateCommand;
import net.jakush.databaseapi.utils.DatabaseCommandBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Impl for {@link UpdateCommand.Builder}
 */
public class UpdateCommandImpl implements UpdateCommand.Builder {

    private String table;
    private SnapshotCondition updateSet;
    private SnapshotCondition condition;

    @Override
    public UpdateCommand.Builder setTable(final @NotNull String table) {
        this.table = table;
        return this;
    }

    @Override
    public UpdateCommand.Builder setUpdate(final @NotNull SnapshotCondition updateSet) {
        this.updateSet = updateSet;
        return this;
    }

    @Override
    public UpdateCommand.Builder setCondition(final @NotNull SnapshotCondition condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public SnapshotCommand build() {
        Objects.requireNonNull(table, "Table was not set!");

        final DatabaseCommandBuilder commandBuilder = DatabaseCommandBuilder.getInstance()
                .setBase(CommandType.UPDATE)
                .setTable("", table, false, false)
                .setUpdate(updateSet)
                .setCondition(condition);

        return new UpdateCommand() {
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
