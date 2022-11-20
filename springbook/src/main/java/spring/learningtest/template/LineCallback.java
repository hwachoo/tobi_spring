package spring.learningtest.template;

public interface LineCallback<T> {
	//제네릭 타입의 T
	//클래스 내부에서 타입을 지정하는 것이 아니라 외부에서 사용자에 의해 지정됨
	T doSomethingWithLine(String line, T value);
}
