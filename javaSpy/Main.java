import java.io.*;

class Main 
{ 
    public static void main (String[] args)  throws java.lang.Exception
   {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String progName = in.readLine().substring(4);
            SysCommandExecutor systemDude = new SysCommandExecutor(".");
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
            
            Notifier notifier = new Notifier(progName, pidChars);
            notifier.start();
       }
   } 
   static class Notifier extends Thread {
       String progName;
       int pidChars;
       public Notifier(String progName, int pidChars) {
           this.progName = progName;
           this.pidChars = pidChars;
        }
       public void run() {
        SysCommandExecutor systemDude;
        int newPidChars;
        while(true)
        {
            systemDude = new SysCommandExecutor(".");
            try{
                systemDude.runCommand("pidof " + progName);
            } catch (Exception e) {
                continue;
            }
            try{
                Thread.sleep(500);
            } catch (InterruptedException e){
            }
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
}  
