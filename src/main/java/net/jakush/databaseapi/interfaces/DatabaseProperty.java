package net.jakush.databaseapi.interfaces;

import net.jakush.databaseapi.enums.DataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Record for property used for creating table (look on {@link Database#createTable(String, List, List)}
 */
public record DatabaseProperty(@NotNull String name, @NotNull DataType type) {}
