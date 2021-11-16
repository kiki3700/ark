package interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.util.CybosConnection;

@Component
public class CybosInterceptor implements MethodInterceptor{
	
	@Autowired
	CybosConnection cybosConnection;
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
	
		int connectionStatus =  cybosConnection.connectionCheck();
		if(connectionStatus <= 0) {
			throw new Exception("Cybos is disconnected"); 
		}
		Object object = invocation.proceed();
		
		return object;
	}
	
}
