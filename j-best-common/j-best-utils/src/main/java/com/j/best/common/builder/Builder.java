package com.j.best.common.builder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Builder<T> {


    private Supplier<T> constructor;

    private List<Consumer<T>> dInjects = new ArrayList<Consumer<T>>();

    Builder(Supplier<T> c){
        constructor = c;
    }

    public static <T> Builder<T> builder(Supplier<T> constructor) {
        return new Builder<>(constructor);
    }

    <P> Builder<T> with(DInjectConsumer<T,P> consumer, P p){
       Consumer<T> c = instance -> consumer.accept(instance,p);
       dInjects.add(c);
       return this;
    }

    private  T build(){
        T instance = constructor.get();
        dInjects.forEach(dInject->dInject.accept(instance));
        return instance;
    }

    public static void main(String[] args) {
        People huangchunwu = Builder.builder(People::new).with(People::setAge, "huangchunwu").with(People::setAge, 31).build();
    }

    @FunctionalInterface
    interface DInjectConsumer<T,P>{
        void accept(T t,P p);
    }



    static class People{

        private int age;
        private String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
