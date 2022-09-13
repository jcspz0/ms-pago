package com.diplo.sharedkernel.mediator;

import com.diplo.sharedkernel.mediator.request.IRequest;
import com.diplo.sharedkernel.mediator.request.IRequestHandler;
import java.util.UUID;

public interface IMediator {
	void registrarComando(IRequest request, IRequestHandler handler);

	public <I, T extends I> I Send(IRequest<I> request) throws Exception;
}
