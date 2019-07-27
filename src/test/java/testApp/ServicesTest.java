package testApp;

import org.junit.Test;

import static org.junit.Assert.*;


public class ServicesTest {

    @Test
    public void addEntry() {
        Services services = new Services();
        services.addEntry("Ivan", 65535);
        services.addEntry("John", 65565565);
        services.addEntry("Seth", 6678535);
        int actual = services.listEntries().size();
        int excepted = 3;
        assertEquals(excepted,actual);
    }

    @Test
    public void getPhoneByName(){
        Services services = new Services();
        services.addEntry("Ivan", 65535);
        services.addEntry("John", 65565565);
        services.addEntry("Seth", 6678535);

        int actual = services.getNumberByName("Ivan");
        int excepted  = 65535;
        assertEquals(excepted,actual);

    }

}