package project.service;

import java.util.ArrayList;
import java.util.TreeMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * An object that stores a Generic object of ANY type. There are two
 * options: 1. Map, mapToJSON will handle converting this to JSON
 * and printing it back as a String.
 * 
 * 2. ArrayList can be stored directly.
 * 
 * Use whichever is easier to work with in business logic.
 * 
 * Originally JsonHandler. Renamed for consistency.
 * @author darkm
 *
 */
public class JsonService<O> {
	// O stands for "Type"
	private TreeMap<Integer, O> oMap = null;
	private ArrayList<O> oList = null;

	public JsonService() {
		// Both will be null
	}

	public JsonService(TreeMap<Integer, O> oMap) {
		super();
		this.oMap = oMap;
	}

	public JsonService(ArrayList<O> oList) {
		super();
		this.oList = oList;
	}

	public TreeMap<Integer, O> getoMap() {
		return oMap;
	}

	public void setoMap(TreeMap<Integer, O> oMap) {
		this.oMap = oMap;
	}

	public ArrayList<O> getoList() {
		return oList;
	}

	public void setoList(ArrayList<O> oList) {
		this.oList = oList;
	}

	/**
	 * Returns the stored Map in JSON String form.
	 * If the map is null, {} is returned. (an empty JSON)
	 * 
	 * Note that a map is key value pairs of ints and
	 * objects. Like so:
	 * {"1":{},"2":{}}
	 * 
	 * @return
	 */
	public String mapToJSON() {
		String jsonString = "";
		// create `ObjectMapper` instance
		ObjectMapper mapper = new ObjectMapper();

		if (getoMap() != null) {
			// convert our current list to JSON
			try {
				jsonString = mapper.writeValueAsString(getoMap());
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			// Return that list as JSON
			return jsonString;
		}

		System.out.println("JsonHandler.mapToJSON: Oi! My map is null! What are you doing bro?");
		return "{}";
	}

	/**
	 * Returns the stored ArrayList as a JSON String.
	 * If the list is null, {} is returned. (an empty JSON)
	 * 
	 * Note that an ArrayList JSON is printed as an array, like so:
	 * [{}, {}]
	 * 
	 * @return
	 */
	public String listToJSON() {
		String jsonString = "";
		// create `ObjectMapper` instance
		ObjectMapper mapper = new ObjectMapper();

		if (getoList() != null) {
			// convert our current list to JSON
			try {
				jsonString = mapper.writeValueAsString(getoList());
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			// Return that list as JSON
			return jsonString;
		}

		System.out.println("JsonHandler.listToJSON: Oi! My list is null! What are you doing bro?");
		return "{}";
	}

}
