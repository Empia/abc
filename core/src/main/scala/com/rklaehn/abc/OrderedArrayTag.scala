package com.rklaehn.abc

import spire.algebra.Order

import scala.reflect.ClassTag
import scala.util.hashing.Hashing
import scala.{specialized => sp}
import spire.implicits._

trait OrderedArrayTag[@sp T] extends ArrayTag[T] {

  implicit def tClassTag: ClassTag[T]

  implicit def tOrder: Order[T]

  def binarySearch(as: Array[T], a0: Int, a1: Int, a: T): Int

  def compare(a: Array[T], ai: Int, b: Array[T], bi: Int): Int
}

object OrderedArrayTag {

  implicit def generic[T](implicit o: Order[T], c: ClassTag[T], h: Hashing[T]): OrderedArrayTag[T] =
    new GenericOrderedArrayTag[T]()(o, c, h)

  private[abc] class GenericOrderedArrayTag[@sp T](implicit val tOrder: spire.algebra.Order[T], val tClassTag: ClassTag[T], val tHashing: Hashing[T]) extends OrderedArrayTag[T] {

    override def compare(a: Array[T], ai: Int, b: Array[T], bi: Int): Int = tOrder.compare(a(ai), b(bi))

    override def singleton(e: T): Array[T] = {
      val r = tClassTag.newArray(1)
      r(0) = e
      r
    }

    override def binarySearch(as: Array[T], a0: Int, a1: Int, a: T) = SetUtils.binarySearch(as, a, a0, a1)

    override def resize(a: Array[T], n: Int) = a.resizeInPlace(n)(tClassTag)

    override def newArray(n: Int): Array[T] = tClassTag.newArray(n)

    override def eqv(a: Array[T], b: Array[T]): Boolean = a === b

    override def hash(a: Array[T]): Int = ArrayHashing.arrayHashCode(a)

    def tEq = tOrder

    val empty = Array.empty[T]
  }

  implicit val byteOrderedArrayTag: OrderedArrayTag[Byte] = new OrderedArrayTag[Byte] {
    val tClassTag = implicitly[ClassTag[Byte]]
    val tOrder = implicitly[Order[Byte]]
    val empty = Array.empty[Byte]
    def compare(a: Array[Byte], ai: Int, b: Array[Byte], bi: Int) = java.lang.Byte.compare(a(ai), b(bi))
    def binarySearch(as: Array[Byte], a0: Int, a1: Int, a: Byte) = java.util.Arrays.binarySearch(as, a0, a1, a)
    def resize(a: Array[Byte], n: Int) = if(n == a.length) a else java.util.Arrays.copyOf(a, n)
    def singleton(e: Byte) = {
      val r = new Array[Byte](1)
      r(0) = e
      r
    }
    def newArray(n: Int) = new Array[Byte](n)
    def hash(a: Array[Byte]) = java.util.Arrays.hashCode(a)
    def eqv(a: Array[Byte], b: Array[Byte]) = java.util.Arrays.equals(a, b)
  }

  implicit val shortOrderedArrayTag: OrderedArrayTag[Short] = new OrderedArrayTag[Short] {
    val tClassTag = implicitly[ClassTag[Short]]
    val tOrder = implicitly[Order[Short]]
    val empty = Array.empty[Short]
    def compare(a: Array[Short], ai: Int, b: Array[Short], bi: Int) = java.lang.Short.compare(a(ai), b(bi))
    def binarySearch(as: Array[Short], a0: Int, a1: Int, a: Short) = java.util.Arrays.binarySearch(as, a0, a1, a)
    def resize(a: Array[Short], n: Int) = if(n == a.length) a else java.util.Arrays.copyOf(a, n)
    def singleton(e: Short) = {
      val r = new Array[Short](1)
      r(0) = e
      r
    }
    def newArray(n: Int) = new Array[Short](n)
    def hash(a: Array[Short]) = java.util.Arrays.hashCode(a)
    def eqv(a: Array[Short], b: Array[Short]) = java.util.Arrays.equals(a, b)
  }

  implicit val intOrderedArrayTag: OrderedArrayTag[Int] = new OrderedArrayTag[Int] {
    val tClassTag = implicitly[ClassTag[Int]]
    val tOrder = implicitly[Order[Int]]
    val empty = Array.empty[Int]
    def compare(a: Array[Int], ai: Int, b: Array[Int], bi: Int) = java.lang.Integer.compare(a(ai), b(bi))
    def binarySearch(as: Array[Int], a0: Int, a1: Int, a: Int) = java.util.Arrays.binarySearch(as, a0, a1, a)
    def resize(a: Array[Int], n: Int) = if(n == a.length) a else java.util.Arrays.copyOf(a, n)
    def singleton(e: Int) = {
      val r = new Array[Int](1)
      r(0) = e
      r
    }
    def newArray(n: Int) = new Array[Int](n)
    def hash(a: Array[Int]) = java.util.Arrays.hashCode(a)
    def eqv(a: Array[Int], b: Array[Int]) = java.util.Arrays.equals(a, b)
  }

  implicit val longOrderedArrayTag: OrderedArrayTag[Long] = new OrderedArrayTag[Long] {
    val tClassTag = implicitly[ClassTag[Long]]
    val tOrder = implicitly[Order[Long]]
    val empty = Array.empty[Long]
    def compare(a: Array[Long], ai: Int, b: Array[Long], bi: Int) = java.lang.Long.compare(a(ai), b(bi))
    def binarySearch(as: Array[Long], a0: Int, a1: Int, a: Long) = java.util.Arrays.binarySearch(as, a0, a1, a)
    def resize(a: Array[Long], n: Int) = if(n == a.length) a else java.util.Arrays.copyOf(a, n)
    def singleton(e: Long) = {
      val r = new Array[Long](1)
      r(0) = e
      r
    }
    def newArray(n: Int) = new Array[Long](n)
    def hash(a: Array[Long]) = java.util.Arrays.hashCode(a)
    def eqv(a: Array[Long], b: Array[Long]) = java.util.Arrays.equals(a, b)
  }

  implicit val floatOrderedArrayTag: OrderedArrayTag[Float] = new OrderedArrayTag[Float] {
    val tClassTag = implicitly[ClassTag[Float]]
    val tOrder = implicitly[Order[Float]]
    val empty = Array.empty[Float]
    def compare(a: Array[Float], ai: Int, b: Array[Float], bi: Int) = java.lang.Double.compare(a(ai), b(bi))
    def binarySearch(as: Array[Float], a0: Int, a1: Int, a: Float) = java.util.Arrays.binarySearch(as, a0, a1, a)
    def resize(a: Array[Float], n: Int) = if(n == a.length) a else java.util.Arrays.copyOf(a, n)
    def singleton(e: Float) = {
      val r = new Array[Float](1)
      r(0) = e
      r
    }
    def newArray(n: Int) = new Array[Float](n)
    def hash(a: Array[Float]) = java.util.Arrays.hashCode(a)
    def eqv(a: Array[Float], b: Array[Float]) = java.util.Arrays.equals(a, b)
  }

  implicit val doubleOrderedArrayTag: OrderedArrayTag[Double] = new OrderedArrayTag[Double] {
    val tClassTag = implicitly[ClassTag[Double]]
    val tOrder = implicitly[Order[Double]]
    val empty = Array.empty[Double]
    def compare(a: Array[Double], ai: Int, b: Array[Double], bi: Int) = java.lang.Double.compare(a(ai), b(bi))
    def binarySearch(as: Array[Double], a0: Int, a1: Int, a: Double) = java.util.Arrays.binarySearch(as, a0, a1, a)
    def resize(a: Array[Double], n: Int) = if(n == a.length) a else java.util.Arrays.copyOf(a, n)
    def singleton(e: Double) = {
      val r = new Array[Double](1)
      r(0) = e
      r
    }
    def newArray(n: Int) = new Array[Double](n)
    def hash(a: Array[Double]) = java.util.Arrays.hashCode(a)
    def eqv(a: Array[Double], b: Array[Double]) = java.util.Arrays.equals(a, b)
  }

  implicit val charOrderedArrayTag: OrderedArrayTag[Char] = new OrderedArrayTag[Char] {
    val tClassTag = implicitly[ClassTag[Char]]
    val tOrder = implicitly[Order[Char]]
    val empty = Array.empty[Char]
    def compare(a: Array[Char], ai: Int, b: Array[Char], bi: Int) = java.lang.Long.compare(a(ai), b(bi))
    def binarySearch(as: Array[Char], a0: Int, a1: Int, a: Char) = java.util.Arrays.binarySearch(as, a0, a1, a)
    def resize(a: Array[Char], n: Int) = if(n == a.length) a else java.util.Arrays.copyOf(a, n)
    def singleton(e: Char) = {
      val r = new Array[Char](1)
      r(0) = e
      r
    }
    def newArray(n: Int) = new Array[Char](n)
    def hash(a: Array[Char]) = java.util.Arrays.hashCode(a)
    def eqv(a: Array[Char], b: Array[Char]) = java.util.Arrays.equals(a, b)
  }
}