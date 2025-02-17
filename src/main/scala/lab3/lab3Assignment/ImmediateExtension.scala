package lab3Assignment
import chisel3._
import chisel3.util._


class ImmediateExtension extends Module{
    val io = IO(new Bundle{
        val inst = Input(UInt(32.W))
        val immediate_Se = Output(SInt(32.W))
    })
    val op_code = io.inst(6,0)
    var immediate = Wire(SInt(32.W))
    immediate := 0.S
    var imm1 = 0.S
    var imm2 = 0.S
    var imm3 = 0.S
    var imm4 = 0.S

    switch(op_code){
        // I type
        is("b0010011".U){
          immediate := io.inst(31,20).asSInt
        }
    //    L type
        is("b0000011".U){
            immediate := io.inst(31,20).asSInt         
        } 
        //  S type
        is("b0100011".U){
            imm1 = io.inst(11,7).asSInt
            imm2 = io.inst(31,25).asSInt
            immediate := Cat(imm2,imm1).asSInt        
        }
        //  B type
        is("b1100011".U){
            imm1 = io.inst(7).asSInt
            imm2 = io.inst(11,8).asSInt
            imm3 = io.inst(30,25).asSInt
            imm4 = io.inst(31).asSInt
            immediate := Cat(imm4,imm1,imm3,imm2,0.U).asSInt         
        }
        // U type lui
        is("b0110111".U){
            immediate := io.inst(31,12).asSInt         
        } 
        // U type auipc
        is("b0010111".U){
            immediate := io.inst(31,12).asSInt         
        } 
        // UJ type JAL
        is("b1101111".U){
            imm1 = io.inst(19,12).asSInt // 10:1
            imm2 = io.inst(20).asSInt // 11
            imm3 = io.inst(30,21).asSInt // 19:12
            imm4 = io.inst(31).asSInt // 20
            immediate := Cat(imm4,imm1,imm2,imm3,0.S).asSInt         
        }                    
        // UJ type JALR
        is("b1100111".U){
            immediate := io.inst(31,20).asSInt         
        } 
        
    }
    


io.immediate_Se := immediate
    


}
