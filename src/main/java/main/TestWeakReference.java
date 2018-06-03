package main;

import java.lang.ref.WeakReference;

/**
 * Created by Cheungqzy on 2018/5/28.
 */
public class TestWeakReference {


    public static void main(String[] args) {

        String car = new String("silver");
        WeakReference<String> weakCar = new WeakReference<String>(car);
        int i=0;
        while(true){
            if(weakCar.get()!=null){
                i++;
                System.out.println("Object is alive for "+i+" loops - "+weakCar);
            }else{
                System.out.println("Object has been collected.");
                break;
            }
        }
    }

}