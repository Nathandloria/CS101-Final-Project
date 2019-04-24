/** This program is to test the amount of memory that is utilized by the program
* @author Adam Cook
*/

public class memoryTester {
  private static final long MEGABYTE = 1024L * 1024L;
  private static final long KILOBYTE = 1024L;

  public static long bytesToMegabytes(long bytes) {
    return bytes / MEGABYTE;
  }
  public static long bytesToKilobytes(long bytes) {
    return bytes / KILOBYTE;
  }
  public static void main(String[] args) {
    Runtime runtime = Runtime.getRuntime();
    runtime.gc();
    long memory = runtime.totalMemory() - runtime.freeMemory();
    System.out.println("\n**********  Total Memory Usage  **********\n");
    System.out.println("Total Memory of System in bytes: " + runtime.totalMemory());
    System.out.println("Memory Available in bytes " + runtime.freeMemory());
    System.out.println("\nTotal Memory used in bytes: " + memory);
    System.out.println("Total Memory used in kilobytes: " + bytesToKilobytes(memory));
    //System.out.println("Total Memory used in megabytes: " + bytesToMegabytes(memory));
    System.out.println();
  }
}
