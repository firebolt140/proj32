import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        String fileName;
        Scanner usr = new Scanner(System.in);
        BinTree bin = new BinTree();

        System.out.print("Enter file name: ");

//        fileName = usr.nextLine();

        usr.close();

        System.out.println();

        fileName = "num2.txt";

        File inputFile = new File(fileName);

        Scanner ifstream = new Scanner(inputFile);

        double cons = 0;
        double exp = 0;

        while(ifstream.hasNext()){
            Boolean check = true;
            String hold = ifstream.nextLine();

            hold = hold.substring(hold.indexOf('|')+2, hold.indexOf(" dx"));

            System.out.println("Before: " + hold);

            hold = hold.replace("^-", "#");
            hold = hold.replace("^ -", "#");
            hold = hold.replace("^  -", "#");

            hold = hold.replace("-", "+-");

            hold = hold.replace(" ", "");

            String[] stringArr = hold.split("\\+");

            for(String temp : stringArr) {
                cons = 0;
                exp = 0;
                if (temp != "") {
                    Term term = new Term();

                    if (!temp.contains("x")) {
                        cons = Double.parseDouble(temp);
                    } else {
                        String[] stringArr2 = temp.split("x");

                        cons = Double.parseDouble(stringArr2[0]);

                        if(stringArr2.length != 1) {
//                            System.out.println("Sup1");
//                            for (String temp2 : stringArr2) {
//                                System.out.println(temp2);
//                            }
//                            System.out.println("Sup2");

                            if (stringArr2[1].contains("^")) {
                                exp = Double.parseDouble(stringArr2[1].substring(stringArr2[1].indexOf("^") + 1));
                            } else {
                                exp = -Double.parseDouble(stringArr2[1].substring(stringArr2[1].indexOf("#") + 1));
                            }
                        }else{
                            exp = 1;
                        }

                    }

                    term.coef = (int) cons;
                    term.exp = (int) exp;

                    Node newNode = new Node(term);

                    Object[] objArr = {false, newNode};

                    objArr = bin.search(newNode);


                    if((boolean)objArr[0]) {
                        Node tempNode = new Node();
                        tempNode = (Node) objArr[1];

                        tempNode.getTerm().coef += cons;

                    }else {
                        bin.insert(newNode);
                    }
                }
            }
            String der = bin.print();

//            System.out.println(der);

            String[] derArr = der.split("\\|");

//            for(String temp: derArr){
//                System.out.println(temp);
//            }

            int[] expArr = new int[derArr.length];
            int[] coefArr = new int[derArr.length];

            int index = 0;
            int index2 = 0;

            int[] exponentSize = new int[derArr.length];

            for(String temp: derArr){
                String[] partDer = temp.split(" ");

                exponentSize[index2] = Integer.parseInt(partDer[1]);
                index2++;
            }

            String tempElement = "";
            int tempExp = 0;
            for(int i = 0; i < derArr.length; i++){
                for(int j = derArr.length - 1; j > i; j--){
                    if(exponentSize[i] < exponentSize[j]){
                        tempExp = exponentSize[j];
                        exponentSize[j] = exponentSize[i];
                        exponentSize[i] = tempExp;

                        tempElement = derArr[j];
                        derArr[j] = derArr[i];
                        derArr[i] = tempElement;
                    }
                }
            }

            System.out.print("After: ");

            boolean firstTerm = true;
            for(String temp: derArr){

                String[] partDer = temp.split(" ");

                if(partDer.length == 2) {
                    expArr[index] = Integer.parseInt(partDer[1]);
                    coefArr[index] = Integer.parseInt(partDer[0]);
                }

                if(expArr[index] != 0) {
                    if (coefArr[index] % (expArr[index] + 1) == 0) {
                        if(!firstTerm) {
                            if ((double)coefArr[index] / ((double)expArr[index] + 1) < 0) {
                                System.out.print(" - ");
                            }
                            else{
                                System.out.print(" + ");
                            }
                        }
                        if(Math.abs(coefArr[index]) / Math.abs(expArr[index] + 1) != 1){
                            System.out.print(Math.abs(coefArr[index]) / Math.abs(expArr[index] + 1));
                        }
                    }else{
                        int tempMod = coefArr[index] % (expArr[index] + 1);
                        int numerator;
                        int denominator;

                        numerator = coefArr[index] / tempMod;
                        denominator = (expArr[index] + 1) / tempMod;

                        if(!firstTerm) {
                            if ((double)coefArr[index] / ((double)expArr[index] + 1) < 0) {
                                System.out.print(" - ");
                            }
                            else{
                                System.out.print(" + ");
                            }
                        }

                        System.out.print("(" + Math.abs(numerator) + "/" + Math.abs(denominator) + ")");
                    }

                    if (expArr[index] + 1 == 1) {
                        System.out.print("x");
                    } else if (expArr[index] + 1 != 0) {
                        System.out.print("x^" + (expArr[index] + 1));
                    }
                }else{
                    if(!firstTerm) {
                        if ((double)coefArr[index] / ((double)expArr[index] + 1) < 0) {
                            System.out.print(" - ");
                        }
                        else{
                            System.out.print(" + ");
                        }
                    }
                    if(Math.abs(coefArr[index]) != 1){
                        System.out.print(Math.abs(coefArr[index]) + "x");
                    }
                    else{
                        System.out.print("x");
                    }
                }

                firstTerm = false;
                index++;
            }

            System.out.println(" + C");
        }

    }
}