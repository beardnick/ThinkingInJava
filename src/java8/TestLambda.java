package java8;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

public class TestLambda {

    @FunctionalInterface
    interface  MathOp{
        int operate(int e1, int e2);
    }

    public static void main(String[] args) {
        TreeSet<Tuple> set = new TreeSet<>((e1, e2) -> e1.second - e2.second);
        set.add(new Tuple(1, 3));
        set.add(new Tuple(9, 4));
        set.add(new Tuple(2 , 3));
        set.add(new Tuple(1, -1));

        System.out.println(set);

        List<Integer> list = Arrays.asList(1, 2, 5, 4, 6, 2, 12);
        list.sort(
                (e1, e2) -> e1.compareTo(e2)
        );
        list.forEach(e -> System.out.print(e + " "));
        System.out.println();
        MathOp subtract = (a , b ) -> a - b;
        MathOp add = (a , b ) -> a + b;
        System.out.println(add.operate(10, 20));

        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
//            方法被当成一种接口传入进去
            cars.add(Car.create(Car::new, i));
        }
//            方法被当成一种接口传入进去
        cars.forEach(Car::showCar);
        Optional op = Arrays.asList("1", 2,  3.14 , true).stream().reduce(
                (e1, e2) -> {
                    String r = ((Serializable) e1).toString() + ((Serializable) e2).toString();
                    return r;
                }
        );
        System.out.println(op.get());
        Optional res = Stream.of(1, 3, 4 , 5, 6, 3 , 2).reduce(
                (e1, e2) -> e1 + e2
        );
        System.out.println(res.get());

    }

    interface Factory<T>{
        T generate(int i);
    }

    static class  Car{


        public static Car create(Factory<Car> factory, int i) {
            return factory.generate(i);
        }

        private int id;

        public Car(int i){
           id = i;
        }

        public void showCar(){
            System.out.println("Car number " + id);
        }

    }
}
