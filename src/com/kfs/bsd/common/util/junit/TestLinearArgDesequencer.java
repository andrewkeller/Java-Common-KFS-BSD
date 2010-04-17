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
		
		public void verifyOutcome( boolean error, int errorCode ) {
			
			// Make sure the error flag is correct.
			
			if( error ) {
				
				assertTrue( "The given arguments should have caused an error.", sequenceError() );
				assertEquals( "The error code should have been "+errorCode+".", errorCode, sequenceErrorCode() );
			}
			else {
				
				assertFalse( "The given arguments should not have caused an error.", sequenceError() );
				assertEquals( "'No Error' should imply that the error code is zero.", 0, sequenceErrorCode() );
			}
		}
	}
	
	protected class LADsFlags extends LADsEmpty {
		
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
		
		public void verifyOutcome( int a1, int a2, int a3, boolean error, int errorCode ) {
			
			// Verify the generic aspects (error and error code):
			
			verifyOutcome( error, errorCode );
			
			// Verify that the given arguments were found correctly.
			
			assertEquals( "LinearArgDesequencer did not find the '-1' flag.", a1, argSet.makeGet( "arg1" ).parcels.size() );
			assertEquals( "LinearArgDesequencer did not find the '-2' flag.", a2, argSet.makeGet( "arg2" ).parcels.size() );
			assertEquals( "LinearArgDesequencer did not find the '-3' flag.", a3, argSet.makeGet( "arg3" ).parcels.size() );
		}
	}
	
	protected class LADsSwitches extends LADsEmpty {
		
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
		
		public void verifyOutcome( int a1, int a2, int a3, boolean error, int errorCode ) {
			
			// Verify the generic aspects (error and error code):
			
			verifyOutcome( error, errorCode );
			
			// Verify that the given arguments were found correctly.
			
			assertEquals( "LinearArgDesequencer did not find the '--arg1' switch.", a1, argSet.makeGet( "arg1" ).parcels.size() );
			assertEquals( "LinearArgDesequencer did not find the '--arg2' switch.", a2, argSet.makeGet( "arg2" ).parcels.size() );
			assertEquals( "LinearArgDesequencer did not find the '--arg3' switch.", a3, argSet.makeGet( "arg3" ).parcels.size() );
		}
	}
	
	protected class LADsParcels extends LADsEmpty {
		
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
		}
		
		public void verifyOutcome( int a1, int a2, boolean error, int errorCode ) {
			
			// Verify the generic aspects (error and error code):
			
			verifyOutcome( error, errorCode );
			
			// Verify that the given arguments were found correctly.
			
			String msg;
			
			msg = "LinearArgDesequencer did not find exactly "+a1+" parcel"+(a1==1?"":"s")+" for the argument '-1'.";
			assertEquals( msg, a1, argSet.makeGet( "arg1" ).getRelevantParcels().length );
			
			msg = "LinearArgDesequencer did not find exactly "+a2+" parcel"+(a2==1?"":"s")+" for the argument '-2'.";
			assertEquals( msg, a2, argSet.makeGet( "arg2" ).getRelevantParcels().length );
		}
		
		public void verifyParcel( String argID, int index, String value ) {
			
			// Verify that the given parcel's value matches the given value.
			
			String [] parcels = argSet.makeGet( argID ).getRelevantParcels();
			String msg = "Parcel[ " + index + " ] should contain '" + value + "'.";
			assertEquals( msg, value, parcels[ index ] );
		}
	}
	
	protected class LADsArrays extends LADsParcels {
		
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
		}
	}
	
	protected class LADsUnbounded extends LADsParcels {
		
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
			
			argSet.addArgumentInfo( "ary0", 'p', "arrayZero", 0, true, true, "Array argument with 0 parcel (unbounded)" );
			argSet.addArgumentInfo( "ary1", 'q', "arrayOne", 1, true, true, "Array argument with 1 parcel (unbounded)" );
			argSet.addArgumentInfo( "ary2", 'w', "arrayTwo", 2, true, true, "Array argument with 2 parcels (unbounded)" );
			
			argSet.addArgumentInfo( "fish", 'f', "fish", 2, false, false, "Argument with 2 parcels." );
		}
		
		public void verifyOutcome( int fish, int arg1, int ary1, int arg2, int ary2, boolean error, int errorCode ) {
			
			// Verify the generic aspects (error and error code):
			
			verifyOutcome( error, errorCode );
			
			// Verify that the given arguments were found correctly.
			
			String msg;
			
			msg = "LinearArgDesequencer did not find exactly "+arg1+" parcel"+(arg1==1?"":"s")+" for the argument '-1'.";
			assertEquals( msg, arg1, argSet.makeGet( "arg1" ).getRelevantParcels().length );
			
			msg = "LinearArgDesequencer did not find exactly "+ary1+" parcel"+(ary1==1?"":"s")+" for the argument '-q'.";
			assertEquals( msg, ary1, argSet.makeGet( "ary1" ).getRelevantParcels().length );
			
			msg = "LinearArgDesequencer did not find exactly "+arg2+" parcel"+(arg2==1?"":"s")+" for the argument '-2'.";
			assertEquals( msg, arg2, argSet.makeGet( "arg2" ).getRelevantParcels().length );
			
			msg = "LinearArgDesequencer did not find exactly "+ary2+" parcel"+(ary2==1?"":"s")+" for the argument '-w'.";
			assertEquals( msg, ary2, argSet.makeGet( "ary2" ).getRelevantParcels().length );
			
			msg = "LinearArgDesequencer did not find exactly "+fish+" parcel"+(fish==1?"":"s")+" for the argument '-f'.";
			assertEquals( msg, fish, argSet.makeGet( "fish" ).getRelevantParcels().length );
		}
	}
	
	protected class LADsDuplicates extends LADsEmpty {
		
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
		
		args.verifyOutcome( false, 0 );
	}
	
	public void testLADsEmptyInvalidArgs1() {
		
		String [] cmdl_args = { "--help" };
		LADsEmpty args = new LADsEmpty( cmdl_args );
		
		args.verifyOutcome( true, args.kErrorCodeUnknownArgument );
	}
	
	public void testLADsEmptyInvalidArgs2() {
		
		String [] cmdl_args = { "--verbose", "--verbose" };
		LADsEmpty args = new LADsEmpty( cmdl_args );
		
		args.verifyOutcome( true, args.kErrorCodeUnknownArgument );
	}
	
	// Test cases on LADsFlags (a subclass of LinearArgDesequencer)
	
	public void testLADsFlagsNoArgs() {
		
		LADsFlags args = new LADsFlags();
		
		args.verifyOutcome( 0, 0, 0, false, 0 );
	}
	
	public void testLADsFlags_1() {
		
		String [] cmdl_args = { "-1" };
		LADsFlags args = new LADsFlags( cmdl_args );
		
		args.verifyOutcome( 1, 0, 0, false, 0 );
	}
	
	public void testLADsFlags_2() {
		
		String [] cmdl_args = { "-2" };
		LADsFlags args = new LADsFlags( cmdl_args );
		
		args.verifyOutcome( 0, 1, 0, false, 0 );
	}
	
	public void testLADsFlags_3() {
		
		String [] cmdl_args = { "-3" };
		LADsFlags args = new LADsFlags( cmdl_args );
		
		args.verifyOutcome( 0, 0, 1, false, 0 );
	}
	
	public void testLADsFlags_12() {
		
		String [] cmdl_args = { "-12" };
		LADsFlags args = new LADsFlags( cmdl_args );
		
		args.verifyOutcome( 1, 1, 0, false, 0 );
	}
	
	public void testLADsFlags_1_2() {
		
		String [] cmdl_args = { "-1", "-2" };
		LADsFlags args = new LADsFlags( cmdl_args );
		
		args.verifyOutcome( 1, 1, 0, false, 0 );
	}
	
	public void testLADsFlags_23() {
		
		String [] cmdl_args = { "-23" };
		LADsFlags args = new LADsFlags( cmdl_args );
		
		args.verifyOutcome( 0, 1, 1, false, 0 );
	}
	
	public void testLADsFlags_2_3() {
		
		String [] cmdl_args = { "-2", "-3" };
		LADsFlags args = new LADsFlags( cmdl_args );
		
		args.verifyOutcome( 0, 1, 1, false, 0 );
	}
	
	public void testLADsFlags_13() {
		
		String [] cmdl_args = { "-13" };
		LADsFlags args = new LADsFlags( cmdl_args );
		
		args.verifyOutcome( 1, 0, 1, false, 0 );
	}
	
	public void testLADsFlags_1_3() {
		
		String [] cmdl_args = { "-1", "-3" };
		LADsFlags args = new LADsFlags( cmdl_args );
		
		args.verifyOutcome( 1, 0, 1, false, 0 );
	}
	
	public void testLADsFlags_123() {
		
		String [] cmdl_args = { "-123" };
		LADsFlags args = new LADsFlags( cmdl_args );
		
		args.verifyOutcome( 1, 1, 1, false, 0 );
	}
	
	public void testLADsFlags_1_23() {
		
		String [] cmdl_args = { "-1", "-23" };
		LADsFlags args = new LADsFlags( cmdl_args );
		
		args.verifyOutcome( 1, 1, 1, false, 0 );
	}
	
	public void testLADsFlags_12_3() {
		
		String [] cmdl_args = { "-12", "-3" };
		LADsFlags args = new LADsFlags( cmdl_args );
		
		args.verifyOutcome( 1, 1, 1, false, 0 );
	}
	
	public void testLADsFlags_1_2_3() {
		
		String [] cmdl_args = { "-1", "-2", "-3" };
		LADsFlags args = new LADsFlags( cmdl_args );
		
		args.verifyOutcome( 1, 1, 1, false, 0 );
	}
	
	public void testLADsFlags_Invalid_14() {
		
		String [] cmdl_args = { "-14" };
		LADsFlags args = new LADsFlags( cmdl_args );
		
		args.verifyOutcome( 1, 0, 0, true, args.kErrorCodeUnknownArgument );
	}
	
	public void testLADsFlags_Invalid_1_4() {
		
		String [] cmdl_args = { "-1", "4" };
		LADsFlags args = new LADsFlags( cmdl_args );
		
		args.verifyOutcome( 1, 0, 0, true, args.kErrorCodeUnknownArgument );
	}
	
	public void testLADsFlags_Invalid_41() {
		
		String [] cmdl_args = { "-41" };
		LADsFlags args = new LADsFlags( cmdl_args );
		
		// Since parsing stops when an invalid argument
		// is found, the '-1' won't be found.
		
		args.verifyOutcome( 0, 0, 0, true, args.kErrorCodeUnknownArgument );
	}
	
	public void testLADsFlags_Invalid_4_2() {
		
		String [] cmdl_args = { "-4", "-2" };
		LADsFlags args = new LADsFlags( cmdl_args );
		
		// Since parsing stops when an invalid argument
		// is found, the '-2' won't be found.
		
		args.verifyOutcome( 0, 0, 0, true, args.kErrorCodeUnknownArgument );
	}
	
	// Test cases on LADsSwitches (a subclass of LinearArgDesequencer)
	
	public void testLADsSwitchesNoArgs() {
		
		LADsSwitches args = new LADsSwitches();
		
		args.verifyOutcome( 0, 0, 0, false, 0 );
	}
	
	public void testLADsSwitches_1() {
		
		String [] cmdl_args = { "--arg1" };
		LADsSwitches args = new LADsSwitches( cmdl_args );
		
		args.verifyOutcome( 1, 0, 0, false, 0 );
	}
	
	public void testLADsSwitches_2() {
		
		String [] cmdl_args = { "--arg2" };
		LADsSwitches args = new LADsSwitches( cmdl_args );
		
		args.verifyOutcome( 0, 1, 0, false, 0 );
	}
	
	public void testLADsSwitches_3() {
		
		String [] cmdl_args = { "--arg3" };
		LADsSwitches args = new LADsSwitches( cmdl_args );
		
		args.verifyOutcome( 0, 0, 1, false, 0 );
	}
	
	public void testLADsSwitches_1_2() {
		
		String [] cmdl_args = { "--arg1", "--arg2" };
		LADsSwitches args = new LADsSwitches( cmdl_args );
		
		args.verifyOutcome( 1, 1, 0, false, 0 );
	}
	
	public void testLADsSwitches_2_3() {
		
		String [] cmdl_args = { "--arg2", "--arg3" };
		LADsSwitches args = new LADsSwitches( cmdl_args );
		
		args.verifyOutcome( 0, 1, 1, false, 0 );
	}
	
	public void testLADsSwitches_1_3() {
		
		String [] cmdl_args = { "--arg1", "--arg3" };
		LADsSwitches args = new LADsSwitches( cmdl_args );
		
		args.verifyOutcome( 1, 0, 1, false, 0 );
	}
	
	public void testLADsSwitches_1_2_3() {
		
		String [] cmdl_args = { "--arg1", "--arg2", "--arg3" };
		LADsSwitches args = new LADsSwitches( cmdl_args );
		
		args.verifyOutcome( 1, 1, 1, false, 0 );
	}
	
	public void testLADsSwitches_Invalid_1_4() {
		
		String [] cmdl_args = { "--arg1", "--arg4" };
		LADsSwitches args = new LADsSwitches( cmdl_args );
		
		args.verifyOutcome( 1, 0, 0, true, args.kErrorCodeUnknownArgument );
	}
	
	public void testLADsSwitches_Invalid_4_2() {
		
		String [] cmdl_args = { "--arg4", "--arg2" };
		LADsSwitches args = new LADsSwitches( cmdl_args );
		
		// Since parsing stops when an invalid argument
		// is found, the '--arg2' won't be found.
		
		args.verifyOutcome( 0, 0, 0, true, args.kErrorCodeUnknownArgument );
	}
	
	// Test cases on LADsParcels (a subclass of LinearArgDesequencer)
	
	public void testLADsParcelsNoArgs() {
		
		LADsParcels args = new LADsParcels();
		
		args.verifyOutcome( 0, 0, false, 0 );
	}
	
	public void testLADsParcels_1x0() {
		
		String [] cmdl_args = { "-1" };
		LADsParcels args = new LADsParcels( cmdl_args );
		
		args.verifyOutcome( 0, 0, true, args.kErrorCodeMissingArgument );
	}
	
	public void testLADsParcels_1x1() {
		
		String [] cmdl_args = { "-1", "foo" };
		LADsParcels args = new LADsParcels( cmdl_args );
		
		args.verifyOutcome( 1, 0, false, 0 );
		args.verifyParcel( "arg1", 0, "foo" );
	}
	
	public void testLADsParcels_1x2() {
		
		String [] cmdl_args = { "-1", "foo", "bar" };
		LADsParcels args = new LADsParcels( cmdl_args );
		
		args.verifyOutcome( 1, 0, true, args.kErrorCodeUnknownArgument );
		args.verifyParcel( "arg1", 0, "foo" );
	}
	
	public void testLADsParcels_2x1() {
		
		String [] cmdl_args = { "-2", "foo" };
		LADsParcels args = new LADsParcels( cmdl_args );
		
		args.verifyOutcome( 0, 1, true, args.kErrorCodeMissingArgument );
		args.verifyParcel( "arg2", 0, "foo" );
	}
	
	public void testLADsParcels_2x2() {
		
		String [] cmdl_args = { "-2", "foo", "bar" };
		LADsParcels args = new LADsParcels( cmdl_args );
		
		args.verifyOutcome( 0, 2, false, 0 );
		args.verifyParcel( "arg2", 0, "foo" );
		args.verifyParcel( "arg2", 1, "bar" );
	}
	
	public void testLADsParcels_2x3() {
		
		String [] cmdl_args = { "-2", "foo", "bar", "fish" };
		LADsParcels args = new LADsParcels( cmdl_args );
		
		args.verifyOutcome( 0, 2, true, args.kErrorCodeUnknownArgument );
		args.verifyParcel( "arg2", 0, "foo" );
		args.verifyParcel( "arg2", 1, "bar" );
	}
	
	public void testLADsParcels_1x1_2x2() {
		
		String [] cmdl_args = { "-1", "foo", "-2", "foo", "bar" };
		LADsParcels args = new LADsParcels( cmdl_args );
		
		args.verifyOutcome( 1, 2, false, 0 );
		args.verifyParcel( "arg1", 0, "foo" );
		args.verifyParcel( "arg2", 0, "foo" );
		args.verifyParcel( "arg2", 1, "bar" );
	}
	
	public void testLADsParcels_1x1_2x3() {
		
		String [] cmdl_args = { "-1", "foo", "-2", "foo", "bar", "fish" };
		LADsParcels args = new LADsParcels( cmdl_args );
		
		args.verifyOutcome( 1, 2, true, args.kErrorCodeUnknownArgument );
		args.verifyParcel( "arg1", 0, "foo" );
		args.verifyParcel( "arg2", 0, "foo" );
		args.verifyParcel( "arg2", 1, "bar" );
	}
	
	public void testLADsParcels_12x2() {
		
		String [] cmdl_args = { "-12", "foo", "bar" };
		LADsParcels args = new LADsParcels( cmdl_args );
		
		args.verifyOutcome( 1, 1, true, args.kErrorCodeMissingArgument );
		args.verifyParcel( "arg1", 0, "foo" );
		args.verifyParcel( "arg2", 0, "bar" );
	}
	
	public void testLADsParcels_12x3() {
		
		String [] cmdl_args = { "-12", "foo", "bar", "fish" };
		LADsParcels args = new LADsParcels( cmdl_args );
		
		args.verifyOutcome( 1, 2, false, 0 );
		args.verifyParcel( "arg1", 0, "foo" );
		args.verifyParcel( "arg2", 0, "bar" );
		args.verifyParcel( "arg2", 1, "fish" );
	}
	
	public void testLADsParcels_12x4() {
		
		String [] cmdl_args = { "-12", "foo", "bar", "fish", "cat" };
		LADsParcels args = new LADsParcels( cmdl_args );
		
		args.verifyOutcome( 1, 2, true, args.kErrorCodeUnknownArgument );
		args.verifyParcel( "arg1", 0, "foo" );
		args.verifyParcel( "arg2", 0, "bar" );
		args.verifyParcel( "arg2", 1, "fish" );
	}
	
	public void testLADsParcels_21x3() {
		
		String [] cmdl_args = { "-21", "foo", "bar", "fish" };
		LADsParcels args = new LADsParcels( cmdl_args );
		
		args.verifyOutcome( 1, 2, false, 0 );
		args.verifyParcel( "arg2", 0, "foo" );
		args.verifyParcel( "arg2", 1, "bar" );
		args.verifyParcel( "arg1", 0, "fish" );
	}
	
	public void testLADsParcels_2x1_1x2() {
		
		String [] cmdl_args = { "-2", "foo", "-1", "bar", "fish" };
		LADsParcels args = new LADsParcels( cmdl_args );
		
		args.verifyOutcome( 1, 2, false, 0 );
		args.verifyParcel( "arg2", 0, "foo" );
		args.verifyParcel( "arg2", 1, "bar" );
		args.verifyParcel( "arg1", 0, "fish" );
	}
	
	public void testLADsParcels_11x2() {
		
		String [] cmdl_args = { "-11", "foo", "bar" };
		LADsParcels args = new LADsParcels( cmdl_args );
		
		// Argument '-1' is NOT an array, so any
		// extra values override existing values.
		
		args.verifyOutcome( 1, 0, false, 0 );
		args.verifyParcel( "arg1", 0, "bar" );
	}
	
	public void testLADsParcels_22x4() {
		
		String [] cmdl_args = { "-22", "foo", "bar", "fish", "cat" };
		LADsParcels args = new LADsParcels( cmdl_args );
		
		// Argument '-2' is NOT an array, so any
		// extra values override existing values.
		
		args.verifyOutcome( 0, 2, false, 0 );
		args.verifyParcel( "arg2", 0, "fish" );
		args.verifyParcel( "arg2", 1, "cat" );
	}
	
	// Test cases on LADsArrays (a subclass of LinearArgDesequencer)
	
	public void testLADsArrays_1x0() {
		
		String [] cmdl_args = { "-1" };
		LADsArrays args = new LADsArrays( cmdl_args );
		
		args.verifyOutcome( 0, 0, true, args.kErrorCodeMissingArgument );
	}
	
	public void testLADsArrays_1x1() {
		
		String [] cmdl_args = { "-1", "foo" };
		LADsArrays args = new LADsArrays( cmdl_args );
		
		args.verifyOutcome( 1, 0, false, 0 );
		args.verifyParcel( "arg1", 0, "foo" );
	}
	
	public void testLADsArrays_1x2() {
		
		String [] cmdl_args = { "-1", "foo", "bar" };
		LADsArrays args = new LADsArrays( cmdl_args );
		
		args.verifyOutcome( 1, 0, true, args.kErrorCodeUnknownArgument );
		args.verifyParcel( "arg1", 0, "foo" );
	}
	
	public void testLADsArrays_1x1_2x2() {
		
		String [] cmdl_args = { "-1", "foo", "-2", "foo", "bar" };
		LADsArrays args = new LADsArrays( cmdl_args );
		
		args.verifyOutcome( 1, 2, false, 0 );
		args.verifyParcel( "arg1", 0, "foo" );
		args.verifyParcel( "arg2", 0, "foo" );
		args.verifyParcel( "arg2", 1, "bar" );
	}
	
	public void testLADsArrays_1x1_2x3() {
		
		String [] cmdl_args = { "-1", "foo", "-2", "foo", "bar", "fish" };
		LADsArrays args = new LADsArrays( cmdl_args );
		
		args.verifyOutcome( 1, 2, true, args.kErrorCodeUnknownArgument );
		args.verifyParcel( "arg1", 0, "foo" );
		args.verifyParcel( "arg2", 0, "foo" );
		args.verifyParcel( "arg2", 1, "bar" );
	}
	
	public void testLADsArrays_12x2() {
		
		String [] cmdl_args = { "-12", "foo", "bar" };
		LADsArrays args = new LADsArrays( cmdl_args );
		
		args.verifyOutcome( 1, 1, true, args.kErrorCodeMissingArgument );
		args.verifyParcel( "arg1", 0, "foo" );
		args.verifyParcel( "arg2", 0, "bar" );
	}
	
	public void testLADsArrays_12x3() {
		
		String [] cmdl_args = { "-12", "foo", "bar", "fish" };
		LADsArrays args = new LADsArrays( cmdl_args );
		
		args.verifyOutcome( 1, 2, false, 0 );
		args.verifyParcel( "arg1", 0, "foo" );
		args.verifyParcel( "arg2", 0, "bar" );
		args.verifyParcel( "arg2", 1, "fish" );
	}
	
	public void testLADsArrays_12x4() {
		
		String [] cmdl_args = { "-12", "foo", "bar", "fish", "cat" };
		LADsArrays args = new LADsArrays( cmdl_args );
		
		args.verifyOutcome( 1, 2, true, args.kErrorCodeUnknownArgument );
		args.verifyParcel( "arg1", 0, "foo" );
		args.verifyParcel( "arg2", 0, "bar" );
		args.verifyParcel( "arg2", 1, "fish" );
	}
	
	public void testLADsArrays_11x2() {
		
		String [] cmdl_args = { "-11", "foo", "bar" };
		LADsArrays args = new LADsArrays( cmdl_args );
		
		args.verifyOutcome( 2, 0, false, 0 );
		args.verifyParcel( "arg1", 0, "foo" );
		args.verifyParcel( "arg1", 1, "bar" );
	}
	
	public void testLADsArrays_22x4() {
		
		String [] cmdl_args = { "-22", "foo", "bar", "fish", "cat" };
		LADsArrays args = new LADsArrays( cmdl_args );
		
		args.verifyOutcome( 0, 4, false, 0 );
		args.verifyParcel( "arg2", 0, "foo" );
		args.verifyParcel( "arg2", 1, "bar" );
		args.verifyParcel( "arg2", 2, "fish" );
		args.verifyParcel( "arg2", 3, "cat" );
	}
	
	public void testLADsArrays_121x4() {
		
		String [] cmdl_args = { "-121", "foo", "bar", "fish", "cat" };
		LADsArrays args = new LADsArrays( cmdl_args );
		
		args.verifyOutcome( 2, 2, false, 0 );
		args.verifyParcel( "arg1", 0, "foo" );
		args.verifyParcel( "arg2", 0, "bar" );
		args.verifyParcel( "arg2", 1, "fish" );
		args.verifyParcel( "arg1", 1, "cat" );
	}
	
	// Test cases on LADsUnbounded (a subclass of LinearArgDesequencer)
	
	public void testLADsUnbounded_0x0() {
		
		// Zero parcels (zero expected, no error):
		
		String [] cmdl_args = { "-0" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 0, 0, 0, 0, false, 0 );
	}
	public void testLADsUnbounded_px0() {
		
		// Same thing, except treated as an array:
		
		String [] cmdl_args = { "-p" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 0, 0, 0, 0, false, 0 );
	}
	public void testLADsUnbounded_0x1() {
		
		// One parcel (zero expected, should cause an error)
		//   Arguments with parcelCount = 0 cannot receive
		//   any parcels, even when unbounded = true.
		
		String [] cmdl_args = { "-0", "foo" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 0, 0, 0, 0, true, args.kErrorCodeUnknownArgument );
	}
	public void testLADsUnbounded_px1() {
		
		// Same thing, except treated as an array:
		
		String [] cmdl_args = { "-p", "foo" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 0, 0, 0, 0, true, args.kErrorCodeUnknownArgument );
	}
	
	public void testLADsUnbounded_1x0() {
		
		// Zero parcels (one expected, should cause an error):
		
		String [] cmdl_args = { "-1" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 0, 0, 0, 0, true, args.kErrorCodeMissingArgument );
	}
	public void testLADsUnbounded_qx0() {
		
		// Same thing, except treated as an array:
		
		String [] cmdl_args = { "-q" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 0, 0, 0, 0, true, args.kErrorCodeMissingArgument );
	}
	public void testLADsUnbounded_1x1() {
		
		// One parcel (one expected, no error)
		
		String [] cmdl_args = { "-1", "foo" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 1, 0, 0, 0, false, 0 );
		args.verifyParcel( "arg1", 0, "foo" );
	}
	public void testLADsUnbounded_qx1() {
		
		// Same thing, except treated as an array:
		
		String [] cmdl_args = { "-q", "foo" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 0, 1, 0, 0, false, 0 );
		args.verifyParcel( "ary1", 0, "foo" );
	}
	public void testLADsUnbounded_1x2() {
		
		// Two parcels (one expected, no error)
		
		String [] cmdl_args = { "-1", "foo", "bar" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 1, 0, 0, 0, false, 0 );
		args.verifyParcel( "arg1", 0, "bar" );
	}
	public void testLADsUnbounded_qx2() {
		
		// Same thing, except treated as an array:
		
		String [] cmdl_args = { "-q", "foo", "bar" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 0, 2, 0, 0, false, 0 );
		args.verifyParcel( "ary1", 0, "foo" );
		args.verifyParcel( "ary1", 1, "bar" );
	}
	
	public void testLADsUnbounded_2x0() {
		
		// Zero parcels (two expected, should cause an error):
		
		String [] cmdl_args = { "-2" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 0, 0, 0, 0, true, args.kErrorCodeMissingArgument );
	}
	public void testLADsUnbounded_wx0() {
		
		// Same thing, except treated as an array:
		
		String [] cmdl_args = { "-w" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 0, 0, 0, 0, true, args.kErrorCodeMissingArgument );
	}
	public void testLADsUnbounded_2x1() {
		
		// One parcel (two expected, should cause an error):
		
		String [] cmdl_args = { "-2", "foo" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 0, 0, 1, 0, true, args.kErrorCodeMissingArgument );
		args.verifyParcel( "arg2", 0, "foo" );
	}
	public void testLADsUnbounded_wx1() {
		
		// Same thing, except treated as an array:
		
		String [] cmdl_args = { "-w", "foo" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 0, 0, 0, 1, true, args.kErrorCodeMissingArgument );
		args.verifyParcel( "ary2", 0, "foo" );
	}
	public void testLADsUnbounded_2x2() {
		
		// Two parcels (two expected, no error)
		
		String [] cmdl_args = { "-2", "foo", "bar" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 0, 0, 2, 0, false, 0 );
		args.verifyParcel( "arg2", 0, "foo" );
		args.verifyParcel( "arg2", 1, "bar" );
	}
	public void testLADsUnbounded_wx2() {
		
		// Same thing, except treated as an array:
		
		String [] cmdl_args = { "-w", "foo", "bar" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 0, 0, 0, 2, false, 0 );
		args.verifyParcel( "ary2", 0, "foo" );
		args.verifyParcel( "ary2", 1, "bar" );
	}
	public void testLADsUnbounded_2x3() {
		
		// Three parcels (two expected, no error)
		
		String [] cmdl_args = { "-2", "foo", "bar", "fish" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 0, 0, 2, 0, false, 0 );
		args.verifyParcel( "arg2", 0, "bar" );
		args.verifyParcel( "arg2", 1, "fish" );
	}
	public void testLADsUnbounded_wx3() {
		
		// Same thing, except treated as an array:
		
		String [] cmdl_args = { "-w", "foo", "bar", "fish" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 0, 0, 0, 3, false, 0 );
		args.verifyParcel( "ary2", 0, "foo" );
		args.verifyParcel( "ary2", 1, "bar" );
		args.verifyParcel( "ary2", 2, "fish" );
	}
	public void testLADsUnbounded_2x4() {
		
		// Four parcels (two expected, no error)
		
		String [] cmdl_args = { "-2", "foo", "bar", "fish", "cat" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 0, 0, 2, 0, false, 0 );
		args.verifyParcel( "arg2", 0, "fish" );
		args.verifyParcel( "arg2", 1, "cat" );
	}
	public void testLADsUnbounded_wx4() {
		
		// Same thing, except treated as an array:
		
		String [] cmdl_args = { "-w", "foo", "bar", "fish", "cat" };
		LADsUnbounded args = new LADsUnbounded( cmdl_args );
		args.verifyOutcome( 0, 0, 0, 0, 4, false, 0 );
		args.verifyParcel( "ary2", 0, "foo" );
		args.verifyParcel( "ary2", 1, "bar" );
		args.verifyParcel( "ary2", 2, "fish" );
		args.verifyParcel( "ary2", 3, "cat" );
	}
}
