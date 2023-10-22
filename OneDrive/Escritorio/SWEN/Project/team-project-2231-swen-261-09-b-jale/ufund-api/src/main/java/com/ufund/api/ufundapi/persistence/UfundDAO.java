package com.ufund.api.ufundapi.persistence;

import java.io.IOException;

import com.ufund.api.ufundapi.model.Need;

/*
 * The interface for persising needs data
 *
 * @author E3M
 */
public interface UfundDAO {
    /**
     * Retrieves all needs 
     * @return An array of need objects 
     * @throws IOException if an issue with underlying storage
     */
    Need[] getNeeds() throws IOException;

    /**
     * Retrieves all needs that match the name 
     * @return An array of need objects 
     * @throws IOException if an issue with underlying storage
     */
    Need[] searchNeeds(String name) throws IOException;

    /**
     * Gets a single need by name
     * @param name of the neet to search for 
     * @throws IOException if an issue with underlying storage
     */
    Need getNeed(String Name) throws IOException;

    /**
     * Creates and saves a need 
     * @param need object to be created and saved
     * @return new need if sucessful, false if otherwise *
     * @throws IOException if an issue with underlying storage
     */
    Need createNeed(Need need) throws IOException;

    /**
     * Updates and saves a need 
     * @param need object to be updated and saved
     * @return updated need if successful, null if otherwise
     * @throws IOException if underlying storage cannot be accessed
     */
    Need updateNeed(Need need) throws IOException;

    /**
     * Deletes a need with the given name
     * @param name The name of the need
     * @return true if the need is deleted sucessfully, false if otherwise
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteNeed(String name) throws IOException;

}
