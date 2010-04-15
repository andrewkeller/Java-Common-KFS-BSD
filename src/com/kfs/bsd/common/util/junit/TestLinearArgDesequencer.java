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
	
	public void testNoSubclass() {
		
		LinearArgDesequencer args = new LinearArgDesequencer();
		
		assertFalse( "Initializing the LinearArgDesequencer with no arguments should not cause an error.", args.sequenceError() );
		assertFalse( "LinearArgDesequencer found the help flag when no arguments were supplied.", args.foundHelpFlag() );
	}
}
