package selenideTests.models;


import lombok.Data;

@Data
public class AddressBodyModel {

    String Index, CountryId, CityId, Street, House, Apartment, Entrance, Floor, isActive;

}


//{ \"Index\": \"123\", \"CountryId\": 21, \"CityId\": 272, \"Street\": \"testovay111111a\", \"House\": \"1\",
// \"Apartment\": \"2\", \"Entrance\": \"3\", \"Floor\": \"3\", \"isActive\": true }");