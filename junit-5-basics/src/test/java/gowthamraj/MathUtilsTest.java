package gowthamraj;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Testing MathUtils Class...")
class MathUtilsTest {

	MathUtils mathutils;
	TestInfo testinfo;
	TestReporter testreporter;
	
	@BeforeEach
	void init(TestInfo testinfo, TestReporter testreporter) {
		this.testinfo = testinfo;
		this.testreporter = testreporter;
		mathutils = new MathUtils();
	}
	
	@BeforeAll
	static void beforeAllInit() {
		System.out.println("This needs to run before all...");
	}
	
	@AfterAll
	static void afterAllInit() {
		System.out.println("This needs to run after all...");
	}
	
	@AfterEach
	void cleanup() {
		System.out.println("Cleaning UP...");
	}
	
	@Test
	@DisplayName("Testing Add Method...")
	void test() {
		int expected=2;
		int actual=mathutils.add(1, 1);
		assertEquals(expected,actual);
	}
	
	@Nested
	@DisplayName("Testing Add Method class...")
	class TestAdd {

		@Test
		@DisplayName("Testing Positive Add Method...")
		void testPositiveAdd() {
			assertEquals(4,mathutils.add(2, 2),"Adds two positive numbers");
		}
		
		@Test
		@DisplayName("Testing Negative Add Method...")
		void testNegativeAdd() {
			assertEquals(-4,mathutils.add(-2, -2),"Adds two negative numbers");
		}
		
	}
	
	@Test
	@Disabled
	@DisplayName("TDD method -> should not run..")
	void testDisabled() {
		fail("This method should not run..");
	}
	
	@Test
	@Tag("Multiplication")
	@DisplayName("Multiply Method")
	void testMultiply() {
		System.out.println("Running Test Info as "+testinfo.getDisplayName()+" with Tags as "+testinfo.getTags());
		testreporter.publishEntry("Running Test Info as "+testinfo.getDisplayName()+" with Tags as "+testinfo.getTags());
		//assertEquals(4,mathutils.multiply(2,2),"Should return correct product");
	    assertAll(
	    		() -> assertEquals(4,mathutils.multiply(2,2)),
	    		() -> assertEquals(0,mathutils.multiply(2,0)),
	    		() -> assertEquals(-2,mathutils.multiply(-1,2)),
	    		() -> assertEquals(100,mathutils.multiply(20,5))
	    		);
	}
	
	@Test
	@EnabledOnOs(OS.LINUX)  //this annotation is used to specify condition that this method runs only on linux OS
	void testDivide() {
		boolean isServerUp = false;
		assumeTrue(isServerUp);
		assertThrows(ArithmeticException.class,()->mathutils.divide(1,0),"Divide by Zero exception");
	}
	
	@Test
	@Tag("Circle")
	void TestComputeCircleRadius() {
		assertEquals(314.1592653589793,mathutils.computeCircleArea(10),"Returns Circle Area");
	}
	
	@Test
	@Tag("Repeat")
	@RepeatedTest(3)
	void testRepeated() {
		System.out.println("Repeated Test...");
	}
}
