
import java.util.*;

class MotTable {
    public static void AllTables(){
        Map<String,Integer> MOT = new HashMap<String,Integer>();
        HashSet<String> LABEL = new HashSet<>();
       HashSet<String> REGISTER = new HashSet<>();


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

        REGISTER.add("AReg");
        REGISTER.add("BReg");
        REGISTER.add("CReg");
        REGISTER.add("DReg");
        System.out.println("Done with initializing tables....");

    }

}

