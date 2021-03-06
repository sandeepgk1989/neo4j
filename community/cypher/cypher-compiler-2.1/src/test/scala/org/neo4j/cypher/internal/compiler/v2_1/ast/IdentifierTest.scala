/**
 * Copyright (c) 2002-2014 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.cypher.internal.compiler.v2_1.ast

import org.neo4j.cypher.internal.compiler.v2_1._
import symbols._
import org.junit.Assert._
import org.junit.Test
import org.scalatest.Assertions

class IdentifierTest extends Assertions {

  @Test
  def shouldDefineIdentifierDuringSemanticCheckWhenUndefined() {
    val position = DummyPosition(0)
    val identifier = Identifier("x")(position)

    val result = identifier.semanticCheck(Expression.SemanticContext.Simple)(SemanticState.clean)
    assertEquals(1, result.errors.size)
    assertEquals(position, result.errors.head.position)
    assertTrue(result.state.symbol("x").isDefined)
    assertEquals(CTAny.covariant, result.state.symbolTypes("x"))
  }
}
