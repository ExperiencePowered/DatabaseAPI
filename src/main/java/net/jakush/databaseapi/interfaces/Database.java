package net.jakush.databaseapi.interfaces;

import com.zaxxer.hikari.HikariDataSource;
import net.jakush.databaseapi.databasetype.mysql.CommandType;
import net.jakush.databaseapi.enums.TableFlags;
import net.jakush.databaseapi.exceptions.QueryInitializationException;
import net.jakush.databaseapi.interfaces.commandtypes.SnapshotCommand;
import net.jakush.databaseapi.interfaces.commandtypes.QueryCommand;
import net.jakush.databaseapi.interfaces.query.Query;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Interface for database management and for database connection work
 */
public abstract class Database {

    protected final static HashMap<String, List<DatabaseProperty>> propertyList = new HashMap<>();
    protected final HikariDataSource hikari;

    public Database() {
        this.hikari = new HikariDataSource();
    }

    /**
     * Provides state of if is hikari connected or not
     * @return If hikari isn't connected, will return false; else true wll be returned
     */
    public boolean isConnected() {
        return !hikari.isClosed();
    }

    /**
     * Closes hikari connection if method {@link #isConnected()} returns true
     */
    public void close() {
        if (isConnected()) {
            hikari.close();
        }
    }

    /**
     * Creates table
     * @param table table name
     * @param flags flags like when should create a database (can be empty)
     * @param properties properties like (player varchar, coins int)
     * @return success (true if it was run successfully)
     */
    public abstract boolean createTable(final @NotNull String table, final @NotNull List<TableFlags> flags, final @NotNull List<DatabaseProperty> properties);

    /**
     * Creates statement
     *
     * @param type     type of statement (see more on {@link CommandType}
     * @param consumer consumer where you'll create the command
     * @return created statement
     */
    public abstract SnapshotCommand createStatement(final @NotNull CommandType type, final @NotNull Consumer<SnapshotCommand> consumer);

    /**
     * Runs statement (in normal case created from {@link Database#createStatement(CommandType, Consumer)})
     * @param statement the statement which will contain info. Example for a built statement is: INSERT INTO table (..., ...) VALUES (..., ...)
     * @return success (true if it was run successfully)
     * @throws QueryInitializationException will be thrown if command which was put in a statement is query command
     */
    public abstract boolean runStatement(final @NotNull SnapshotCommand statement) throws QueryInitializationException;

    /**
     * Runs query (in normal case created from {@link #createStatement(CommandType, Consumer)})
     * @param statement statement - the statement which will contain info about what to search in the query
     * @param consumer consumer which will contain all data from ran {@link java.sql.ResultSet}
     * @return success (true if it was run successfully)
     */
    public abstract boolean runQuery(final @NotNull QueryCommand statement, final @NotNull Consumer<Query> consumer);

    /**
     * When a database is created,
     * its name and properties (this part '... table (..., ...)') are saved in static hashmap.
     * This is method which accesses it
     * @param table table name
     * @return returns properties - (..., ...)
     */
    public static @NotNull @UnmodifiableView List<DatabaseProperty> getProperties(final @NotNull String table) {
        final List<DatabaseProperty> tableProperties = propertyList.get(table);
        Objects.requireNonNull(tableProperties);

        return Collections.unmodifiableList(tableProperties);
    }
}
