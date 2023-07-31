package net.jakush.databaseapi.interfaces;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Interface for providing all data which will be used later for connection
 */
public interface DatabaseData {

    /**
     * Gets host name
     * @return host name
     */
    String getHost();

    /**
     * Gets port
     * @return port
     */
    int getPort();

    /**
     * Gets database name
     * @return database name
     */
    String getDatabase();

    /**
     * Gets username, may be null, so it's recommended to always check if value is present via {@link Optional#isPresent()} before accessing value
     * @return optional of username
     */
    Optional<String> getUsername();
    /**
     * Gets password, may be null, so it's recommended to always check if value is present via {@link Optional#isPresent()} before accessing value
     * @return optional of password
     */
    Optional<String> getPassword();

    /**
     * Method for creating instance without having to create messy code, important to say, values which are put in method won't be able to be changed as its final and implemented
     * @param host host name
     * @param port port name
     * @param database database name
     * @param username username (may be null)
     * @param password password (may be null)
     * @return new instance of {@link DatabaseData}
     */
    @Contract(value = "_, _, _, _, _ -> new", pure = true)
    static @NotNull DatabaseData getInstance(final @NotNull String host, final int port, final @NotNull String database, final @Nullable String username, final @Nullable String password) {
        return new DatabaseData() {
            @Override
            public String getHost() {
                return host;
            }

            @Override
            public int getPort() {
                return 3306;
            }

            @Override
            public String getDatabase() {
                return database;
            }

            @Override
            public Optional<String> getUsername() {
                return Optional.ofNullable(username);
            }

            @Override
            public Optional<String> getPassword() {
                return Optional.ofNullable(password);
            }
        };
    }

}
