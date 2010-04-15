/*  Written by Andrew Keller
 *
 *  This file is licensed as BSD.  Copyright 2010 Andrew Keller.
 *
 *  JUnit Test Case for LinearArgDesequencer
 */

package com.kfs.bsd.common.util.junit;

import junit.framework.*;
import com.kfs.bsd.common.util.LinearArgDesequencer;

public class TestLinearArgDesequencer extends TestCase {
	
	public void testNoSubclassNoArgs() {
		
		LinearArgDesequencer args = new LinearArgDesequencer();
		
		verifyBasics( args, false, 0, false, 0 );
	}
	
	public void testNoSubclassWithArgs_h() {
		
		String [] cmdl_args = { "-h" };
		LinearArgDesequencer args = new LinearArgDesequencer( cmdl_args );
		
		verifyBasics( args, true, 0, false, 0 );
	}
	
	public void testNoSubclassWithArgs_hh() {
		
		String [] cmdl_args = { "-hh" };
		LinearArgDesequencer args = new LinearArgDesequencer( cmdl_args );
		
		verifyBasics( args, true, 0, false, 0 );
	}
	
	public void testNoSubclassWithArgs_h_h() {
		
		String [] cmdl_args = { "-h", "-h" };
		LinearArgDesequencer args = new LinearArgDesequencer( cmdl_args );
		
		verifyBasics( args, true, 0, false, 0 );
	}
	
	public void testNoSubclassWithArgs_Help() {
		
		String [] cmdl_args = { "--help" };
		LinearArgDesequencer args = new LinearArgDesequencer( cmdl_args );
		
		verifyBasics( args, true, 0, false, 0 );
	}
	
	public void testNoSubclassWithArgs_Help_Help() {
		
		String [] cmdl_args = { "--help", "--help" };
		LinearArgDesequencer args = new LinearArgDesequencer( cmdl_args );
		
		verifyBasics( args, true, 0, false, 0 );
	}
	
	public void testNoSubclassWithArgs_v() {
		
		String [] cmdl_args = { "-v" };
		LinearArgDesequencer args = new LinearArgDesequencer( cmdl_args );
		
		verifyBasics( args, false, 1, false, 0 );
	}
	
	public void testNoSubclassWithArgs_vv() {
		
		String [] cmdl_args = { "-vv" };
		LinearArgDesequencer args = new LinearArgDesequencer( cmdl_args );
		
		verifyBasics( args, false, 2, false, 0 );
	}
	
	public void testNoSubclassWithArgs_v_v() {
		
		String [] cmdl_args = { "-v", "-v" };
		LinearArgDesequencer args = new LinearArgDesequencer( cmdl_args );
		
		verifyBasics( args, false, 2, false, 0 );
	}
	
	public void testNoSubclassWithArgs_Verbose() {
		
		String [] cmdl_args = { "--verbose" };
		LinearArgDesequencer args = new LinearArgDesequencer( cmdl_args );
		
		verifyBasics( args, false, 1, false, 0 );
	}
	
	public void testNoSubclassWithArgs_Verbose_Verbose() {
		
		String [] cmdl_args = { "--verbose", "--verbose" };
		LinearArgDesequencer args = new LinearArgDesequencer( cmdl_args );
		
		verifyBasics( args, false, 2, false, 0 );
	}
	
	public void verifyBasics( LinearArgDesequencer lad, boolean help, int verbosity, boolean error, int errorCode ) {
		
		// Make sure the error flag is correct.
		
		if( error ) {
			
			assertTrue( "The given arguments should have caused an error.", lad.sequenceError() );
			assertEquals( "The error code should have been "+errorCode+".", errorCode, lad.sequenceErrorCode() );
		}
		else {
			
			assertFalse( "The given arguments should not have caused an error.", lad.sequenceError() );
			assertEquals( "'No Error' should imply that the error code is zero.", 0, lad.sequenceErrorCode() );
		}
		
		// Make sure the help flag is correct.
		
		if( help )
			assertTrue( "LinearArgDesequencer did not find the help flag.", lad.foundHelpFlag() );
		
		else
			assertFalse( "LinearArgDesequencer found a help flag when one was not passed.", lad.foundHelpFlag() );
		
		// Make sure the verbosity level is correct.
		
		String msg = "LinearArgDesequencer did not find exactly "+verbosity+" verbose flag"+(verbosity==1?"":"s")+".";
		assertEquals( msg, verbosity, lad.verbosity() );
	}
}
