package com.github.pageobject.proxy;

public interface DecoratorObject<T> {
	public void setInner(T value);
	public T getInner();
	public T self();

}
