package resources;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

public class DataObjects {

	
private DataObjects() {
		
	}
	
	
	static ObjectMapper mapper = new ObjectMapper();
	static ObjectReader reader = mapper.readerFor(DataObjects.class);


	@JsonProperty("url")
	String url;
	
	@JsonProperty("searchText")
	String searchText;

	@JsonProperty("country")
	String country;
	
	
	
	public String getUrl() {
		return url;
	}
	
	
	public String getSearchText() {
		return searchText;
	}

	public String getCountry() {
		return country;
	}
	public static DataObjects getData(String fileName) throws JsonParseException, JsonMappingException, IOException {	
		return reader.readValue(new File(fileName));

	}
	
	
}
