import java.io.*;

class Main 
{ 
    public static void main (String[] args)  throws java.lang.Exception
   {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String progName = in.readLine();
        SysCommandExecutor systemDude = new SysCommandExecutor("/home/maksimv/Desktop/javaSpy");
        systemDude.runCommand("pidof " + progName);
        Thread.sleep(500);
        int pidChars = systemDude.getCommandOutput().length();
        System.out.println( progName + " " + systemDude.getCommandOutput().length());        
        if(pidChars > 0)
        {
            System.out.println(progName + " is running");
        }
        if(pidChars == 0)
        {
            System.out.println(progName + " is not running");
        }
        
        int newPidChars;
        while(true)
        {
            systemDude = new SysCommandExecutor("/home/maksimv/Desktop/javaSpy");
            systemDude.runCommand("pidof " + progName);
            Thread.sleep(500);
            newPidChars = systemDude.getCommandOutput().length();
            if(newPidChars > 0 && pidChars == 0)
            {
                System.out.println(progName + "  is now running!");
            }
            
            if(newPidChars == 0 && pidChars > 0)
            {
                System.out.println(progName + "  is no longer running");
            }
            pidChars = newPidChars;
            
        }       
    

      
   } 
}  
