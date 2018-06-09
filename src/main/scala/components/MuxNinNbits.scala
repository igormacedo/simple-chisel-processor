package components

import chisel3._
import chisel3.Driver
import math.{log10, floor, ceil, pow}

class MuxNinNbits(val n_in:Int, val n_bits:Int) extends Module {
    val log2 = (x: Int) => log10( x.toDouble )/log10( 2.0 )
    
    // Finds the min number of bits to represent a number
    val min_bits = (x: Int) => floor( log2( x - 1) + 1).toInt
    // Find find min power of two greater than number
    val min_power = (x: Int) => pow(2,ceil( log2( x ))).toInt

    val io = IO(new Bundle {
        val inputVec = Input(Vec(min_power(n_in), UInt(n_bits.W)))
        val select = Input(UInt(min_bits(n_in).W))
        val output = Output(UInt(n_bits.W))
    })

    io.output := io.inputVec(io.select)
}

object MuxNinNbits {
    def main(args: Array[String]): Unit = {
        chisel3.Driver.execute(args, () => new MuxNinNbits(10,3))
    }
}