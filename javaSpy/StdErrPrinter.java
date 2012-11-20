public class StdErrPrinter implements ILogDevice
{
    public void log(String str)
    {
        System.err.print(str);
    }
}
