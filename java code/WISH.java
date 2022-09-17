 
/**
 * interface WISH specifies requirements for 5COM1059 (Sem B) 2022
 * 
 * @author A.Marczyk 
 * @version 03/11/21
 */
public interface WISH
{
    /**
     * Returns the location and all  details of all planets including the permits
     * currently on each planet, or "No permits"
     * @return location and details of all planets including location 
     * and all permits currently on each planet, or "No permits" 
     */
    public String toString();
    
    
    /**Returns a String with details of a permit
     * @param permitId - id number of the permit
     * @return the details of the permit as a String, or "No such permit"
     */
    public String getPermitDetails(int permitId);

    
        /** Given the name of a planet, returns the planet id number
     * or -1 if planet does not exist
     * @param name of planet
     * @return id number of planet
     */
    public int getPlanetNumber(String ww);

    /**Returns a String representation of permits on each planet.
     * Include the name of the planet 
     * @return a String representation of permits on each planet.
     **/
    public String getAllPermitsOnEachPlanet();
    
    
    /**Returns the name of the planet which contains the specified permit or null
     * @param tr - the specified permit
     * @return the name of the Planet which contains the permit, or null
     **/
    public String getPermitLocation(int tr);
    
                
    /**Returns a String representation of all the permits on specified planet
     * @return a String representation of all permits on specified planet
     **/
    public String getAllPermitsOnOnePlanet(String planet);
  
    
     /**Returns true if a Permit is allowed to move using the shuttle, false otherwise
     * A move can be made if:  
     * the rating of the permit  >= the rating of the destination planet
     * AND the destination planet is not full
     * AND the permit has sufficient credits
     * AND the permit is currently on the source planet
     * AND the permit id is for a permit on the system
     * AND the shuttle code is the code for a shuttle on the system
     * @param trId is the id of the permit requesting the move
     * @param shtlCode is the code of the shuttle journey by which the permit wants to pPermitel
     * @return true if the permit is allowed on the shuttle journey, false otherwise 
     **/
    public boolean canTravel(int trId, String shtlCode);
    

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
     * @param pPermitId is the id of the permit requesting the move
     * @param shtlCode is the code of the shuttle journey by which the permit wants to pPermitel
     * @return a String giving the result of the request 
     **/
    public String travel(int permitId, String shtlCode );
    
     
      
    /** Allows a permit to top up their credits.This method is not concerned with 
     *  the cost of a credit as currency and prices may vary between resorts.
     *  @param id the id of the permit toping up their credits
     *  @param creds the number of credits purchased to be added to permits information
     */
    public void topUpCredits(int id, int creds);
    
    public String getRichGuests();
    
    public void moveHome(int id);
    
    public void evacuateAll();
    
    public String listPlanetDetails();
    
   
}
