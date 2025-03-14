package FiveStage
import chisel3._
import chisel3.experimental.MultiIOModule

class InstructionFetch extends MultiIOModule {

  // Don't touch
  val testHarness = IO(
    new Bundle {
      val IMEMsetup = Input(new IMEMsetupSignals)
      val PC = Output(UInt())
    }
  )


  /**
    * TODO: Add signals for handling events such as jumps
    */
  val io = IO(
    new Bundle {
      val PC = Output(UInt())
    })

  val IMEM = Module(new IMEM)
  val PC   = RegInit(UInt(32.W), 0.U)


  /**
    * Setup. You should not change this code
    */
  IMEM.testHarness.setupSignals := testHarness.IMEMsetup
  testHarness.PC := IMEM.testHarness.requestedAddress


  /**
    * TODO: Your code here.
    * 
    * You should expand on or rewrite the code below.
    */
  io.PC := PC
  IMEM.io.instructionAddress := PC

  // PC := PC + 4.U

  val instruction = Wire(new Instruction)
  instruction := IMEM.io.instruction.asTypeOf(new Instruction)




  /**
    * Setup. You should not change this code
    */
  when(testHarness.IMEMsetup.setup) {
    PC := 0.U
    // TODO: You must set the instruction to Instruction.NOP here.
    // throw new Exception("Just making sure you're seeing the line above")
  }
}
