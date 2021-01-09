package com.ers;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses( { EmployeeControllerTest.class, ManagerControllerTest.class } )
public class TestSuite {

}
