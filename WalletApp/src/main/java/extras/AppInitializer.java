package extras;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {
	
	private AnnotationConfigWebApplicationContext getContext()
	{
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("mvc,beans");
		return context;
		
	}

	@Override
	public void onStartup(ServletContext ctx) throws ServletException {
		
		WebApplicationContext context = getContext(); // configures a listener for web application
		ctx.addListener(new ContextLoaderListener(context));  //configures a DispatcherServlet
		ServletRegistration.Dynamic dispatcher = ctx.addServlet("DispatcherServlet", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1); //asks spring to load  this servlet with 1st priority
		dispatcher.addMapping("/");   //Configures servlet to accept all incoming request
		
	}	
}
