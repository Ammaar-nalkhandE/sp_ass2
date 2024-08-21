//
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.util.ArrayList;
//
//public class Main {
//    public static void main(String[] args) {
//        File file_location = new File("D:\\VU sem5\\SPL\\Ass2\\asm_code.txt");
//
//        try {
//            BufferedReader asm = new BufferedReader(new FileReader(file_location));
//            String st;
//            ArrayList<String> prog = new ArrayList<String>();
//            while ((st = asm.readLine()) != null) {
//                // Print the string
//                System.out.println(st);
//                prog.add(st);
//            }
//            for (String line : prog) {
//                System.out.println(line);
//                String[] splits = line.split(" ");
//                String instruction = splits[0];
//
//                // Check if the instruction is in the MOT table
//                if (MotTable.containsMOT(instruction)) {
//                    if (instruction.equals("LTORG")) {
//                        MotTable.processLTORG(); // Process LTORG
//                    } else {
//                        System.out.println("MOT value: " + MotTable.getMOTValue(instruction));
//                    }
//                } else if (MotTable.containsLabel(instruction)) {
//                    System.out.println("Found a LABEL: " + instruction);
//                } else if (MotTable.containsRegister(instruction)) {
//                    System.out.println("Found a REGISTER: " + instruction);
//                } else {
//                    // Assume it might be a literal
//                    MotTable.addLiteral(instruction);
//                    System.out.println("Added literal: " + instruction);
//                }
//            }
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//}



import java.io.*;
import java.util.*;

class AssemblerPass1 {
    // MOT structure
    static class MOTEntry {
        String mnemonic;
        String type;
        int opcode;

        MOTEntry(String mnemonic, String type, int opcode) {
            this.mnemonic = mnemonic;
            this.type = type;
            this.opcode = opcode;
        }
    }

    static Map<String, MOTEntry> MOT = new HashMap<>();
    static Map<String, Integer> symbolTable = new HashMap<>();
    static List<String> intermediateCode = new ArrayList<>();
    static List<String> literalTable = new ArrayList<>();
    static List<Integer> poolTable = new ArrayList<>();
    static int locationCounter = 0;
    static int litCounter = 0;

    public static void main(String[] args) {
        initializeMOT();
        File file_location = new File("D:\\VU sem5\\SPL\\Ass2\\asm_code.txt");

        try (BufferedReader asm = new BufferedReader(new FileReader(file_location))) {
            String line;

            while ((line = asm.readLine()) != null) {
                processLine(line.trim());
            }

            // At the end, process any remaining literals
            if (!literalTable.isEmpty()) {
                poolTable.add(litCounter);
                litCounter += literalTable.size();
            }

            generateOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void initializeMOT() {
        MOT.put("START", new MOTEntry("START", "AD", 1));
        MOT.put("END", new MOTEntry("END", "AD", 2));
        MOT.put("ORIGIN", new MOTEntry("ORIGIN", "AD", 3));
        MOT.put("EQU", new MOTEntry("EQU", "AD", 4));
        MOT.put("LTORG", new MOTEntry("LTORG", "AD", 5));

        MOT.put("MOV", new MOTEntry("MOV", "IS", 4));
        MOT.put("ADD", new MOTEntry("ADD", "IS", 1));
        MOT.put("SUB", new MOTEntry("SUB", "IS", 2));
        MOT.put("MUL", new MOTEntry("MUL", "IS", 3));

        MOT.put("DS", new MOTEntry("DS", "DL", 1));
        MOT.put("DC", new MOTEntry("DC", "DL", 2));
    }

    static void processLine(String line) {
        String[] tokens = line.split("\\s+");

        if (MOT.containsKey(tokens[0])) {
            MOTEntry entry = MOT.get(tokens[0]);
            if (entry.type.equals("AD")) {
                processAD(entry, tokens);
            } else if (entry.type.equals("IS")) {
                processIS(entry, tokens);
            } else if (entry.type.equals("DL")) {
                processDL(entry, tokens);
            }
        } else if (tokens[0].endsWith(":")) {
            String symbol = tokens[0].substring(0, tokens[0].length() - 1);
            symbolTable.put(symbol, locationCounter);
            processLine(String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length)));
        } else {
            System.err.println("Invalid instruction: " + tokens[0]);
        }
    }

    static void processAD(MOTEntry entry, String[] tokens) {
        if (entry.mnemonic.equals("START")) {
            locationCounter = Integer.parseInt(tokens[1]);
            intermediateCode.add("(AD, " + entry.opcode + ") (C, " + tokens[1] + ")");
        } else if (entry.mnemonic.equals("LTORG") || entry.mnemonic.equals("END")) {
            processLiterals();
        }
    }

    static void processIS(MOTEntry entry, String[] tokens) {
        intermediateCode.add("(IS, " + entry.opcode + ") " + tokens[1] + " " + tokens[2]);
        locationCounter++;
    }

    static void processDL(MOTEntry entry, String[] tokens) {
        intermediateCode.add("(DL, " + entry.opcode + ") (C, " + tokens[1] + ")");
        locationCounter += Integer.parseInt(tokens[1]);
    }

    static void processLiterals() {
        for (String literal : literalTable) {
            intermediateCode.add("(L, " + litCounter + ")");
            litCounter++;
        }
        poolTable.add(litCounter);
        literalTable.clear();
    }

    static void generateOutput() {
        System.out.println("Intermediate Code:");
        intermediateCode.forEach(System.out::println);

        System.out.println("\nSymbol Table:");
        symbolTable.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println("\nLiteral Table:");
        for (int i = 0; i < literalTable.size(); i++) {
            System.out.println("L" + i + " -> " + literalTable.get(i));
        }

        System.out.println("\nPool Table:");
        poolTable.forEach(System.out::println);
    }
}
