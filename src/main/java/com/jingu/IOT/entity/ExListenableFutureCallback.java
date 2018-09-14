package com.jingu.IOT.entity;

import org.springframework.util.concurrent.ListenableFutureCallback;

public abstract class ExListenableFutureCallback<T> implements ListenableFutureCallback<T> {

	private RuleEntity param;
	
	public ExListenableFutureCallback(RuleEntity ruleEntity){
		this.param = ruleEntity;
	}
	
	public RuleEntity getParam(){
		return this.param;
	}
	
	@Override
	public abstract void onSuccess(T result);

	@Override
	public abstract void onFailure(Throwable ex);

}
