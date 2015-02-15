/*******************************************************************************
 * Copyright 2014 Guy Davenport Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 *******************************************************************************/
package org.corehunter.tests;

import java.util.Set;
import java.util.TreeSet;

import org.corehunter.data.DistanceMatrixData;
import org.corehunter.simple.NamedDistanceMatrixData;

/**
 * @author Guy Davenport
 */
public class TestData
{
	public static final double PRECISION = 0.000000001 ; 
	
	public static final double[][]	        DISTANCES	= new double[][] {
	    new double[] { 1.0, 0.8, 0.6, 0.4, 0.2 },
	    new double[] { 0.8, 1.0, 0.8, 0.6, 0.4 },
	    new double[] { 0.6, 0.8, 1.0, 0.9, 0.6 },
	    new double[] { 0.4, 0.6, 0.9, 1.0, 0.8 },
	    new double[] { 0.2, 0.4, 0.6, 0.8, 1.0 }	      };

	public static final String[]	          NAMES	    = new String[] { "Name1",
	    "Name2", "Name3", "Name4", "Name5"	            };

	public static final Set<Integer>	      SET	      = new TreeSet<Integer>();

	static
	{
		SET.add(0);
		SET.add(1);
		SET.add(2);
		SET.add(3);
		SET.add(4);
	}

	public static final Set<Integer>	      SUBSET1	  = new TreeSet<Integer>();

	static
	{
		SUBSET1.add(2);
		SUBSET1.add(3);
	}

	public static final DistanceMatrixData	DATA	    = new NamedDistanceMatrixData(
	                                                        NAMES, DISTANCES);

	
	public static final String[] MARKER_NAMES = new String [] 
			{
				"mk1",
				"mk2",
				"mk3",
				"mk4",
				"mk5",
				"mk6",
				"mk7"
			};
	
	public static final int[][] ALLELE_SCORES = new int [][] 
			{
		new int [] {1,0,2,1,1,0,0},
		new int [] {2,0,2,0,1,2,1},
		new int [] {1,0,1,0,1,1,0},
		new int [] {1,0,1,1,1,2,2},
		new int [] {1,0,2,0,1,2,0}
	};
	
	public static final double[][][] ALLELES = new double [][][]
	{
	    new double[][] 
	    { 
	    		new double[] { 0.33, 0.33, 0.33 },
	    		new double[] { 0.5, 0.5 }, 
	    		new double[] { 0, 0.5, 0.5 },
	    		new double[] { 0.25, 0.25, 0.25, 0.25 },
	    		new double[] { 0.33, 0.33, 0.33 }, 
	    		new double[] { 0.0, 1.0 },
	    		new double[] { 1.0, 0.0 } 
	    },
	    new double[][] 
	    { 
	    		new double[] { 0.33, 0.33, 0.33 },
	    		new double[] { 0.5, 0.5 }, 
	    		new double[] { 0, 0.5, 0.5 },
	    		new double[] { 0.25, 0.25, 0.25, 0.25 },
	    		new double[] { 0.33, 0.33, 0.33 }, 
	    		new double[] { 0.0, 1.0 },
	    		new double[] { 1.0, 0.0 } 
	    },
	    new double[][] 
	    { 
	    		new double[] { 0.33, 0.33, 0.33 },
	    		new double[] { 0.5, 0.5 }, 
	    		new double[] { 0, 0.5, 0.5 },
	    		new double[] { 0.25, 0.25, 0.25, 0.25 },
	    		new double[] { 0.33, 0.33, 0.33 }, 
	    		new double[] { 0.0, 1.0 },
	    		new double[] { 1.0, 0.0 } 
	    },
	    new double[][] 
	    { 
	    		new double[] { 0.33, 0.33, 0.33 },
	    		new double[] { 0.5, 0.5 }, 
	    		new double[] { 0, 0.5, 0.5 },
	    		new double[] { 0.25, 0.25, 0.25, 0.25 },
	    		new double[] { 0.33, 0.33, 0.33 }, 
	    		new double[] { 0.0, 1.0 },
	    		new double[] { 1.0, 0.0 } 
	    },
	    new double[][] 
	    { 
	    		new double[] { 0.33, 0.33, 0.33 },
	    		new double[] { 0.5, 0.5 }, 
	    		new double[] { 0, 0.5, 0.5 },
	    		new double[] { 0.25, 0.25, 0.25, 0.25 },
	    		new double[] { 0.33, 0.33, 0.33 }, 
	    		new double[] { 0.0, 1.0 },
	    		new double[] { 1.0, 0.0 } 
	    }
	};

	public static final String[][] ALLELE_NAMES = new String [][] 
			{
		new String [] {"mk1-1","mk1-2","mk1-3"},
		new String [] {"mk2-1","mk2-2"},
		new String [] {"mk3-1","mk3-2","mk3-3"},
		new String [] {"mk4-1","mk4-2","mk4-3","mk4-3"},
		new String [] {"mk5-1","mk5-2","mk5-3"},
		new String [] {"mk6-1","mk6-2"},
		new String [] {"mk7-1","mk7-2"}
	};
	
	public static final double[][] CAVALLI_SFORZA_EDWARDS_DISTANCES	= new double[][] {
    new double[] { 0.0, 0.8, 0.6, 0.4, 0.2 },
    new double[] { 0.8, 0.0, 0.8, 0.6, 0.4 },
    new double[] { 0.6, 0.8, 0.0, 0.9, 0.6 },
    new double[] { 0.4, 0.6, 0.9, 0.0, 0.8 },
    new double[] { 0.2, 0.4, 0.6, 0.8, 0.0 }	      };
	
	public static final double[][] MODIFIED_ROGERS_DISTANCES	= new double[][] {
    new double[] { 0.0, 0.8, 0.6, 0.4, 0.2 },
    new double[] { 0.8, 0.0, 0.8, 0.6, 0.4 },
    new double[] { 0.6, 0.8, 0.0, 0.9, 0.6 },
    new double[] { 0.4, 0.6, 0.9, 0.0, 0.8 },
    new double[] { 0.2, 0.4, 0.6, 0.8, 0.0 }	      };
	
	public static final double COVERAGE_SUBSET1	= 1.0 ;

	public static final double HETROZYGOUS_LOCI_DIVERSITY_SUBSET1	= 1.0 ;
	
	public static final double NUMBER_EFFECTIVE_ALLELES_SUBSET1	= 1.0 ;

	public static final double PROPORTION_NON_INFORMATIVE_ALLELES_SUBSET1	= 1.0 ;

	public static final double SHANNONS_DIVERSITY_SUBSET1	= 1.0 ;

}