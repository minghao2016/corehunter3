/*--------------------------------------------------------------*/
/* Licensed to the Apache Software Foundation (ASF) under one   */
/* or more contributor license agreements.  See the NOTICE file */
/* distributed with this work for additional information        */
/* regarding copyright ownership.  The ASF licenses this file   */
/* to you under the Apache License, Version 2.0 (the            */
/* "License"); you may not use this file except in compliance   */
/* with the License.  You may obtain a copy of the License at   */
/*                                                              */
/*   http://www.apache.org/licenses/LICENSE-2.0                 */
/*                                                              */
/* Unless required by applicable law or agreed to in writing,   */
/* software distributed under the License is distributed on an  */
/* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       */
/* KIND, either express or implied.  See the License for the    */
/* specific language governing permissions and limitations      */
/* under the License.                                           */
/*--------------------------------------------------------------*/

package org.corehunter.data.simple;

import static uno.informatics.common.Constants.UNKNOWN_INDEX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.corehunter.data.BiAllelicGenotypeVariantData;
import org.corehunter.data.NamedGenotypeVariantData;

import uno.informatics.common.io.FileProperties;
import uno.informatics.common.io.IOUtilities;
import uno.informatics.common.io.RowReader;

/**
 * @author Guy Davenport
 */
public class SimpleBiAllelicGenotypeVariantData extends AbstractNamedSubsetData
        implements BiAllelicGenotypeVariantData, NamedGenotypeVariantData {

    private int[][] alleleScores;
    private int numberOfMarkers;
    private String[] markerNames;

    public SimpleBiAllelicGenotypeVariantData(String name, String[] itemNames,
                                              String[] markerNames, int[][] alleleScores) {
        super(name, itemNames);

        if (markerNames == null) {
            throw new IllegalArgumentException("Marker names not defined!");
        }

        if (alleleScores == null) {
            throw new IllegalArgumentException("Alleles not deifned!");
        }

        if (getNames().length != alleleScores.length) {
            throw new IllegalArgumentException("Number of alleleScores don't match number of names!");
        }

        if (alleleScores.length > 0) {
            numberOfMarkers = alleleScores[0].length;

            if (numberOfMarkers != markerNames.length) {
                throw new IllegalArgumentException("Number of marker names don't match number of markers!");
            }

            this.markerNames = new String[numberOfMarkers];

            this.alleleScores = new int[alleleScores.length][numberOfMarkers];

            for (int j = 0; j < numberOfMarkers; ++j) {
                this.markerNames[j] = markerNames[j];
            }

            for (int i = 0; i < alleleScores.length; ++i) {
                if (numberOfMarkers != alleleScores[i].length) {
                    throw new IllegalArgumentException("Number of markers don't match for id : " + i);
                }

                for (int j = 0; j < numberOfMarkers; ++j) {
                    this.alleleScores[i][j] = alleleScores[i][j];
                }
            }
        } else {
            this.alleleScores = new int[0][0];
        }
    }

    public SimpleBiAllelicGenotypeVariantData(String name, List<String> itemNames,
            List<String> markerNames, List<List<Integer>> alleleScores) {
        
        super(name, itemNames);

        if (markerNames == null) {
            throw new IllegalArgumentException("Marker names not defined!");
        }

        if (alleleScores == null) {
            throw new IllegalArgumentException("Alleles not deifned!");
        }

        if (getNames().length != alleleScores.size()) {
            throw new IllegalArgumentException("Number of alleleScores don't match number of names!");
        }

        if (alleleScores.size() > 0) {
            numberOfMarkers = markerNames.size();

            this.markerNames = new String[numberOfMarkers];

            this.alleleScores = new int[alleleScores.size()][numberOfMarkers];

            Iterator<String> markerNameIterator = markerNames.iterator();

            int i = 0;
            int j = 0;

            while (markerNameIterator.hasNext()) {
                this.markerNames[j] = markerNameIterator.next();

                ++j;
            }

            Iterator<List<Integer>> alleleScoresIterator = alleleScores.iterator();
            List<Integer> markerAlleleScores;
            Iterator<Integer> markerAlleleScoresIterator;

            while (alleleScoresIterator.hasNext()) {
                j = 0;

                markerAlleleScores = alleleScoresIterator.next();

                if (numberOfMarkers != markerAlleleScores.size()) {
                    throw new IllegalArgumentException("Number of markers don't match for id : " + i);
                }

                markerAlleleScoresIterator = markerAlleleScores.iterator();

                while (markerAlleleScoresIterator.hasNext()) {
                    this.alleleScores[i][j] = markerAlleleScoresIterator.next();

                    ++j;
                }

                ++i;
            }
        } else {
            this.alleleScores = new int[0][0];
        }
    }

    /* 
     * (non-Javadoc)
     * @see org.corehunter.data.GenotypeVariantData#getNumberOfMarkers()
     */
    @Override
    public int getNumberOfMarkers() {
        return numberOfMarkers;
    }

    /* 
     * (non-Javadoc)
     * @see org.corehunter.data.BiAllelicGenotypeVariantData#getAlleleScore(int, int)
     */
    @Override
    public int getAlleleScore(int id, int markerIndex) {
        return alleleScores[id][markerIndex];
    }

    /*
     * (non-Javadoc)
     * @see org.corehunter.data.NamedGenotypeVariantData#getMarkerName(int)
     */
    @Override
    public String getMarkerName(int markerIndex)
            throws ArrayIndexOutOfBoundsException {
        return markerNames[markerIndex];
    }

    public static final SimpleBiAllelicGenotypeVariantData readData(
            FileProperties fileProperties) throws IOException {
        
        RowReader reader;

        if (fileProperties == null) {
            throw new IOException("File properties not defined!");
        }

        if (fileProperties.getFile() == null) {
            throw new IOException("File not defined!");
        }

        if (fileProperties.getFileType() == null) {
            throw new IOException("File type not defined!");
        }

        if (!fileProperties.hasColumnHeader()) {
            throw new IOException("Column headers must be defined!");
        }

        if (!fileProperties.hasRowHeader()) {
            throw new IOException("Row headers must be defined!");
        }

        if (fileProperties.getDataRowPosition() <= fileProperties.getColumnHeaderPosition()) {
            throw new IOException("Column header position : " + fileProperties.getDataRowPosition()
                                + " must be before data position : " + fileProperties.getColumnHeaderPosition());
        }

        if (!fileProperties.getFile().exists()) {
            throw new IOException("File does not exist : " + fileProperties.getFile());
        }

        List<String> itemsNames = new LinkedList<>();
        List<String> markerNames = new LinkedList<>();

        String name;

        List<Integer> alleleScoresRow;
        List<List<Integer>> alleleScores;

        int row = 0;

        try {
            reader = IOUtilities.createRowReader(fileProperties);

            if (reader != null && reader.ready()) {
                int columnCount = 0;

                if (reader.nextRow()) {
                    if (fileProperties.getRowHeaderPosition() > UNKNOWN_INDEX) {
                        while (row < fileProperties.getRowHeaderPosition() && reader.nextRow()) {
                            ++row;
                        }
                    }

                    reader.nextColumn();
                    reader.nextColumn();

                    markerNames = reader.getRowCellsAsString();

                    columnCount = markerNames.size();

                    alleleScores = new LinkedList<>();

                    if (fileProperties.getDataRowPosition() > UNKNOWN_INDEX) {
                        while (row < fileProperties.getDataRowPosition() && reader.nextRow()) {
                            ++row;
                        }
                    }

                    reader.nextColumn();

                    name = reader.getCellAsString();

                    itemsNames.add(name);

                    reader.nextColumn();

                    alleleScoresRow = reader.getRowCellsAsInt();

                    if (alleleScoresRow.size() != columnCount) {
                        throw new IOException("Rows are not all the same size!");
                    }

                    alleleScores.add(alleleScoresRow);

                    ++row;

                    while (reader.nextRow()) {
                        reader.nextColumn();

                        name = reader.getCellAsString();

                        itemsNames.add(name);

                        reader.nextColumn();

                        alleleScoresRow = reader.getRowCellsAsInt();

                        if (alleleScoresRow.size() != columnCount) {
                            throw new IOException("Rows are not all the same size!");
                        }

                        alleleScores.add(alleleScoresRow);

                        ++row;
                    }
                } else {
                    alleleScores = new ArrayList<>(0);
                }
            } else {
                alleleScores = new ArrayList<>(0);
            }

            if (reader != null) {
                reader.close();
            }

            return new SimpleBiAllelicGenotypeVariantData(fileProperties.getFile().getName(),
                                                          itemsNames, markerNames, alleleScores);

        } catch (IOException e) {
            throw new IOException("Error reading file at row : " + row + " due to " + e.getMessage(), e);
        }
    }
}