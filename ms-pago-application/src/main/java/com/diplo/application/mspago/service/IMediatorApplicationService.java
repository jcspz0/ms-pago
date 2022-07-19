package com.diplo.application.mspago.service;

import com.diplo.application.mspago.mediator.IMediator;
import com.diplo.sharekernel.core.IApplicationService;

public interface IMediatorApplicationService extends IApplicationService {
	IMediator getMediator();
}
