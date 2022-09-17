
/**
 * A Permit has an id number, name, a luxury rating and number of credits.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Permit 
{
    private int permitID;
    private String name;
    private int luxuryRating;
    private int numberOfCredits;
    
     /** Creates a Permit
     * pID = permits's ID
     * n = permits's name
     * Rating = permit's rating
     * credit = permit's credit
     */
    public Permit(int pID, String n, int Rating, int Credit)
    {
        permitID = pID;
        name = n;
        if (Rating < 0) luxuryRating = 0;
        else luxuryRating = Rating;
        
        if (Credit < 0) numberOfCredits = 0;
        numberOfCredits = Credit;
    }

    /** increment credit
     */
    public void topUp(int credit)
    {
        numberOfCredits = numberOfCredits + credit;
    }

    /** deduct credit
     */
    public void deductnumberOfCredits(int Credit)
    {
        numberOfCredits = numberOfCredits - Credit;
        if (numberOfCredits < 0)
        numberOfCredits = 0;
    }
    
    /** reset credits
     */
    public void resetnumberOfCredits()
    {
        numberOfCredits = 0;
    }

    /** return permitID
     */
    public int getpermitID()
    {
        return permitID;
    }

    /** return luxuryRating
     */
    public int getRating()
    {
        return luxuryRating;
    }

    /** return numberOfCredits
     */
    public int getnumberOfCredits()
    {
        return numberOfCredits;
    }

    /** return permit name
     */
    public String getName()
    {
        return name;
    }

    /** checking credit
     */
    public boolean enoughcredit()
    {
        if (numberOfCredits >= 3)
        return true;
        else return false;
    }
    
    /** return string that reprecent the information of the permit
     */
    public String tostring()
    {
        String s;
        s = "\nPermit ID: " + permitID + "   " + name 
            + "\nLuxury rating: " + luxuryRating + "\nNumber of credits: " + numberOfCredits;
        return s;
    }
}

