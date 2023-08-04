package net.jakush.databaseapi.enums;

import net.jakush.databaseapi.databasetype.mysql.command.DeleteCommandImpl;
import net.jakush.databaseapi.databasetype.mysql.command.InsertCommandImpl;
import net.jakush.databaseapi.databasetype.mysql.command.SelectCommandImpl;
import net.jakush.databaseapi.databasetype.mysql.command.UpdateCommandImpl;
import net.jakush.databaseapi.interfaces.commandtypes.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Types of command with builder class (so they can be used later)
 */
public enum CommandType {

    SELECT(SelectCommandImpl.class),
    UPDATE(UpdateCommandImpl.class),
    DELETE_FROM(DeleteCommandImpl.class),
    INSERT_INTO(InsertCommandImpl.class);

    private final Class<? extends SnapshotCommand.Builder> builderClass;

    CommandType(final @NotNull Class<? extends SnapshotCommand.Builder> builderClass) {
        this.builderClass = builderClass;
    }

    public Class<? extends SnapshotCommand.Builder> getBuilderClass() {
        return builderClass;
    }

    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return name();
    }
}
