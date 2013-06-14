package com.github.pageobject.runner;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import com.github.jsteak.JSteakRunnerBuilder;
import com.github.pageobject.DefaultFactory;

public class PageObjectRunner extends Runner {
	private Class<?> clazz;
	private JSteakRunnerBuilder steakRunner;
	private PageObjectDescription descriptionGetter;
	private DefaultFactory factory;

	public PageObjectRunner(Class<?> clazz){
		ObjectConstructor objConst = new ObjectConstructor();
		this.clazz = clazz;
		this.steakRunner = new JSteakRunnerBuilder(clazz);
		this.descriptionGetter = new PageObjectDescription(
				this.steakRunner.getDescriptionGetter(),
				objConst, 
				clazz
		);
		factory = new DefaultFactory(descriptionGetter.getRepository());
		steakRunner.setDefaultClassUtil(new ClassReflectionUtilsImpl(
				objConst,
				factory
		));		
	}
	
	@Override
	public Description getDescription() {
		return this.descriptionGetter.getDescription();
	}

	@Override
	public void run(RunNotifier notifier) {
		try{
			steakRunner.run(notifier);
		}catch(RuntimeException ex){
			factory.getBrowser().close();
		}
	}

}
