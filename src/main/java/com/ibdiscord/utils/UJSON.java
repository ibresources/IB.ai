/* Copyright 2017-2019 Jarred Vardy <jarred.vardy@gmail.com>
 *
 * This file is part of IB.ai.
 *
 * IB.ai is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IB.ai is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with IB.ai. If not, see http://www.gnu.org/licenses/.
 */

package com.ibdiscord.utils;

import de.arraying.kotys.JSON;

import java.io.*;

public final class UJSON {

    /**
     * Reads a file from disk and loads data into a JSON object.
     * @param relativeFilePath Relative path to JSON file from project root
     * @param charset The charset to use to decode the file.
     * @return JSON object corresponding to file at desired location.
     */
    public static JSON retrieveJSONFromFile(String relativeFilePath, String charset) {
        StringBuilder jsonBuilder = new StringBuilder();
        try {
            //BufferedReader reader = new BufferedReader(new FileReader(relativeFilePath));
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(new File(relativeFilePath)),
                    charset
            ));
            String line = reader.readLine();
            while(line != null) {
                jsonBuilder.append(line);
                line = reader.readLine();
            }
            reader.close();
            System.out.println("Loading using charset " + charset);
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        return new JSON(jsonBuilder.toString());
    }

    /**
     * Alias to {@link #retrieveJSONFromFile(String, String)} with default UTF-8 encoding.
     * @param relativeFilePath Relatie path to JSON file from project root.
     * @return JSON object corresponding to file at desired location.
     */
    public static JSON retrieveJSONFromFile(String relativeFilePath) {
        return retrieveJSONFromFile(relativeFilePath, "UTF-8");
    }

}
