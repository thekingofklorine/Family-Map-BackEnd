package services;

import dao.*;
import model.AuthorizationToken;
import model.Event;
import model.Person;
import model.User;
import org.junit.jupiter.api.*;
import request.LoginRequest;
import request.RegisterRequest;
import results.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

  private Database db;

  private Event bestEvent;
  private EventDAO eDao;

  private Person bestPerson;
  private Person secondPerson;
  private PersonDAO pDao;
  private AuthorizationToken authorizationToken;
  private AuthorizationToken authorizationTokenTwo;

  private UserDAO uDao;

  private AuthorizationTokenDAO aDao;

  private User userOne;

  private ArrayList<Person> personArray;
  private ArrayList<Event> eventArray;

  private Event secondEvent;


  @BeforeEach
  public void setUp() throws DataAccessException {
    // Here we can set up any classes or variables we will need for each test
    // lets create a new instance of the Database class
    db = new Database();
    // and a new event with random data
    bestEvent = new Event("Biking_123A", "billBob", "123abc",
            35.9f, 140.1f, "Japan", "Ushiku",
            "Biking_Around", 2016);
   secondEvent = new Event("sleep", "billBob", "123abc",
            35.9f, 140.1f, "USA", "Boston",
            "Biking_Around", 2020);
    bestPerson = new Person("123abc", "billBob", "Bill",
            "Bob", "m", "246", "357",
            "999");
    secondPerson = new Person("321cba", "billBob", "Bob",
            "Bill", "f", "123", "456",
            "111");

    userOne = new User("rob", "123", "e@yahoo", "Evan", "Bearman", "m", "billBob");

    authorizationToken = new AuthorizationToken("youmaypass", "billBob");

    authorizationTokenTwo = new AuthorizationToken("badstuff", "Gale");

//    Connection conn = db.openConnection();
//    eDao = new EventDAO(conn);
//    pDao = new PersonDAO(conn);
//    eDao.clear();
//    pDao.clear();
//    db.closeConnection(true);
  }

  @AfterEach
  public void tearDown() throws DataAccessException {
    // Here we close the connection to the database file, so it can be opened again later.
    // We will set commit to false because we do not want to save the changes to the database
    // between test cases.
    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    uDao = new UserDAO(db.getConnection());
    aDao = new AuthorizationTokenDAO(db.getConnection());
    uDao.clear();
    aDao.clear();
    eDao.clear();
    pDao.clear();
    db.closeConnection(true);


  }


  @Test
  @DisplayName("First Clear Test")
  public void ClearPass() throws DataAccessException {
    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    eDao.insert(bestEvent);
    pDao.insert(bestPerson);
    pDao.insert(secondPerson);
    db.closeConnection(true);

    ClearService service = new ClearService();
    ClearResult result = service.clear();
    assertTrue(result.getSuccess());

    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    assertNull(pDao.find(bestPerson.getPersonID()));
    assertNull(eDao.find(bestEvent.getEventID()));
    assertNull(pDao.find(secondPerson.getPersonID()));
    db.closeConnection(false);
  }
  @Test
  @DisplayName("Second Clear Test")
  public void ClearPassTwo() throws DataAccessException {
    ClearService service = new ClearService();
    ClearResult result = service.clear();
    assertTrue(result.getSuccess());
    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    eDao.insert(bestEvent);
    pDao.insert(bestPerson);
    pDao.insert(secondPerson);
    assertNotNull(pDao.find(bestPerson.getPersonID()));
    assertNotNull(eDao.find(bestEvent.getEventID()));
    assertNotNull(pDao.find(secondPerson.getPersonID()));
    db.closeConnection(true);
    result = service.clear();
    assertTrue(result.getSuccess());
  }
  @Test
  @DisplayName("Person Pass")
  public void PersonPass() throws DataAccessException {
    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    uDao = new UserDAO(db.getConnection());
    aDao = new AuthorizationTokenDAO(db.getConnection());
    eDao.insert(bestEvent);
    pDao.insert(bestPerson);
    pDao.insert(secondPerson);
    uDao.insert(userOne);
    aDao.insert(authorizationToken);
    String authToken = aDao.find(bestPerson.getAssociatedUsername()).getAuthToken();
    db.closeConnection(true);
    PersonService service = new PersonService();
    PersonResult result = service.person(bestPerson.getPersonID(), authToken);
    db.openConnection();
    assertNotNull(result);
    assertEquals(bestPerson.getGender(),result.getGender(), "Person was not the right gender");
    assertEquals(bestPerson.getFirstName(),result.getFirstName(), "wrong first name");
    assertEquals(bestPerson.getLastName(),result.getLastName(), "wrong last name");
    db.closeConnection(true);
  }

  @Test
  @DisplayName("Person Fail")
  public void PersonFail() throws DataAccessException {  //how do you handle the failures
    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    uDao = new UserDAO(db.getConnection());
    aDao = new AuthorizationTokenDAO(db.getConnection());
    eDao.insert(bestEvent);
    pDao.insert(bestPerson);
    secondPerson.setAssociatedUsername("badUsername");
    pDao.insert(secondPerson);
    uDao.insert(userOne);
    aDao.insert(authorizationToken);
    String authToken = aDao.find(bestPerson.getAssociatedUsername()).getAuthToken();
    db.closeConnection(true);
    PersonService service = new PersonService();
    PersonResult result = service.person(secondPerson.getPersonID(), authToken);
    assertFalse(result.getSuccess());
  }

  @Test
  @DisplayName("Person Array Pass")
  public void PersonArrayPass() throws DataAccessException {
    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    uDao = new UserDAO(db.getConnection());
    aDao = new AuthorizationTokenDAO(db.getConnection());
    eDao.insert(bestEvent);
    pDao.insert(bestPerson);
    pDao.insert(secondPerson);
    uDao.insert(userOne);
    aDao.insert(authorizationToken);
    String authToken = aDao.find(bestPerson.getAssociatedUsername()).getAuthToken();
    db.closeConnection(true);
    PersonService service = new PersonService();
    PersonResult result = service.personArray(authToken);
    assertTrue(result.getSuccess());
    personArray = new ArrayList<>();
    personArray.add(bestPerson);
    personArray.add(secondPerson);
    assertTrue(personArray.get(0).equals(result.getData()[0]));
  }

  @Test
  @DisplayName("Person Array Fail")
  public void PersonArrayFail() throws DataAccessException {
    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    uDao = new UserDAO(db.getConnection());
    aDao = new AuthorizationTokenDAO(db.getConnection());
    eDao.insert(bestEvent);
    pDao.insert(bestPerson);
    pDao.insert(secondPerson);
    uDao.insert(userOne);
    aDao.insert(authorizationToken);
    String authToken = aDao.find(bestPerson.getAssociatedUsername()).getAuthToken();
    db.closeConnection(true);
    PersonService service = new PersonService();
    PersonResult result = service.personArray("badToken");
    assertFalse(result.getSuccess());
    assertTrue(result.getMessage().toLowerCase().contains("error"));
  }


  @Test
  @DisplayName("Event Pass")
  public void EventPass() throws DataAccessException {
    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    uDao = new UserDAO(db.getConnection());
    aDao = new AuthorizationTokenDAO(db.getConnection());
    eDao.insert(bestEvent);
    eDao.insert(secondEvent);
    pDao.insert(bestPerson);
    pDao.insert(secondPerson);
    uDao.insert(userOne);
    aDao.insert(authorizationToken);
    String authToken = aDao.find(bestPerson.getAssociatedUsername()).getAuthToken();
    db.closeConnection(true);
    EventService service = new EventService();
    EventResult result = service.event(bestEvent.getEventID(), authToken);
    db.openConnection();
    assertNotNull(result);
    assertEquals(bestEvent.getAssociatedUsername(),result.getAssociatedUsername(), "event not assciated usernam");
    assertTrue(bestEvent.getEventType().equals(result.getEventType()), "wrong event type");
    db.closeConnection(true);
  }

  @Test
  @DisplayName("Event Fail")
  public void EventFail() throws DataAccessException {
    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    uDao = new UserDAO(db.getConnection());
    aDao = new AuthorizationTokenDAO(db.getConnection());
    eDao.insert(bestEvent);
    eDao.insert(secondEvent);
    pDao.insert(bestPerson);
    secondEvent.setEventID("badID");
    pDao.insert(secondPerson);
    uDao.insert(userOne);
    aDao.insert(authorizationToken);
    String authToken = aDao.find(bestPerson.getAssociatedUsername()).getAuthToken();
    db.closeConnection(true);
    EventService service = new EventService();
    EventResult result = service.event(secondEvent.getEventID(), authToken);
    assertFalse(result.getSuccess());
  }

  @Test
  @DisplayName("Event Array Pass")
  public void EventArrayPass() throws DataAccessException {
    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    uDao = new UserDAO(db.getConnection());
    aDao = new AuthorizationTokenDAO(db.getConnection());
    eDao.insert(bestEvent);
    eDao.insert(secondEvent);
    pDao.insert(bestPerson);
    pDao.insert(secondPerson);
    uDao.insert(userOne);
    aDao.insert(authorizationToken);
    String authToken = aDao.find(bestPerson.getAssociatedUsername()).getAuthToken();
    db.closeConnection(true);
    EventService service = new EventService();
    EventResult result = service.eventArray(authToken);
    assertTrue(result.getSuccess());
    eventArray = new ArrayList<>();
    eventArray.add(bestEvent);
    eventArray.add(secondEvent);
    assertTrue(eventArray.get(0).equals(result.getData()[0]));
  }

  @Test
  @DisplayName("Event Array Fail")
  public void EventArrayFail() throws DataAccessException {
    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    uDao = new UserDAO(db.getConnection());
    aDao = new AuthorizationTokenDAO(db.getConnection());
    eDao.insert(bestEvent);
    eDao.insert(secondEvent);
    pDao.insert(bestPerson);
    pDao.insert(secondPerson);
    uDao.insert(userOne);
    aDao.insert(authorizationToken);
    String authToken = aDao.find(bestPerson.getAssociatedUsername()).getAuthToken();
    db.closeConnection(true);
    EventService service = new EventService();
    EventResult result = service.eventArray("badToken");
    assertFalse(result.getSuccess());
    assertTrue(result.getMessage().toLowerCase().contains("error"));
  }

  @Test
  @DisplayName("Fill Pass")
  public void FillPass() throws DataAccessException {
    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    uDao = new UserDAO(db.getConnection());
    aDao = new AuthorizationTokenDAO(db.getConnection());
    eDao.insert(bestEvent);
    eDao.insert(secondEvent);
    pDao.insert(bestPerson);
    pDao.insert(secondPerson);
    uDao.insert(userOne);
    aDao.insert(authorizationToken);
    String authToken = aDao.find(bestPerson.getAssociatedUsername()).getAuthToken();
    db.closeConnection(true);
    FillService service = new FillService();
    FillResult result = service.fill(userOne.getUsername(),4);
    assertTrue(result.getSuccess());
    assertTrue(result.getMessage().toLowerCase().contains("31"));
  }

  @Test
  @DisplayName("Fill Fail")
  public void FillFail() throws DataAccessException {
    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    uDao = new UserDAO(db.getConnection());
    aDao = new AuthorizationTokenDAO(db.getConnection());
    eDao.insert(bestEvent);
    eDao.insert(secondEvent);
    pDao.insert(bestPerson);
    pDao.insert(secondPerson);
    uDao.insert(userOne);
    aDao.insert(authorizationToken);
    String authToken = aDao.find(bestPerson.getAssociatedUsername()).getAuthToken();
    db.closeConnection(true);
    FillService service = new FillService();
    FillResult result = service.fill("badUsername",4);
    assertFalse(result.getSuccess());
    assertTrue(result.getMessage().toLowerCase().contains("error"));
  }

  @Test
  @DisplayName("Load Pass")
  public void LoadPass() throws DataAccessException {
    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    uDao = new UserDAO(db.getConnection());
    aDao = new AuthorizationTokenDAO(db.getConnection());
    eDao.insert(bestEvent);
    eDao.insert(secondEvent);
    pDao.insert(bestPerson);
    pDao.insert(secondPerson);
    uDao.insert(userOne);
    aDao.insert(authorizationToken);
    String authToken = aDao.find(bestPerson.getAssociatedUsername()).getAuthToken();
    db.closeConnection(true);
//    LoadRequest r = new LoadRequest();
//    LoadService service = new LoadService();
//    LoadResult result = service.load();
    //todo didnt get to finish
  }

  @Test
  @DisplayName("Load Fail")
  public void LoadFail() throws DataAccessException {
    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    uDao = new UserDAO(db.getConnection());
    aDao = new AuthorizationTokenDAO(db.getConnection());
    eDao.insert(bestEvent);
    eDao.insert(secondEvent);
    pDao.insert(bestPerson);
    pDao.insert(secondPerson);
    uDao.insert(userOne);
    aDao.insert(authorizationToken);
    String authToken = aDao.find(bestPerson.getAssociatedUsername()).getAuthToken();
    db.closeConnection(true);
      //todo didnt get to finish
  }

  @Test
  @DisplayName("Login Pass")
  public void LoginPass() throws DataAccessException {
    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    uDao = new UserDAO(db.getConnection());
    aDao = new AuthorizationTokenDAO(db.getConnection());
    eDao.insert(bestEvent);
    eDao.insert(secondEvent);
    pDao.insert(bestPerson);
    pDao.insert(secondPerson);
    uDao.insert(userOne);
    aDao.insert(authorizationToken);
    String authToken = aDao.find(bestPerson.getAssociatedUsername()).getAuthToken();
    db.closeConnection(true);
    LoginRequest request = new LoginRequest("rob", "123");
    LoginService service = new LoginService();
    LoginResult result = service.login(request);
    assertTrue(result.getSuccess());
    assertTrue("rob".equals(userOne.getUsername()), "username doesnt match");

  }

  @Test
  @DisplayName("Login Fail")
  public void LoginFail() throws DataAccessException {
    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    uDao = new UserDAO(db.getConnection());
    aDao = new AuthorizationTokenDAO(db.getConnection());
    eDao.insert(bestEvent);
    eDao.insert(secondEvent);
    pDao.insert(bestPerson);
    pDao.insert(secondPerson);
    uDao.insert(userOne);
    aDao.insert(authorizationToken);
    String authToken = aDao.find(bestPerson.getAssociatedUsername()).getAuthToken();
    db.closeConnection(true);
    LoginRequest request = new LoginRequest("rob", "456");
    LoginService service = new LoginService();
    LoginResult result = service.login(request);
    assertFalse(result.getSuccess());
    assertTrue(result.getMessage().toLowerCase().contains("error"));
  }

  @Test
  @DisplayName("Register Pass")
  public void RegisterPass() throws DataAccessException {
    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    uDao = new UserDAO(db.getConnection());
    aDao = new AuthorizationTokenDAO(db.getConnection());
    eDao.insert(bestEvent);
    eDao.insert(secondEvent);
    pDao.insert(bestPerson);
    pDao.insert(secondPerson);
    uDao.insert(userOne);
    aDao.insert(authorizationToken);
    String authToken = aDao.find(bestPerson.getAssociatedUsername()).getAuthToken();
    db.closeConnection(true);
    RegisterRequest request = new RegisterRequest("user", "pass", "@", "steve", "jpobs", "f");
    RegisterService service = new RegisterService();
    RegisterResult result = service.register(request);
    assertTrue(result.isSuccess());
  }

  @Test
  @DisplayName("Register Fail")
  public void RegisterFail() throws DataAccessException {
    db.openConnection();
    eDao = new EventDAO(db.getConnection());
    pDao = new PersonDAO(db.getConnection());
    uDao = new UserDAO(db.getConnection());
    aDao = new AuthorizationTokenDAO(db.getConnection());
    eDao.insert(bestEvent);
    eDao.insert(secondEvent);
    pDao.insert(bestPerson);
    pDao.insert(secondPerson);
    uDao.insert(userOne);
    aDao.insert(authorizationToken);
    String authToken = aDao.find(bestPerson.getAssociatedUsername()).getAuthToken();
    db.closeConnection(true);
    RegisterRequest request = new RegisterRequest(userOne.getUsername(),userOne.getPassword(), userOne.getEmail(), userOne.getFirstName(), userOne.getLastName(), userOne.getGender());
    RegisterService service = new RegisterService();
    RegisterResult result = service.register(request);
    assertFalse(result.isSuccess());
    assertTrue(result.getMessage().toLowerCase().contains("error"));
  }
}
