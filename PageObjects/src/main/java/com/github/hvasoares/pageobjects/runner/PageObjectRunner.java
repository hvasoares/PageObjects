package com.github.hvasoares.pageobjects.runner;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import com.github.hvasoares.jsteak.JSteakRunnerBuilder;
import com.github.hvasoares.pageobjects.DefaultFactory;
import com.github.hvasoares.pageobjects.RepositoryAwareFactory;
import com.github.hvasoares.pageobjects.impl.browser.BrowserLocker;
import com.github.hvasoares.pageobjects.impl.browser.TestSuiteAwareBrowser;
import com.github.hvasoares.pageobjects.report.ReportListenerFactory;

public class PageObjectRunner extends Runner implements BrowserLocker{
	private JSteakRunnerBuilder steakRunner;
	private PageObjectDescription descriptionGetter;
	private RepositoryAwareFactory factory;
	private boolean repositorySetted;

	public PageObjectRunner(Class<?> clazz){
		this(clazz,null);
	}
	
	public PageObjectRunner(Class<?> clazz, RepositoryAwareFactory factory){
		repositorySetted = false;
		ObjectConstructor objConst = new ObjectConstructor();
		this.steakRunner = new JSteakRunnerBuilder(clazz);
		this.descriptionGetter = new PageObjectDescription(
			this.steakRunner.getDescriptionGetter(),
			objConst, 
			clazz
		);
		
		if(factory == null){
			this.factory = new DefaultFactory(descriptionGetter.getRepository());
			repositorySetted=true;
		}else{
			this.factory=factory;
		};
		
		steakRunner.setDefaultClassUtil(new ClassReflectionUtilsImpl(
				objConst,
				this.factory
		));
	} 
	
	@Override
	public Description getDescription() {
		return this.descriptionGetter.getDescription();
	}

	@Override
	public void run(RunNotifier notifier) {
		TestSuiteAwareBrowser instance = TestSuiteAwareBrowser.getInstance();
		instance.setBrowserLocker(this);
		notifier.addListener( ReportListenerFactory.createListener() );
		try{
			if(!repositorySetted)
				factory.setRepository(descriptionGetter.getRepository());
			steakRunner.run(notifier);
			instance.close(this);;
		}catch(RuntimeException ex){
			instance.close(this);
			throw ex;
		}
	}

}
