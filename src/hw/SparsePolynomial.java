package hw;

import java.util.Map;
import java.util.TreeMap;

public class SparsePolynomial implements Polynomial {
    private final TreeMap<Integer, Integer> powerCoeffMap;

    public SparsePolynomial(String s) {
        s = s.replace(" ", "");
        String[] temp = s.split("\\+");
        this.powerCoeffMap = new TreeMap<Integer, Integer>();
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].contains("x^"))
                powerCoeffMap.put(Integer.parseInt(temp[i].split("x\\^")[1]), Integer.parseInt(temp[i].split("x\\^")[0]));
            else if (temp[i].contains("x"))
                powerCoeffMap.put(1, Integer.parseInt(temp[i].substring(0, temp[i].indexOf('x'))));
            else powerCoeffMap.put(0, Integer.parseInt(temp[i]));
        }
        for (Map.Entry<Integer, Integer> i : powerCoeffMap.entrySet()) {
            System.out.println(i.getKey() + " : " + i.getValue());
        }
    }

    public SparsePolynomial(TreeMap<Integer,Integer> tm){
        this.powerCoeffMap = tm;

    }

    @Override
    public int degree() {
        return powerCoeffMap.lastEntry().getKey();
    }

    @Override
    public int getCoefficient(int d) {
        return powerCoeffMap.get(d);
    }

    @Override
    public boolean isZero() {
        for (double b : powerCoeffMap.values()) {
            if (b != 0) return false;
        }
        ;
        return true;
    }

    @Override
    public Polynomial add(Polynomial q) {
        SparsePolynomial p1 = new SparsePolynomial(powerCoeffMap);
        SparsePolynomial p2 = new SparsePolynomial(q.toString());
        return null;
    }

    @Override
    public Polynomial multiply(Polynomial q) {
        return null;
    }

    @Override
    public Polynomial subtract(Polynomial q) {
        return null;
    }

    @Override
    public Polynomial minus() {
        return null;
    }

    @Override
    public boolean wellFormed() {
        return false;
    }

    @Override
    public String toString() {
        String temp2 = "";
        int currentEntryKey = ((TreeMap<Integer, Integer>) powerCoeffMap).lastEntry().getKey();
        int treesetSize = powerCoeffMap.size();
        while(treesetSize>0){
            if(treesetSize== powerCoeffMap.size()){
                switch(currentEntryKey){
                    case 1: temp2+=powerCoeffMap.get(currentEntryKey)+"x";break;
                    case 0:  temp2+=powerCoeffMap.get(currentEntryKey);break;
                    default:temp2+=powerCoeffMap.get(currentEntryKey)+"x^"+ currentEntryKey;break;
                }
            }
            else{
                switch(currentEntryKey){
                    case 1: temp2+=powerCoeffMap.get(currentEntryKey)+"x";break;
                    case 0:  temp2+=powerCoeffMap.get(currentEntryKey);break;
                    default:temp2+=powerCoeffMap.get(currentEntryKey)+"x^"+ currentEntryKey;break;
                }
            }
            if(treesetSize>1)currentEntryKey=((TreeMap<Integer, Integer>) powerCoeffMap).lowerEntry(currentEntryKey).getKey();
            if(treesetSize>1) {
               temp2+="+";
            }
            treesetSize--;
        }
        return temp2;
    }
}
