package com.persistent.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.persistent.tests.controller.StudentControllerTestCase;
import com.persistent.tests.service.StudentServiceTestCase;

@RunWith(Suite.class)
@SuiteClasses({ StudentControllerTestCase.class, StudentServiceTestCase.class })
public class TestSuite {

}
