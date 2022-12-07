/*******************************************************************************
 * Copyright (c) 2001-2014 Yann-Gaël Guéhéneuc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Yann-Gaël Guéhéneuc and others, see in file; API and its implementation
 ******************************************************************************/
package ptidej.sad.smelldetectioncaller;

public class SmellDetectionCaller {
	public static void main(final String[] args) {
		 //SmellDetectionHelper.analyseCodeLevelModelFromJavaClassFiles("../0 - SmellDetectionCaller/bin/",
			//	"SmellDetectionCaller Itself", "rsc/test5/");
		SmellDetectionHelper
		.analyseCodeLevelModelFromJavaClassFiles(
				"F:/Talwinder/Learning/Masters/Final Thesis/Code/AntiPatternTest/build/classes/java/",
				"Antipattern test", "rsc/testres/");
		 
		 
		 //SmellDetectionHelper
		 //.analyseCodeLevelModelFromJavaClassFiles("F:/Talwinder/Learning/Masters/Final Thesis/Code/deadcodetestproject/target/classes/com/eakonovalov/test/", 
		//		 "DeadCodeTestProject", "rsc/deadcodetest/");
		 
		 //SmellDetectionHelper
		 //.analyseCodeLevelModelFromJavaClassFiles("C:/Users/hp/Downloads/jforum2-code-r984-branches-v2.3.6_csrf/jforum2-code-r984-branches-v2.3.6_csrf/target/classes/net/", 
		//		 "JForum", "rsc/jforum/");
	}
}
