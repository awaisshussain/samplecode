 
/**
 * A shuttle provides a one-way connection between two planets. It
 * has a Shuttle code and information about both the source and
 * the destination planet
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shuttle
{
    private String journeyCode;
    private Planet fromPlanet;
    private Planet toPlanet;
    
    /** create a shuttle
     * jrnycode = journey code
     * fmplnt = From planet
     * toplnt = to planet
     */  
    public Shuttle (String jrnycode, Planet fmPlnt, Planet toPlnt)
    {
        journeyCode = jrnycode;
        fromPlanet = fmPlnt;
        toPlanet = toPlnt;
    }
    
    /** return journey code
     */  
    public String getJourneyCode()
    {
        return journeyCode;
    }
    
    /** return from planet
     */  
    public Planet getFromPlanet()
    {
        return fromPlanet;
    }
    
    /** return to planet
     */  
    public Planet getToPlanet()
    {
        return toPlanet;
    }
    
    /** compare to see if you can ride the shuttle 
     */  
    public boolean canTravel(Permit p)
    {
       if (p.getRating() >= toPlanet.getRating()  &&
            !toPlanet.isFull() &&
                p.enoughcredit() &&
                    fromPlanet.isInPlanet(p.getpermitID()))
                    {
                        return true;
                    }
       else return false;
    }
    
    /** return if the permit can or cant travel
     * if not why it wasnt allowed 
     */  
    public String transferPermit(Permit pp)
    {
            
        if (pp.getRating() < toPlanet.getRating())
        {
            return "Not enough rating to travel - permit not added";
        }
        else if (toPlanet.isFull())
        {
            return "Planet is full - permit not added";
        }
        else if (!pp.enoughcredit())
        {
            return "Not enough credit to travel - permit not added";
        }
        else if (!fromPlanet.isInPlanet(pp.getpermitID()))
        {
            return "Permit is not on the planet listed - permit not added";
        }
        else
        {
            toPlanet.enter(pp);
            fromPlanet.leave(pp);
            pp.deductnumberOfCredits(3);
            return "permit " + pp.getpermitID()
            + "added to planet " + toPlanet.getplanetName();
        }
    }      
    
    /** returns a string representing the information of the shuttles
     */  
    public String toString()
    {
        return "\nJourney Code : " + journeyCode + "\nFrom : " 
                            + fromPlanet.getplanetNumber() + "  " 
                            + fromPlanet.getplanetName() 
                            + "\nTo : " + toPlanet.getplanetNumber() +
                            "  " + toPlanet.getplanetName();
    }
}
