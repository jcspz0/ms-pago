package com.diplo.sharedkernel.mediator;

import com.diplo.sharedkernel.mediator.request.IRequest;
import com.diplo.sharedkernel.mediator.request.IRequestHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.springframework.stereotype.Service;

@Service
public class Mediator implements IMediator {

	//Map<IRequest, IRequestHandler> _lista;
	Map<Class<?>, IRequestHandler> _lista;

	public Mediator() {
		super();
		this._lista = new HashMap<>();
	}

	public Mediator(Map<Class<?>, IRequestHandler> _lista) {
		super();
		this._lista = _lista;
	}

	@Override
	public void registrarComando(IRequest request, IRequestHandler handler) {
		this._lista.put(request.getClass(), handler);
	}

	@Override
	public <I, T extends I> I Send(IRequest<I> request) throws Exception {
		try {
			IRequestHandler requestHandler = this._lista.get(request.getClass());
			Future future = requestHandler.Handle(request);
			if (future == null) {
				return null;
			}
			Object result = future.get();
			return (I) result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(
				"Falla al tratar de procesar el request en el mediador"
			);
		}
	}
}
