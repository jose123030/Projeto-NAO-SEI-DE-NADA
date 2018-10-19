package test;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class TesteExecutionListener extends RunListener {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    boolean failed = false;


    public void testRunStarted(Description description) throws Exception {
        System.out.println("Number of tests to execute: " + description.testCount());
    }

    // public void testRunFinished(Result result) throws Exception {
    //     System.out.println("Number of tests executed: " + result.getRunCount());
    // }

    public void testStarted(Description description) throws Exception {
        // System.out.println("Starting: " + description.getMethodName());
        // System.out.print(description.getMethodName());
        System.out.print(ANSI_BLUE + "-> " + ANSI_RESET);
    }

    public void testFinished(Description description) throws Exception {
        // System.out.println("Finished: " + description.getMethodName());
        if (!this.failed) {
            System.out.println(description.getMethodName() + ANSI_GREEN + " [OK]" + ANSI_RESET);
        }
        this.failed = false;
    }

    public void testFailure(Failure failure) throws Exception {
        // System.out.println("\t" + failure.getDescription().getMethodName());
        this.failed = true;
        // System.out.println(ANSI_RED + " [Falha] " + ANSI_RESET);
        System.out.println(ANSI_RED + failure.toString() + ANSI_RESET);
    }

    // public void testAssumptionFailure(Failure failure) {
    //     System.out.println("Failed: " + failure.getDescription().getMethodName());
    // }

    // public void testIgnored(Description description) throws Exception {
    //     System.out.println("Ignored: " + description.getMethodName());
    // }
}