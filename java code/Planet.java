 
import java.util.*;
/**
 * An planet is part of a STAR resort.Each planet has a name,  a luxury rating
 * and a capacity which represents the maximum number of people(permits) who can be on the  
 * planet at any one time. Each planet must maintain a list of all people (permits)
 * currently on the planet. These lists are updated whenever permits enter or leave 
 * an planet,so that it is always possible to say how many people (permits) are on the planet 
 * and who they are.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Planet 
{
    private int referenceNumber;
    private String name;
    private int planetRating;
    private int maxCapacity;
    
    private ArrayList<Permit> permitList;// declare array list 
    
    /** Creates a Planet
     * refNum = planet's reference number
     * plntNme = planet's name
     * plntRatn = planet's rating
     * maxCap = planet's capacity
     * arraylist = number of permits
     */
    public Planet (int refNum, String plntNme, int plntRatn, int mxCap)
    {
        referenceNumber = refNum;
        name = plntNme;
        
        if (plntRatn < 0) planetRating = 0;
        else planetRating = plntRatn;
        
        if (mxCap < 0) maxCapacity = 0;
        else maxCapacity = mxCap;
        
        
        permitList = new ArrayList<Permit>();
    }
    
    /** return reference number of the planet
     */
    public int getplanetNumber()
    {
        return referenceNumber;
    }
    
    /** return name of the planet
     */
    public String getplanetName()
    {
        return name;
    }
    
    /** return rating of the planet
     */
    public int getRating()
    {
        return planetRating;
    }
    
    /** return capacity of the planet
     */
    public int getCapacity()
    {
        return maxCapacity;
    }
    
    /** adds a permit to the planet
     */
    public void enter(Permit p)
    {
        if (permitList.size() + 1 < maxCapacity)
        permitList.add(p);
    }

    /** removes a permit from the planet
     */
    public void leave(Permit p)
    {
        permitList.remove(p);
    }

    /** capacity of the planet has been meet
     */
    public boolean isFull()
    {
        if (permitList.size() == maxCapacity)
        {
            return true;
        }
        else return false;
    }
    
    /** retuen the number of permits on the planet
     */
    public int noOfPermits()
    {
        return permitList.size();
    }

    /** find a permit on a planet
     */
    public Permit findPermitOnPlanet(int pID)
    {
        for (int indx=0; indx<permitList.size(); indx++)
        {
            Permit temp = permitList.get(indx);
            if (temp.getpermitID() == pID)
            {
                return temp;
            }
        }
        return null;
    }
    
    /** retuen if the permit is on the planet
     */
    public boolean isInPlanet(int pID)
    {
        for(Permit temp : permitList)
        {
            if (temp.getpermitID() == pID)
            {
                return true;
            }
        }
        return false;
    }
    
    /** list all the permits on the planet
     */
    public String getPermitList()
    {
        String ss = "";
        if (permitList.size() >0)
        {
            for(Permit temp: permitList)
            {
                ss  += "\n" + temp.toString();
            }
            return ss + "\n*******************************";
        }
        return "\nNo Permits" + "\n*******************************";
    }
    
    /** returns a string representing the information of the planets
     */    
    public String toString()
    {
        return ("\n" + referenceNumber + "   " + name
                     + "   " + planetRating + "   " + maxCapacity
                     + "\n*********PermitList***" + getPermitList());
    }
}
