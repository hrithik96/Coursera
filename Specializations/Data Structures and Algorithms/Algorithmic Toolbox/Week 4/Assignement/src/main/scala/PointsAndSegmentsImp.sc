def countSegments(starts: List[Int], ends: List[Int], points: List[Int]): String = {

  def mergePoints(left: Array[Int], right: Array[Int]): Array[Int] = {
    (left zip right) map {case (l, r) => l + r}
  }

  def getPoints(start: Int, end: Int, pts: List[Int], indx: Int, counts: Array[Int]): Array[Int] =
    pts match {
      case Nil => counts
      case p :: px if start <= p && p <= end => {
        counts(indx) += 1
        getPoints(start, end, px, indx + 1, counts)
      }
      case p :: px => getPoints(start, end, px, indx + 1, counts)
    }

  def countSegmentsIter(xs: List[Int], xe: List[Int], output: Array[Int]): Array[Int] =
    (xs, xe) match {
      case (Nil, Nil) => output
      case (x :: xs1, y :: ys) => {
        val mid = xs.length / 2
        val (lstarts, rstarts) = xs1 splitAt mid
        val (lends, rends) = ys splitAt mid
        val lCount = countSegmentsIter(lstarts, lends, output)
        val rCount = countSegmentsIter(rstarts, rends, output)
        val pts = getPoints(x, y, points, 0, new Array(points.length))
        mergePoints(mergePoints(lCount, rCount), pts)
      }
    }
  countSegmentsIter(starts, ends, new Array(points.length)).mkString(" ")
}

val s1 = List(0, 7)
val e1 = List(5, 10)
val p1 = List(1, 6, 11)

countSegments(s1, e1, p1)

val s2 = List(0, -3, 7)
val e2 = List(5, 2, 10)
val p2 = List(1, 6)

countSegments(s2, e2, p2)

val s3 = List(-10)
val e3 = List(10)
val p3 = List(-100, 100, 0)

countSegments(s3, e3, p3)

val s4 = List(-4, -2, -4, -4)
val e4 = List(-2, 4, -2, 2)
val p4 = List(14, -2, 2, 30, 9)

countSegments(s4, e4, p4)

val s5 = List(4, 1)
val e5 = List(0, 3)
val p5 = List(2)

countSegments(s5, e5, p5)