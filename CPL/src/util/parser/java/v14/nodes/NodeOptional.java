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

package util.parser.java.v14.nodes;

/**
 * Represents an grammar optional node, e.g. ( A )? or [ A ]
 */
public class NodeOptional implements Node {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public NodeOptional() {
      this.node = null;
   }

   public NodeOptional(Node n) {
      addNode(n);
   }

   public void addNode(Node n)  {
      if ( this.node != null)                // Oh oh!
         throw new Error("Attempt to set optional node twice");

      this.node = n;
   }
   public void accept(util.parser.java.v14.visitors.Visitor v) {
      v.visit(this);
   }
   public Object accept(util.parser.java.v14.visitors.ObjectVisitor v, Object argu) {
      return v.visit(this,argu);
   }
   public boolean present()   { return this.node != null; }

   public Node node;
}

