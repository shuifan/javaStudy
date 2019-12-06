package other;

import java.util.ArrayList;
import java.util.List;

public class A implements Addable {
    @Override
    public void print() {
        System.out.println(1);
    }

    public static void aa(Addable addable){
        addable.print();
    }

    public static void main(String[] a){
        List<A> aList = new ArrayList<>();
////        aList.add(new A());
////        aList.add(new A());
////        test(aList);
    }

    private static void test(List<? extends Addable> list){
        for (Addable addable : list){
            addable.print();
        }
    }
}
