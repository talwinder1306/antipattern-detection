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
 * f0 -> "enum"
 * f1 -> <IDENTIFIER>
 * f2 -> [ ImplementsList(false) ]
 * f3 -> EnumBody()
 */
public class EnumDeclaration implements Node {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public NodeToken f0;
   public NodeToken f1;
   public NodeOptional f2;
   public EnumBody f3;

   public EnumDeclaration(NodeToken n0, NodeToken n1, NodeOptional n2, EnumBody n3) {
      this.f0 = n0;
      this.f1 = n1;
      this.f2 = n2;
      this.f3 = n3;
   }

   public EnumDeclaration(NodeToken n0, NodeOptional n1, EnumBody n2) {
      this.f0 = new NodeToken("enum");
      this.f1 = n0;
      this.f2 = n1;
      this.f3 = n2;
   }

   public void accept(util.parser.java.v15.visitors.Visitor v) {
      v.visit(this);
   }
   public Object accept(util.parser.java.v15.visitors.ObjectVisitor v, Object argu) {
      return v.visit(this,argu);
   }
}

