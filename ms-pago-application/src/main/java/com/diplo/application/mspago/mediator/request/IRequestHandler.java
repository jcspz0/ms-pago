package com.diplo.application.mspago.mediator.request;

import java.util.concurrent.Future;

public interface IRequestHandler<T extends IRequest<?>, X> {
	Future<X> Handle(T request) throws Exception;
}
