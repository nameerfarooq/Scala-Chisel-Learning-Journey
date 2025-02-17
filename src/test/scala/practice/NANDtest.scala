package practice
import org.scalatest._
import chiseltest._
import chisel3._

class NANDtest extends FreeSpec with ChiselScalatestTester{
    "NAND Gate Test1" in {
        test(new NAND()){ c=>
        c.io.a.poke(0.U)
        c.io.b.poke(0.U)
        c.clock.step(1)
        c.io.output.expect(1.U)
        }
    }
    "NAND Gate Test2" in {
        test(new NAND()){ c=>
        c.io.a.poke(0.U)
        c.io.b.poke(1.U)
        c.clock.step(1)
        c.io.output.expect(1.U)
        }
    }
    "NAND Gate Test3" in {
        test(new NAND()){ c=>
        c.io.a.poke(1.U)
        c.io.b.poke(0.U)
        c.clock.step(1)
        c.io.output.expect(1.U)
        }
    }
    "NAND Gate Test4" in {
        test(new NAND()){ c=>
        c.io.a.poke(1.U)
        c.io.b.poke(1.U)
        c.clock.step(1)
        c.io.output.expect(0.U)
        }
    }
}