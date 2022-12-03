/*******************************************************************************
 * Copyright (c) 2014 Yann-GaÃƒÆ’Ã‚Â«l GuÃƒÆ’Ã‚Â©hÃƒÆ’Ã‚Â©neuc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Yann-GaÃƒÆ’Ã‚Â«l GuÃƒÆ’Ã‚Â©hÃƒÆ’Ã‚Â©neuc and others, see in file; API and its implementation
 ******************************************************************************/
//
// Generated by JTB 1.2.2
//

package util.parser.java.v14.nodes;

/**
 * Grammar production:
 * f0 -> [ PackageDeclaration() ]
 * f1 -> ( ImportDeclaration() )*
 * f2 -> ( TypeDeclaration() )*
 * f3 -> <EOF>
 */
public class CompilationUnit implements Node {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public NodeOptional f0;
   public NodeListOptional f1;
   public NodeListOptional f2;
   public NodeToken f3;

   public CompilationUnit(NodeOptional n0, NodeListOptional n1, NodeListOptional n2, NodeToken n3) {
      this.f0 = n0;
      this.f1 = n1;
      this.f2 = n2;
      this.f3 = n3;
   }

   public CompilationUnit(NodeOptional n0, NodeListOptional n1, NodeListOptional n2) {
      this.f0 = n0;
      this.f1 = n1;
      this.f2 = n2;
      this.f3 = new NodeToken("");
   }

   public void accept(util.parser.java.v14.visitors.Visitor v) {
      v.visit(this);
   }
   public Object accept(util.parser.java.v14.visitors.ObjectVisitor v, Object argu) {
      return v.visit(this,argu);
   }
}

