package net.jakush.databaseapi.databasetype.unknown;

import net.jakush.databaseapi.enums.CommandType;
import net.jakush.databaseapi.enums.DatabaseType;
import net.jakush.databaseapi.enums.TableFlags;
import net.jakush.databaseapi.interfaces.Database;
import net.jakush.databaseapi.interfaces.DatabaseCredentials;
import net.jakush.databaseapi.interfaces.DatabaseProperty;
import net.jakush.databaseapi.interfaces.commandtypes.QueryCommand;
import net.jakush.databaseapi.interfaces.commandtypes.SnapshotCommand;
import net.jakush.databaseapi.interfaces.query.Query;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Impl for {@link Database} abstract class<br>
 * If you want to get database instance of MySQL, use {@link DatabaseType#NON_SUPPORTED}
 * This impl is made for debugging use from now. This means this impl may be removed at any time without deprecation
 */
public final class Unknown extends Database {

    public Unknown(final @NotNull DatabaseCredentials credentials) {
        throw new UnsupportedOperationException("This database type is not implemented.");
    }

    @Override
    public boolean isConnected() {
        throw new UnsupportedOperationException("This database type is not implemented.");
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("This database type is not implemented.");
    }

    @Override
    public boolean createTable(final @NotNull String table, final @NotNull List<TableFlags> flags, final @NotNull List<DatabaseProperty> properties) {
        throw new UnsupportedOperationException("This database type is not implemented.");
    }

    @Override
    public SnapshotCommand createStatement(final @NotNull CommandType type, final @NotNull Consumer<SnapshotCommand.Builder> consumer) {
        throw new UnsupportedOperationException("This database type is not implemented.");
    }

    @Override
    public boolean runStatement(final @NotNull SnapshotCommand statement) {
        throw new UnsupportedOperationException("This database type is not implemented.");
    }

    @Override
    public boolean runQuery(final @NotNull QueryCommand statement, final @NotNull Consumer<Query> consumer) {
        throw new UnsupportedOperationException("This database type is not implemented.");
    }
}
