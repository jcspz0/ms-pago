package com.diplo.application.mspago.mediator;

import com.diplo.application.mspago.mediator.request.IRequest;
import com.diplo.application.mspago.mediator.request.IRequestHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.logging.log4j.CloseableThreadContext.Instance;

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
			IRequestHandler requestHandler =
				this._lista.get(request.getClass());
			Future future = requestHandler.Handle(request);
			if (future == null) {
				return null;
			}
			Object result = future.get();
			return (I) result;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(
				"Falló al tratar de procesar el request en el mediador"
			);
		}
		return null;
	}
}
