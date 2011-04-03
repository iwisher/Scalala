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

import scalar.Scalar;

import domain.IterableDomain;
import generic.collection._;

import mutable.TensorBuilder;

/**
 * Proxy for a Tensor instance.
 *
 * @author dramage
 */
trait TensorProxyLike
[@specialized(Int, Long) K,
 @specialized(Int, Long, Float, Double, Boolean) V,
 +D<:IterableDomain[K],
 Inner <: Tensor[K,V],
 +This<:Tensor[K,V]]
extends TensorLike[K,V,D,This] {
  def inner : Inner;
  
  override def repr = inner.asInstanceOf[This];

  override def domain = inner.domain.asInstanceOf[D];
  
  override val scalar = inner.scalar;

  override def newBuilder[NK,NV:Scalar](domain : IterableDomain[NK])
  : TensorBuilder[NK,NV,Tensor[NK,NV]] =
    inner.newBuilder[NK,NV](domain);

  override def foreachPair[U](fn: (K,V) => U) : Unit =
    inner.foreachPair(fn);

  override def foreachValue[U](fn : (V=>U)) =
    inner.foreachValue(fn);

  override def foreachNonZeroPair[U](fn : ((K,V)=>U)) : Boolean =
    inner.foreachNonZeroPair(fn);

  override def foreachNonZeroValue[U](fn : (V=>U)) =
    inner.foreachNonZeroValue(fn);
    
  override def forall(fn : (K,V) => Boolean) : Boolean =
    inner.forall(fn);

  override def forallNonZero(fn : (K,V) => Boolean) : Boolean =
    inner.forallNonZero(fn);

  override def forall(fn : V => Boolean) : Boolean =
    inner.forall(fn);
 
  override def forallNonZero(fn : V => Boolean) : Boolean =
    inner.forallNonZero(fn);

  override def keysIterator : Iterator[K] =
    inner.keysIterator;

  override def pairsIterator : Iterator[(K,V)] =
    inner.pairsIterator;

  override def valuesIterator : Iterator[V] =
    inner.valuesIterator;

  override def find(p : V => Boolean) : Option[K] =
    inner.find(p);

  override def findAll(p : V => Boolean) : Iterable[K] =
    inner.findAll(p);

  override def apply(key : K) : V =
    inner(key);

  override def argsort(implicit cm : Manifest[K], ord : Ordering[V]) : Array[K] =
    inner.argsort;

  override def argmax : K =
    inner.argmax;

  override def argmin : K =
    inner.argmin;

  override def max : V =
    inner.max;

  override def min : V =
    inner.min;

  override def sum : V =
    inner.sum;

  override def asOrdering(implicit ord : Ordering[V]) =
    inner.asOrdering;

  override def asMap =
    inner.asMap;

  override def toMap =
    inner.toMap;

  override def hashCode() =
    inner.hashCode;
}

/**
 * K proxy for a generic Tensor.
 *
 * @author dramage
 */
trait TensorProxy[@specialized(Int,Long) K, @specialized(Int,Long,Float,Double,Boolean) V, Inner <: Tensor[K,V]]
extends Tensor[K,V] with TensorProxyLike[K,V,IterableDomain[K],Inner,TensorProxy[K,V,Inner]];

