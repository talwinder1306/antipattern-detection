/*******************************************************************************
 * Copyright (c) 2014 Yann-GaÃƒÂ«l GuÃƒÂ©hÃƒÂ©neuc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Yann-GaÃƒÂ«l GuÃƒÂ©hÃƒÂ©neuc and others, see in file; API and its implementation
 ******************************************************************************/
//
// Generated by JTB 1.2.2
//

package util.parser.java.v15.nodes;

/**
 * Grammar production:
 * f0 -> MultiplicativeExpression()
 * f1 -> ( ( "+" | "-" ) MultiplicativeExpression() )*
 */
public class AdditiveExpression implements Node {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public MultiplicativeExpression f0;
   public NodeListOptional f1;

   public AdditiveExpression(MultiplicativeExpression n0, NodeListOptional n1) {
      this.f0 = n0;
      this.f1 = n1;
   }

   public void accept(util.parser.java.v15.visitors.Visitor v) {
      v.visit(this);
   }
   public Object accept(util.parser.java.v15.visitors.ObjectVisitor v, Object argu) {
      return v.visit(this,argu);
   }
}
