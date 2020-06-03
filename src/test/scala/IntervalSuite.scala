import ai.economicdatasciences.intervals.Interval
import org.scalatest.FunSuite

class IntervalSuite extends FunSuite {
    test("interval addition should work according to interval arithmetic") {
        val interval1 = new Interval(0.1, 0.2)
        val interval2 = new Interval(1.0, 3.0)
        val sum = interval1 + interval2
        assert(sum.a == 1.1)
        assert(sum.b == 3.2)
    }

    test("interval subtraction should work according to interval arithmetic"){
        val interval1 = new Interval(0.1, 0.2)
        val interval2 = new Interval(1.0, 3.0)
        val sub = interval1 - interval2
        assert(sub.a == -2.9)
        assert(sub.b == -0.8)
    }

    test("inclusion should return turn if a Double falls within the interval bounds"){
        val interval = new Interval(-1.0, 1.0)
        assert(interval.contains(0.0))
        assert(!interval.contains(2.0))
        assert(!interval.contains(-2.0))
    }

    test("interval multiplication should work according to interval arithmetric"){
        val interval1 = new Interval(-2.0, 4.0)
        val interval2 = new Interval(-3.0, -1.0)
        val mul = interval1 * interval2
        assert(mul.a == -12.0)
        assert(mul.b == 6.0)
    }

    test("interval division should work according to interval arithmeetic"){
        val interval1 = new Interval(-2.0, 4.0)
        val interval2 = new Interval(-3.0, -1.0)
        intercept[ArithmeticException]{
            interval2 / interval1
        }
        val div = interval1/interval2
        assert(div.a == -4.0)
        assert(div.b == 2.0)
    }

    
}
