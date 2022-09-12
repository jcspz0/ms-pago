package com.diplo.application.mspago.service;

import com.diplo.sharedkernel.core.IApplicationService;
import com.diplo.sharedkernel.mediator.IMediator;

public interface IMediatorApplicationService extends IApplicationService {
	IMediator getMediator();
}
