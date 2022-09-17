 
import java.util.*;
/**
 * Write a description of class OTester here.
 * 
 * @author 
 * @version 
 */
public class MyTester 
{   

    private void doTest()
    {
        Resort wayward = new Resort("Wayward Asteroids");
        // write your tests here by invoking Resort methods on the Resort object called wayward
        
        //find a fake permit (expected fail)
        System.out.println("\n Finding permit 0000");
        String s1 = wayward.getPermitLocation(0000);
        System.out.println(s1);
        
        //move permit id 1001 to home 
        System.out.println("Permit 1001 current location: ");
        System.out.println("/n" + wayward.getPermitLocation(1001));
        
        //sucessful locations
        System.out.println("\nSucessful travel: ");
        System.out.println(wayward.travel(1000, "ABC1"));
        System.out.println(wayward.travel(1000, "CDE3"));
        
        //permit 1001 moving from Home to Sprite
        System.out.println("\n Moveing Permit to different locations");
        String rslt = wayward.travel(1001, "ABC1");
        System.out.println(rslt);
        //from Sprite to Tropicana
        String rslt2 = wayward.travel(1001, "CDE3");
        System.out.println(rslt2);
        
        // location of ID that just traveled 
        System.out.println("Permit 1001 is now in the location of: ");
        System.out.println("\n" + wayward.getPermitLocation(1001));
        
        //moving the permit back to home
        System.out.println("Permit 1001 back to home: ");
        wayward.moveHome(1001);
        System.out.println(wayward.getPermitLocation(1001));
        
        // get all the permits and planets details
        System.out.print("--permits--");
        System.out.println(wayward.getAllPermitsOnEachPlanet());
        System.out.print("--planets--");
        System.out.println(wayward.listPlanetDetails());
        
        // find permit on planet
        System.out.println("\n Finding which planet 1001 is on");
        String s = wayward.getPermitLocation(1001);
        System.out.println(s);
    }
     
    
    public static void main(String[] args)
    { 
        MyTester xx = new MyTester();
        xx.doTest();
    }
    
}
