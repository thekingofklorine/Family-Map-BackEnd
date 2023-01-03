package utility;

import com.google.gson.Gson;
import dao.*;
import model.Event;
import model.Person;
import model.User;
import results.FillResult;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * this needs to take a username or a username and generation to create all the people
 */
public class FillGeneration {
  private String username;
  private int numGen;
  private ArrayList<Person> personArrayList;
  private ArrayList<Event> eventArrayList;

  private String[] femaleNameArray;
  private String[] maleNameArray;
  private String[] surNameArray;
  private ParseLocationJson.Location[] locationNameArray;

  private final Database db = new Database();

  public FillGeneration(String username, int numGen) {
    this.username=username;
    this.numGen= numGen;
  }

  public FillResult fillTree(){
    FillResult result;
    int loopNumGen = numGen;
    try{
      db.openConnection();
      UserDAO uDao = new UserDAO(db.getConnection());
      PersonDAO pDao = new PersonDAO(db.getConnection());
      EventDAO eDao = new EventDAO(db.getConnection());
      eDao.clear(username);
      pDao.clear(username);
      User user = uDao.find(username);

      Person userPerson = new Person(user.getPersonID(), user.getUsername(), user.getFirstName(), user.getLastName(),
              user.getGender(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), null);

      Gson gson = new Gson();
      Reader reader = new FileReader("json/fnames.json");
      femaleNameArray = gson.fromJson(reader, ParseJson.class).data;
      reader.close();
      reader = new FileReader("json/mnames.json");
      maleNameArray = gson.fromJson(reader, ParseJson.class).data;
      reader.close();
      reader = new FileReader("json/snames.json");
      surNameArray = gson.fromJson(reader, ParseJson.class).data;
      reader.close();
      reader = new FileReader("json/locations.json");
      locationNameArray = gson.fromJson(reader, ParseLocationJson.class).data;
      reader.close();

      ParseLocationJson.Location location = getRandom(locationNameArray);
      Event userBirth = new Event(UUID.randomUUID().toString(), username, userPerson.getPersonID(), location.latitude, location.longitude, location.country, location.city, "Birth", 2000 );

      personArrayList = new ArrayList<>();
      eventArrayList = new ArrayList<>();

      personArrayList.add(userPerson);
      eventArrayList.add(userBirth);

      createTriangle(userPerson.getMotherID(), userPerson.getFatherID(), loopNumGen);

      //create for loop to add both array lists to db
      for(int i = 0; i < personArrayList.size(); i++){
        pDao.insert(personArrayList.get(i));
      }
      for(int i = 0; i < eventArrayList.size(); i++){
        eDao.insert(eventArrayList.get(i));
      }
      db.closeConnection(true);

      result = new FillResult("Successfully added " + personArrayList.size() + " persons and " + eventArrayList.size() + "events", true);
      return result;

    }catch (DataAccessException e){
      e.printStackTrace();
      db.closeConnection(false);
      result= new FillResult("error: fail to login", false);
      return result;
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  private void createTriangle(String motherId, String fatherId, int loopNumGen){

    int birthYear = 2000-(40*(5-loopNumGen));
    int marriageYear = birthYear + 20;

    Person mother = new Person(motherId, username, getRandom(femaleNameArray), getRandom(surNameArray), "f", UUID.randomUUID().toString(), UUID.randomUUID().toString(), fatherId);
    Person father = new Person(fatherId, username, getRandom(maleNameArray), getRandom(surNameArray), "m", UUID.randomUUID().toString(), UUID.randomUUID().toString(), motherId);
    ParseLocationJson.Location location = getRandom(locationNameArray);
    Event motherBirth = new Event(UUID.randomUUID().toString(), username, motherId, location.latitude, location.longitude, location.country, location.city,
            "Birth", birthYear   );
    location = getRandom(locationNameArray);
    Event fatherBirth = new Event(UUID.randomUUID().toString(), username, fatherId, location.latitude, location.longitude, location.country, location.city,
            "Birth", birthYear -1);
    location = getRandom(locationNameArray);
    Event motherMarriage = new Event(UUID.randomUUID().toString(), username, motherId, location.latitude,  location.longitude, location.country, location.city,
            "Marriage", marriageYear  );
    Event fatherMarriage = new Event(UUID.randomUUID().toString(), username, fatherId, location.latitude,  location.longitude, location.country, location.city,
            "Marriage", marriageYear);

    int deathYear = birthYear + 83;
    location = getRandom(locationNameArray);
    Event motherDeath = new Event(UUID.randomUUID().toString(), username, motherId, location.latitude,  location.longitude, location.country, location.city,
            "Death", deathYear );
    location = getRandom(locationNameArray);
    Event fatherDeath = new Event(UUID.randomUUID().toString(), username, fatherId, location.latitude,  location.longitude, location.country, location.city,
            "Death", deathYear-2);

    eventArrayList.add(motherDeath);
    eventArrayList.add(fatherDeath);
    eventArrayList.add(motherBirth);
    eventArrayList.add(fatherBirth);
    eventArrayList.add(motherMarriage);
    eventArrayList.add(fatherMarriage);

    loopNumGen--;
    if(loopNumGen > 0){
      createTriangle(mother.getMotherID(), mother.getFatherID(),loopNumGen);
      createTriangle(father.getMotherID(), father.getFatherID(),loopNumGen);
    } else{
      mother.setMotherID("");
      mother.setFatherID("");
      father.setMotherID("");
      father.setFatherID("");
    }


    personArrayList.add(mother);
    personArrayList.add(father);

  }

  private <T> T getRandom(T[] array){
    Random random = new Random();
    return array[random.nextInt(array.length)];
  }


}
