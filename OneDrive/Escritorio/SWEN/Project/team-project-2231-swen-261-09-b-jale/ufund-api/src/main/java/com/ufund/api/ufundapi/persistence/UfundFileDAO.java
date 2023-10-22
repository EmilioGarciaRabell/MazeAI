package com.ufund.api.ufundapi.persistence;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Need;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/*
 * An implementation of the UfundDAO that stores
 * data to a file
 *
 * @author E3M
 */
@Component
public class UfundFileDAO implements UfundDAO {

	private static final Logger LOG = Logger.getLogger(UfundFileDAO.class.getName());

	HashMap<String, Need> needs;

	private ObjectMapper objectMapper;

	private String filename;

	public UfundFileDAO(@Value("${needs.file}")String filename ,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    /**
     * Generates an array of {@linkplain Hero heroes} from the tree map
     *
     * @return  The array of {@link Hero heroes}, may be empty
     */
    // private Need[] getNeedsArray() {
    //     return getNeedsArray(null);
    // }

	// /**
	//  *
	//  */

	// private Need[] getNeedsArray(String containsText){
	// 	ArrayList<Need> needArrayList = new ArrayList<>();

    //     for (Need need : needs.values()) {
    //         if (containsText == null || need.getName().contains(containsText)) {
    //             needArrayList.add(need);
    //         }
    //     }

    //     Need[] needArray = new Need[needArrayList.size()];
    //     needArrayList.toArray(needArray);
    //     return needArray;
	// }


	private boolean load() throws IOException {
        needs = new HashMap<>();

        // Deserializes the JSON objects from the file into an array of needs
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file

        Need[] needArray = objectMapper.readValue(new File(filename),Need[].class);

        for (Need need : needArray) {
            needs.put(need.getName(),need);
        }
        return true;
    }

    private boolean save() throws IOException {
        Need[] needsArray = getNeeds();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),needsArray);
        return true;
    }

    @Override
    public Need[] getNeeds() throws IOException {
        ArrayList<Need> needArrayList = new ArrayList<>();
        for(Map.Entry<String, Need> entry : needs.entrySet()){
            needArrayList.add(entry.getValue());
        }

        Need[] needArray = new Need[needArrayList.size()];
        for(int i = 0; i < needArrayList.size(); i++) {
            needArray[i] = needArrayList.get(i);
        }
        return needArray;

    }

    @Override
    public Need[] searchNeeds(String name) throws IOException {
        ArrayList<Need> needArrayList = new ArrayList<>();

        for (Need need : needs.values()) {
            if (need.getName().contains(name)) {
                needArrayList.add(need);
            }
        }

        Need[] needArray = new Need[needArrayList.size()];
        needArrayList.toArray(needArray);
        return needArray;
    }

    @Override
    public Need getNeed(String Name) throws IOException {
        if (needs.containsKey(Name)){
            return needs.get(Name);
        }
        else{
            return null;
        }

    }

    @Override
    public Need createNeed(Need need) throws IOException {
        synchronized(needs) {
            Need newNeed = new Need(need.getName(),need.getCost(),need.isFulfilled());
            if(needs.containsKey(need.getName())) {
                return null;
            }
            needs.put(newNeed.getName(),newNeed);
            save(); // may throw an IOException
            return newNeed;
        }
    }

    @Override
    public Need updateNeed(Need need) throws IOException {
        synchronized(needs) {
            if (needs.containsKey(need.getName()) == false)
                return null;  // need does not exist

            needs.put(need.getName(),need);
            save(); // may throw an IOException
            return need;
        }
    }

    @Override
    public boolean deleteNeed(String name) throws IOException {
        synchronized(needs) {
            if (needs.containsKey(name)) {
                needs.remove(name);
                return save();
            }
            else
                return false;
        }
    }

}