package net.jakush.databaseapi.serializers;

import net.jakush.databaseapi.enums.DataType;
import net.jakush.databaseapi.exceptions.PropertySerializationException;
import net.jakush.databaseapi.interfaces.DatabaseProperty;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * This file is a part of DatabaseAPI <br>
 * Author: <a href="https://github.com/Jakush">Jakush</a><br>
 * <br>
 * Serializer (& deserializer) class for list of {@link DatabaseProperty}
 */
public class DatabasePropertySerializer {

    /**
     * Converts a property list to String
     * @param propertyList list with properties
     * @param braces if on start and on end should be braces - ()
     * @return deserialized string
     */
    public static @NotNull String deserialize(final @NotNull List<DatabaseProperty> propertyList, boolean braces) {
        StringBuilder propertiesString = new StringBuilder();
        int expectedSize = propertyList.size();

        if (braces) propertiesString.append("(");
        IntStream.range(0, expectedSize).forEach(i -> {
            DatabaseProperty property = propertyList.get(i);
            propertiesString
                    .append(property.name())
                    .append(" ")
                    .append(property
                            .type()
                            .name()
                            .replace("_", "")
                    );
            if (property.type().getSize() != -1) propertiesString
                    .append("(")
                    .append(property
                            .type()
                            .getSize())
                    .append(")");
            if (i != expectedSize - 1) {
                propertiesString
                        .append(", ");
            }
        });
        if (braces) propertiesString.append(")");

        return propertiesString.toString();
    }

    /**
     * Converts a list to a property list
     * @param list list which should be converted
     * @return serialized list
     */
    public static @NotNull List<DatabaseProperty> serialize(final @NotNull List<String> list) throws PropertySerializationException {
        final List<DatabaseProperty> propertyList = new ArrayList<>();

        list.forEach(string -> {
            String[] array = string.split(" ");
            if (array.length > 2) {
                throw new PropertySerializationException("Invalid property.");
            }
            String name = array[0];
            DataType type = DataType.valueOf(array[1].toUpperCase());

            propertyList.add(new DatabaseProperty(name, type));
        });

        return propertyList;
    }
}
