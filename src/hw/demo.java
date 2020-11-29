package hw;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class demo {
    public static void main(String[] args) {
//        String s = "-2x^3 -3x - 1";
//        String s2= s.replaceAll("[^+-]","");
//        if(s.charAt(0)==('-')||s.charAt(0)==('+'))s= s.substring(1,s.length());
//        else s2='+'+s2;
//        char[] c1 = s2.toCharArray();
//        int [] i1 = new int[c1.length];
//        for(int i =0;i<c1.length;i++){
//            if(c1[i]=='-') i1[i]=-1;
//            else i1[i]=1;
//        }
//        System.out.println(s2);
//        System.out.println(s+"***");
//        s =s.replace(" ","");
//        String[] temp = s.split("-|\\+");
//        System.out.println("break" +Arrays.toString(temp));
//        Map<Integer, Double> tempMap = new TreeMap<Integer, Double>();
//        int ii=0;
//        for(String t : temp){
//           if(t.contains("x^")){
//               System.out.println(t);
//               String [] temp1 =t.split("x\\^");
//               System.out.println(Arrays.toString(temp1));
//               tempMap.put(Integer.parseInt(temp1[1]),Double.parseDouble(temp1[0])*i1[ii]);
//           }
//           else if(t.contains("x")){
//                tempMap.put(1,Double.parseDouble(t.substring(0,t.indexOf('x')))*i1[ii]);
//           }
//           else tempMap.put(0,Double.parseDouble(t)*i1[ii]);
//           ii++;
//        }
//        for(Map.Entry<Integer,Double> i : tempMap.entrySet()){
//            System.out.println(i.getKey()+" : "+i.getValue());
//        }
//        String temp2 = "";
//        int currentEntryKey = ((TreeMap<Integer, Double>) tempMap).lastEntry().getKey();
//        int treesetSize = tempMap.size();
//        while(treesetSize>0){
//            if(treesetSize== tempMap.size()){
//                switch(currentEntryKey){
//                    case 1: temp2+=tempMap.get(currentEntryKey)+"x";break;
//                    case 0:  temp2+=tempMap.get(currentEntryKey);break;
//                    default:temp2+=tempMap.get(currentEntryKey)+"x^"+ currentEntryKey;break;
//                }
//            }
//            else{
//                switch(currentEntryKey){
//                    case 1: temp2+=tempMap.get(currentEntryKey)+"x";break;
//                    case 0:  temp2+=tempMap.get(currentEntryKey);break;
//                    default:temp2+=tempMap.get(currentEntryKey)+"x^"+ currentEntryKey;break;
//                }
//                System.out.println(temp2);
//            }
//            if(treesetSize>1)currentEntryKey=((TreeMap<Integer, Double>) tempMap).lowerEntry(currentEntryKey).getKey();
//            if(treesetSize>1) {
//                if(tempMap.get(currentEntryKey)>=0) temp2+="+";
//
//            }
//            treesetSize--;
//        }
//        SparsePolynomial sp1 = new SparsePolynomial("");
//        System.out.println(sp1.toString());

        DensePolynomial dp1 = new DensePolynomial("");
        System.out.println(dp1.toString());

    }

}
