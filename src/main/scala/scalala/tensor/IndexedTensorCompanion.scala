/*
 * Distributed as part of Scalala, a linear algebra library.
 *
 * Copyright (C) 2008- Daniel Ramage
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110 USA
 */
package scalala;
package tensor;

import domain._;
import generic._;

import scalala.operators._;
import scalala.generic.collection.{CanMapValues,CanMapKeyValuePairs,CanJoinValues};

/**
 * Base companion methods for tensors that have a known key type,
 * such as Vectors (Int) and Matrices (Int,Int).  This class is
 * almost identical to TensorCompanion but Bound only specifies
 * a value type, not a key type.  There might be a cleverer way
 * to do this with type constructors and inheriting from TensorCompanion.
 *
 * @author dramage
 */
trait IndexedTensorCompanion[K,Bound[V]<:Tensor[K,V]] {
  implicit def canMapValues[V,RV](implicit c : CanMapValues[Tensor[K,V],V,RV,Tensor[K,RV]]) =
    c.asInstanceOf[CanMapValues[Bound[V],V,RV,Bound[RV]]];

  implicit def canMapKeyValuePairs[V,RV](implicit c : CanMapKeyValuePairs[Tensor[K,V],(Int,Int),V,RV,Tensor[K,RV]]) =
    c.asInstanceOf[CanMapKeyValuePairs[Bound[V],K,V,RV,Bound[RV]]];

  implicit def canJoinValues[V1,V2,RV](implicit c : CanJoinValues[Tensor[K,V1],Tensor[(Int,Int),V2],V1,V2,RV,Tensor[K,RV]]) =
    c.asInstanceOf[CanJoinValues[Bound[V1],Tensor[(Int,Int),V2],V1,V2,RV,Tensor[K,RV]]];

  implicit def binaryOp[V1,V2,Op<:OpType,RV](implicit c : BinaryOp[Tensor[K,V1],V2,Op,Tensor[K,RV]]) =
    c.asInstanceOf[BinaryOp[Bound[V1],V2,Op,Bound[RV]]];
}

