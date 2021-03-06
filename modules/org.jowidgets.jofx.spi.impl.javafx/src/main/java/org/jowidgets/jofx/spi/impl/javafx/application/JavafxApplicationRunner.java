/*
 * Copyright (c) 2010, grossmann
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 * * Neither the name of the jo-widgets.org nor the
 *   names of its contributors may be used to endorse or promote products
 *   derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL jo-widgets.org BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGE.
 */

package org.jowidgets.jofx.spi.impl.javafx.application;

import javafx.application.Application;
import javafx.stage.Stage;

import org.jowidgets.common.application.IApplication;
import org.jowidgets.common.application.IApplicationLifecycle;
import org.jowidgets.common.application.IApplicationRunner;
import org.jowidgets.util.Assert;

public class JavafxApplicationRunner extends Application implements IApplicationRunner {

	private static IApplication application;

	@Override
	public synchronized void run(final IApplication application) {
		Assert.paramNotNull(application, "application");
		if (JavafxApplicationRunner.application != null) {
			throw new IllegalStateException("Only one application can run at the same time");
		}
		JavafxApplicationRunner.application = application;
		launch();
	}

	@Override
	public void start(final Stage stage) throws Exception {

		final IApplicationLifecycle lifecycle = new IApplicationLifecycle() {

			@Override
			public void finish() {
				try {
					stop();
				}
				catch (final Exception e) {
					//TODO handle Exception
					//CHECKSTYLE:OFF
					e.printStackTrace();
					//CHECKSTYLE:ON
				}

			}
		};

		application.start(lifecycle);
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		application = null;
	}

}
