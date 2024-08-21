//import java.util.*;
//
//public class MotTable {
// private static final Map<String, Integer> MOT = new HashMap<>();
// private static final Set<String> LABEL = new HashSet<>();
// private static final Set<String> REGISTER = new HashSet<>();
//
// static {
//  // Initialize the MOT map
//  MOT.put("ADD", 20);
//  MOT.put("SUB", 21);
//  MOT.put("MUL", 22);
//  MOT.put("DIV", 23);
//  MOT.put("MOD", 24);
//  MOT.put("MOV", 25);
//  MOT.put("INT", 26);
//  MOT.put("END", null);
//  MOT.put("START", null);
//  MOT.put("NEG", 27);
//  MOT.put("AND", 28);
//  MOT.put("OR", 29);
//  MOT.put("XOR", 30);
//  MOT.put("NOR", 31);
//  MOT.put("JMP", 32);
//  MOT.put("JEQ", 33);
//  MOT.put("JNE", 34);
//  MOT.put("JLE", 35);
//  MOT.put("JLT", 36);
//  MOT.put("JGT", 37);
//  MOT.put("JGE", 38);
//  MOT.put("LOAD", 39);
//  MOT.put("STORE", 40);
//  MOT.put("LDI", 41);
//  MOT.put("STI", 42);
//  MOT.put("HALT", 43);
//  MOT.put("PUSH", 44);
//  MOT.put("POP", 45);
//  MOT.put("LOOP", 46);
//  MOT.put("DS", 47);
//  MOT.put("DC", 48);
//  MOT.put("DD", 4);
//
//  // Initialize the LABEL set
//  LABEL.add("DATA");
//  LABEL.add("CODE");
//  LABEL.add("PROC");
//  LABEL.add("ENDP");
//  LABEL.add("MACRO");
//  LABEL.add("ENDM");
//  LABEL.add("MODEL");
//  LABEL.add("STACK");
//  LABEL.add("EXTERN");
//  LABEL.add("GLOBAL");
//  LABEL.add("EQU");
//  LABEL.add("ENDC");
//  LABEL.add("ENDP");
//  LABEL.add("ORG");
//
//  // Initialize the REGISTER set
//  REGISTER.add("AReg");
//  REGISTER.add("BReg");
//  REGISTER.add("CReg");
//  REGISTER.add("DReg");
//
//  System.out.println("Done with initializing tables....");
// }
//
// // Accessor methods
// public static boolean containsMOT(String key) {
//  return MOT.containsKey(key);
// }
//
// public static boolean containsLabel(String label) {
//  return LABEL.contains(label);
// }
//
// public static boolean containsRegister(String reg) {
//  return REGISTER.contains(reg);
// }
//
// public static Integer getMOTValue(String key) {
//  return MOT.get(key);
// }
//}
import java.util.*;

public class MotTable {
 private static final Map<String, Integer> MOT = new HashMap<>();
 private static final Set<String> LABEL = new HashSet<>();
 private static final Set<String> REGISTER = new HashSet<>();
 private static final Map<String, Integer> LIT = new HashMap<>();
 private static int litCounter = 0;

 static {
  // Initialize the MOT map
  MOT.put("ADD", 20);
  MOT.put("SUB", 21);
  MOT.put("MUL", 22);
  MOT.put("DIV", 23);
  MOT.put("MOD", 24);
  MOT.put("MOV", 25);
  MOT.put("INT", 26);
  MOT.put("END", null);
  MOT.put("START", null);
  MOT.put("NEG", 27);
  MOT.put("AND", 28);
  MOT.put("OR", 29);
  MOT.put("XOR", 30);
  MOT.put("NOR", 31);
  MOT.put("JMP", 32);
  MOT.put("JEQ", 33);
  MOT.put("JNE", 34);
  MOT.put("JLE", 35);
  MOT.put("JLT", 36);
  MOT.put("JGT", 37);
  MOT.put("JGE", 38);
  MOT.put("LOAD", 39);
  MOT.put("STORE", 40);
  MOT.put("LDI", 41);
  MOT.put("STI", 42);
  MOT.put("HALT", 43);
  MOT.put("PUSH", 44);
  MOT.put("POP", 45);
  MOT.put("LOOP", 46);
  MOT.put("DS", 47);
  MOT.put("DC", 48);
  MOT.put("DD", 4);
  MOT.put("LTORG", null); // Added LTORG directive

  // Initialize the LABEL set
  LABEL.add("DATA");
  LABEL.add("CODE");
  LABEL.add("PROC");
  LABEL.add("ENDP");
  LABEL.add("MACRO");
  LABEL.add("ENDM");
  LABEL.add("MODEL");
  LABEL.add("STACK");
  LABEL.add("EXTERN");
  LABEL.add("GLOBAL");
  LABEL.add("EQU");
  LABEL.add("ENDC");
  LABEL.add("ENDP");
  LABEL.add("ORG");

  // Initialize the REGISTER set
  REGISTER.add("AReg");
  REGISTER.add("BReg");
  REGISTER.add("CReg");
  REGISTER.add("DReg");

  System.out.println("Done with initializing tables....");
 }

 // Accessor methods
 public static boolean containsMOT(String key) {
  return MOT.containsKey(key);
 }

 public static boolean containsLabel(String label) {
  return LABEL.contains(label);
 }

 public static boolean containsRegister(String reg) {
  return REGISTER.contains(reg);
 }

 public static Integer getMOTValue(String key) {
  return MOT.get(key);
 }

 // LTORG processing
 public static void processLTORG() {
  System.out.println("Processing LTORG...");
  for (Map.Entry<String, Integer> entry : LIT.entrySet()) {
   System.out.println("Literal: " + entry.getKey() + ", Address: " + entry.getValue());
  }
  LIT.clear(); // Clear the literal table after processing
 }

 public static void addLiteral(String literal) {
  if (!LIT.containsKey(literal)) {
   LIT.put(literal, litCounter++);
  }
 }
}
