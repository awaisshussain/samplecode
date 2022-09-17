import java.util.*;
/**This class implements the WISH interface
 *
 * @author 
 * @version 
 **/
public class Resort implements WISH
{
    private ArrayList<Permit> allPermits = new ArrayList<Permit>();
    private ArrayList<Planet> allPlanets = new ArrayList<Planet>();
    private ArrayList<Shuttle> allShuttles = new ArrayList<Shuttle>();
    private String location;

    /** constructor
     */
    public Resort(String loc) 
    {
        location = loc;
        loadPlanets();
        loadPermits();
        setUpShuttles();
    }

    /**
     * Returns all of the details of all planets including the permits
     * currently on each planet, on "No permits"
     * @return all of the details of all planets including location 
     * and all permits currently on each planet, or "No permits" 
     */
    public String toString()
    {
        String s= "";
        for(Planet p : allPlanets)
        {
            s = s + p.toString() + "/n" + "*Next Planet*\n";
        }
        return s;
    }

    /**Returns a String with details of a permit
     * @param permitId - id number of the permit
     * @return the details of the permit as a String, or "No such permit"
     */
    public String getPermitDetails(int pID)
    {
        for(Permit temp: allPermits)
        {
            if (temp.getpermitID() == pID)
            {
                return temp.toString();
            }
        }
        return "No Permit";
    }

    /** Given the name of a planet, returns the planet id number
     * or -1 if planet does not exist
     * @param name of planet
     * @return id number of planet
     */
    public int getPlanetNumber(String ww)
    {
        for(Planet temp: allPlanets)
        {
            if (temp.getplanetName().equals(ww))
            {
                return temp.getplanetNumber();
            }
        }
        return -1;
    }

    /**Returns a String representation of all the permits on all planets
     * @return a String representation of all permits on all planets
     **/
    public String getAllPermitsOnEachPlanet()
    {
        {
            String s = "";
            for (Planet p: allPlanets)
            {
                s = s + p.getPermitList();
            }
            return s;
        }
    } 
    
    public String listPlanetDetails()
    {
        String s = "";
        for (Planet temp: allPlanets)
        {
            s = s + temp.toString();
        }
        return s;
    }

    /**Returns the name of the planet which contains the specified permit or null
     * @param tr - the specified permit
     * @return the name of the Planet which contains the permit, or null
     **/
    public String getPermitLocation(int tr)
    {
        {
           String ss = "";
           for (Planet temp: allPlanets)
           {
               if (temp.isInPlanet(tr))
               {
                   return ss += temp.getplanetName();
               }
           }
           return null;
        }
    }

    /**Returns a String representation of all the permits on specified planet
     * @return a String representation of all permits on specified planet
     **/
    public String getAllPermitsOnOnePlanet(String planet)
    {
        String s = "";
        for (Planet temp: allPlanets)
        {
            if(temp.getplanetName().equals(planet))
            {
                return s = s + temp.getPermitList();
            }
        }
        return "No planet with this exists";
    } 

    /**Returns true if a Permit is allowed to move using the shuttle, false otherwise
     * A move can be made if:  
     * the rating of the permit  >= the rating of the destination planet//
     * AND the destination planet is not full//
     * AND the permit has sufficient credits//
     * AND the permit is currently on the source planet//
     * AND the permit id is for a permit on the system
     * AND the shuttle code is the code for a shuttle on the system
     * @param pId is the id of the permit requesting the move
     * @param shtlCode is the code of the shuttle journey by which the permit wants to pPermitel
     * @return true if the permit is allowed on the shuttle journey, false otherwise 
     **/
    public boolean canTravel(int pId, String shtlCode)
    {   //other checks optional
        Shuttle sh = getShuttle(shtlCode);
        Permit pt = getPermit(pId);
        if ( sh == null || pt == null || sh.canTravel(pt))
        {
            return false;
        }
        else
        {
            return true;
        }
    }     

    /**Returns the result of a permit requesting to move by Shuttle.
     * A move will be successful if:  
     * the luxury rating of the permit  >= the luxury rating of the destination planet
     * AND the destination planet is not full
     * AND the permit has sufficient credits
     * AND the permit is currently on the source planet
     * AND the permit id is for a permit on the system
     * AND the shuttle code is the code for a shuttle on the system
     * If the shuttle journey can be made, the permit information is removed from the source
     * planet, added to the destination planet and a suitable message returned.
     * If shuttle journey cannot be made, the state of the system remains unchanged
     * and a message specifying the reason is returned.
     * @param ppId is the id of the permit requesting the move
     * @param shtlCode is the code of the shuttle journey by which the permit wants to pPermitel
     * @return a String giving the result of the request 
     **/
    public String travel(int ppId, String shtlCode )
    {   //other checks optional
        Shuttle sh = getShuttle(shtlCode);
        Permit pt = getPermit(ppId);
        if ( sh == null)
        {
            return "\nNo such shuttle";
        }
        else if (pt == null)
        {
            return "\nNo such permit";
        }
        else
        {
            return sh.transferPermit(pt);
        }
        
    } 

    // These methods are for Task 6 only and not required for the Demonstration 
    // If you choose to implement them, uncomment the following code

    /** Allows a permit to top up their credits.This method is not concerned with 
     *  the cost of a credit as currency and prices may vary between resorts.
     *  @param id the id of the permit toping up their credits
     *  @param creds the number of credits purchased to be added to permits information
     */
    public void topUpCredits(int id, int creds)
    {
        Permit temp = getPermit(id);
        if (temp == null)
        {
            System.out.println("Invalid ID");
        }
        else
        {
            temp.topUp(creds);
        }
    }

    //***************private methods**************
    /** loading information given in breif
     */  
    private void loadPlanets()
    {
        Planet plnt1 = new Planet (00, "Home", 0, 1000 );
        allPlanets.add(plnt1);
        Planet plnt2 = new Planet (01,"Sprite", 1, 100);
        allPlanets.add(plnt2);
        Planet plnt3 = new Planet (02, "Tropicana", 3, 10);
        allPlanets.add(plnt3);
        Planet plnt4 = new Planet (03,"Fantasia", 5, 2);
        allPlanets.add(plnt4);
        Planet plnt5 = new Planet (04,"Solo", 1, 1);
        allPlanets.add(plnt5);
    }

    /** loading information given in breif
     */  
    private void setUpShuttles()
    {
        Planet plnt1 = getPlanet("Home");
        Planet plnt2 = getPlanet("Sprite");
        Planet plnt3 = getPlanet("Tropicana");
        Planet plnt4 = getPlanet("Fantasia");
        Planet plnt5 = getPlanet("Solo");
        
        allShuttles.add (new Shuttle ("ABC1", plnt1, plnt2));
        allShuttles.add (new Shuttle ("BCD2", plnt2, plnt1));
        allShuttles.add (new Shuttle ("CDE3", plnt2, plnt3));
        allShuttles.add (new Shuttle ("DEF4", plnt3, plnt2));
        allShuttles.add (new Shuttle ("EFG5", plnt4, plnt2));
        allShuttles.add (new Shuttle ("GHJ6", plnt2, plnt5));
        allShuttles.add (new Shuttle ("HJK7", plnt5, plnt2));
        allShuttles.add (new Shuttle ("JKL8", plnt3, plnt4));
    }

    /** loading information given in breif
     */ 
    private void loadPermits()
    {
        Permit p1 = new Permit (1000, "Lynn", 5, 10);
        Permit p2 = new Permit (1001, "May", 3, 20);
        Permit p3 = new Permit (1002, "Nils", 10, 20);
        Permit p4 = new Permit (1003, "Olek", 2, 12);
        Permit p5 = new Permit (1004, "Pan", 3, 3);
        Permit p6 = new Permit (1005, "Quin", 1, 5);
        Permit p7 = new Permit (1006, "Raj", 10, 6);
        Permit p8 = new Permit (1007, "sol", 7, 20);
        Permit p9 = new Permit (1008, "Tel", 6, 24);
        
        Planet Home = getPlanet("Home");
        Home.enter(p1);
        Home.enter(p2);
        Home.enter(p3);
        Home.enter(p4);
        Home.enter(p5);
        Home.enter(p6);
        Home.enter(p7);
        Home.enter(p8);
        Home.enter(p9);
        
        allPermits.add(p1);
        allPermits.add(p2);
        allPermits.add(p3);
        allPermits.add(p4);
        allPermits.add(p5);
        allPermits.add(p6);
        allPermits.add(p7);
        allPermits.add(p8);
        allPermits.add(p9);
    }
    
    // extra method
     /** loading information given in breif
     */ 
    public String addPermitToPlanet(int id, String planet)
    {
        Permit temp = getPermit(id);
        Planet plnt1 = getPlanet(planet);
        if (temp == null || plnt1 == null)
        {
            return "Permit or Planet does not exist";
        }
        else
        {
            if(plnt1.isFull())
            {
                return "The planet requested is full";
            }
            else 
            {
                plnt1.enter(temp);
                return "Permit" + id + "id added to the planet" + planet;
            }
        }
    }
    
    /** Returns the permit with the permit id specified by the parameter
     * @return the permit with the specified name
     **/
    public Permit getPermit(int id)
    {
        for(int i = 0; i < allPermits.size(); i++)
        {
            Permit temp = allPermits.get(i);
            if (temp.getpermitID() == id)
            {
                return temp;   
            }
        }
        return null;
    }

    /** Returns the planet with the name specified by the parameter
     * @return the planet with the specified name
     **/
    private Planet getPlanet(String planetName)
    {
        for(Planet temp : allPlanets)
        {
            if(temp.getplanetName().equals(planetName))
            {
                return temp;
            }
        }
        return null;
    }

    /** Returns the planet with the name specified by the parameter
     * @return the planet with the specified name
     **/
    private Shuttle getShuttle(String shut)
    {
        for(Shuttle temp : allShuttles)
        {
            if(temp.getJourneyCode().equals(shut))
            {
                return temp;
            }
        }
        return null;
    }
    
    /* Additional functionality for Task 7

     * return the guests that have rating higher than 5
     */
    public String getRichGuests()
    {
        String s = "";
        for (int i = 0; i < allPermits.size(); i++)
        {
            Permit temp = allPermits.get(i);
            if(temp.getRating() > 5)
            {
                s = s + "\n" + temp.toString();
            }
        }
        return s;
    }
    
    /** move permit home
     */
    public void moveHome(int pid)
    {
        String planet = getPermitLocation(pid);
        String homeplnt = "Home";
        Permit pp = getPermit(pid);
        Planet home = getPlanet(homeplnt);
        
        for (Planet temp: allPlanets)
        {
            if(temp.getplanetName().equals(planet))
            {
                temp.leave(pp);
            }
            home.enter(pp);
        }
    }
    
    /** evacuating all permits 
     */
    public void evacuateAll()
    {
        String homeplnt = "Home";
        for (Permit p:allPermits)
        {
            moveHome(p.getpermitID());
        }
    }
}