/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package woordenapplicatie.testsuite;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

/**
 *
 * @author Bjork & Jurgen
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
    RunTestSuite.class})
public class RunTestSuite {
    
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(WoordenApplicatieTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("all tests were succesful");
        } else {
            System.out.println("test suite was not succesful");
        }
    }
}
