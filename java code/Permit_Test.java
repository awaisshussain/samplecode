
/**
 * Write a description of class Permit_Test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Permit_Test
{
    private void doTest()
    {
        // testing to see if the information of the permit program will run as expected
        Permit P1 = new Permit(1234, "Awais", 4, 30);
        System.out.println("Permit ID: " + P1.getpermitID());
        System.out.println("Permit Name: " + P1.getName());
        System.out.println("Permit Rating: " + P1.getRating());
        System.out.println("Permit Credit: " + P1.getnumberOfCredits());
        P1.topUp(10);
        System.out.println(P1.getnumberOfCredits());
        P1.deductnumberOfCredits(20);
        System.out.println(P1.getnumberOfCredits());
        System.out.println(P1.enoughcredit());
        P1.deductnumberOfCredits(30);
        System.out.println(P1.getnumberOfCredits());
        System.out.println(P1.toString());
        
        // testing if negative is entered into credits and rating 
        Permit P11 = new Permit(5678, "Shristi", -20, -20);
        System.out.println("\nPermit ID: " + + P11.getpermitID());
        System.out.println("\nPermit name: " + P11.getName());
        System.out.println("\nPermit rating: " + P11.getRating());
        System.out.println("\nPermit Credits: " + P11.getnumberOfCredits());
        P11.topUp(30);
        System.out.println(P11.getnumberOfCredits());
        
        //planet 1 for shuttle test
        Planet plnt1 = new Planet(0, "Home", 0, 1000);
        
        //testing to see if the information of the Planet program will run as expected
        Planet plnt2 = new Planet(1, "Sprite", 1, 100);
        System.out.println("\nPlanet reference number: " + plnt2.getplanetNumber());
        System.out.println("\nPlanet name: " + plnt2.getplanetName());
        System.out.println("\nPlanet rating: " + plnt2.getRating());
        System.out.println("\nPlanet capacit: " + plnt2.getCapacity());
        System.out.println(plnt2.toString());
        
        //shuttle 1
        Shuttle S1 = new Shuttle ("ABC1", plnt1, plnt2);
        

    }
    
    public static void main(String[] args)
    {
        Permit_Test xx = new Permit_Test();
        xx.doTest();
    }
}
