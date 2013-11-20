package com.github.hvasoares.pageobjects.proxy;

public interface DecoratorObject<T> {
	public void setInner(T value);
	public T getInner();
	public T self();
	public void setOuter(DecoratorObject<T> value);
	public T getOuter();

}
