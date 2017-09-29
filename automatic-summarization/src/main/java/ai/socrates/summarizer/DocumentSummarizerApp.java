package ai.socrates.summarizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class DocumentSummarizerApp extends SpringBootServletInitializer{
	
	public static void main( String[] args )
    {
    	SpringApplication springApplication = new SpringApplication(DocumentSummarizerApp.class);
    	//System.out.println(PropertyHelper.getPidFile());
    	ApplicationPidFileWriter apfd=new ApplicationPidFileWriter("document-summarizer.pid");
    	apfd.setTriggerEventType(ApplicationReadyEvent.class);
    	springApplication.addListeners(apfd);
    	springApplication.run(args);
    	//IntentDirectoryController.init();
    }
    
    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<DocumentSummarizerApp> applicationClass = DocumentSummarizerApp.class;

}
