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
 * f0 -> "{"
 * f1 -> [ EnumConstant() ( "," EnumConstant() )* ]
 * f2 -> [ "," ]
 * f3 -> [ ";" ( ClassOrInterfaceBodyDeclaration(false) )* ]
 * f4 -> "}"
 */
public class EnumBody implements Node {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public NodeToken f0;
   public NodeOptional f1;
   public NodeOptional f2;
   public NodeOptional f3;
   public NodeToken f4;

   public EnumBody(NodeToken n0, NodeOptional n1, NodeOptional n2, NodeOptional n3, NodeToken n4) {
      this.f0 = n0;
      this.f1 = n1;
      this.f2 = n2;
      this.f3 = n3;
      this.f4 = n4;
   }

   public EnumBody(NodeOptional n0, NodeOptional n1, NodeOptional n2) {
      this.f0 = new NodeToken("{");
      this.f1 = n0;
      this.f2 = n1;
      this.f3 = n2;
      this.f4 = new NodeToken("}");
   }

   public void accept(util.parser.java.v15.visitors.Visitor v) {
      v.visit(this);
   }
   public Object accept(util.parser.java.v15.visitors.ObjectVisitor v, Object argu) {
      return v.visit(this,argu);
   }
}

