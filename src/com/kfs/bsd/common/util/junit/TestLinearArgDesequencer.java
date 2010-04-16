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
	
	// LinearArgDesequencer is intended to be used via
	// a subclass, so we will need some for testing.
	
	protected class LADsEmpty extends LinearArgDesequencer {
		
		// This is a subclass of LinearArgDesequencer
		// that does not define any arguments.
		
		public LADsEmpty() {
			
			// Java requires that this be here.
			
			// Forward the request to super.
			
			super();
		}
		
		public LADsEmpty( String [] args ) {
			
			// Java requires that this be here.
			
			// Forward the request to super.
			
			super( args );
		}
		
		protected void populateArgSet() {
			
			// Overriding super.populateArgSet().
			
			// Do not add any arguments, and do not call
			// super.populateArgSet(), which would add
			// the default arguments, help and verbose.
		}
	}
	
	protected class LADsFlags extends LinearArgDesequencer {
		
		// This is a subclass of LinearArgDesequencer that
		// has arguments applicable for testing flags.
		
		public LADsFlags() {
			
			// Java requires that this be here.
			
			// Forward the request to super.
			
			super();
		}
		
		public LADsFlags( String [] args ) {
			
			// Java requires that this be here.
			
			// Forward the request to super.
			
			super( args );
		}
		
		protected void populateArgSet() {
			
			// For simplicity, do not add the default argument set.
			
			// Add some more of our own arguments:
			
			argSet.addArgumentInfo( "arg1", '1', 0, false, "Argument 1" );
			argSet.addArgumentInfo( "arg2", '2', 0, false, "Argument 2" );
			argSet.addArgumentInfo( "arg3", '3', 0, false, "Argument 3" );
		}
	}
	
	protected class LADsSwitches extends LinearArgDesequencer {
		
		// This is a subclass of LinearArgDesequencer that
		// has arguments applicable for testing switches.
		
		public LADsSwitches() {
			
			// Java requires that this be here.
			
			// Forward the request to super.
			
			super();
		}
		
		public LADsSwitches( String [] args ) {
			
			// Java requires that this be here.
			
			// Forward the request to super.
			
			super( args );
		}
		
		protected void populateArgSet() {
			
			// For simplicity, do not add the default argument set.
			
			// Add some more of our own arguments:
			
			argSet.addArgumentInfo( "arg1", "arg1", 0, false, "Argument 1" );
			argSet.addArgumentInfo( "arg2", "arg2", 0, false, "Argument 2" );
			argSet.addArgumentInfo( "arg3", "arg3", 0, false, "Argument 3" );
		}
	}
	
	protected class LADsParcels extends LinearArgDesequencer {
		
		// This is a subclass of LinearArgDesequencer that has arguments
		// applicable for testing arguments with multiple parcels.
		
		public LADsParcels() {
			
			// Java requires that this be here.
			
			// Forward the request to super.
			
			super();
		}
		
		public LADsParcels( String [] args ) {
			
			// Java requires that this be here.
			
			// Forward the request to super.
			
			super( args );
		}
		
		protected void populateArgSet() {
			
			// For simplicity, do not add the default argument set.
			
			// Add some more of our own arguments:
			
			argSet.addArgumentInfo( "arg1", '1', 1, false, "Argument with 1 parcel" );
			argSet.addArgumentInfo( "arg2", '2', 2, false, "Argument with 2 parcels" );
			argSet.addArgumentInfo( "arg3", '3', 3, false, "Argument with 3 parcels" );
		}
	}
	
	protected class LADsArrays extends LinearArgDesequencer {
		
		// This is a subclass of LinearArgDesequencer that
		// has arguments applicable for testing arrays.
		
		public LADsArrays() {
			
			// Java requires that this be here.
			
			// Forward the request to super.
			
			super();
		}
		
		public LADsArrays( String [] args ) {
			
			// Java requires that this be here.
			
			// Forward the request to super.
			
			super( args );
		}
		
		protected void populateArgSet() {
			
			// For simplicity, do not add the default argument set.
			
			// Add some more of our own arguments:
			
			argSet.addArgumentInfo( "arg1", '1', 1, true, "Array argument with 1 parcel" );
			argSet.addArgumentInfo( "arg2", '2', 2, true, "Array argument with 2 parcels" );
			argSet.addArgumentInfo( "arg3", '3', 3, true, "Array argument with 3 parcels" );
		}
	}
	
	protected class LADsUnbounded extends LinearArgDesequencer {
		
		// This is a subclass of LinearArgDesequencer that has
		// arguments applicable for testing unbounded arguments.
		
		public LADsUnbounded() {
			
			// Java requires that this be here.
			
			// Forward the request to super.
			
			super();
		}
		
		public LADsUnbounded( String [] args ) {
			
			// Java requires that this be here.
			
			// Forward the request to super.
			
			super( args );
		}
		
		protected void populateArgSet() {
			
			// For simplicity, do not add the default argument set.
			
			// Add some more of our own arguments:
			
			argSet.addArgumentInfo( "arg0", '0', "argZero", 0, false, true, "Argument with 0 parcels (unbounded)" );
			argSet.addArgumentInfo( "arg1", '1', "argOne", 1, false, true, "Argument with 1 parcel (unbounded)" );
			argSet.addArgumentInfo( "arg2", '2', "argTwo", 2, false, true, "Argument with 2 parcels (unbounded)" );
			argSet.addArgumentInfo( "arg3", '3', "argThree", 3, false, true, "Argument with 3 parcels (unbounded)" );
			
			argSet.addArgumentInfo( "ary0", 'p', "arrayZero", 0, true, true, "Array argument with 0 parcel (unbounded)" );
			argSet.addArgumentInfo( "ary1", 'q', "arrayOne", 1, true, true, "Array argument with 1 parcel (unbounded)" );
			argSet.addArgumentInfo( "ary2", 'w', "arrayTwo", 2, true, true, "Array argument with 2 parcels (unbounded)" );
			argSet.addArgumentInfo( "ary3", 'e', "arrayThree", 3, true, true, "Array argument with 3 parcels (unbounded)" );
		}
	}
	
	protected class LADsDuplicates extends LinearArgDesequencer {
		
		// This is a subclass of LinearArgDesequencer that
		// has arguments applicable for testing what happens
		// when duplicate argument definitions are defined.
		
		public LADsDuplicates() {
			
			// Java requires that this be here.
			
			// Forward the request to super.
			
			super();
		}
		
		public LADsDuplicates( String [] args ) {
			
			// Java requires that this be here.
			
			// Forward the request to super.
			
			super( args );
		}
		
		protected void populateArgSet() {
			
			// For simplicity, do not add the default argument set.
			
			// Add some more of our own arguments:
			
			argSet.addArgumentInfo( "debug", 'd', 0, false, "Debug Mode" );
			argSet.addArgumentInfo( "debug", 'd', 1, true, "Disaster Mode" );
			
			argSet.addArgumentInfo( "bd", 'b', 1, false, "Bird Dog Mode" );
			argSet.addArgumentInfo( "bd", 's', "yummy", 3, true, true, "Mongolian Mode" );
		}
	}
	
	// Common testing code
	
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
	
	// Test cases on LinearArgDesequencer directly
	
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
	
	public void testNoSubclassInvalidArgs1() {
		
		String [] cmdl_args = { "--foo" };
		LinearArgDesequencer args = new LinearArgDesequencer( cmdl_args );
		
		verifyBasics( args, false, 0, true, args.kErrorCodeUnknownArgument );
	}
	
	public void testNoSubclassInvalidArgs2() {
		
		String [] cmdl_args = { "--foo", "--bar" };
		LinearArgDesequencer args = new LinearArgDesequencer( cmdl_args );
		
		verifyBasics( args, false, 0, true, args.kErrorCodeUnknownArgument );
	}
	
	public void testNoSubclassInvalidArgs3() {
		
		String [] cmdl_args = { "-a" };
		LinearArgDesequencer args = new LinearArgDesequencer( cmdl_args );
		
		verifyBasics( args, false, 0, true, args.kErrorCodeUnknownArgument );
	}
	
	public void testNoSubclassInvalidArgs4() {
		
		String [] cmdl_args = { "-ab" };
		LinearArgDesequencer args = new LinearArgDesequencer( cmdl_args );
		
		verifyBasics( args, false, 0, true, args.kErrorCodeUnknownArgument );
	}
	
	public void testNoSubclassInvalidArgs5() {
		
		String [] cmdl_args = { "-ab", "--foo" };
		LinearArgDesequencer args = new LinearArgDesequencer( cmdl_args );
		
		verifyBasics( args, false, 0, true, args.kErrorCodeUnknownArgument );
	}
	
	// Test cases on LADsEmpty (a subclass of LinearArgDesequencer)
	
	public void testLADsEmptyNoArgs() {
		
		LADsEmpty args = new LADsEmpty();
		
		verifyBasics( args, false, 0, false, 0 );
	}
	
	public void testLADsEmptyInvalidArgs1() {
		
		String [] cmdl_args = { "--help" };
		LADsEmpty args = new LADsEmpty( cmdl_args );
		
		verifyBasics( args, false, 0, true, args.kErrorCodeUnknownArgument );
	}
	
	public void testLADsEmptyInvalidArgs2() {
		
		String [] cmdl_args = { "--verbose", "--verbose" };
		LADsEmpty args = new LADsEmpty( cmdl_args );
		
		verifyBasics( args, false, 0, true, args.kErrorCodeUnknownArgument );
	}
}
